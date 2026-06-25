package pe.edu.upc.retailstore.sales.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.retailstore.sales.domain.model.aggregates.SalesOrder;
import pe.edu.upc.retailstore.shared.domain.model.valueobjects.CustomerId;

/**
 * Repository interface for SalesOrder entities.
 */
@Repository
public interface SalesOrderRepository extends JpaRepository<SalesOrder, Long> {

  /**
   * Check if a sales order exists for a given customer.
   *
   * @param customerId Customer identifier
   * @return true if a sales order exists, false otherwise
   */
  boolean existsByCustomerId(CustomerId customerId);
}
