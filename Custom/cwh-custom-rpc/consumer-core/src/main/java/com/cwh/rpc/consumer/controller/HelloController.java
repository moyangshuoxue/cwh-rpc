package com.cwh.rpc.consumer.controller;

import com.cwh.rpc.client.annotation.RpcReference;
import com.cwh.rpc.common.service.AbstractService;
import com.cwh.rpc.common.service.HelloService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping
public class HelloController {

    @RpcReference
    private HelloService helloService;

    @RpcReference
    private AbstractService abstractService;


    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name) {
        return helloService.sayHello(name);
    }

    @RequestMapping("/hello/test/{count}")
    public Map<String, Long> performTest(@PathVariable("count") Long count) {
        Map<String, Long> result = new HashMap<>();
        result.put("调用次数", count);
        long start = System.currentTimeMillis();
        for (long i = 0; i < count; i++) {
            helloService.sayHello(Long.toString(i));
        }
        result.put("耗时", System.currentTimeMillis() - start);
        return result;
    }

    @RequestMapping("/abstracthello/{name}")
    public String abstractHello(@PathVariable("name") String name) {
        return abstractService.abstractHello(name);
    }
}
