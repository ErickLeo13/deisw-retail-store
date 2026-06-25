package pe.edu.upc.retailstore.sales.domain.services;

import pe.edu.upc.retailstore.sales.domain.model.commands.CreateSalesOrderCommand;
import pe.edu.upc.retailstore.sales.domain.model.commands.CreateSalesOrderItemCommand;

/**
 * Sales Order Command Service Interface.
 */
public interface SalesOrderCommandService {

  /**
   * Create a Sales Order Item.
   *
   * @param command Sales Order Item Command
   * @return Sales Order Item Id
   */
  Long handle(CreateSalesOrderItemCommand command);

  /**
   * Create a Sales Order.
   *
   * @param command Sales Order Command
   * @return Sales Order Id
   */
  Long handle(CreateSalesOrderCommand command);
}
