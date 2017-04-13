package com.biz.core.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 自定义Zookeeper客户端
 * <p>
 * 更改:
 * 1. 将ZookeeperUrl通过构造函数参数传入
 * </p>
 */
public class ZooClient implements AutoCloseable {

    private final static Logger logger = LoggerFactory.getLogger(ZooClient.class);

    private static CuratorFramework client;

    public static synchronized CuratorFramework getClient(String zookeeperUrl) {
        if (client == null) {
            // zookeeper地址
            zookeeperUrl = zookeeperUrl.replace("zookeeper://", "").replace("?backup=", ",");
            logger.info("zookeeperUrl: {}", zookeeperUrl);
            try {
                client = CuratorFrameworkFactory.builder().connectString(zookeeperUrl).retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000))
                        .connectionTimeoutMs(8000).build();
                client.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    private static String getZookeeperUrl() {
        String url = ConfigUtils.getZookeeperUrl();

        if (url == null) {
            try {
                url = ConfigUtils.getString("zookeeper.properties", "zookeeper.url");
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        return url;
    }

    public void close() {
        if (client != null) {
            client.close();
        }
    }

}
