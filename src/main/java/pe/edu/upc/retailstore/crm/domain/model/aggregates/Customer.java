package pe.edu.upc.retailstore.crm.domain.model.aggregates;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import java.util.Objects;
import java.util.UUID;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pe.edu.upc.retailstore.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.retailstore.shared.domain.model.valueobjects.Address;
import pe.edu.upc.retailstore.shared.domain.model.valueobjects.CustomerId;

/**
 * Represents a Customer aggregate in the CRM bounded context.
 *
 * @author Open Source Application Development Team
 */
@Getter
@Entity
@Table(name = "customers")
public class Customer extends AuditableAbstractAggregateRoot<Customer> {

  @Embedded
  @AttributeOverrides(
      @AttributeOverride(name = "value",
          column = @Column(name = "customer_id", nullable = false, unique = true))
  )
  private final CustomerId customerId;

  @Setter @NonNull
  @Column(name = "name", length = 40, nullable = false)
  private String name;

  @Setter @NonNull
  @Email
  @Column(name = "email", length = 100, nullable = false, unique = true)
  private String email;

  @Setter @NonNull
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "street",
          column = @Column(name = "street", length = 80, nullable = false)),
      @AttributeOverride(name = "city",
          column = @Column(name = "city", length = 50, nullable = false)),
      @AttributeOverride(name = "postalCode",
          column = @Column(name = "postal_code", length = 20, nullable = false)),
      @AttributeOverride(name = "country",
          column = @Column(name = "country", length = 50, nullable = false))
  })
  private Address address;

  /**
   * Default constructor for JPA.
   */
  public Customer() {
    this.customerId = new CustomerId(UUID.randomUUID());
    // this.name = "";
    // this.email = "";
    // this.address = new Address();
  }

  /**
   * Creates a Customer aggregate with the given information.
   *
   * @param name      the customer name, it must not be null or blank
   * @param email     the customer email, it must not be null or blank
   * @param address   the customer address, it must not be null
   *
   * @throws IllegalArgumentException if any of the parameters is null or blank
   */
  public Customer(String name, String email, Address address) {
    if (Objects.isNull(name) || name.isBlank()) {
      throw new IllegalArgumentException("Customer name cannot be null or blank");
    }
    if (Objects.isNull(email) || email.isBlank()) {
      throw new IllegalArgumentException("Customer email cannot be null or blank");
    }
    if (Objects.isNull(address)) {
      throw new IllegalArgumentException("Customer address cannot be null");
    }

    this.customerId = new CustomerId();
    this.name = name;
    this.email = email;
    this.address = address;
  }

  /**
   * Updates the contact information of the customer.
   *
   * @param email     the customer email, it must not be null or blank
   * @param address   the customer address, it must not be null
   */
  public void updateContactInfo(@NonNull String email, @NonNull Address address) {
    if (email.isBlank()) {
      throw new IllegalArgumentException("Customer email cannot be null or blank");
    }
    this.email = email;
    this.address = address;
  }

  public String getContactInfo() {
    return String.format("%s <%s>, %s", name, email, address);
  }
}
