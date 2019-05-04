/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: RespStatus
 * Author:   liqing
 * Date:     2019/5/4 8:29 PM
 * Description: 响应码
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.charili.idmaker.model.constant;

/**
 * 〈一句话功能简述〉<br> 
 * 〈响应码〉
 *
 * @author liqing
 * @create 2019/5/4
 * @since 1.0.0
 */
public enum RespStatus {

    Success(200, "处理成功"),
    Failure(400, "处理失败");
    public final int code;
    public final String name;

    private RespStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

}
