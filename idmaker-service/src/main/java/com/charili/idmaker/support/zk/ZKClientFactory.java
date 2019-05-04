/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ZKClientFactory
 * Author:   liqing
 * Date:     2019/5/3 10:26 PM
 * Description: zk客户端工厂类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.charili.idmaker.support.zk;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * 〈一句话功能简述〉<br> 
 * 〈zk客户端工厂类〉
 *
 * @author liqing
 * @create 2019/5/3
 * @since 1.0.0
 */
public class ZKClientFactory {

    private ZKClientFactory(){}

    public static CuratorFramework createSimple(String connectionString){
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000,3);
        return CuratorFrameworkFactory.newClient(connectionString,retryPolicy);
    }

    public static CuratorFramework createWithOptions(String connectionString, RetryPolicy retryPolicy,
                                                     int connectionTimeoutMs, int sessionTimeoutMs){
        return CuratorFrameworkFactory.builder().connectString(connectionString).retryPolicy(retryPolicy).connectionTimeoutMs(connectionTimeoutMs).sessionTimeoutMs(sessionTimeoutMs).build();

    }
}
