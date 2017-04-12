package com.biz.core.zookeeper;

import com.biz.core.asserts.SystemAsserts;
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
public class SoaIndexFactory {

    private static final Logger logger = LoggerFactory.getLogger(SoaIndexFactory.class);

    private static Integer soaIdx = 1;

    private static int MAX_SOA_IDX = 32;

    private static final String NODE_SEPARATOR = "/";

    private SoaIndexFactory(String soaIdxZooNodePath, String zookeeperUrl) {
        try {
            final CuratorFramework curator = ZooClient.getClient(zookeeperUrl);
            if (soaIdx == null) {
                if (curator.checkExists().forPath(soaIdxZooNodePath) == null) {
                    curator.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(soaIdxZooNodePath);
                }
                // 当前存活的节点
                List<String> aliveNodes = curator.getZookeeperClient().getZooKeeper().getChildren(soaIdxZooNodePath, false);
                if (CollectionUtils.isNotEmpty(aliveNodes)) {
                    for (int i = 1; i <= MAX_SOA_IDX; i++) {
                        if (!aliveNodes.contains(String.valueOf(i))) {
                            soaIdx = i;
                            break;
                        }
                    }
                } else {
                    soaIdx = 1;
                }
                SystemAsserts.notNull(soaIdx, "初始化soaIdx失败");
                // 创建临时节点
                curator.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                        .forPath(soaIdxZooNodePath + NODE_SEPARATOR + soaIdx);
            }
        } catch (Exception e) {
            logger.error("初始化soaIdx失败", e);
        }
    }

    public static SoaIndexFactory newInstance(String soaIdxZooNodePath, String zookeeperUrl) {
        return SoaIdxFactoryHolder.getInstance(soaIdxZooNodePath, zookeeperUrl);
    }

    private static class SoaIdxFactoryHolder {
        static SoaIndexFactory instance;

        static SoaIndexFactory getInstance(String soaIdxZooNodePath, String zookeeperUrl) {
            if (instance == null) {
                instance = new SoaIndexFactory(soaIdxZooNodePath, zookeeperUrl);
            }
            return instance;
        }
    }

    /**
     * 获取当前进程soaIdx
     *
     * @author defei
     * @date 2015年11月18日
     */
    public int getSoaIdx() {
        return soaIdx;
    }

}
