package fr.captain.crud_demo.dao;

import fr.captain.crud_demo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    private final EntityManager entityManager;

    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Employee getEmployeeById(int id) {
        TypedQuery<Employee> query
                = entityManager.createQuery("SELECT e FROM Employee e WHERE e.id=:id", Employee.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Employee> getAllEmployees() {
        return entityManager.createQuery("FROM Employee", Employee.class).getResultList();
    }
}
