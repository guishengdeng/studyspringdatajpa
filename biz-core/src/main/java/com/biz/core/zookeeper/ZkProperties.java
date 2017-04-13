package com.biz.core.zookeeper;

import com.biz.core.exceptions.SystemException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * zookeeper properties配置
 * <p/>
 * <p>
 * 从zookeeper加载properties格式配置, 支持保存
 * </p>
 *
 * @author defei
 * @date 2015年10月29日
 */
public class ZkProperties {

    private static final Logger logger = LoggerFactory.getLogger(ZkProperties.class);

    private Properties config;
    private byte[] configBytes;

    public ZkProperties(final String zookeeperUrl, final String path) {
        this(zookeeperUrl, path, false);
    }

    public ZkProperties(final String zookeeperUrl, final String path, boolean watch) {
        try {
            final CuratorFramework curator = ZooClient.getClient(zookeeperUrl);
            if (curator.checkExists().forPath(path) == null) {
                logger.debug("zk 文件:{} 不存在, 创建zk文件", path);
                curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
            }

            byte[] data;
            if (watch) {
                final Watcher watcher = new Watcher() {

                    @Override
                    public void process(WatchedEvent event) {
                        if (event.getType() == EventType.NodeCreated || event.getType() == EventType.NodeDataChanged) {
                            logger.debug("NodeDataChanged");
                            try {
                                byte[] data = curator.getData().usingWatcher(this).forPath(path);
                                reloadProperties(data);
                            } catch (Exception e) {
                                logger.error("error", e);
                            }
                        }
                    }
                };
                data = curator.getData().usingWatcher(watcher).forPath(path);
            } else {
                data = curator.getData().forPath(path);
            }

            reloadProperties(data);
            logger.debug("加载zk properties : {} 成功", path);
        } catch (Exception e) {
            throw new SystemException("初始化失败", e);
        }
    }

    /**
     * @throws IOException
     * @throws ConfigurationException
     */
    private void reloadProperties(byte[] data) throws IOException {
        Properties c = createEmptyConfig();
        if (data != null) {
            c.load(new ByteArrayInputStream(data));
        }
        this.config = c;
        this.configBytes = data;
    }

    /**
     * 获取属性
     *
     * @author defei
     * @date 2015年10月29日
     */
    public String getProperty(String key) {
        return config == null ? null : config.getProperty(key);
    }

    /**
     * 获取Long类型的property
     *
     * @author defei
     * @date 2015年12月2日
     */
    public Long getLong(String key) {
        String p = getProperty(key);
        return StringUtils.isBlank(p) ? null : Long.valueOf(p);
    }

    /**
     * 获取所有属性
     *
     * @author defei
     * @date 2015年10月29日
     */
    public Properties getProperties() {
        return config;
    }

    public byte[] getPropertiesBytes() {
        return this.configBytes;
    }

    private static Properties createEmptyConfig() {
        Properties c = new Properties();
        return c;
    }

}
