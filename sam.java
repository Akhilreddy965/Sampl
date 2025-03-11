package com.example.demo;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Entity
@Entity
@Table(name = "employees")
class Employee {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private String name, email;
    public Employee() {}  
    public Employee(String name, String email) { this.name = name; this.email = email; }
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
}

// Repository
@Repository
interface EmployeeRepository extends JpaRepository<Employee, Long> {}

// Controller
@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
class EmployeeController {
    @Autowired private EmployeeRepository repository;
    
    @GetMapping public List<Employee> getAll() { return repository.findAll(); }
    @PostMapping public Employee add(@RequestBody Employee emp) { return repository.save(emp); }
}
