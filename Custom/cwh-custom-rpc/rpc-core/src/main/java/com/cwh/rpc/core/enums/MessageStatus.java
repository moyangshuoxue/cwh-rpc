package com.cwh.rpc.core.enums;

import lombok.Getter;

/**
 * @Author 蔡文瀚
 * @Date 2024/5/11 13:15
 * @Version 1.0
 * @ClassName MessageStatus
 */
public enum MessageStatus {

    /**
     * 成功
     */
    SUCCESS((byte) 0),

    /**
     * 失败
     */
    FAIL((byte) 1);

    @Getter
    private final byte code;

    MessageStatus(byte code) {
        this.code = code;
    }

    public static boolean isSuccess(byte code) {
        return MessageStatus.SUCCESS.code == code;
    }

}
