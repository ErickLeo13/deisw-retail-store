package pe.edu.upc.retailstore.sales.interfaces.rest.resources;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Represents a response for a Sales Order Item.
 *
 * @param productId 128-bit UUID
 * @param quantity 16-bit integer
 * @param unitAmount 128-bit decimal
 * @param totalAmount 128-bit decimal
 * @param currency 32-bit character
 */
public record SalesOrderItemResponse(UUID productId, Integer quantity, BigDecimal unitAmount,
                                     BigDecimal totalAmount, String currency) {
}
