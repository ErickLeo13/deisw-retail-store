package pe.edu.upc.retailstore.sales.interfaces.rest.resources;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Represents a response for a Sales Order.
 *
 * @param salesOrderId Sales Order identifier
 * @param customerId Customer identifier
 * @param totalAmount Total amount of the Sales Order
 * @param currency Currency of the Sales Order
 * @param items List of items in the Sales Order
 */
public record SalesOrderResponse(String salesOrderId, UUID customerId, BigDecimal totalAmount,
                                 String currency, List<SalesOrderItemResponse> items) {
}
