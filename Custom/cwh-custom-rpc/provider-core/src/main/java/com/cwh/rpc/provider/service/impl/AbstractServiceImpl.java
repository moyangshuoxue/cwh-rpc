package com.cwh.rpc.provider.service.impl;

import com.cwh.rpc.common.service.AbstractService;
import com.cwh.rpc.server.annotation.RpcService;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/10 20:02
 * @Version 1.0
 * @ClassName AbstractServiceImpl
 * @Description This is a general-purpose Java class.
 */

@RpcService(interfaceClass = AbstractService.class)
public class AbstractServiceImpl extends AbstractService {
    @Override
    public String abstractHello(String name) {
       return  "abstract hello " + name;
    }
}
