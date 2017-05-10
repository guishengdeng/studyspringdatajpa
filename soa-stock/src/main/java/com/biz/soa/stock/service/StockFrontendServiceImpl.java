package com.biz.soa.stock.service;

import com.biz.gbck.dao.redis.repository.stock.*;
import com.biz.gbck.dao.redis.ro.stock.*;
import com.biz.gbck.enums.product.GeoLevelEnum;
import com.biz.gbck.exceptions.DepotNextDoorException;
import com.biz.gbck.exceptions.product.IllegalParameterException;
import com.biz.gbck.vo.common.response.CityResponseVo;
import com.biz.gbck.vo.depot.DepotResponseVo;
import com.biz.gbck.vo.stock.*;
import com.biz.gbck.vo.warehouse.WarehouseResponseVo;
import com.biz.service.AbstractBaseService;
import com.biz.service.depot.DepotService;
import com.biz.service.geo.interfaces.GeoService;
import com.biz.service.stock.frontend.StockService;
import com.biz.service.warehouse.frontend.WarehouseService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.ValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static com.google.common.collect.Sets.newLinkedHashSet;

/**
 * 库存业务
 *
 * @author lei
 * @date 2016年12月10日
 * @reviewer
 */
@Service
public class StockFrontendServiceImpl extends AbstractBaseService implements StockService {

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
    protected DepotStockLockRedisDao depotStockLockRedisDao;

    @Autowired
    protected GeoService geoService;

    @Autowired
    protected DepotService depotService;

    @Autowired
    protected WarehouseService warehouseService;

    /******************************* Frontend Service *******************************/

    /**
     * 获取省市可用库存
     */
    private Integer getGeoAvailableStock(Integer geoId, String productCode, GeoLevelEnum geoLevel) {
        Integer geoQuantity = 0;
        Integer geoLockQuantity = 0;
        switch (geoLevel) {
            case GEO_PROVINCE:
                geoQuantity = provinceStockRedisDao.getQuantityByProvinceIdAndProductCode(geoId, productCode);
                geoLockQuantity = provinceStockLockRedisDao.getQuantityByProvinceIdAndProductCode(geoId, productCode);
                break;
            case GEO_CITY:
                geoQuantity = cityStockRedisDao.getQuantityByCityIdAndProductCode(geoId, productCode);
                geoLockQuantity = cityStockLockRedisDao.getQuantityByCityIdAndProductCode(geoId, productCode);
                break;
            default:
                break;
        }
        return this.calcAvailableStock(geoQuantity, geoLockQuantity);
    }

    /**
     * 批量获取省市可用库存(全省可用库存+省仓可用库存)
     */
    private Map<String, Integer> getAvailableGeoStocks(Set<String> productCodes, Integer geoId, GeoLevelEnum geoLevel) {
        Map<String, Integer> productCodeToAvailableGeoStock = newHashMap();
        switch (geoLevel) {
            case GEO_PROVINCE:
                List<ProvinceStockRo> provinceStockRos = provinceStockRedisDao.getByProvinceIdAndProductCodes(geoId,
                        productCodes);
                List<ProvinceStockLockRo> provinceLockStockRos = provinceStockLockRedisDao
                        .getByProvinceIdAndProductCodes(geoId, productCodes);

                //开始获取对应省库存
                Map<String, Integer> availableWarehouseStocks = newHashMap(); //省仓可用库存
                WarehouseResponseVo warehouseResponseVo = warehouseService.findByProvinceId(geoId); //获取省仓编码
                if (warehouseResponseVo != null && StringUtils.isNotBlank(warehouseResponseVo.getWarehouseCode())) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("根据省Id获取到省仓信息[name={}, code={}]", warehouseResponseVo.getName(),
                                warehouseResponseVo.getWarehouseCode());
                    }
                    String warehouseCode = warehouseResponseVo.getWarehouseCode();
                    availableWarehouseStocks = this.getAvailableDepotStocks(productCodes, warehouseCode);
                }

                for (ProvinceStockRo provinceStockRo : provinceStockRos) {
                    String productCode = provinceStockRo.getProductCode();
                    Integer availableQuantity = provinceStockRo.getQuantity();

                    //省库存
                    for (ProvinceStockLockRo provinceLockStockRo : provinceLockStockRos) {
                        if (Objects.equals(provinceStockRo.getProductCode(), provinceLockStockRo.getProductCode())) {
                            availableQuantity = this.calcAvailableStock(availableQuantity, provinceLockStockRo
                                    .getQuantity());
                        }
                    }
                    //省仓库存
                    availableQuantity += ValueUtils.getValue(availableWarehouseStocks.get(productCode));
                    productCodeToAvailableGeoStock.put(productCode, availableQuantity);
                }
                break;
            case GEO_CITY:
                List<CityStockRo> cityStockRos = cityStockRedisDao.getByCityIdAndProductCodes(geoId, productCodes);
                List<CityStockLockRo> cityLockStockRos = cityStockLockRedisDao.getByCityIdAndProductCodes(geoId,
                        productCodes);
                for (CityStockRo cityStockRo : cityStockRos) {
                    String productCode = cityStockRo.getProductCode();
                    Integer availableQuantity = cityStockRo.getQuantity();
                    for (CityStockLockRo cityStockLockRo : cityLockStockRos) {
                        if (Objects.equals(cityStockRo.getProductCode(), cityStockLockRo.getProductCode())) {
                            availableQuantity = this.calcAvailableStock(availableQuantity, cityStockLockRo
                                    .getQuantity());
                        }
                    }
                    productCodeToAvailableGeoStock.put(productCode, availableQuantity);
                }
                break;
            default:
                break;
        }
        return productCodeToAvailableGeoStock;
    }

    /**
     * 获取门店可用库存
     */
    private Integer getAvailableDepotStock(String depotCode, String productCode) {
        Integer depotQuantity = depotStockRedisDao.getQuantityByDepotCodeAndProductCode(depotCode, productCode);
        Integer depotLockQuantity = depotStockLockRedisDao.getQuantityByDepotCodeAndProductCode(depotCode, productCode);
        return this.calcAvailableStock(depotQuantity, depotLockQuantity);
    }

    /**
     * 批量获取门店可用库存
     */
    private Map<String, Integer> getAvailableDepotStocks(Set<String> productCodes, String depotCode) {
        Map<String, Integer> productCodeToAvailableDepotStock = newHashMap();
        List<DepotStockRo> depotStockRos = depotStockRedisDao.getByDepotCodeAndProductCodes(depotCode, productCodes);
        List<DepotStockLockRo> depotStockLockRos = depotStockLockRedisDao.getByDepotCodeAndProductCodes(depotCode,
                productCodes);
        for (DepotStockRo depotStockRo : depotStockRos) {
            String productCode = depotStockRo.getProductCode();
            Integer availableQuantity = depotStockRo.getQuantity();
            for (DepotStockLockRo depotStockLockRo : depotStockLockRos) {
                if (Objects.equals(productCode, depotStockLockRo.getProductCode())) {
                    availableQuantity = this.calcAvailableStock(availableQuantity, depotStockLockRo.getQuantity());
                }
            }
            productCodeToAvailableDepotStock.put(productCode, availableQuantity);
        }
        return productCodeToAvailableDepotStock;
    }

    /**
     * 获取全国可用库存
     */
    private Integer getAvailableStock(String productCode) {
        Integer quantity = stockRedisDao.getQuantityByProductCode(productCode);
        Integer lockQuantity = stockLockRedisDao.findQuantityByProductCode(productCode);
        return calcAvailableStock(quantity, lockQuantity);
    }

    /**
     * 批量获取全国可用库存
     */
    private Map<String, Integer> getAvailableStocks(Set<String> productCodes) {
        Map<String, Integer> productCodeToAvailableStock = newHashMap();
        List<StockRo> stockRos = stockRedisDao.findByProductCodes(productCodes);
        List<StockLockRo> stockLockRos = stockLockRedisDao.findByProductCodes(productCodes);
        for (StockRo stockRo : stockRos) {
            String productCode = stockRo.getProductCode();
            Integer availableQuantity = stockRo.getQuantity();
            for (StockLockRo stockLockRo : stockLockRos) {
                if (Objects.equals(productCode, stockLockRo.getProductCode())) {
                    availableQuantity = this.calcAvailableStock(availableQuantity, stockLockRo.getQuantity());
                }
            }
            productCodeToAvailableStock.put(productCode, availableQuantity);
        }
        return productCodeToAvailableStock;
    }

    /**
     * 根据库存和锁定库存计算可用库存
     */
    private Integer calcAvailableStock(Integer quantity, Integer lockQuantity) {
        lockQuantity = ValueUtils.getValue(lockQuantity) <= 0 ? 0 : lockQuantity;
        Integer availableQuantity = ValueUtils.getValue(quantity) - lockQuantity;
        return availableQuantity > 0 ? availableQuantity : 0;
    }


    /********************************************
     * 对外公共接口
     ***********************************************************************/
    @Override
    public StockResponseVo getStock(StockRequestVo reqVo) throws DepotNextDoorException {
        if (logger.isDebugEnabled()) {
            logger.debug("库存查询-------请求vo: {}", reqVo);
        }
        StockResponseVo resVo = new StockResponseVo();
        if (reqVo == null || StringUtils.isBlank(reqVo.getProductCode())) {
            logger.warn("库存查询-------参数不合法: {}", reqVo);
            throw new IllegalParameterException("参数不合法");
        }
        resVo.setProductCode(reqVo.getProductCode());
        resVo.setDepotCode(reqVo.getDepotCode());

        /**
         * 全国
         */
        if (reqVo.getDepotCode() == null) { //全国
            if (logger.isDebugEnabled()) {
                logger.debug("库存查询-------范围: 总库存. {}", reqVo);
            }
            Integer availableQuantity = this.getAvailableStock(reqVo.getProductCode());
            resVo.setQuantity(availableQuantity);
        } else {  //门店or全省
            boolean isGetDepotStock = !reqVo.getIsProvince();
            if (!isGetDepotStock) { //全省
                if (logger.isDebugEnabled()) {
                    logger.debug("库存查询-------范围: 全省. {}", reqVo);
                }
                DepotResponseVo depotResponseVo = depotService.findDepotByDepotCode(reqVo.getDepotCode());
                if (depotResponseVo != null && depotResponseVo.getProvinceId() != null) {
                    Integer warehouseAvailableStock = this.getAvailableDepotStock(depotResponseVo.getWarehouseCode(),
                            reqVo.getProductCode());
                    Integer provinceAvailableStock = this.getGeoAvailableStock(depotResponseVo.getProvinceId(), reqVo
                            .getProductCode(), GeoLevelEnum.GEO_PROVINCE);
                    resVo.setQuantity(warehouseAvailableStock + provinceAvailableStock);
                } else {
                    isGetDepotStock = true;
                }
            }
            if (isGetDepotStock) { //门店
                if (logger.isDebugEnabled()) {
                    logger.debug("库存查询-------范围: 门店. {}", reqVo);
                }
                Integer availableDepotStock = this.getAvailableDepotStock(reqVo.getDepotCode(), reqVo.getProductCode());
                resVo.setQuantity(availableDepotStock);
            }
        }
        logger.info("库存查询-------请求: {}, 返回值: {}", reqVo, resVo);
        return resVo;
    }

    @Override
    public List<StockResponseVo> getStocks(BatchStocksRequestVo batchStocksRequestVo) {
        if (logger.isDebugEnabled()) {
            logger.debug("批量库存查询-------请求vo: {}", batchStocksRequestVo);
        }
        List<StockResponseVo> stockResponseVos = newArrayList();
        if (batchStocksRequestVo == null || CollectionUtils.isEmpty(batchStocksRequestVo.getProductCodes())) {
            logger.warn("批量库存查询-------参数不合法: {}", batchStocksRequestVo);
            return stockResponseVos;
        }

        Set<String> productCodes = newLinkedHashSet(batchStocksRequestVo.getProductCodes());
        String depotCode = batchStocksRequestVo.getDepotCode();
        if (depotCode == null) { //全国
            if (logger.isDebugEnabled()) {
                logger.debug("批量库存查询-------范围: 总库存. {}", batchStocksRequestVo);
            }
            Map<String, Integer> productCodeToAvailableQuantity = this.getAvailableStocks(productCodes);
            for (String productCode : productCodes) {
                Integer availableQuantity = ValueUtils.getValue(productCodeToAvailableQuantity.get(productCode));
                stockResponseVos.add(new StockResponseVo(productCode, availableQuantity));
            }
        } else { //门店or全省
            boolean isGetDepotStock = !batchStocksRequestVo.getIsProvince();
            if (!isGetDepotStock) { //全省
                if (logger.isDebugEnabled()) {
                    logger.debug("批量库存查询-------范围: 全省. {}", batchStocksRequestVo);
                }
                DepotResponseVo depotResponseVo = depotService.findDepotByDepotCode(depotCode);
                if (depotResponseVo != null && depotResponseVo.getProvinceId() != null) {
                    Integer provinceId = depotResponseVo.getProvinceId();
                    if (logger.isDebugEnabled()) {
                        logger.debug("批量库存查询-------省Id. {}", provinceId);
                    }
                    Map<String, Integer> productCodeToAvailableProvinceStock = this.getAvailableGeoStocks
                            (productCodes, provinceId, GeoLevelEnum.GEO_PROVINCE);
                    for (String productCode : batchStocksRequestVo.getProductCodes()) {
                        Integer availableQuantity = ValueUtils.getValue(productCodeToAvailableProvinceStock.get
                                (productCode));
                        stockResponseVos.add(new StockResponseVo(productCode, depotCode, availableQuantity));
                    }
                } else {
                    isGetDepotStock = true;
                }
            }
            if (isGetDepotStock) { //门店
                if (logger.isDebugEnabled()) {
                    logger.debug("批量库存查询-------范围: 门店. {}", batchStocksRequestVo);
                }
                Map<String, Integer> productCodeToAvailableDepotStock = this.getAvailableDepotStocks(productCodes,
                        depotCode);
                for (String productCode : batchStocksRequestVo.getProductCodes()) {
                    Integer availableQuantity = ValueUtils.getValue(productCodeToAvailableDepotStock.get(productCode));
                    stockResponseVos.add(new StockResponseVo(productCode, depotCode, availableQuantity));
                }
            }
        }
        logger.info("批量库存查询-------请求: {}, 返回值: {}", batchStocksRequestVo, stockResponseVos);
        return stockResponseVos;
    }

    @Override
    public List<GeoStockResponseVo> getGeoStocks(BatchGeoStocksRequestVo batchGeoStocksRequestVo) {
        if (logger.isDebugEnabled()) {
            logger.debug("Geo库存查询-------请求vo: {}", batchGeoStocksRequestVo);
        }
        List<GeoStockResponseVo> geoStockResponseVos = newArrayList();
        if (batchGeoStocksRequestVo == null || batchGeoStocksRequestVo.getGeoId() == null || CollectionUtils.isEmpty
                (batchGeoStocksRequestVo.getProductCodes())) {
            logger.warn("Geo库存查询-------参数不合法: {}", batchGeoStocksRequestVo);
            return geoStockResponseVos;
        }

        Integer geoId = batchGeoStocksRequestVo.getGeoId();
        if (batchGeoStocksRequestVo.getGeoLevel() == GeoLevelEnum.GEO_CITY) {
            geoId = this.getProvinceIdByCityId(geoId);
        }

        Map<String, Integer> productCodeToAvailableProvinceStock = this.getAvailableGeoStocks(newLinkedHashSet
                (batchGeoStocksRequestVo.getProductCodes()), geoId, batchGeoStocksRequestVo.getGeoLevel());
        for (String productCode : batchGeoStocksRequestVo.getProductCodes()) {
            Integer availableQuantity = ValueUtils.getValue(productCodeToAvailableProvinceStock.get(productCode));
            geoStockResponseVos.add(new GeoStockResponseVo(productCode, batchGeoStocksRequestVo.getGeoId(),
                    availableQuantity));
        }
        logger.info("Geo库存查询-------请求: {}, 返回值: {}", batchGeoStocksRequestVo, geoStockResponseVos);
        return geoStockResponseVos;
    }

    @Override
    public void updateDepotLockStock(List<DepotLockStockRequestVo> depotLockStockRequestVos) {
        if (logger.isDebugEnabled()) {
            logger.debug("门店Lock库存更新-------请求vo: {}", depotLockStockRequestVos);
        }
        if (CollectionUtils.isEmpty(depotLockStockRequestVos)) {
            logger.warn("门店Lock库存更新-------参数不合法: {}", depotLockStockRequestVos);
            return;
        }
        for (DepotLockStockRequestVo depotLockStockRequestVo : depotLockStockRequestVos) {
            if (depotLockStockRequestVo == null || StringUtils.isBlank(depotLockStockRequestVo.getOrderCode()) ||
                    StringUtils.isBlank(depotLockStockRequestVo.getDepotCode()) || CollectionUtils.isEmpty
                    (depotLockStockRequestVo.getItemVos())) {
                logger.warn("门店锁定库存参数无效!");
                return;
            }
            String depotCode = depotLockStockRequestVo.getDepotCode();
            Map<String, Integer> keyToLockQuantity = newHashMap();
            List<StockLockRo> stockLockRos = newArrayList();
            List<DepotStockLockRo> depotStockLockRos = newArrayList();
            List<CityStockLockRo> cityStockLockRos = newArrayList();
            List<ProvinceStockLockRo> provinceStockLockRos = newArrayList();
            for (LockStockItemRequestVo vo : depotLockStockRequestVo.getItemVos()) {
                if (vo != null && StringUtils.isNotBlank(vo.getProductCode())) {
                    StockLockRo stockLockRo = new StockLockRo(vo.getProductCode(), null);
                    stockLockRos.add(stockLockRo);
                    keyToLockQuantity.put(StockLockRedisDao.getHashKey(vo.getProductCode()), ValueUtils.getValue(vo
                            .getQuantity()));

                    DepotStockLockRo depotStockLockRo = new DepotStockLockRo(depotCode, vo.getProductCode(), null);
                    depotStockLockRos.add(depotStockLockRo);
                    keyToLockQuantity.put(DepotStockLockRedisDao.getHashKey(depotCode, vo.getProductCode()),
                            ValueUtils.getValue(vo.getQuantity()));
                    DepotResponseVo depotResponseVo = depotService.findDepotByDepotCode(depotCode);
                    if (depotResponseVo != null) {
                        if (depotResponseVo.getCityId() != null) {
                            CityStockLockRo cityStockLockRo = new CityStockLockRo(depotResponseVo.getCityId(), vo
                                    .getProductCode(), null);
                            cityStockLockRos.add(cityStockLockRo);
                            keyToLockQuantity.put(CityStockLockRedisDao.getHashKey(depotResponseVo.getCityId(), vo
                                    .getProductCode()), ValueUtils.getValue(vo.getQuantity()));
                        }
                        if (depotResponseVo.getProvinceId() != null) {
                            ProvinceStockLockRo provinceStockLockRo = new ProvinceStockLockRo(depotResponseVo
                                    .getProvinceId(), vo.getProductCode(), null);
                            provinceStockLockRos.add(provinceStockLockRo);
                            keyToLockQuantity.put(ProvinceStockLockRedisDao.getHashKey(depotResponseVo.getProvinceId
                                    (), vo.getProductCode()), ValueUtils.getValue(vo.getQuantity()));
                        }
                    }
                } else {
                    logger.warn("门店Lock库存更新-------item参数不合法: {}", vo);
                }
            }
            logger.info("门店Lock库存更新-------[key-quantity]: {}", keyToLockQuantity);
            stockRedisDao.updateQuantities(keyToLockQuantity);
            stockLockRedisDao.save(stockLockRos);
            depotStockLockRedisDao.save(depotStockLockRos);
            cityStockLockRedisDao.save(cityStockLockRos);
            provinceStockLockRedisDao.save(provinceStockLockRos);
            orderStockLockRedisDao.recordOrderLockStock(depotLockStockRequestVo.getOrderCode(), keyToLockQuantity,
                    depotLockStockRequestVo.getAliveTime());
        }

    }

    @Override
    public void updateGeoLockStock(List<UpdateGeoLockStockRequestVo> updateGeoLockStockRequestVos) {
        if (logger.isDebugEnabled()) {
            logger.debug("Geo Lock库存更新-------请求vo: {}", updateGeoLockStockRequestVos);
        }
        if (CollectionUtils.isEmpty(updateGeoLockStockRequestVos)) {
            logger.warn("Geo Lock库存更新-------参数不合法: {}", updateGeoLockStockRequestVos);
            return;
        }
        for (UpdateGeoLockStockRequestVo reqVo : updateGeoLockStockRequestVos) {
            if (reqVo == null || StringUtils.isBlank(reqVo.getOrderCode()) || CollectionUtils.isEmpty(reqVo
                    .getItemVos())) {
                logger.warn("Geo Lock库存更新-------参数不合法: {}", reqVo);
                continue;
            }
            Integer geoId = reqVo.getGeoId();
            GeoLevelEnum geoLevel = GeoLevelEnum.GEO_PROVINCE;
            if (geoId == null) {
                logger.warn("Geo Lock库存更新-------geoId为空: {}", reqVo);
            }

            Map<String, Integer> keyToLockQuantity = newHashMap();
            List<StockLockRo> stockLockRos = newArrayList();
            List<CityStockLockRo> cityStockLockRos = newArrayList();
            List<ProvinceStockLockRo> provinceStockLockRos = newArrayList();

            for (LockStockItemRequestVo vo : reqVo.getItemVos()) {
                if (vo != null && StringUtils.isNotBlank(vo.getProductCode())) {
                    StockLockRo stockLockRo = new StockLockRo(vo.getProductCode(), null);
                    stockLockRos.add(stockLockRo);
                    keyToLockQuantity.put(StockLockRedisDao.getHashKey(vo.getProductCode()), ValueUtils.getValue(vo
                            .getQuantity()));
                    Integer provinceId;
                    if (geoLevel == GeoLevelEnum.GEO_CITY) {
                        CityStockLockRo cityStockLockRo = new CityStockLockRo(geoId, vo.getProductCode(), null);
                        cityStockLockRos.add(cityStockLockRo);
                        keyToLockQuantity.put(CityStockLockRedisDao.getHashKey(geoId, vo.getProductCode()), vo
                                .getQuantity());
                        provinceId = this.getProvinceIdByCityId(geoId);
                    } else {
                        provinceId = geoId;
                    }
                    if (provinceId != null) {
                        ProvinceStockLockRo provinceStockLockRo = new ProvinceStockLockRo(geoId, vo.getProductCode(),
                                null);
                        provinceStockLockRos.add(provinceStockLockRo);
                        keyToLockQuantity.put(ProvinceStockLockRedisDao.getHashKey(geoId, vo.getProductCode()), vo
                                .getQuantity());
                    }
                } else {
                    logger.warn("Geo Lock库存更新-------item参数不合法: {}", vo);
                }
            }
            logger.info("Geo Lock库存更新-------[key-quantity]: {}", keyToLockQuantity);
            stockRedisDao.updateQuantities(keyToLockQuantity);
            stockLockRedisDao.save(stockLockRos);
            cityStockLockRedisDao.save(cityStockLockRos);
            provinceStockLockRedisDao.save(provinceStockLockRos);
            orderStockLockRedisDao.recordOrderLockStock(reqVo.getOrderCode(), keyToLockQuantity, reqVo
                    .getAliveTime());
        }
    }

    @Override
    public void updateLockStocks(List<UpdateLockStockRequestVo> updateLockStockRequestVos) {
        if (logger.isDebugEnabled()) {
            logger.debug("Lock库存更新-------请求vo: {}", updateLockStockRequestVos);
        }
        if (CollectionUtils.isEmpty(updateLockStockRequestVos)) {
            logger.warn("Lock库存更新-------参数不合法: {}", updateLockStockRequestVos);
            return;
        }
        for (UpdateLockStockRequestVo updateLockStockRequestVo : updateLockStockRequestVos) {
            if (updateLockStockRequestVo == null || StringUtils.isBlank(updateLockStockRequestVo.getOrderCode()) ||
                    CollectionUtils.isEmpty(updateLockStockRequestVo.getItemVos())) {
                logger.warn("Lock库存更新-------参数不合法: {}", updateLockStockRequestVo);
                continue;
            }
            String orderCode = updateLockStockRequestVo.getOrderCode();
            Map<String, Integer> keyToLockQuantity = newHashMap();
            List<StockLockRo> stockLockRos = newArrayList();
            for (LockStockItemRequestVo vo : updateLockStockRequestVo.getItemVos()) {
                if (vo != null && StringUtils.isNotBlank(vo.getProductCode())) {
                    StockLockRo stockLockRo = new StockLockRo(vo.getProductCode(), null);
                    stockLockRos.add(stockLockRo);
                    keyToLockQuantity.put(StockLockRedisDao.getHashKey(vo.getProductCode()), ValueUtils.getValue(vo
                            .getQuantity()));
                } else {
                    logger.warn("Lock库存更新-------商品编码不合法: {}", vo);
                }
            }
            logger.info("Lock库存更新-------[key-quantity]: {}", keyToLockQuantity);
            stockRedisDao.updateQuantities(keyToLockQuantity);
            stockLockRedisDao.save(stockLockRos);
            orderStockLockRedisDao.recordOrderLockStock(orderCode, keyToLockQuantity, updateLockStockRequestVo
                    .getAliveTime());
        }
    }

    @Override
    public void updateDepotStocks(List<UpdateDepotStockRequestVo> updateDepotStockRequestVos) {
        if (logger.isDebugEnabled()) {
            logger.debug("门店库存更新-------请求vo: {}", updateDepotStockRequestVos);
        }
        if (CollectionUtils.isEmpty(updateDepotStockRequestVos)) {
            logger.warn("门店库存更新-------参数不合法: {}", updateDepotStockRequestVos);
            return;
        }
        Map<String, Integer> keyToQuantity = newHashMap();
        List<StockRo> stockRos = newArrayList();
        List<DepotStockRo> depotStockRos = newArrayList();
        List<CityStockRo> cityStockRos = newArrayList();
        List<ProvinceStockRo> provinceStockRos = newArrayList();
        for (UpdateDepotStockRequestVo vo : updateDepotStockRequestVos) {
            if (vo == null || StringUtils.isBlank(vo.getProductCode())) {
                logger.warn("门店库存更新-------商品参数不合法: {}", vo);
                continue;
            }
            //全国
            StockRo stockRo = new StockRo(vo.getProductCode(), null);
            stockRos.add(stockRo);
            keyToQuantity.put(StockRedisDao.getHashKey(vo.getProductCode()), ValueUtils.getValue(vo.getQuantity()));

            //门店
            if (StringUtils.isNotBlank(vo.getDepotCode())) {
                DepotStockRo depotStockRo = new DepotStockRo(vo.getDepotCode(), vo.getProductCode(), null, null, null);
                depotStockRos.add(depotStockRo);
                keyToQuantity.put(DepotStockRedisDao.getHashKey(vo.getDepotCode(), vo.getProductCode()), ValueUtils
                        .getValue(vo.getQuantity()));
            }

            //省市
            DepotResponseVo depotResponseVo = depotService.findDepotByDepotCode(vo.getDepotCode());
            if (depotResponseVo != null) {
                if (depotResponseVo.getCityId() != null) {
                    CityStockRo cityStockRo = new CityStockRo(depotResponseVo.getCityId(), vo.getProductCode(), null);
                    cityStockRos.add(cityStockRo);
                    keyToQuantity.put(CityStockRedisDao.getHashKey(depotResponseVo.getCityId(), vo.getProductCode()),
                            ValueUtils.getValue(vo.getQuantity()));
                }
            }
            if (depotResponseVo.getProvinceId() != null) {
                ProvinceStockRo provinceStockRo = new ProvinceStockRo(depotResponseVo.getProvinceId(), vo
                        .getProductCode(), null);
                provinceStockRos.add(provinceStockRo);
                keyToQuantity.put(ProvinceStockRedisDao.getHashKey(depotResponseVo.getProvinceId(), vo.getProductCode
                        ()), ValueUtils.getValue(vo.getQuantity()));
            }
        }
        logger.info("门店库存更新-------[key-quantity]: {}", keyToQuantity);
        stockRedisDao.updateQuantities(keyToQuantity);
        stockRedisDao.save(stockRos);
        depotStockRedisDao.save(depotStockRos);
        cityStockRedisDao.save(cityStockRos);
        provinceStockRedisDao.save(provinceStockRos);
    }

    @Override
    public void updateGeoStocks(List<UpdateGeoStockRequestVo> updateGeoStockRequestVos) {
        if (logger.isDebugEnabled()) {
            logger.debug("Geo库存更新-------参数: {}", updateGeoStockRequestVos);
        }
        Map<String, Integer> keyToQuantity = newHashMap();
        List<StockRo> stockRos = newArrayList();
        List<CityStockRo> cityStockRos = newArrayList();
        List<ProvinceStockRo> provinceStockRos = newArrayList();
        for (UpdateGeoStockRequestVo vo : updateGeoStockRequestVos) {
            if (vo != null && StringUtils.isNotBlank(vo.getProductCode())) {
                StockRo stockRo = new StockRo(vo.getProductCode(), null);
                stockRos.add(stockRo);
                keyToQuantity.put(StockRedisDao.getHashKey(vo.getProductCode()), ValueUtils.getValue(vo.getQuantity()));
            } else {
                logger.warn("Geo库存更新-------商品编码不合法参数: {}", vo);
            }
            Integer geoId = vo.getGeoId();
            if (geoId != null) {
                vo.setGeoLevel(GeoLevelEnum.GEO_PROVINCE); //默认省级
                Integer provinceId;
                if (vo.getGeoLevel() == GeoLevelEnum.GEO_CITY) {
                    CityStockRo cityStockRo = new CityStockRo(geoId, vo.getProductCode(), null);
                    cityStockRos.add(cityStockRo);
                    keyToQuantity.put(CityStockRedisDao.getHashKey(geoId, vo.getProductCode()), ValueUtils
                            .getValue(vo.getQuantity()));
                    provinceId = this.getProvinceIdByCityId(geoId);
                } else {
                    provinceId = geoId;
                }

                if (provinceId != null) {
                    ProvinceStockRo provinceStockRo = new ProvinceStockRo(geoId, vo.getProductCode(), null);
                    provinceStockRos.add(provinceStockRo);
                    keyToQuantity.put(ProvinceStockRedisDao.getHashKey(provinceId, vo.getProductCode()), ValueUtils
                            .getValue(vo.getQuantity()));
                }
            } else {
                logger.warn("Geo库存更新-------GeoId为空: {}", vo);
            }
        }

        logger.info("Geo库存更新-------[key-quantity]: {}", keyToQuantity);
        stockRedisDao.updateQuantities(keyToQuantity);
        stockRedisDao.save(stockRos);
        cityStockRedisDao.save(cityStockRos);
        provinceStockRedisDao.save(provinceStockRos);
    }

    //TODO 考虑销售区域
    private Integer getProvinceIdByCityId(Integer geoId) {
        CityResponseVo cityVo = geoService.findCity(geoId);
        return cityVo != null ? cityVo.getProvinceId() : null;
    }

    @Override
    public void updateStocks(List<UpdateStockRequestVo> updateStockRequestVos) {
        if (logger.isDebugEnabled()) {
            logger.debug("库存更新-------请求vo: {}", updateStockRequestVos);
        }
        List<StockRo> stockRos = newArrayList();
        Map<String, Integer> keyToQuantity = newHashMap();
        for (UpdateStockRequestVo vo : updateStockRequestVos) {
            if (vo != null && StringUtils.isNotBlank(vo.getProductCode())) {
                StockRo stockRo = new StockRo(vo.getProductCode(), null);
                stockRos.add(stockRo);
                keyToQuantity.put(StockRedisDao.getHashKey(vo.getProductCode()), ValueUtils.getValue(vo.getQuantity()));
            } else {
                logger.warn("库存更新-------商品编码为空: {}", vo);
            }
        }

        logger.info("库存更新-------[key-quantity]: {}", keyToQuantity);
        stockRedisDao.updateQuantities(keyToQuantity);
        stockRedisDao.save(stockRos);
    }

    /******************************* Frontend Service *******************************/
}
