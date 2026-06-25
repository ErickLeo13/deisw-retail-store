package pe.edu.upc.retailstore.sales.interfaces.rest.resources;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

/**
 * Represents a request to create a Sales Order.
 *
 * @param customerId Customer identifier
 * @param items List of items to be added to the Sales Order
 */
public record CreateSalesOrderRequest(UUID customerId, List<CreateItemSalesOrderRequest> items) {
}
