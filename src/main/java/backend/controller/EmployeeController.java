package backend.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import backend.model.Employee;
import backend.service.EmployeeService;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;
    
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(employees);
    }
    
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        // Validamos el ID del empleado para evitar IDs negativos o inválidos.
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del empleado debe ser un número positivo.");
        }
        
        Employee employee = employeeService.getEmployeeById(id);
        
        // Si el empleado no existe, retornamos null
        if (employee == null) {
            return null;
        }
        
        return employee;
    }
    
    @GetMapping("/employee/{id}/annual-salary")
    public BigDecimal calculateAnnualSalary(@PathVariable int id) {
        // Validamos el ID del empleado para evitar IDs negativos o inválidos.
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del empleado debe ser un número positivo.");
        }
        
        return employeeService.calculateAnnualSalary(id);
    }
}