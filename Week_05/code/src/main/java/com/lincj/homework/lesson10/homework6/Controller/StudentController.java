package com.lincj.homework.lesson10.homework6.Controller;

import com.lincj.homework.lesson10.homework6.Entity.Student;
import com.lincj.homework.lesson10.homework6.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.UUID;

@RestController
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @GetMapping(value = "insert")
    private String insert() {
        studentRepository.insert(new Student(UUID.randomUUID().toString().replace("-", ""), "lincj", 15));
        return "插入成功";
    }

    @GetMapping(value = "update")
    private String update() throws SQLException {
        studentRepository.update(new Student("7d1aa081ca2c4a849184d0d82e1244ab", "lincj", 20));
        return "更新成功";
    }
}
