package com.challenge.spring.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import com.challenge.spring.entity.Employee;

@Repository
public interface EmployeeRepository {
  int save(Employee book);

  int update(Employee book);

  Employee findById(Integer id);

  int deleteById(Integer id);

  List<Employee> findAll();

  List<Employee> findByNameContainingIgnoreCase(String keyword);
}
