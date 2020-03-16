package com.zamoiski.rest;

import com.zamoiski.entity.Employee;
import com.zamoiski.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EmployeeRestController {
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable Long employeeId) {
        return employeeService.findById(employeeId);
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        employeeService.save(employee);
        return employee;
    }

    @PutMapping("/employees")
    public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee) {
        employeeService.update(employee);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/employees/{departmentName}")
    public void updateTitleEmployee(@PathVariable String departmentName, @RequestParam String jobTitle) {
        employeeService.updateTitle(jobTitle, departmentName);
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long employeeId) {
        employeeService.deleteById(employeeId);
        return ResponseEntity.ok().build();
    }
}
