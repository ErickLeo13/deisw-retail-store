package pe.edu.upc.retailstore.sales.domain.model.commands;

import java.util.List;
import pe.edu.upc.retailstore.shared.domain.model.valueobjects.CustomerId;

/**
 * Represents a command to create a Sales Order.
 *
 * @param customerId Customer identifier
 * @param items List of items to be added to the Sales Order
 */
public record CreateSalesOrderCommand(CustomerId customerId,
                                      List<CreateItemSalesOrderCommand> items) {
}
