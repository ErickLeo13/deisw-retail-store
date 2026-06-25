package pe.edu.upc.retailstore.sales.domain.model.aggregates;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pe.edu.upc.retailstore.sales.domain.model.valueobjects.ProductId;
import pe.edu.upc.retailstore.shared.domain.model.entities.AuditableModel;
import pe.edu.upc.retailstore.shared.domain.model.valueobjects.Money;



/**
 * Represents an item within a Sales Order.
 * This entity is managed by the Sales Order aggregate.
 *
 * @author Open Source Application Development Team
 */
@Getter
@Entity
@Table(name = "sales_order_items")
public class SalesOrderItem extends AuditableModel {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Embedded
  @AttributeOverrides(
      @AttributeOverride(name = "value",
          column = @Column(name = "product_id", nullable = false, updatable = false))
  )
  private final ProductId productId;

  @Setter
  @Positive
  private int quantity;

  @Setter
  @Embedded
  @AttributeOverrides({
      @AttributeOverride(name = "currency", column = @Column(name = "currency", nullable = false)),
      @AttributeOverride(name = "amount", column = @Column(name = "amount", nullable = false))
  })
  private Money unitPrice;

  @NotNull
  @ManyToOne
  @JoinColumn(name = "sales_order_id", nullable = false)
  private SalesOrder salesOrder;

  /**
   * Default constructor for JPA.
   */
  public SalesOrderItem() {
    this.productId = new ProductId();
    // this.quantity = 1;
    // this.unitPrice = new Money();
  }

  /**
   * Constructs a SalesOrderItem instance.
   *
   * @param productId the product identifier, it must not be null
   * @param quantity  the quantity of the product, it must be greater than zero
   * @param unitPrice the unit price of the product, it must not be null and must
   *                  be greater than zero
   */
  public SalesOrderItem(@NonNull ProductId productId, int quantity, @NonNull Money unitPrice,
                        @NonNull SalesOrder salesOrder) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("Quantity must be greater than zero");
    }
    if (unitPrice.amount().compareTo(BigDecimal.ZERO) <= 0) {
      throw new IllegalArgumentException("Unit price must be greater than zero");
    }
    if (Objects.isNull(unitPrice.currency())
        || Objects.isNull(unitPrice.currency().getCurrencyCode())
        || unitPrice.currency().getCurrencyCode().isBlank()) {
      throw new IllegalArgumentException("Unit price must have a currency");
    }
    if (Objects.isNull(salesOrder)) {
      throw new IllegalArgumentException("Sales order must not be null");
    }

    this.productId = productId;
    this.quantity = quantity;
    this.unitPrice = unitPrice;
    this.salesOrder = salesOrder;
  }

  /**
   * Calculates the total amount of the item.
   *
   * @return the total amount for the item
   */
  public Money calculateItemAmount() {
    return unitPrice.multiply(quantity);
  }
}
