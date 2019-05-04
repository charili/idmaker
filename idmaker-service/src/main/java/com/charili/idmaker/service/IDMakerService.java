/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: IDMakerService
 * Author:   liqing
 * Date:     2019/5/3 9:32 PM
 * Description: id生成服务接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.charili.idmaker.service;

import com.charili.idmaker.model.dto.Resp;

/**
 * 〈一句话功能简述〉<br> 
 * 〈id生成服务接口〉
 *
 * @author liqing
 * @create 2019/5/3
 * @since 1.0.0
 */
public interface IDMakerService {
    /**
     * 获取唯一号
     * @param bizId
     * @return
     */
    Resp<Long> getNextId(int bizId);

}
