package com.zamoiski.rest;

import com.zamoiski.entity.Department;
import com.zamoiski.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class DepartmentRestController {
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public List<Department> findAll() {
        return departmentService.findAll();
    }

    @GetMapping("/departments/{departmentId}")
    public Department findById(@PathVariable Long departmentId) {
        return departmentService.findById(departmentId);
    }

    @PostMapping("/departments")
    public Department addEmployee(@RequestBody Department department) {
        departmentService.save(department);
        return department;
    }

    @PutMapping("/departments")
    public ResponseEntity<Object> updateEmployee(@RequestBody Department department) {
        departmentService.update(department);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/departments/{departmentsId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable Long departmentsId) {
        departmentService.deleteById(departmentsId);
        return ResponseEntity.ok().build();
    }
}
