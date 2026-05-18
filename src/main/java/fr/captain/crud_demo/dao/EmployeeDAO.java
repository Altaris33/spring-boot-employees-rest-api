package fr.captain.crud_demo.dao;

import fr.captain.crud_demo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    Employee getEmployeeById(int id);

    List<Employee> getAllEmployees();
}
