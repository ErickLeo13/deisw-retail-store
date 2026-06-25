package pe.edu.upc.retailstore.sales.interfaces.rest.transform;

import java.util.Currency;
import pe.edu.upc.retailstore.sales.domain.model.commands.CreateItemSalesOrderCommand;
import pe.edu.upc.retailstore.sales.domain.model.commands.CreateSalesOrderCommand;
import pe.edu.upc.retailstore.sales.domain.model.valueobjects.ProductId;
import pe.edu.upc.retailstore.sales.interfaces.rest.resources.CreateSalesOrderRequest;
import pe.edu.upc.retailstore.shared.domain.model.valueobjects.CustomerId;
import pe.edu.upc.retailstore.shared.domain.model.valueobjects.Money;

/**
 * Assembler to convert a CreateSalesOrderRequest to a CreateSalesOrderCommand.
 */
public class CreateSalesOrderCommandFromRequestAssembler {

  /**
   * Transform a CreateSalesOrderRequest into a CreateSalesOrderCommand.
   *
   * @param request CreateSalesOrderRequest informacion de la orden de compra
   * @return CreateSalesOrderCommand
   */
  public static CreateSalesOrderCommand toCommand(CreateSalesOrderRequest request) {

    return new CreateSalesOrderCommand(
        new CustomerId(request.customerId()),
        request.items().stream()
            .map(item ->
                new CreateItemSalesOrderCommand(new ProductId(item.productId()), item.quantity(),
                    new Money(item.unitAmount(), Currency.getInstance(item.currency())))
            )
            .toList()
    );
  }
}
