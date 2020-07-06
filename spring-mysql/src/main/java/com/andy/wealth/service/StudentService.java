package com.andy.wealth.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class StudentService implements InitializingBean {
    @Autowired
    private TeacherService teacherService;

    public void studentOut() {
        System.out.println("I am a student...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        teacherService.teacherOut();
    }
}
