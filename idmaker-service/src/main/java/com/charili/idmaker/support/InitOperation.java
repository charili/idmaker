/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: InitOperation
 * Author:   liqing
 * Date:     2019/5/4 7:57 PM
 * Description: 节点初始化操作
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.charili.idmaker.support;

import com.charili.idmaker.support.zk.ZKFacade;
import com.google.common.base.Splitter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br> 
 * 〈节点初始化操作〉
 *
 * @author liqing
 * @create 2019/5/4
 * @since 1.0.0
 */
@Component
public class InitOperation {

    private static final Logger LOGGER     = LoggerFactory.getLogger(InitOperation.class);

    public static final int DEFAULT_ID = 2;

    private static final Splitter SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();

    @Value("${idmaker.allow.bizid}")
    private String                allowBiz;

    @Value("${idmaker.machine.ids}")
    private String                idmakerMachineIds;

    private Integer               machineId;

    @Resource
    private ZKFacade zkFacade;

    @PostConstruct
    public void init(){
        if (StringUtils.isBlank(idmakerMachineIds)) {

            LOGGER.warn("idmaker.machine.id is null {} use default value {}", new Object[] { idmakerMachineIds,
                    DEFAULT_ID });
            machineId = Integer.valueOf(DEFAULT_ID);
        } else{

            try{
                Iterable<String> split = SPLITTER.split(idmakerMachineIds);
                for (String id : split){
                    boolean isCreate = zkFacade.try2Create(id);
                    if(isCreate){
                        LOGGER.info("{} machine id node is created",new Object[] { id });
                        machineId = Integer.valueOf(id);
                        break;
                    }
                }

            }catch (Exception ex){
                machineId = Integer.valueOf(DEFAULT_ID);
                LOGGER.error("get node from zk error ", ex);
                return;
            }

        }
        if (machineId == null) {
            machineId = DEFAULT_ID;
            LOGGER.info("use default machine ID {}", new Object[] { machineId });
        }

        LOGGER.info("---- machineID {} ", new Object[] { machineId });
    }


    public String getAllowBiz() {
        return allowBiz;
    }

    public int getMachineId() {
        return machineId;
    }

    /**
     *destroy
     */
    @PreDestroy
    public void destory() {
    }

}
