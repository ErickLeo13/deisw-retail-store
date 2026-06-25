package pe.edu.upc.retailstore.sales.domain.services;

import java.util.List;
import java.util.Optional;
import pe.edu.upc.retailstore.sales.domain.model.aggregates.SalesOrder;
import pe.edu.upc.retailstore.sales.domain.model.queries.GetAllSalesOrdersQuery;
import pe.edu.upc.retailstore.sales.domain.model.queries.GetSalesOrderByIdQuery;

/**
 * Sales Order Query Service Interface.
 */
public interface SalesOrderQueryService {

  /**
   * Get a sales order by id.
   *
   * @param query Sales Order Query
   * @return Sales Order
   */
  Optional<SalesOrder> handle(GetSalesOrderByIdQuery query);

  /**
   * Get all sales orders.
   *
   * @param query Sales Order Query
   * @return List of Sales Orders
   */
  List<SalesOrder> handle(GetAllSalesOrdersQuery query);
}
