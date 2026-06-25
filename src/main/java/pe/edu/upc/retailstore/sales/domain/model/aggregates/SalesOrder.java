package pe.edu.upc.retailstore.sales.domain.model.aggregates;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NonNull;
import pe.edu.upc.retailstore.sales.domain.model.valueobjects.ProductId;
import pe.edu.upc.retailstore.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import pe.edu.upc.retailstore.shared.domain.model.valueobjects.CustomerId;
import pe.edu.upc.retailstore.shared.domain.model.valueobjects.Money;



/**
 * Represents a Sales Order aggregate in the Sales context.
 * This aggregate manages the lifecycle of a sales order and its items.
 *
 * @author Open Source Application Development Team
 */
@Entity
@Table(name = "sales_orders")
public class SalesOrder extends AuditableAbstractAggregateRoot<SalesOrder> {
  @Getter
  @Column(name = "sales_order_id", nullable = false, unique = true, updatable = false)
  private final UUID salesOrderId;

  @Getter
  @AttributeOverrides({
      @AttributeOverride(name = "value",
          column = @Column(name = "customer_id", nullable = false, updatable = false))
  })
  private final CustomerId customerId;

  @Getter
  @Column(name = "order_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private LocalDateTime orderDate;

  @Getter
  @OneToMany(mappedBy = "salesOrder", cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<SalesOrderItem> items;

  @Getter
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "currency", column = @Column(name = "currency", nullable = false)),
      @AttributeOverride(name = "amount", column = @Column(name = "amount", nullable = false))
  })
  private Money totalAmount;

  /**
   * Default constructor for JPA.
   */
  public SalesOrder() {
    this.salesOrderId = UUID.randomUUID();
    this.customerId = new CustomerId(UUID.randomUUID());
    this.orderDate = LocalDateTime.now();
    this.items = new ArrayList<>();
    this.totalAmount = Money.zero();
  }

  /**
   * Constructs a SalesOrder instance.
   *
   * @param customerId the customer identifier, it must not be null
   */
  public SalesOrder(@NonNull CustomerId customerId) {
    this.salesOrderId = UUID.randomUUID();
    this.customerId = customerId;
    this.orderDate = LocalDateTime.now();
    this.items = new ArrayList<>();
    this.totalAmount = Money.zero();
  }

  /**
   * Adds a new item to the order.
   *
   * @param productId the product identifier, it must not be null
   * @param quantity  the quantity of the product, it must be greater than zero
   * @param unitPrice the unit price of the product, it must not be null
   *                  and must be greater than zero
   */
  public void addItem(@NonNull ProductId productId, int quantity, @NonNull Money unitPrice) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be greater than zero");
    }
    if (unitPrice.amount().compareTo(Money.zero().amount()) <= 0) {
      throw new IllegalArgumentException("Unit price must be greater than zero");
    }
    SalesOrderItem newItem = new SalesOrderItem(productId, quantity, unitPrice, this);
    this.items.add(newItem);
    this.totalAmount = calculateTotalAmount();
  }

  /**
   * Adds a list of items to the order.
   *
   * @param items the list of items to add, it must not be empty
   */
  public void addItems(@NonNull List<SalesOrderItem> items) {
    if (items.isEmpty()) {
      throw new IllegalArgumentException("Items list cannot be empty");
    }
    this.items.addAll(items);
    this.totalAmount = calculateTotalAmount();
  }

  /**
   * Calculates the total amount of the order.
   *
   * @return the total amount for the order
   */
  public Money calculateTotalAmount() {
    return this.items.stream()
        .map(SalesOrderItem::calculateItemAmount)
        .reduce(Money.zero(), Money::add);
  }

  /**
   * Sets the order date.
   * This method is used when the system is registering an offline order with a specific date.
   *
   * @param orderDate the order date, it must not be null
   * @return this Sales Order instance
   */
  public SalesOrder withOrderDate(@NonNull LocalDateTime orderDate) {
    this.orderDate = orderDate;
    return this;
  }

  /**
   * Returns the total amount of the order as a string.
   *
   * @return the total amount as a string, formatted as "amount currency"
   */
  public String getTotalAmountAsString() {
    return this.totalAmount.amount() + " " + this.totalAmount.currency().getCurrencyCode();
  }

}
