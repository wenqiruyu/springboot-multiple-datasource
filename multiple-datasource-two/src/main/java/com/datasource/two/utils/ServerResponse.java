package com.datasource.two.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;

/**
 * 项目名称：springboot-multiple-datasource
 * 类名称：ServerResponse
 * 类描述：TODO
 * 创建人：yingx
 * 创建时间： 2019/10/18
 * 修改人：yingx
 * 修改时间： 2019/10/18
 * 修改备注：
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL) // 保证json序列化的时候，如果是null对象，key也会消失
public class ServerResponse<T> implements Serializable {

    private int status;
    private String msg;
    private T data;

    /**
     * 只返回错误码
     *
     * @param status
     */
    private ServerResponse(int status) {
        this.status = status;
    }

    /**
     * 返回错误码和数据
     *
     * @param status
     * @param data
     */
    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    /**
     * 返回错误码，错误提示和数据
     *
     * @param status
     * @param msg
     * @param data
     */
    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回错误码、错误提示
     *
     * @param status
     * @param msg
     */
    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    /**
     * 使之不再进行序列化
     * 判断是否返回成功的提示码
     */
    @JsonIgnore
    public boolean isSuccess() {
        // 响应成功
        return this.status == ReponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public static <T> ServerResponse<T> createBySuccess() {
        return new ServerResponse<T>(ReponseCode.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccess(T data) {
        return new ServerResponse<T>(ReponseCode.SUCCESS.getCode(), "success", data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data) {
        return new ServerResponse<T>(ReponseCode.SUCCESS.getCode(), msg, data);
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg) {
        return new ServerResponse<T>(ReponseCode.SUCCESS.getCode(), msg);
    }

    public static <T> ServerResponse<T> createByError() {
        return new ServerResponse<T>(ReponseCode.ERROR.getCode(), ReponseCode.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new ServerResponse<T>(errorCode, errorMessage);
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage) {
        return new ServerResponse<T>(ReponseCode.ERROR.getCode(), errorMessage);
    }

}