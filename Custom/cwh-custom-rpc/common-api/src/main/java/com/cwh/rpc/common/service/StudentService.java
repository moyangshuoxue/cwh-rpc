package com.cwh.rpc.common.service;

import com.cwh.rpc.common.entity.Student;

import java.util.List;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/10 19:58
 * @Version 1.0
 * @ClassName StudentService
 * @Description 用于Consumer调用与Provider实现.
 */
public interface StudentService {
    Student queryUser();

    List<Student> getAllUsers();
}
