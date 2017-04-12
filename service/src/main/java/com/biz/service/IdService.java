package com.biz.service;

import com.biz.core.zookeeper.SoaIndexFactory;
import org.codelogger.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 全局Id生成器 从网上copy的
 *
 * @author gongshutao
 */
public class IdService {

    private static final Logger logger = LoggerFactory.getLogger(IdService.class);

    private final static long twepoch = 1409030641843L;
    // 机器标识位数
    private final static long workerIdBits = 5L;
    // 数据中心标识位数
    private final static long dataCenterIdBits = 5L;
    // 机器ID最大值
    //private final static long maxWorkerId = -1L ^ (-1L << workerIdBits);
    // 数据中心ID最大值
    private final static long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
    // 毫秒内自增位
    private final static long sequenceBits = 12L;
    // 机器ID偏左移12位
    private final static long workerIdShift = sequenceBits;
    // 数据中心ID左移17位
    private final static long dataCenterIdShift = sequenceBits + workerIdBits;
    // 时间毫秒左移22位
    private final static long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;

    private final static long sequenceMask = -1L ^ (-1L << sequenceBits);

    private static long lastTimestamp = -1L;

    private long sequence = 0L;

    private final long workerId;

    private final long dataCenterId;

    public IdService(long dataCenterId, String soaIdxZooNodePath, String zookeeperUrl) {
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException("data center Id can't be greater than %d or less than 0");
        }

        long workerId;
        if (StringUtils.isBlank(soaIdxZooNodePath) || StringUtils.isBlank(zookeeperUrl)) {
            logger.warn("未配置SoaIdx在Zookeeper中存储的路径或者ZookeeperUrl, 使用默认的SoaIdx[1]");
            // 如果未配置SoaIdx
            workerId = 1;
        } else {
            workerId = SoaIndexFactory.newInstance(soaIdxZooNodePath, zookeeperUrl).getSoaIdx();
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    public synchronized long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            try {
                throw new Exception(
                        "Clock moved backwards.  Refusing to generate id for " + (lastTimestamp
                                - timestamp) + " milliseconds");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (lastTimestamp == timestamp) {
            // 当前毫秒内，则+1
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                // 当前毫秒内计数满了，则等待下一秒
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;
        // ID偏移组合生成最终的ID，并返回ID
        long nextId =
                ((timestamp - twepoch) << timestampLeftShift) | (dataCenterId << dataCenterIdShift) | (
                        workerId << workerIdShift) | sequence;

        return nextId;
    }

    private long tilNextMillis(final long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    private long timeGen() {
        return System.currentTimeMillis();
    }

}
