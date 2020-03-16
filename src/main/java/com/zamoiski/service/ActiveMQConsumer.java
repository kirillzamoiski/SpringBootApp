package com.zamoiski.service;

import com.zamoiski.dao.EmployeeDAO;
import com.zamoiski.entity.JmsMessage;
import lombok.AllArgsConstructor;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ActiveMQConsumer {

    private EmployeeDAO employeeDAO;

    @JmsListener(destination = "changeTitle")
    public void processMessages(JmsMessage jmsMessage) {
        employeeDAO.updateTitle(jmsMessage.getTitle(), jmsMessage.getName());
    }
}
