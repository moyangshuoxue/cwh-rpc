package com.cwh.rpc.core.codec;

import com.cwh.rpc.core.config.constant.ProtocolConstants;
import com.cwh.rpc.core.protocol.MessageHeader;
import com.cwh.rpc.core.protocol.RpcMessage;
import com.cwh.rpc.core.serialzation.Serialization;
import com.cwh.rpc.core.serialzation.SerializationFactory;
import com.cwh.rpc.core.util.MessageTypeUtil;
import com.cwh.rpc.core.util.SerializationTypeUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.Arrays;
import java.util.List;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 10:31
 * @Version 1.0
 * @ClassName SharaableRpcMessageCodec
 * @Description 由于本模块 consumer与provider消息类型一致，可以同时在一个Codec编码解码 所以继承MessageToByteEncoder
 */
public class SharableRpcMessageCodec extends MessageToMessageCodec<ByteBuf, RpcMessage> {

    @Override
    protected void encode(ChannelHandlerContext ChannelHandlerContext, RpcMessage msg, List<Object> out) throws Exception {
        //1.创建ByteBuf  使用ChannelHandlerContext的好处--> 直接使用netty的io线程进行读写减少拷贝 （池化通用）
        ByteBuf buf = ChannelHandlerContext.alloc().buffer();
        MessageHeader header = msg.getHeader();
        // 4字节 魔数 用于校验消息是否
        buf.writeBytes(header.getMagicNum());
        // 1字节 版本号 用于不同版本切换 为后序imlp更新
        buf.writeByte(header.getVersion());
        // 1字节 序列化算法 对数据进行加密解密
        buf.writeByte(header.getSerializerType());
        // 1字节 消息类型 判断是登录 注册 发送等消息种类
        buf.writeByte(header.getMessageType());
        // 1字节 消息状态
        buf.writeByte(header.getMessageStatus());
        // 4字节 消息序列号
        buf.writeInt(header.getSequenceId());
        // 取出消息体
        Object body = msg.getBody();
        // 获取序列化算法
        Serialization serialization = SerializationFactory.getSerialization(SerializationTypeUtil.parseByType(header.getSerializerType()));
        // 进行序列化
        byte[] bytes = serialization.serialize(body);
        // 设置消息体长度
        header.setLength(bytes.length);
        // 4字节 请求头长度
        buf.writeInt(header.getLength());
        // 不固定字节 消息内容字节数组
        buf.writeBytes(bytes);
        // 传递到下一个出站处理器
        out.add(buf);

    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf msg, List<Object> out) throws Exception {
        //处理魔术
        int length = ProtocolConstants.MAGIC_NUM.length;
        byte[] magicNum = new byte[length];
        //判断魔术是否正确，对非法数据进行处理
        msg.readBytes(magicNum,0,length);
        for (int i = 0; i < ProtocolConstants.MAGIC_NUM.length; i++) {
            if(magicNum[i] !=ProtocolConstants.MAGIC_NUM[i]){
                throw new IllegalArgumentException("Unknown magic code: " + Arrays.toString(magicNum));
            }
        }
        byte version = msg.readByte();
        if (ProtocolConstants.VERSION !=version) {
            throw new IllegalArgumentException("The version isn't compatible " + version);
        }
        // 处理序列化
        byte serializeType = msg.readByte();
        // 获取序列化算法
        Serialization serialization = SerializationFactory.getSerialization(SerializationTypeUtil.parseByType(serializeType));
        // 获取消息类型 用于获取具体具体的Message类型
        byte messageType = msg.readByte();
        // 消息状态
        byte messageStatus = msg.readByte();
        // 消息序列号
        int sequenceId = msg.readInt();
        // 获取消息长度
        int messageBodyLength = msg.readInt();
        //获取消息主体
        byte[] messageBodyBytes = new byte[messageBodyLength];
        msg.readBytes(messageBodyBytes,0,length);
        //重建(还原)MessageHeader
        MessageHeader header = MessageHeader.builder()
                .magicNum(magicNum)
                .version(version)
                .serializerType(serializeType)
                .sequenceId(sequenceId)
                .messageStatus(messageStatus)
                .length(messageBodyLength).build();
        // 进行MessageBody反序列化处理 并装配
        RpcMessage message = new RpcMessage();
        //填充消息头
        message.setHeader(header);
        //获取具体消息类型
        Class declaringClass = MessageTypeUtil.parseByType(messageType).getClazz();
        Class detailMessageBody = serialization.deserialize(declaringClass.getClass(), messageBodyBytes);

        //填充具体消息体
        message.setBody(detailMessageBody);
        out.add(message);
    }
}
