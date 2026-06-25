package pe.edu.upc.retailstore.sales.domain.model.queries;

/**
 * Represents a query to get a Sales Order by its identifier.
 *
 * @param salesOrderId Sales Order identifier
 */
public record GetSalesOrderByIdQuery(Long salesOrderId) {
}
