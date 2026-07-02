package com.example.demo.controller;

import com.example.demo.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/employees")  // Base URL for all endpoints in this class
public class EmployeeController {

    
	private List<Employee> employees = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        int newId = idCounter.getAndIncrement();
        employee.setId(newId);
        employees.add(employee);
        return employee;  
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employees.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    // ------------------- UPDATE (PUT) -------------------
    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee updatedEmp) {
        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            if (emp.getId() == id) {
                emp.setName(updatedEmp.getName());
                emp.setDepartment(updatedEmp.getDepartment());
                emp.setSalary(updatedEmp.getSalary());
                return emp;
            }
        }
        throw new RuntimeException("Employee not found with id: " + id);
    }

    // ------------------- DELETE (DELETE) -------------------
    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        boolean removed = employees.removeIf(emp -> emp.getId() == id);
        if (removed) {
            return "Employee deleted successfully with id: " + id;
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }
}
