package com.challenge.spring.controller;

import java.util.ArrayList;
import java.util.List;

import com.challenge.spring.entity.Employee;
import com.challenge.spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {

  @Autowired
  private EmployeeRepository employeeRepository;

  @GetMapping("/")
  public String getAll(Model model, @Param("keyword") String keyword) {
    try {
      List<Employee> employees = new ArrayList<Employee>();

      if (keyword == null) {
        employeeRepository.findAll().forEach(employees::add);
      } else {
        employeeRepository.findByNameContainingIgnoreCase(keyword).forEach(employees::add);
        model.addAttribute("keyword", keyword);
      }

      model.addAttribute("employees", employees);
    } catch (Exception e) {
      model.addAttribute("message", e.getMessage());
    }

    return "employees";
  }

  @GetMapping("/employees/new")
  public String addEmployee(Model model) {
    Employee employee = new Employee();

    model.addAttribute("employee", employee);
    model.addAttribute("pageTitle", "Create new Employee");

    return "employee_form";
  }

  @PostMapping("/employees/save")
  public String saveEmployee(Employee employee, RedirectAttributes redirectAttributes) {
    try {
      employeeRepository.save(employee);

      redirectAttributes.addFlashAttribute("message", "The Employee has been saved successfully!");
    } catch (Exception e) {
      redirectAttributes.addAttribute("message", e.getMessage());
    }

    return "redirect:/employees";
  }

  @GetMapping("/employees/{id}")
  public String editEmployee(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    try {
      Employee employee = employeeRepository.findById(id);

      model.addAttribute("employee", employee);
      model.addAttribute("pageTitle", "Edit Employee (ID: " + id + ")");

      return "employee_form";
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", e.getMessage());

      return "redirect:/employees";
    }
  }

  @GetMapping("/employees/delete/{id}")
  public String deleteEmployee(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
    try {
      employeeRepository.deleteById(id);

      redirectAttributes.addFlashAttribute("message", "The Employee with id=" + id + " has been deleted successfully!");
    } catch (Exception e) {
      redirectAttributes.addFlashAttribute("message", e.getMessage());
    }

    return "redirect:/employees";
  }

}
