package com.datasource.two.utils;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：ReponseCode
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/18
 * 修改人：yingx
 * 修改时间： 2019/10/18
 * 修改备注：
 */
public enum ReponseCode {

    ERROR(0, "ERROR"),
    SUCCESS(1, "SUCCESS"),
    NEED_LOGIN(3, "NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2, "ILLEGAL_ARGUMENT");
    // illegal_argument 非法参数
    private final int code;
    private final String desc;

    ReponseCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}