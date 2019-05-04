/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: IDMakerServiceImpl
 * Author:   liqing
 * Date:     2019/5/3 9:31 PM
 * Description: id生成服务实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.charili.idmaker.service.impl;

import com.charili.idmaker.biz.IDWorker;
import com.charili.idmaker.model.dto.Resp;
import com.charili.idmaker.service.IDMakerService;
import com.charili.idmaker.support.BizCodeChecker;
import com.charili.idmaker.support.InitOperation;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 〈一句话功能简述〉<br> 
 * 〈id生成服务实现类〉
 *
 * @author liqing
 * @create 2019/5/3
 * @since 1.0.0
 */
public class IDMakerServiceImpl implements IDMakerService{

    private Logger logger = LoggerFactory.getLogger(IDMakerServiceImpl.class);
    @Resource
    private InitOperation initOperation;
    @Resource
    private BizCodeChecker bizCodeChecker;

    // 业务类型的缓存
    private Cache<Integer, IDWorker> bizIDCache    = CacheBuilder.newBuilder().expireAfterAccess(12, TimeUnit.HOURS).concurrencyLevel(Runtime.getRuntime().availableProcessors() + 1).build();


    @Override
    public Resp<Long> getNextId(int bizId) {
        if (bizId <= 0 || !bizCodeChecker.isAllow(bizId)){
            logger.error("bizId参数错误:"+ bizId);
            return Resp.failure("bizId is invalid:" + bizId);
        }
        long nextId;
        try {

            IDWorker idWorker = getIdWorker(bizId);
            nextId =  idWorker.nextId();
            return Resp.success(nextId);

        } catch (Exception e) {
            logger.error("get next ID error ,biz ID {}", new Object[] { String.valueOf(bizId) },e);
            return Resp.failure("get next Id failed:"+ bizId);
        }
    }

    private IDWorker getIdWorker(final Integer bizID) throws ExecutionException {
        return bizIDCache.get(bizID, () -> new IDWorker(initOperation.getMachineId(), bizID));
    }
}
