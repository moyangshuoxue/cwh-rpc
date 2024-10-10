package com.cwh.rpc.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/10 19:53
 * @Version 1.0
 * @ClassName
 * @Description  Student 用于rpc调用的消息主体
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    private String sname;
    private Integer sage;
    private String sid;
}
