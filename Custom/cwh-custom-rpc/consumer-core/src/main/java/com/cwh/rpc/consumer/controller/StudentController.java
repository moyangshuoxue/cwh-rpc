package com.cwh.rpc.consumer.controller;

import com.cwh.rpc.client.annotation.RpcReference;
import com.cwh.rpc.common.entity.Student;
import com.cwh.rpc.common.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class StudentController {

    @RpcReference
    private StudentService studentService;


    @RequestMapping("/user/getUser")
    public Student getUser() {

        return studentService.queryUser();
    }

    @RequestMapping("/user/getAllUser")
    public List<Student> getAllUser() {

        return studentService.getAllUsers();
    }

}
