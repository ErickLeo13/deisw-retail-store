package pe.edu.upc.retailstore.sales.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.retailstore.sales.domain.model.aggregates.SalesOrder;
import pe.edu.upc.retailstore.sales.domain.model.aggregates.SalesOrderItem;
import pe.edu.upc.retailstore.sales.domain.model.commands.CreateSalesOrderCommand;
import pe.edu.upc.retailstore.sales.domain.model.commands.CreateSalesOrderItemCommand;
import pe.edu.upc.retailstore.sales.domain.services.SalesOrderCommandService;
import pe.edu.upc.retailstore.sales.infrastructure.persistence.jpa.repositories.SalesOrderRepository;

/**
 * Sales Order Command Service Implementation.
 */
@Service
public class SalesOrderCommandServiceImpl implements SalesOrderCommandService {

  private final SalesOrderRepository salesOrderRepository;

  /**
   * Constructor.
   *
   * @param salesOrderRepository SalesOrderRepository
   */
  public SalesOrderCommandServiceImpl(SalesOrderRepository salesOrderRepository) {
    this.salesOrderRepository = salesOrderRepository;
  }

  @Override
  public Long handle(CreateSalesOrderCommand command) {
    // Validate if customer already has a sales order
    if (this.salesOrderRepository.existsByCustomerId(command.customerId())) {
      throw new IllegalArgumentException("Sales order with customer id "
          + command.customerId() + " already exists");
    }

    // Create sales order
    var salesOrder = new SalesOrder(command.customerId());

    var salesOrderItems = command.items().stream()
        .map(item -> {
          if (item.quantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
          }
          return new SalesOrderItem(item.productId(), item.quantity(),
              item.unitPrice(), salesOrder);
        })
        .toList();
    salesOrder.addItems(salesOrderItems);

    try {
      this.salesOrderRepository.save(salesOrder);
    } catch (Exception e) {
      throw new IllegalArgumentException("Error while saving sales order: " + e.getMessage());
    }

    return salesOrder.getId();
  }

  @Override
  public Long handle(CreateSalesOrderItemCommand command) {
    return 0L;
  }


}
