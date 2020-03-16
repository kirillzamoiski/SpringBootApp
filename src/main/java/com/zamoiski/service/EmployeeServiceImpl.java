package com.zamoiski.service;

import com.zamoiski.dao.EmployeeDAO;
import com.zamoiski.entity.Employee;
import com.zamoiski.entity.JmsMessage;
import lombok.AllArgsConstructor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDAO employeeDAO;
    private JmsTemplate jmsTemplate;

    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(Long id) { return employeeDAO.findById(id); }

    @Override
    public void save(Employee employee) {
        employeeDAO.save(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeDAO.update(employee);
    }

    @Override
    public void deleteById(Long id) {
        employeeDAO.deleteById(id);
    }

    @Override
    public void updateTitle(String title, String departmentName) {
        jmsTemplate.convertAndSend("changeTitle", new JmsMessage(title, departmentName));
    }

}
