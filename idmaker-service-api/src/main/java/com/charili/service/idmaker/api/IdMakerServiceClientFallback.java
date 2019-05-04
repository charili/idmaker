/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: IdMakerServiceClientFallback
 * Author:   liqing
 * Date:     2019/5/3 11:03 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.charili.service.idmaker.api;

import com.charili.idmaker.model.dto.Resp;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author liqing
 * @create 2019/5/3
 * @since 1.0.0
 */
public class IdMakerServiceClientFallback implements IdMakerServiceClient{

    @Override
    public Resp<Long> getId(int id) {
        return null;
    }
}
