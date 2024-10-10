package com.cwh.rpc.core.codec;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.ByteOrder;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 12:32
 * @Version 1.0
 * @ClassName RpcFrameDecoder
 * @Description 粘包拆包编码器，使用固定长度的帧解码器，通过约定用定长字节表示接下来数据的长度。<p>
 *              非共享，保存了 ByteBuf 的状态信息
 */
public class RpcFrameDecoder  extends LengthFieldBasedFrameDecoder {

    /**
     * 得到当前约定协议的帧解码器，
     * <pre>{@code
     *    this.RpcFrameDecoder(1024, 12, 4)
     *    maxFrameLength:1024  最大消息长度
     *    lengthFieldOffset:12 MessageHeader中的
     *    魔数 (4byte)      | 版本号 (1byte)   | 序列化算法 (1byte)
     *    消息类型 (1byte)  | 状态类型 (1byte)  | 消息序列号 (4byte)
     *    lengthFieldLength：4
     *    消息长度 (4byte)
     * }</pre>
     * 引用：{@link RpcFrameDecoder#RpcFrameDecoder(int, int, int)}
     */

    public RpcFrameDecoder() {
        this(1024, 12, 4);
    }

    /**
     * 构造方法
     *
     * @param maxFrameLength    数据帧的最大长度
     * @param lengthFieldOffset 长度域的偏移字节数
     * @param lengthFieldLength 长度域所占的字节数
     */
    public RpcFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
    }


}
