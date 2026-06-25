package pe.edu.upc.retailstore.sales.application.internal.queryservices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upc.retailstore.sales.domain.model.aggregates.SalesOrder;
import pe.edu.upc.retailstore.sales.domain.model.queries.GetAllSalesOrdersQuery;
import pe.edu.upc.retailstore.sales.infrastructure.persistence.jpa.repositories.SalesOrderRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SalesOrderQueryServiceImplTest {

    @Mock
    private SalesOrderRepository salesOrderRepository;

    @InjectMocks
    private SalesOrderQueryServiceImpl salesOrderQueryService;

    @Test
    @DisplayName("handle(GetAllSalesOrdersQuery) debe retornar todas las órdenes cuando el repositorio no está vacío (AAA)")
    void handleGetAllSalesOrders_whenRepositoryHasData_returnsList() {
        // Arrange
        List<SalesOrder> expected = new ArrayList<>();
        expected.add(new SalesOrder());
        expected.add(new SalesOrder());
        when(salesOrderRepository.findAll()).thenReturn(expected);

        // Act
        List<SalesOrder> result = salesOrderQueryService.handle(new GetAllSalesOrdersQuery());

        // Assert
        assertNotNull(result, "El resultado no debe ser nulo");
        assertEquals(2, result.size(), "Debe devolver dos órdenes de venta");
        assertSame(expected, result, "La lista devuelta debe ser la misma instancia proporcionada por el repositorio");
        verify(salesOrderRepository, times(1)).findAll();
        verifyNoMoreInteractions(salesOrderRepository);
    }

    @Test
    @DisplayName("handle(GetAllSalesOrdersQuery) debe retornar lista vacía cuando no existen órdenes (AAA)")
    void handleGetAllSalesOrders_whenRepositoryIsEmpty_returnsEmptyList() {
        // Arrange
        List<SalesOrder> expected = List.of();
        when(salesOrderRepository.findAll()).thenReturn(expected);

        // Act
        List<SalesOrder> result = salesOrderQueryService.handle(new GetAllSalesOrdersQuery());

        // Assert
        assertNotNull(result, "El resultado no debe ser nulo aunque esté vacío");
        assertTrue(result.isEmpty(), "La lista debe estar vacía");
        assertSame(expected, result, "Debe ser exactamente la lista vacía devuelta por el repositorio");
        verify(salesOrderRepository, times(1)).findAll();
        verifyNoMoreInteractions(salesOrderRepository);
    }
}
