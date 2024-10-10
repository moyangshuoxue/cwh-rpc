package com.cwh.rpc.core.config.constant;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 11:37
 * @Version 1.0
 * @ClassName ProtocolConstants
 * @Description 线程池配置类
 */
public class ProtocolConstants {

    private static final AtomicInteger ai = new AtomicInteger();

    /**
     * 魔数，用来第一时间判断是否无效数据包
     */
    public static final byte[] MAGIC_NUM = new byte[]{(byte) 'm', (byte) 'y', (byte) 's', (byte) 'x'};

    public static final byte VERSION = 1;

    public static final String PING = "ping";

    public static final String PONG = "pong";

    public static int getSequenceId() {
        // todo: 实现原子操作
        return ai.getAndIncrement();
    }

}
