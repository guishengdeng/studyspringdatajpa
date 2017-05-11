package com.biz.soa.stock.service;

import com.alibaba.fastjson.JSON;
import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.redis.repository.stock.*;
import com.biz.gbck.dao.redis.ro.stock.CityStockRo;
import com.biz.gbck.dao.redis.ro.stock.DepotStockRo;
import com.biz.gbck.dao.redis.ro.stock.ProvinceStockRo;
import com.biz.gbck.dao.redis.ro.stock.StockRo;
import com.biz.gbck.enums.oms.InvalidOmsDataType;
import com.biz.gbck.enums.oms.InvalidOmsMessageType;
import com.biz.gbck.enums.oss.OssType;
import com.biz.gbck.enums.product.GeoLevelEnum;
import com.biz.gbck.vo.depot.DepotResponseVo;
import com.biz.gbck.vo.oms.event.InvalidOmsMessageVo;
import com.biz.gbck.vo.search.bbc.ProductIdxIdentityVo;
import com.biz.gbck.vo.stock.MnsFullDepotStockVo;
import com.biz.gbck.vo.stock.MnsStockChangeVo;
import com.biz.gbck.vo.warehouse.WarehouseResponseVo;
import com.biz.message.MessageService;
import com.biz.message.SimpleBizMessage;
import com.biz.message.queue.BizBaseQueue;
import com.biz.service.AbstractBaseService;
import com.biz.service.depot.DepotService;
import com.biz.service.stock.backend.StockBackendService;
import com.biz.service.warehouse.frontend.WarehouseService;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.ValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.biz.gbck.enums.product.GeoLevelEnum.GEO_PROVINCE;
import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newHashSet;

/**
 * 库存业务
 *
 * @author lei
 * @date 2016年12月10日
 * @reviewer
 */
@Service
public class StockBackendServiceImpl extends AbstractBaseService implements StockBackendService {

    private static final int PIPELINE_COUNT = 1000;

    @Autowired
    protected OrderStockLockRedisDao orderStockLockRedisDao;

    @Autowired
    protected StockRedisDao stockRedisDao;

    @Autowired
    protected StockLockRedisDao stockLockRedisDao;

    @Autowired
    protected ProvinceStockRedisDao provinceStockRedisDao;

    @Autowired
    protected ProvinceStockLockRedisDao provinceStockLockRedisDao;

    @Autowired
    protected CityStockRedisDao cityStockRedisDao;

    @Autowired
    protected CityStockLockRedisDao cityStockLockRedisDao;

    @Autowired
    protected DepotStockRedisDao depotStockRedisDao;

    @Autowired
    protected StockChangeRedisDao stockChangeRedisDao;
    @Autowired
    protected DepotService depotService;
    @Autowired
    protected WarehouseService warehouseService;
    @Autowired
    private MessageService messageService;

    /******************************* Backend Service *******************************/

    /**
     * 将未通过校验的Vo发送指定队列
     */
    private void sendInvalidVoToMessage(InvalidOmsMessageVo vo) {
        messageService.sendMessage(BizBaseQueue.MQ_INVALID_OMS_MSG_QUEUE, SimpleBizMessage.newMessage(vo));
    }

    /**
     * 校验从中台过来的全量库存对象中的参数
     */
    private InvalidOmsMessageVo validMnsFullDepotStockVo(MnsFullDepotStockVo vo) {
        InvalidOmsMessageVo invalidOmsMessageVo = new InvalidOmsMessageVo();
        invalidOmsMessageVo.setType(InvalidOmsDataType.OMS_STOCK_ALL);
        List<String> messages = Lists.newArrayList();
        if (StringUtils.isBlank(vo.getMcuCode())) {
            logger.warn("中台全量库存门店编码为空");
            invalidOmsMessageVo.setValid(false);
            messages.add(InvalidOmsMessageType.STOCK_ALL_DEPOT_CODE_EMPTY.getDescription());
        }
        if (StringUtils.isBlank(vo.getProductCode())) {
            logger.warn("中台全量库存商品编码为空");
            invalidOmsMessageVo.setValid(false);
            messages.add(InvalidOmsMessageType.STOCK_ALL_PRODUCT_CODE_EMPTY.getDescription());
        }
        if (vo.getType() == null) {
            logger.warn("中台全量库存类型为空");
            invalidOmsMessageVo.setValid(false);
            messages.add(InvalidOmsMessageType.STOCK_ALL_TYPE_INVALID.getDescription());
        }
        if (CollectionUtils.isEmpty(messages)) {
            invalidOmsMessageVo.setValid(true);
            return invalidOmsMessageVo;
        }
        //将未通过校验的Vo序列化为json字符串
        invalidOmsMessageVo.setContent(JSON.toJSONString(vo));
        invalidOmsMessageVo.setMessages(messages);
        logger.debug("未通过校验Vo序列化为: {}", JSON.toJSONString(vo));
        return invalidOmsMessageVo;
    }

    /**
     * 批量校验中台全量库存
     */
    private List<MnsFullDepotStockVo> validStockVos(List<MnsFullDepotStockVo> mnsDepotStockAllVos) {
        //创建已经校验通过的库存Vo集合
        List<MnsFullDepotStockVo> validStockVos = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(mnsDepotStockAllVos)) {
            //首先校验中台全量库存Vo集合中每个对象的参数
            for (MnsFullDepotStockVo vo : mnsDepotStockAllVos) {
                InvalidOmsMessageVo messageVo = this.validMnsFullDepotStockVo(vo);
                if (!messageVo.isValid()) {
                    //将未通过校验的vo发送到指定的RabbitMQ队列里
                    logger.debug("发送未通过校验对象到队列");
                    this.sendInvalidVoToMessage(messageVo);
                    logger.debug("完成发送未通过校验对象到队列的操作");
                } else {
                    validStockVos.add(vo);
                }
            }
            return validStockVos;
        } else {
            logger.warn("中台全量库存Vo集合为空");
        }
        return Lists.newArrayList();
    }

    @Override
    public void saveDepotProductStocks(List<MnsFullDepotStockVo> mnsDepotStockAllVos) {
        if (logger.isDebugEnabled()) {
            logger.debug("开始保存全量库存(未校验)数据完成, Size: {}", mnsDepotStockAllVos.size());
        }
        List<DepotStockRo> depotStockRos = newArrayList();
        List<MnsFullDepotStockVo> validStockVos = this.validStockVos(mnsDepotStockAllVos);
        if (CollectionUtils.isNotEmpty(validStockVos)) {
            Long currentVersion = depotStockRedisDao.getLatestDepotStockVersion(mnsDepotStockAllVos.get(0).getType());
            Set<String> productCodes = newHashSet();
            for (MnsFullDepotStockVo vo : validStockVos) {
                //获取到当前全量库存的版本号
                DepotStockRo currentStockRo = new DepotStockRo(vo.getMcuCode(), vo.getProductCode(), vo.getStock(),
                        currentVersion, vo.getType(), vo.getTime());
                depotStockRos.add(currentStockRo);
                productCodes.add(vo.getProductCode());
            }
            depotStockRedisDao.save(depotStockRos);
            if (logger.isDebugEnabled()) {
                logger.debug("保存全量库存(有效)数据完成, Size: {}", depotStockRos.size());
            }
            stockRedisDao.saveTypeBProductCodes(productCodes); //保存B类商品编码
            if (logger.isDebugEnabled()) {
                logger.debug("保存B类商品编码完成, productCode: {}", productCodes);
            }
        } else {
            logger.warn("已经通过校验的中台库存Vos为空");
        }
    }

    @Override
    public void preHandleFullStocks(OssType type) {
        //设置自增的数字
        Long previousVersion = depotStockRedisDao.getLatestDepotStockVersion(type);
        Long latestVersion = ValueUtils.getValue(previousVersion) + 1;
        //根据中台全量库存的Vo类型来设置不同的版本号
        depotStockRedisDao.saveLatestDepotStockVersion(type, latestVersion);
        logger.info("库存版本更新, from[] => to[]", previousVersion, latestVersion);
    }

    @Override
    public void afterHandleFullStocks(OssType type) {
        this.stockStatTask();
        //构建全量索引
        logger.info("更新全量库存完成, 开始更新全量商品索引. 时间: {}", DateUtil.now());
        messageService.sendMessage(BizBaseQueue.MQ_SEARCH_PRODUCT_QUEUE, SimpleBizMessage.newMessage(new ProductIdxIdentityVo()));
    }

    public void stockStatTask() {
        try {
            logger.info("开始重置全量商品库存及省库存. 开始时间: {}", DateUtil.now());
            Stopwatch stopwatch = Stopwatch.createStarted();
            List<String> productCodes = stockRedisDao.getAllTypeBProductCodes();
            if (CollectionUtils.isNotEmpty(productCodes)) {
                logger.info("总共{}个B类商品编码", productCodes.size());
            } else {
                logger.warn("重置省库存, 全部商品数据为空, 结束统计!!");
                return;
            }

            Map<Integer, Set<String>> provinceIdToDepotCodesMap = this.mapGeoIdToDepotCodes(GEO_PROVINCE);
            if (MapUtils.isEmpty(provinceIdToDepotCodesMap)) {
                logger.warn("重置省库存, 全部门店省数据为空, 结束统计!");
                return;
            }
            Map<String, Integer> productCodeToQuantity = newHashMap(); //全量商品编码 to 库存数量
            int totalProducts = productCodes.size();

            Long latestDepotStockVersion = depotStockRedisDao.getLatestDepotStockVersion(OssType.STOCK);
            logger.info("统计省库存, 当前门店库存版本, version: {}", latestDepotStockVersion);
            for (Map.Entry<Integer, Set<String>> entry : provinceIdToDepotCodesMap.entrySet()) {
                Integer provinceId = entry.getKey();
                Set<String> depotCodes = entry.getValue();
                Map<String, Integer> productCodeToProvinceQuantity = newHashMap();
                int count = 0;
                logger.info("统计省库存[provinceId={}], 门店总数: {}, 商品总数: {}", provinceId, depotCodes.size(), totalProducts);
                for (String productCode : productCodes) {
                    if (StringUtils.isBlank(productCode)) {
                        logger.warn("商品编号为空. {}", productCode);
                        continue;
                    }
                    logger.debug("省库存统计: provinceId:{}, productCode:{}", provinceId, productCode);
                    //计算productCode商品在depotCodes门店(即该省)内所有库存
                    Integer quantity = this.calcTotalQuantityFor(productCode, depotCodes, latestDepotStockVersion);

                    //统计该商品当前省库存
                    Integer provinceQuantity = productCodeToProvinceQuantity.get(productCode);
                    provinceQuantity = ValueUtils.getValue(provinceQuantity) + quantity;
                    productCodeToProvinceQuantity.put(productCode, provinceQuantity);

                    //统计该商品总库存
                    Integer productQuantity = productCodeToQuantity.get(productCode);
                    productQuantity = ValueUtils.getValue(productQuantity) + quantity;
                    productCodeToQuantity.put(productCode, productQuantity);
                    logger.debug("省库存[provinceId={}]统计. 商品: {}. 完成: {}/{}", provinceId, productCode, ++count,
                            totalProducts);
                }
                this.saveProvinceStocks(provinceId, productCodeToProvinceQuantity);
            }
            this.saveProductStocks(productCodeToQuantity);
            logger.info("完成重置全量商品量及省库存. 结束时间: {}, 用时: {} ms", DateUtil.now(), stopwatch.elapsed(TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            logger.error("重置全量商品库存及省库存异常.", e);
        }
    }

    /**
     * 映geoId -> 门店编码集合
     */
    private Map<Integer, Set<String>> mapGeoIdToDepotCodes(GeoLevelEnum geoLevel) {
        Map<Integer, Set<String>> geoIdToDepotCodes = newHashMap();

        List<DepotResponseVo> depotVos = depotService.findAllDepots();
        if (CollectionUtils.isEmpty(depotVos)) {
            logger.warn("重置省库存, 全部门店省数据为空, 结束统计!");
            return geoIdToDepotCodes;
        }

        switch (geoLevel) {
            case GEO_PROVINCE:
                List<WarehouseResponseVo> warehouseVos = warehouseService.findAll();
                if (CollectionUtils.isEmpty(warehouseVos)) {
                    logger.warn("重置省库存, 省仓数据为空, 结束统计!!");
                }

                Map<String, Integer> warehouseCodeToProvinceId = newHashMap();
                for (WarehouseResponseVo warehouseVo : warehouseVos) {
                    warehouseCodeToProvinceId.put(warehouseVo.getWarehouseCode(), warehouseVo.getProvinceId());
                }

                for (DepotResponseVo depotVo : depotVos) {
                    String warehouseCode = depotVo.getWarehouseCode();
                    Integer provinceId = warehouseCodeToProvinceId.get(warehouseCode);
                    if (provinceId == null) {
                        continue;
                    }
                    Set<String> depotCodes = geoIdToDepotCodes.get(provinceId);
                    if (CollectionUtils.isNotEmpty(depotCodes)) {
                        depotCodes.add(depotVo.getDepotCode());
                    } else {
                        depotCodes = newHashSet(depotVo.getDepotCode());
                    }
                    geoIdToDepotCodes.put(provinceId, depotCodes);
                }
                break;
            case GEO_CITY:
                for (DepotResponseVo depotVo : depotVos) {
                    Integer cityId = depotVo.getCityId();
                    if (cityId == null) {
                        continue;
                    }
                    Set<String> depotCodes = geoIdToDepotCodes.get(cityId);
                    if (CollectionUtils.isNotEmpty(depotCodes)) {
                        depotCodes.add(depotVo.getDepotCode());
                    } else {
                        depotCodes = newHashSet(depotVo.getDepotCode());
                    }
                    geoIdToDepotCodes.put(cityId, depotCodes);
                }
                break;
            default:
                return geoIdToDepotCodes;
        }

        return geoIdToDepotCodes;
    }

    @Override
    public void updateStock(MnsStockChangeVo mnsStockChangeVo) {
        String depotCode = mnsStockChangeVo.getMcuCode();
        String productCode = mnsStockChangeVo.getProductCode();
        int changeStock = mnsStockChangeVo.getChangeStock();
        Timestamp changeTime = mnsStockChangeVo.getChangeTime();
        this.updateDepotStock(depotCode, productCode, changeStock, changeTime);
        DepotResponseVo depotResponseVo = depotService.findDepotByDepotCode(depotCode);
        if (depotResponseVo == null) {
            logger.warn("未查询到门店对应Geo信息");
            return;
        }
        Integer provinceId = depotResponseVo.getProvinceId();
        Integer cityId = depotResponseVo.getCityId();
        this.updateCityStock(cityId, productCode, changeStock, changeTime);
        this.updateProvinceStock(provinceId, productCode, changeStock, changeTime);
        this.updateStock(productCode, changeStock, changeTime);
    }

    private void updateDepotStock(String depotCode, String productCode, Integer changeStock, Timestamp changeTime) {
        logger.debug("开始增减门店库存[depotCode={}, productCode={}, changeStock={}]", depotCode, productCode, changeStock);
        WarehouseResponseVo warehouseVo = warehouseService.findByWarehouseCode(depotCode);
        OssType type = OssType.STOCK; //门店
        if (warehouseVo != null) {    //省仓
            type = OssType.WAREHOUSE_STOCK;
        }
        DepotStockRo depotStockRo = depotStockRedisDao.findByDepotCodeAndProductCode(depotCode, productCode);
        Long latestDepotStockVersion = depotStockRedisDao.getLatestDepotStockVersion(type);
        if (depotStockRo == null) {
            depotStockRo = new DepotStockRo(depotCode, productCode, ValueUtils.getValue(changeStock).longValue(),
                    latestDepotStockVersion, type);
        } else {
            if (depotStockRo.getUpdateTimestamp() == null || depotStockRo.getUpdateTimestamp().before(changeTime)) {
                depotStockRedisDao.updateQuantity(depotCode, productCode, ValueUtils.getValue(changeStock));
                depotStockRo = new DepotStockRo(depotCode, productCode, null, latestDepotStockVersion, type);
            }
        }
        depotStockRedisDao.save(depotStockRo);
    }

    /**
     * 更新商品总库存
     */
    private void updateStock(String productCode, int changeStock, Timestamp changeTime) {
        StockRo stockRo = new StockRo(productCode, null);
        logger.debug("更新商品库存, 商品编码: {}, 库存变动: {}, 时间: {}", productCode, changeStock, changeTime);
        stockRedisDao.save(stockRo);
        stockRedisDao.updateQuantity(productCode, changeStock);
    }

    private void updateProvinceStock(Integer provinceId, String productCode, int changeStock, Timestamp changeTime) {
        logger.debug("更新省库存, 省Id: {}, 商品编码: {}, 库存变动: {}, 时间: {}", provinceId, productCode, changeStock, changeTime);
        ProvinceStockRo provinceStockRo = new ProvinceStockRo(provinceId, productCode, null);
        provinceStockRedisDao.save(provinceStockRo);
        provinceStockRedisDao.updateQuantity(provinceId, productCode, changeStock);
    }

    private void updateCityStock(Integer cityId, String productCode, int changeStock, Timestamp changeTime) {
        logger.debug("更新城市库存, 城市Id: {}, 商品编码: {}, 库存变动: {}, 时间: {}", cityId, productCode, changeStock, changeTime);
        ProvinceStockRo provinceStockRo = new ProvinceStockRo(cityId, productCode, null);
        provinceStockRedisDao.save(provinceStockRo);
        provinceStockRedisDao.updateQuantity(cityId, productCode, changeStock);
    }

    private void saveProvinceStocks(Integer provinceId, Map<String, Integer> productCodeToProvinceQuantity) {
        if (MapUtils.isEmpty(productCodeToProvinceQuantity)) {
            return;
        }
        List<ProvinceStockRo> provinceStockRos = newArrayList();
        for (Map.Entry<String, Integer> entry : productCodeToProvinceQuantity.entrySet()) {
            ProvinceStockRo provinceStockRo = new ProvinceStockRo(provinceId, entry.getKey(), entry.getValue());
            provinceStockRos.add(provinceStockRo);
            if (provinceStockRos.size() == PIPELINE_COUNT) {
                provinceStockRedisDao.save(provinceStockRos);
                provinceStockRos.clear();
            }
        }
        provinceStockRedisDao.save(provinceStockRos);
    }

    private void saveProductStocks(Map<String, Integer> productCodeToQuantity) {
        if (MapUtils.isEmpty(productCodeToQuantity)) {
            return;
        }
        List<StockRo> stockRos = newArrayList();
        for (Map.Entry<String, Integer> entry : productCodeToQuantity.entrySet()) {
            StockRo stockRo = new StockRo(entry.getKey(), entry.getValue());
            stockRos.add(stockRo);
            if (stockRos.size() == PIPELINE_COUNT) {
                stockRedisDao.save(stockRos);
                stockRos.clear();
            }
        }
        stockRedisDao.save(stockRos);
    }

    private void saveCityStocks(Integer cityId, Map<String, Integer> productCodeToCityQuantity) {
        if (MapUtils.isEmpty(productCodeToCityQuantity)) {
            return;
        }
        List<CityStockRo> cityStockRos = newArrayList();
        for (Map.Entry<String, Integer> entry : productCodeToCityQuantity.entrySet()) {
            CityStockRo cityStockRo = new CityStockRo(cityId, entry.getKey(), entry.getValue());
            cityStockRos.add(cityStockRo);
            if (cityStockRos.size() == PIPELINE_COUNT) {
                cityStockRedisDao.save(cityStockRos);
                cityStockRos.clear();
            }
        }
        cityStockRedisDao.save(cityStockRos);
    }

    private int calcTotalQuantityFor(String productCode, Set<String> depotCodes, Long latestDepotStockVersion) {
        if (StringUtils.isBlank(productCode) || CollectionUtils.isEmpty(depotCodes)) {
            return 0;
        }
        List<String> keys = newArrayList();
        for (String depotCode : depotCodes) {
            keys.add(DepotStockRedisDao.getHashKey(depotCode, productCode));
        }
        return ValueUtils.getValue(depotStockRedisDao.sumQuantityByKeys(keys, latestDepotStockVersion));
    }

    @Override
    public void reduceLockStock(String orderCode, String depotCode, String productCode, int changeStock) {
        if (changeStock < 0 && orderStockLockRedisDao.existOrderStockLock(orderCode)) { //存在
            Map<String, Integer> orderStockLockKeyWithQuantities = orderStockLockRedisDao
                    .getOrderStockLockKeyWithQuantities(orderCode);
            Map<String, Integer> keyToReleaseQuantity = newHashMap();
            for (Map.Entry<String, Integer> entry : orderStockLockKeyWithQuantities.entrySet()) {
                if (StringUtils.contains(entry.getKey(), productCode)) {
                    keyToReleaseQuantity.put(entry.getKey(), Math.abs(changeStock) > entry.getValue() ? changeStock :
                            -entry.getValue());
                }
            }
            orderStockLockRedisDao.updateQuantities(keyToReleaseQuantity);
            orderStockLockRedisDao.removeExpiredOrderStockLockKeys(orderCode, keyToReleaseQuantity.keySet());
        }
    }

    @Override
    public void releaseLockStocks() {
        logger.info("开始释放订单锁定库存. 当前时间: {}", DateUtil.now());
        Stopwatch stopwatch = Stopwatch.createStarted();
        Set<String> expiredLockStockOrderCodes = orderStockLockRedisDao.getExpiredLockStockOrderCodes(DateUtil.now());
        if (CollectionUtils.isEmpty(expiredLockStockOrderCodes)) {
            logger.warn("未查询到无过期锁定库存订单!");
        }
        int total = expiredLockStockOrderCodes.size();
        logger.debug("锁定库存过期订单总数: {}", total);
        int count = 0;
        Map<String, Integer> keyToReleaseQuantity = newHashMap();
        for (String expiredLockStockOrderCode : expiredLockStockOrderCodes) {
            Map<String, Integer> orderStockLockKeyWithQuantities = orderStockLockRedisDao
                    .getOrderStockLockKeyWithQuantities(expiredLockStockOrderCode);
            for (Map.Entry<String, Integer> entry : orderStockLockKeyWithQuantities.entrySet()) {
                keyToReleaseQuantity.put(entry.getKey(), -entry.getValue());
            }
            orderStockLockRedisDao.updateQuantities(keyToReleaseQuantity);
            orderStockLockRedisDao.removeExpiredOrderStockLockKeys(expiredLockStockOrderCode, keyToReleaseQuantity
                    .keySet());
            orderStockLockRedisDao.removedExpiredLockStockOrder(expiredLockStockOrderCode);
            logger.debug("释放订单[{}]锁定库存. 进度: {}/{}", expiredLockStockOrderCode, ++count, total);
        }
        logger.info("完成释放订单锁定库存. 用时: {} ms, 当前时间: {}", stopwatch.elapsed(TimeUnit.MILLISECONDS), DateUtil.now());
    }

    @Override
    public boolean existChangeStock(Long changeStockId) {
        return changeStockId != null && stockChangeRedisDao.exists(changeStockId);
    }

    @Override
    public void saveChangeStock(MnsStockChangeVo mnsStockChangeVo) {
        if (mnsStockChangeVo != null) {
            stockChangeRedisDao.saveChange(mnsStockChangeVo.getId(), mnsStockChangeVo.getBill());
        }
    }

    /******************************* Backend Service *******************************/
}
