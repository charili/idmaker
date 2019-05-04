/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: IDMakerController
 * Author:   liqing
 * Date:     2019/5/3 10:56 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.charili.idmaker.controller;

import com.charili.idmaker.model.dto.Resp;
import com.charili.idmaker.service.IDMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author liqing
 * @create 2019/5/3
 * @since 1.0.0
 */
@RestController
@RequestMapping("idMaker")
public class IDMakerController {

    @Autowired
    private IDMakerService idMakerService;


    @GetMapping("/opt/{id}")
    public Resp<Long> getId(@PathVariable("id") int id) {

        return idMakerService.getNextId(id);
    }

}
