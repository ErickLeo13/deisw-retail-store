package pe.edu.upc.retailstore.sales.interfaces.rest.transform;

import pe.edu.upc.retailstore.sales.domain.model.aggregates.SalesOrderItem;
import pe.edu.upc.retailstore.sales.interfaces.rest.resources.SalesOrderItemResponse;

/**
 * Assembler to convert a SalesOrderItem entity to a SalesOrderItemResponse.
 */
public class SalesOrderItemResponseFromEntityAssembler {

  /**
   * Transform SalesOrderItem entity to SalesOrderItemResponse.
   *
   * @param entity SalesOrderItem
   * @return SalesOrderItemResponse
   */
  public static SalesOrderItemResponse toResponse(SalesOrderItem entity) {
    return new SalesOrderItemResponse(
        entity.getProductId().value(),
        entity.getQuantity(),
        entity.getUnitPrice().amount(),
        entity.calculateItemAmount().amount(),
        entity.getUnitPrice().currency().getCurrencyCode()
    );
  }
}
