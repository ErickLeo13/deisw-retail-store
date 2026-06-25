package pe.edu.upc.retailstore.sales.interfaces.rest.transform;

import pe.edu.upc.retailstore.sales.domain.model.aggregates.SalesOrder;
import pe.edu.upc.retailstore.sales.interfaces.rest.resources.SalesOrderResponse;

/**
 * Assembler to convert a SalesOrder entity to a SalesOrderResponse.
 */
public class SalesOrderResponseFromEntityAssembler {

  /**
   * SalesOrderResponseFromEntityAssembler constructor.
   *
   * @param salesOrder SalesOrder
   * @return SalesOrderResponse
   */
  public static SalesOrderResponse toResponse(SalesOrder salesOrder) {
    var salesOrderItemResponses = salesOrder.getItems().stream()
        .map(SalesOrderItemResponseFromEntityAssembler::toResponse)
        .toList();
    return new SalesOrderResponse(salesOrder.getSalesOrderId().toString(),
        salesOrder.getCustomerId().value(), salesOrder.getTotalAmount().amount(),
        salesOrder.getTotalAmount().currency().getCurrencyCode(), salesOrderItemResponses);
  }
}
