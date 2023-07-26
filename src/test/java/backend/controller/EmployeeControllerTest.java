package backend.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import backend.model.Employee;
import backend.service.EmployeeService;

public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllEmployees() {
        // Datos simulados de respuesta del servicio
        List<Employee> employees = Arrays.asList(
            new Employee(1, "John Doe", new BigDecimal("50000")),
            new Employee(2, "Jane Smith", new BigDecimal("60000"))
        );

        // Simulamos el comportamiento del servicio
        when(employeeService.getAllEmployees()).thenReturn(employees);

        ResponseEntity<List<Employee>> responseEntity = employeeController.getAllEmployees();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON, responseEntity.getHeaders().getContentType());
        assertEquals(2, responseEntity.getBody().size());
    }

    @Test
    public void testGetEmployeeById_Success() {
        // Datos simulados de respuesta del servicio
        Employee employee = new Employee(1, "John Doe", new BigDecimal("50000"));

        // Simulamos el comportamiento del servicio
        when(employeeService.getEmployeeById(1)).thenReturn(employee);

        Employee result = employeeController.getEmployeeById(1);

        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("John Doe", result.getEmployeeName());
    }

    @Test
    public void testGetEmployeeById_InvalidId() {
        // Simulamos el comportamiento del servicio con un ID inválido
        assertThrows(IllegalArgumentException.class, () -> {
            employeeController.getEmployeeById(-1);
        });
    }

    @Test
    public void testGetEmployeeById_EmployeeNotFound() {
        // Simulamos el comportamiento del servicio cuando el empleado no se encuentra
        when(employeeService.getEmployeeById(1)).thenReturn(null);

        Employee result = employeeController.getEmployeeById(1);
        assertNull(result);
    }

    @Test
    public void testCalculateAnnualSalary_Success() {
        // Datos simulados de respuesta del servicio
        BigDecimal annualSalary = new BigDecimal("60000");

        // Simulamos el comportamiento del servicio
        when(employeeService.calculateAnnualSalary(1)).thenReturn(annualSalary);

        BigDecimal result = employeeController.calculateAnnualSalary(1);

        assertNotNull(result);
        assertEquals(annualSalary, result);
    }

    @Test
    public void testCalculateAnnualSalary_InvalidId() {
        // Simulamos el comportamiento del servicio con un ID inválido
        assertThrows(IllegalArgumentException.class, () -> {
            employeeController.calculateAnnualSalary(-1);
        });
    }
}