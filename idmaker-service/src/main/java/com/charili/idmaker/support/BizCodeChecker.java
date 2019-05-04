/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: BizCodeChecker
 * Author:   liqing
 * Date:     2019/5/4 7:54 PM
 * Description: 节点验证
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.charili.idmaker.support;

import com.google.common.base.Splitter;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 〈一句话功能简述〉<br> 
 * 〈业务id支持范围验证〉
 *
 * @author liqing
 * @create 2019/5/4
 * @since 1.0.0
 */
@Component
public class BizCodeChecker {

    private static final Logger LOGGER = LoggerFactory.getLogger(BizCodeChecker.class);

    @Resource
    private InitOperation initOperation;
    private volatile List<Integer> allowBizs;

    public boolean isAllow(int bizId){
        initAllowBizs();
        return allowBizs.contains(bizId);
    }

    private void initAllowBizs(){
        if(CollectionUtils.isEmpty(allowBizs)){
            synchronized (this){
                if(CollectionUtils.isEmpty(allowBizs)){
                    String allowBiz = initOperation.getAllowBiz();
                    allowBizs = new ArrayList<>();

                    Iterable<String> split = Splitter.on(",").omitEmptyStrings().trimResults().split(allowBiz);

                    for (String s : split) {
                        allowBizs.add(Integer.valueOf(s));
                    }
                }
            }
        }
    }

}
