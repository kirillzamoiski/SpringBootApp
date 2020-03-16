package com.zamoiski;

import com.zamoiski.dao.EmployeeDAO;
import com.zamoiski.entity.TitleByName;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMQConsumer {

    private EmployeeDAO employeeDAO;

    public ActiveMQConsumer(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @JmsListener(destination = "changeTitle")
    public void processMessages(TitleByName dNameTitle){
        employeeDAO.updateTitle(dNameTitle.getTitle(),dNameTitle.getName());
    }
}
