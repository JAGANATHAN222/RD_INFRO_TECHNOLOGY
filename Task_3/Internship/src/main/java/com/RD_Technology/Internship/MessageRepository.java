package com.RD_Technology.Internship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageRepository {

    public String getGoalMessage() {
        return "Goal: become a Spring Boot developer";
    }
}
