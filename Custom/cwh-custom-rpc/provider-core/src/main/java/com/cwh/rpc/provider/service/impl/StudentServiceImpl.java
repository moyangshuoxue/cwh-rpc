package com.cwh.rpc.provider.service.impl;

import com.cwh.rpc.common.entity.Student;
import com.cwh.rpc.common.service.StudentService;
import com.cwh.rpc.server.annotation.RpcService;

import java.util.Arrays;
import java.util.List;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/10 20:06
 * @Version 1.0
 * @ClassName StudentServiceImpl
 * @Description This is a general-purpose Java class.
 */
@RpcService(interfaceClass = StudentServiceImpl.class)
public class StudentServiceImpl implements StudentService {
    @Override
    public Student queryUser() {
        return new Student("moyangshuoxue",18,"042140309");
    }

    @Override
    public List<Student> getAllUsers() {
        List<Student> students = Arrays.asList(new Student("moyangshuoxue", 18, "042140309"), new Student("moyangshuoxue", 18, "042140309"), new Student("moyangshuoxue", 18, "042140309"));
        return students;
    }
}
