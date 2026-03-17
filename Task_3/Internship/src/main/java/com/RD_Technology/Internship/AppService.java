package com.RD_Technology.Internship;

import com.RD_Technology.Internship.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppService {

    @Autowired
    private MessageRepository repository;

    public void printGoal() {
        System.out.println(repository.getGoalMessage());
    }
}
