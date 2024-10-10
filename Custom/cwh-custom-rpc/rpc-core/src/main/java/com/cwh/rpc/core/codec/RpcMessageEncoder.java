package com.cwh.rpc.core.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/10 21:11
 * @Version 1.0
 * @ClassName RpcMessageEncoder
 * @Description 继承MessageToByteEncoder 更使用于Provider端 将信息按照不同格式发送 更专注于编码
*/
public class RpcMessageEncoder<T>  extends MessageToByteEncoder<T> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, T t, ByteBuf byteBuf) throws Exception {

    }
}
