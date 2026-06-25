package pe.edu.upc.retailstore.sales.domain.model.commands;

import pe.edu.upc.retailstore.sales.domain.model.valueobjects.ProductId;
import pe.edu.upc.retailstore.shared.domain.model.valueobjects.Money;

/**
 * Represents a command to create a Sales Order Item.
 *
 * @param productId Product identifier for the item being added to the sales order
 * @param quantity Quantity of the item being added
 * @param unitPrice Unit price of the item being added
 * @param salesOrderId Sales Order identifier to which the item is being added
 */
public record CreateSalesOrderItemCommand(ProductId productId,
                                          int quantity, Money unitPrice, Long salesOrderId) {
}
