package pe.edu.upc.retailstore.sales.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.edu.upc.retailstore.sales.domain.model.queries.GetAllSalesOrdersQuery;
import pe.edu.upc.retailstore.sales.domain.model.queries.GetSalesOrderByIdQuery;
import pe.edu.upc.retailstore.sales.domain.services.SalesOrderCommandService;
import pe.edu.upc.retailstore.sales.domain.services.SalesOrderQueryService;
import pe.edu.upc.retailstore.sales.interfaces.rest.resources.CreateSalesOrderRequest;
import pe.edu.upc.retailstore.sales.interfaces.rest.resources.SalesOrderResponse;
import pe.edu.upc.retailstore.sales.interfaces.rest.transform.CreateSalesOrderCommandFromRequestAssembler;
import pe.edu.upc.retailstore.sales.interfaces.rest.transform.SalesOrderResponseFromEntityAssembler;

/**
 * Sales Order Controller.
 */
@RestController
@RequestMapping(value = "/api/v1/salesorders", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Sales Orders", description = "Sales Order Management Endpoints")
public class SalesOrderController {

  private final SalesOrderQueryService salesOrderQueryService;
  private final SalesOrderCommandService salesOrderCommandService;

  /**
   * Constructor.
   *
   * @param salesOrderQueryService salesOrderQueryService
   * @param salesOrderCommandService salesOrderCommandService
   */
  public SalesOrderController(SalesOrderQueryService salesOrderQueryService,
                              SalesOrderCommandService salesOrderCommandService) {
    this.salesOrderQueryService = salesOrderQueryService;
    this.salesOrderCommandService = salesOrderCommandService;
  }

  /**
   * Create a new sales order.
   *
   * @param request Sales Order Request
   * @return Sales Order Response
   */
  @PostMapping
  public ResponseEntity<SalesOrderResponse> createSalesOrder(
      @RequestBody CreateSalesOrderRequest request) {
    var createSalesOrderCommand = CreateSalesOrderCommandFromRequestAssembler.toCommand(request);
    var salesOrderId = this.salesOrderCommandService.handle(createSalesOrderCommand);

    if (salesOrderId.equals(0L)) {
      return ResponseEntity.status(400).build();
    }

    var getSalesOrderByIdQuery = new GetSalesOrderByIdQuery(salesOrderId);
    var optionalSalesOrder = salesOrderQueryService.handle(getSalesOrderByIdQuery);
    if (optionalSalesOrder.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    var salesOrderResponse = SalesOrderResponseFromEntityAssembler
        .toResponse(optionalSalesOrder.get());
    return ResponseEntity.status(HttpStatus.CREATED).body(salesOrderResponse);
  }

  /**
   * Get all sales orders.
   *
   * @return List of Sales Orders
   */
  @GetMapping
  public ResponseEntity<List<SalesOrderResponse>> getSalesOrders() {
    var getAllSalesOrdersQuery = new GetAllSalesOrdersQuery();
    var salesOrders = salesOrderQueryService.handle(getAllSalesOrdersQuery);
    var salesOrderResponses = salesOrders.stream()
        .map(SalesOrderResponseFromEntityAssembler::toResponse)
        .toList();
    return ResponseEntity.ok(salesOrderResponses);
  }
}
