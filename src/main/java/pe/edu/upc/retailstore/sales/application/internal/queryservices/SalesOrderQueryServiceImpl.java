package pe.edu.upc.retailstore.sales.application.internal.queryservices;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pe.edu.upc.retailstore.sales.domain.model.aggregates.SalesOrder;
import pe.edu.upc.retailstore.sales.domain.model.queries.GetAllSalesOrdersQuery;
import pe.edu.upc.retailstore.sales.domain.model.queries.GetSalesOrderByIdQuery;
import pe.edu.upc.retailstore.sales.domain.services.SalesOrderQueryService;
import pe.edu.upc.retailstore.sales.infrastructure.persistence.jpa.repositories.SalesOrderRepository;



/**
 * Sales Order Query Service Implementation.
 */
@Service
public class SalesOrderQueryServiceImpl implements SalesOrderQueryService {

  private final SalesOrderRepository salesOrderRepository;

  /**
   * Constructor.
   *
   * @param salesOrderRepository SalesOrderRepository
   */
  public SalesOrderQueryServiceImpl(SalesOrderRepository salesOrderRepository) {
    this.salesOrderRepository = salesOrderRepository;
  }

  @Override
  public Optional<SalesOrder> handle(GetSalesOrderByIdQuery query) {
    return this.salesOrderRepository.findById(query.salesOrderId());
  }

  @Override
  public List<SalesOrder> handle(GetAllSalesOrdersQuery query) {
    return this.salesOrderRepository.findAll();
  }
}
