package com.depotnextdoor.core.zookeeper;

import com.depotnextdoor.core.exceptions.SystemException;
import com.google.common.collect.Maps;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 迁移
 * @author yanweijin
 * @since 2016年8月3日
 * @usage
 * @reviewer
 */
public class ConfigUtils {

    private final static Map<String, PropertiesConfiguration> propertyConfigMap = Maps.newHashMap();

    private ConfigUtils() {

    }

    private static PropertiesConfiguration getConfig(String propertyFileName) {

        PropertiesConfiguration config = propertyConfigMap.get(propertyFileName);
        if (config == null) {
            try {
                config = new PropertiesConfiguration(propertyFileName);
                logger.debug("加载配置文件成功");
            } catch (ConfigurationException e) {
                throw new RuntimeException("加载配置" + propertyFileName + "出错", e);
            }

            propertyConfigMap.put(propertyFileName, config);
        }

        return config;
    }

    public static String getString(String propertyFileName, String propertyName) {
        return getConfig(propertyFileName).getString(propertyName);
    }

    /**
     * 判断是否由zookeeper配置
     */
    public static boolean isConfiguredByZoo() {
        return getZookeeperUrl() != null;
    }

    /**
     * 从启动参数获取zookeeper url
     */
    public static String getZookeeperUrl() {
        return System.getProperty("zookeeper.url");
    }

    /**
     * 从classpath或zookeeper加载配置文件
     */
    public static Properties loadFromFileOrZooKeeper(String zooPath, String classPath) {

        Properties conf = new Properties();
        InputStream inputStream = null;
        try {
            if (ConfigUtils.isConfiguredByZoo() && StringUtils.isNotBlank(zooPath)) {
                ZkProperties zkProperties = new ZkProperties(zooPath);
                inputStream = new ByteArrayInputStream(zkProperties.getPropertiesBytes());
            } else {
                inputStream = new FileInputStream(ConfigUtils.class.getResource("/").getPath() + classPath);
            }

            conf.load(new InputStreamReader(inputStream, "UTF-8"));
            return conf;
        } catch (Exception e) {
            throw new SystemException("载配置失败", e);
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(ConfigUtils.class);

}
