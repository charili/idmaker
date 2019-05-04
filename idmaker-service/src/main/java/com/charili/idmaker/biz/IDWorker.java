/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: IDWorker
 * Author:   liqing
 * Date:     2019/5/3 9:29 PM
 * Description: id生成器核心类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.charili.idmaker.biz;

/**
 * 〈一句话功能简述〉<br> 
 * 〈id生成器核心类〉
 *
 * @author liqing
 * @create 2019/5/3
 * @since 1.0.0
 */
public class IDWorker {

    private final long machineId;
    private final long bizId;
    private static final long TWEPOCH = 1483200000L;//timestamp from start date
    private long sequence = 0L;
    private static final long SEQUENCE_BITS    = 12L;
    public static final long  SEQUENCE_MASK    = -1L ^ -1L << SEQUENCE_BITS;
    private static final long MACHINE_ID_BITS = 3L;
    private static final long MAX_MACHINE_ID = -1L ^ -1L << MACHINE_ID_BITS;
    private static final long BIZ_ID_BITS = 4L:
    private static final long MAX_BIZ_ID = -1L ^ -1L << BIZ_ID_BITS;
    private long              lastTimestamp    = -1L;


    /**
     * @param machineId 机器ID
     * @param bizId 业务ID
     */
    public IDWorker(final long machineId,final long bizId){
        if(machineId > MACHINE_ID_BITS || machineId < 0){
            throw new IllegalArgumentException(String.format("machine Id can't be greater than %d or less than 0",
                    MAX_MACHINE_ID));
        }
        this.machineId = machineId;
        if(bizId > MAX_BIZ_ID || bizId < 0){
            throw new IllegalArgumentException(String.format("biz Id can't be greater than %d or less than 0",
                    MAX_BIZ_ID));
        }
        this.bizId = bizId;
    }

    public synchronized long nextId(){
        long timestamp = this.timeGen();
        if(this.lastTimestamp == timestamp){
            this.sequence = (this.sequence + 1) & SEQUENCE_MASK;
            if(this.sequence == 0){
                timestamp = this.tillNextMillis(this.lastTimestamp);
            }
        }else{
            this.sequence = 0;
        }
        if(timestamp < this.lastTimestamp){
            throw new IllegalStateException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds",
                            this.lastTimestamp - timestamp));
        }
        this.lastTimestamp = timestamp;
        return ((timestamp -TWEPOCH) << BIZ_ID_BITS + MACHINE_ID_BITS + SEQUENCE_BITS) + (bizId << MACHINE_ID_BITS + SEQUENCE_BITS) + (machineId << SEQUENCE_BITS) + sequence;

    }

    private long tillNextMillis(final long lastTimestamp){
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp){
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen(){
        return System.currentTimeMillis();
    }



}
