/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: IdMakerServiceClient
 * Author:   liqing
 * Date:     2019/5/3 11:02 PM
 * Description: id生成服务客户端接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.charili.service.idmaker.api;

import com.charili.idmaker.model.dto.Resp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 〈一句话功能简述〉<br> 
 * 〈id生成服务客户端接口〉
 *
 * @author liqing
 * @create 2019/5/3
 * @since 1.0.0
 */

@FeignClient(name = "${feign.idmaker.name:idmaker-service}", url = "${feign.idmaker.url:}", fallback = IdMakerServiceClientFallback.class)
@RequestMapping("idMaker")
public interface IdMakerServiceClient {

    @RequestMapping(value = "/opt/{id}", method = RequestMethod.GET)
    Resp<Long> getId(@PathVariable("id") int id);

}
