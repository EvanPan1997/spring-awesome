package com.example.utils;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@Data
public class ResponseResult<T> implements Serializable {
    private int status;
    private String msg;
    @Nullable
    private T data;

    public ResponseResult(int status, String msg, @Nullable T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static <T> ResponseResult<T> build(int code, String msg) {
        return ResponseResult.build(code, msg, null);
    }

    public static <T> ResponseResult<T> build(int code, String msg, T data) {
        return new ResponseResult(code, msg, data);
    }

    public static <T> ResponseResult<T> success() {
        return ResponseResult.success("操作成功", null);
    }

    public static <T> ResponseResult<T> success(String msg) {
        return ResponseResult.success(msg, null);
    }

    public static <T> ResponseResult<T> success(String msg, T data) {
        return ResponseResult.build(HttpStatus.OK.value(), msg, data);
    }

    public static <T> ResponseResult<T> error() {
        return ResponseResult.error("操作失败", null);
    }

    public static <T> ResponseResult<T> error(String msg) {
        return ResponseResult.error(msg, null);
    }

    public static <T> ResponseResult<T> error(String msg, T data) {
        return ResponseResult.build(HttpStatus.INTERNAL_SERVER_ERROR.value(), msg, data);
    }

}
