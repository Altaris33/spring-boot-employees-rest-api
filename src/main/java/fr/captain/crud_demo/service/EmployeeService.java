package fr.captain.crud_demo.service;

import ch.qos.logback.classic.pattern.EpochConverter;
import fr.captain.crud_demo.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();

    Employee save(Employee employee);

    void deleteById(int id);
}
