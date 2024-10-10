package com.cwh.rpc.core.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/10 21:12
 * @Version 1.0
 * @ClassName RpcMessageDecoder
 * @Description ByteToMessageDecoder 更使用于Client端 当接受消息过多 而内部需要信息不同
 *             可以按照业务将分解成不同Message 更专注于解码
 */
public class RpcMessageDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

    }
}
