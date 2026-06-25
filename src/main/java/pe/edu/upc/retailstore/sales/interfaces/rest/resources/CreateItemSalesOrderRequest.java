package pe.edu.upc.retailstore.sales.interfaces.rest.resources;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Represents a request to create an item in a sales order.
 *
 * @param productId Product identifier
 * @param quantity Quantity of the item
 * @param unitAmount Unit amount of the item
 * @param currency Currency of the item
 */
public record CreateItemSalesOrderRequest(UUID productId, Integer quantity, BigDecimal unitAmount,
                                          String currency) {
}
