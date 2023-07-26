package backend.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import backend.dao.EmployeeDAO;
import backend.model.Employee;

@Service
public class EmployeeService {
    private final EmployeeDAO employeeDAO;
    
    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }
    
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeDAO.getAllEmployees();
        // Calcular el salario anual y agregarlo a la lista de empleados
        employees.forEach(employee -> {
            BigDecimal monthlySalary = employee.getEmployeeSalary();
            BigDecimal annualSalary = monthlySalary.multiply(new BigDecimal(12));
            employee.setAnnualSalary(annualSalary);
        });
        return employees;
    }
    
    public BigDecimal calculateAnnualSalary(int employeeId) {
        // Validamos el ID del empleado para evitar cálculos con ID negativo.
        if (employeeId <= 0) {
            throw new IllegalArgumentException("El ID del empleado debe ser un número positivo.");
        }

        Employee employee = getEmployeeById(employeeId);
        if (employee == null) {
            throw new RuntimeException("Empleado con ID " + employeeId + " no encontrado.");
        }
        
        BigDecimal monthlySalary = employee.getEmployeeSalary();
        return monthlySalary.multiply(new BigDecimal(12));
    }
    
    public Employee getEmployeeById(int employeeId) {
        // Realizamos una validación para evitar buscar empleados con ID negativo.
        if (employeeId <= 0) {
            throw new IllegalArgumentException("El ID del empleado debe ser un número positivo.");
        }
        
        Employee employee = employeeDAO.getEmployeeById(employeeId);
        BigDecimal annualSalary = employee.getEmployeeSalary().multiply(new BigDecimal(12));
        employee.setAnnualSalary(annualSalary);
        // Si el empleado no existe, retornamos null.
        if (employee == null) {
            return null;
        }
        
        return employee;
    }
}