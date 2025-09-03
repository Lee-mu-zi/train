package com.leemuzi.train.common.exception;


/**
 * 异常处理异常枚举类
 */
public enum BusinessExceptionEnum {
    MEMBER_MOBILE_EXIST("手机号已经注册");

    private String desc;

    BusinessExceptionEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "BusinessExceptionEnum{" +
                "desc='" + desc + '\'' +
                '}';
    }

}
