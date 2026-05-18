package fr.captain.crud_demo.rest;

import fr.captain.crud_demo.entity.Employee;
import fr.captain.crud_demo.service.EmployeeServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeServiceImpl.getEmployeeById(id);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return employeeServiceImpl.getAllEmployees();
    }

}
