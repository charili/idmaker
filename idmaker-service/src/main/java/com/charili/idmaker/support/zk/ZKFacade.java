/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ZKFacade
 * Author:   liqing
 * Date:     2019/5/3 10:46 PM
 * Description: zk客户端装饰器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.charili.idmaker.support.zk;

import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 〈一句话功能简述〉<br> 
 * 〈zk客户端装饰器〉
 *
 * @author liqing
 * @create 2019/5/3
 * @since 1.0.0
 */
@Component
public class ZKFacade {

    private static final Logger LOGGER      = LoggerFactory.getLogger(ZKFacade.class);

    @Value("${idmaker.zk.url}")
    private String              zkURL;

    private CuratorFramework curatorFramework;

    public static final String  PARENT_PATH = "/idmaker/ids";

    @PostConstruct
    public void init() throws Exception{
        curatorFramework = ZKClientFactory.createSimple(zkURL);
        curatorFramework.start();
        Stat stat = curatorFramework.checkExists().forPath(PARENT_PATH);
        if(stat == null){
            curatorFramework.create().creatingParentContainersIfNeeded().forPath(PARENT_PATH);

        }
    }

    /**
     * 尝试建立临时节点
     * @param id
     * @return
     */
    public boolean try2Create(String id){
        String childPath = PARENT_PATH + "/" + id;
        String s = null;
        try{
            Stat stat = curatorFramework.checkExists().forPath(childPath);
            if(stat != null){
                LOGGER.info("current id node is exist {}",id);
                return false;
            }
            s = curatorFramework.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(childPath);

        }catch (Exception ex){
            LOGGER.error("",ex);
            return false;
        }
        return StringUtils.isNotBlank(s) ? true : false;
    }


}
