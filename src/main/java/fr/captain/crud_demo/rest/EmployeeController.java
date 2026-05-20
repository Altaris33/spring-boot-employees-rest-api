package fr.captain.crud_demo.rest;

import fr.captain.crud_demo.entity.Employee;
import fr.captain.crud_demo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final JsonMapper jsonMapper;
    private final EmployeeService employeeService;

    public EmployeeController(JsonMapper jsonMapper, EmployeeService employeeService) {
        this.jsonMapper = jsonMapper;
        this.employeeService = employeeService;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            throw new RuntimeException("Employee id not found: " + id);
        }
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return employeeService.getAllEmployees();
    }

    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PatchMapping("/employees/{id}")
    public Employee patchEmployee(@PathVariable int id, @RequestBody Map<String, Object> payload) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            throw new RuntimeException("Employee id not found: " + id);
        }

        if (payload.containsKey("id")) {
            throw new RuntimeException("Request Body cannot contain an id: " + id);
        }

        Employee patchedEmployee = jsonMapper.updateValue(employee, payload);
        return employeeService.save(patchedEmployee);
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee == null) {
            throw new RuntimeException("Employee id not found: " + id);
        }
        employeeService.deleteById(id);
    }
}
