package pe.edu.upc.retailstore.sales.domain.model.commands;

import pe.edu.upc.retailstore.sales.domain.model.valueobjects.ProductId;
import pe.edu.upc.retailstore.shared.domain.model.valueobjects.Money;

/**
 * Represents a command to create an item in a Sales Order.
 *
 * @param productId Product identifier
 * @param quantity Quantity of the item
 * @param unitPrice Unit price of the item
 */
public record CreateItemSalesOrderCommand(ProductId productId, int quantity, Money unitPrice) {
}
