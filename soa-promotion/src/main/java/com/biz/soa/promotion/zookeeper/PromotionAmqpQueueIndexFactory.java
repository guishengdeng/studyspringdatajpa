package com.biz.soa.promotion.zookeeper;

import com.biz.core.asserts.SystemAsserts;
import com.biz.core.zookeeper.ZooClient;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 迁移自1919 bbc
 * <p>
 * 更改:
 * 将之前SoaIdx存放Zookeeper的节点更改为配置注入
 * <p/>
 *
 * @author yanweijin
 * @usage
 * @reviewer
 * @since 2016年8月3日
 */
public class PromotionAmqpQueueIndexFactory {

    private static final Logger logger = LoggerFactory.getLogger(PromotionAmqpQueueIndexFactory.class);

    public static final String NODE_SEPARATOR = "/";

    private static Integer queueIdx;

    private static int MAX_QUEUE_IDX = 32;

    private PromotionAmqpQueueIndexFactory(String amqpQueueIdxZooNodePath, String zookeeperUrl) {
        try {
            final CuratorFramework curator = ZooClient.getClient(zookeeperUrl);
            if (queueIdx == null) {
                if (curator.checkExists().forPath(amqpQueueIdxZooNodePath) == null) {
                    curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(amqpQueueIdxZooNodePath);
                }
                // 当前存活的节点
                List<String> aliveNodes = curator.getZookeeperClient().getZooKeeper().getChildren(amqpQueueIdxZooNodePath, false);
                if (CollectionUtils.isNotEmpty(aliveNodes)) {
                    for (int i = 1; i <= MAX_QUEUE_IDX; i++) {
                        if (!aliveNodes.contains(String.valueOf(i))) {
                            queueIdx = i;
                            break;
                        }
                    }
                } else {
                    queueIdx = 1;
                }
                SystemAsserts.notNull(queueIdx, "初始化soaIdx失败");
                // 创建临时节点
                curator.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                        .forPath(amqpQueueIdxZooNodePath + NODE_SEPARATOR + queueIdx);
            }
        } catch (Exception e) {
            logger.error("初始化soaIdx失败", e);
        }
    }

    public static PromotionAmqpQueueIndexFactory newInstance(String amqpQueueIdxZooNodePath, String zookeeperUrl) {
        return AmqpQueueIdxFactoryHolder.getInstance(amqpQueueIdxZooNodePath, zookeeperUrl);
    }

    /**
     * 获取当前进程amqpQueueIdx
     */
    public int getAmqpQueueIdx() {
        return queueIdx;
    }

    private static class AmqpQueueIdxFactoryHolder {
        static PromotionAmqpQueueIndexFactory instance;

        static PromotionAmqpQueueIndexFactory getInstance(String amqpQueueIdxZooNodePath, String zookeeperUrl) {
            if (instance == null) {
                instance = new PromotionAmqpQueueIndexFactory(amqpQueueIdxZooNodePath, zookeeperUrl);
            }
            return instance;
        }
    }

}
