package com.andy.wealth.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
public class TeacherService implements InitializingBean {
    @Autowired
    private StudentService studentService;

    public void teacherOut() {
        System.out.println("I am a teacher....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        studentService.studentOut();
    }
}
