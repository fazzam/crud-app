package com.challenge.spring.repository;

import java.util.List;

import com.challenge.spring.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcEmployeeRepository implements EmployeeRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update("INSERT INTO employees (name, number, starting_date, limit_reimburs,  created_date, updated_date) VALUES(?,?,?,?,?,?)",
                new Object[] { employee.getName(), employee.getNumber(), employee.getStartingDate(), employee.getLimitReimburs(),
                employee.getCreatedDate(), employee.getUpdatedDate() });
    }

    @Override
    public int update(Employee employee) {
        return jdbcTemplate.update("UPDATE employees SET name=?, number=?, starting_date=?, limit_reimburs=?, created_date=?, updated_date=? WHERE id=?",
                new Object[] { employee.getName(), employee.getNumber(), employee.getStartingDate(), employee.getLimitReimburs(),
                        employee.getCreatedDate(), employee.getUpdatedDate() });
    }

    public Employee findById(Integer id) {
        try {
            Employee employee = jdbcTemplate.queryForObject("SELECT * FROM employees WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Employee.class), id);

            return employee;
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }

    public int deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM employees WHERE id=?", id);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * from employees", BeanPropertyRowMapper.newInstance(Employee.class));
    }


    @Override
    public List<Employee> findByNameContainingIgnoreCase(String title) {
        String q = "SELECT * from employees WHERE title ILIKE '%" + title + "%'";

        return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Employee.class));
    }


}
