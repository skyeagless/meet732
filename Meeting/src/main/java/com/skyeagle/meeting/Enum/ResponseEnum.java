package com.skyeagle.meeting.Enum;

import lombok.Getter;

/**
 * @projectName:meeting
 * @packageName:PACKAGE_NAME
 * @className:ResponseEnum
 * @Description:[一句话描述该类的功能]
 * @Author:lvyang
 * @CreateDate:2021-2-1 21:27
 */

@Getter
public enum ResponseEnum {
    SUCCESS(0,"成功"),
    INSERT_ERROR(1,"预定时间存在冲突，无法插入"),
    DELETE_ERROR(2,"删除失败"),
    PARSE_ERROR(3,"预定时间输入不正确"),
    ;
    Integer code;
    String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
