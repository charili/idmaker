/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Resp
 * Author:   liqing
 * Date:     2019/5/3 9:35 PM
 * Description: 响应对象
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.charili.idmaker.model.dto;

import com.charili.idmaker.model.constant.RespStatus;

import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br> 
 * 〈响应对象〉
 *
 * @author liqing
 * @create 2019/5/3
 * @since 1.0.0
 */
public class Resp<T> implements Serializable {

    public Resp(Integer status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private String message;
    private T data;


    public static <T> Resp<T> success(T data) {
        return success((String)null, data);
    }

    public static <T> Resp<T> success(String message, T data) {
        return new Resp(Integer.valueOf(RespStatus.Success.code), message, data);
    }

    public static <T> Resp<T> failure(String message) {
        return failure(message, null);
    }

    public static <T> Resp<T> failure(String message, T data) {
        return new Resp(Integer.valueOf(RespStatus.Failure.code), message, data);
    }

}
