package com.biz.soa.product.service.frontend;

import com.alibaba.fastjson.JSON;
import com.biz.gbck.vo.product.RapidProductItemVo;
import com.biz.gbck.vo.product.frontend.ProductStockReqProductVo;
import com.biz.gbck.vo.product.frontend.TypeBProductStockVo;
import com.biz.gbck.vo.stock.BatchStocksRequestVo;
import com.biz.gbck.vo.stock.StockRequestVo;
import com.biz.gbck.vo.stock.StockResponseVo;
import com.biz.service.stock.frontend.StockService;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.ValueUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 商品库存 Service 实现
 *
 * @author david-liu
 * @date 2017年01月12日
 * @reviewer
 * @see
 */
//@Service
public class ProductStockServiceImpl implements IProductStockService {

    private static final Logger logger = LoggerFactory.getLogger(ProductStockServiceImpl.class);

    private static final long serialVersionUID = -4468709917657664734L;

    @Autowired
    private StockService stockService;

    @Override
    public List<Integer> getTypeAProductStock(List<String> typeAProductCodes) {
        if (logger.isDebugEnabled()) {
            logger.debug("ProductStockServiceImpl#getTypeAProductStock productCodes: {}", typeAProductCodes);
        }

        // 如果商品编码集合为空, 返回空集合
        if (CollectionUtils.isEmpty(typeAProductCodes)) {
            return Lists.newArrayList();
        }

        BatchStocksRequestVo requestVo = new BatchStocksRequestVo();
        requestVo.setProductCodes(typeAProductCodes);
        logger.debug("stockService.getStocks reqVo = {}", JSON.toJSONString(requestVo));
        List<StockResponseVo> responseVos = stockService.getStocks(requestVo);
        logger.debug("stockService.getStocks respVo = {}", JSON.toJSONString(responseVos));
        List<Integer> stocks = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(responseVos)) {
            for (StockResponseVo vo : responseVos) {
                stocks.add(vo.getQuantity());
            }
        }

        if (logger.isDebugEnabled()) {
            logger.debug("type A product stocks: {}", stocks.toString());
        }
        return stocks;
    }

    /**
     * 批量获取B类商品的库存信息
     *
     * @param reqVos 库存请求Vo
     * @return B类商品库存Vo
     */
    public List<TypeBProductStockVo> getTypeBProductStock(List<ProductStockReqProductVo> reqVos, String depotCode, String warehouseDepotCode) {
        List<TypeBProductStockVo> stockVos = Lists.newArrayList();
        reqVos = Optional.fromNullable(reqVos).or(Lists.<ProductStockReqProductVo>newArrayList());
        List<String> productCodes = Lists.newArrayList();
        BatchStocksRequestVo depotStockReqVo = new BatchStocksRequestVo();
        BatchStocksRequestVo provinceStockReqVo = new BatchStocksRequestVo();
        HashMap<String, Integer> productCodeIndexMap = Maps.newHashMap();
        for (ProductStockReqProductVo reqVo : reqVos) {
            if (reqVo.getRapidProduct()) {
                if (reqVo.getRapidProductItems() == null || reqVo.getRapidProductItems().size() == 0) {
                    logger.error("组合商品没有子商品[{}]", reqVo.getProductCode());
                }
                for (RapidProductItemVo itemVo : reqVo.getRapidProductItems()) {
                    if (!productCodeIndexMap.containsKey(itemVo.getProductCode())) {
                        productCodes.add(itemVo.getProductCode());
                        productCodeIndexMap.put(itemVo.getProductCode(), productCodes.indexOf(itemVo.getProductCode()));
                    }

                }
            } else {
                if (!productCodeIndexMap.containsKey(reqVo.getProductCode())) {
                    productCodes.add(reqVo.getProductCode());
                    productCodeIndexMap.put(reqVo.getProductCode(), productCodes.indexOf(reqVo.getProductCode()));
                }
            }
        }
        // 获取全省库存
        provinceStockReqVo.setProductCodes(productCodes);
        provinceStockReqVo.setDepotCode(warehouseDepotCode); //省仓所在省份库存(适配库存查询条件3)
        provinceStockReqVo.setIsProvince(Boolean.TRUE);
        logger.debug("查询B类商品库存  包含组合商品的查询数量为={},包含=[{}]", productCodes.size(), JSON.toJSONString(productCodes));
        List<StockResponseVo> provinceStocks = stockService.getStocks(provinceStockReqVo);
        provinceStocks = getStockResponseVos(reqVos, productCodeIndexMap, provinceStocks);
        if (CollectionUtils.isNotEmpty(provinceStocks)) {
            logger.debug("返回库存数量为 {}", provinceStocks.size());
        } else {
            logger.debug("返回库存数量为空");
        }
        if (StringUtils.isBlank(depotCode)) {
            List<Integer> stocks = this.getStocks(reqVos, provinceStocks);
            Preconditions.checkArgument(stocks.size() == reqVos.size());
            for (Integer stock : stocks) {
                TypeBProductStockVo stockVo = new TypeBProductStockVo();
                stockVo.setDepotCode(warehouseDepotCode);
                stockVo.setProvinceStock(stock);
                stockVos.add(stockVo);
            }
        } else {
            // 获取门店库存
            depotStockReqVo.setProductCodes(productCodes);
            depotStockReqVo.setDepotCode(depotCode);
            depotStockReqVo.setIsProvince(Boolean.FALSE);
            List<StockResponseVo> depotStocks = stockService.getStocks(depotStockReqVo);
            depotStocks = getStockResponseVos(reqVos, productCodeIndexMap, depotStocks);
            List<Integer> depotQuantities = this.getStocks(reqVos, depotStocks);
            List<Integer> provinceQuantities = this.getStocks(reqVos, provinceStocks);
            Preconditions.checkArgument(depotQuantities.size() == reqVos.size() && reqVos.size() == provinceQuantities.size());
            for (int i = 0; i < depotQuantities.size(); i++) {
                Integer depotQuantity = depotQuantities.get(i);
                Integer provinceQuantity = provinceQuantities.get(i);
                TypeBProductStockVo stockVo = new TypeBProductStockVo();
                stockVo.setDepotCode(depotCode);
                stockVo.setDepotStock(depotQuantity);
                stockVo.setProvinceStock(provinceQuantity);
                stockVos.add(stockVo);
            }
        }

        return stockVos;
    }

    /**
     * 重新组装库存
     */
    private List<StockResponseVo> getStockResponseVos(List<ProductStockReqProductVo> reqVos, HashMap<String, Integer> productCodeIndexMap, List<StockResponseVo> provinceStocks) {
        List<StockResponseVo> tempStockList = Lists.newArrayList();
        //转换库存查询结果
        for (ProductStockReqProductVo index : reqVos) {
            if (index.getRapidProduct() == Boolean.TRUE) {
                for (RapidProductItemVo item : index.getRapidProductItems()) {
                    tempStockList.add(provinceStocks.get(productCodeIndexMap.get(item.getProductCode())));
                }
            } else {
                tempStockList.add(provinceStocks.get(productCodeIndexMap.get(index.getProductCode())));
            }
        }
        return tempStockList;
    }

    @Override
    public Integer getTypeAProductStock(String typeAProductCode) {
        if (logger.isDebugEnabled()) {
            logger.debug("getTypeAProductStock typeAProductCode: {}", typeAProductCode);
        }

        if (StringUtils.isBlank(typeAProductCode)) {
            return 0;
        }

        StockRequestVo requestVo = new StockRequestVo();
        requestVo.setProductCode(typeAProductCode);

        if (logger.isDebugEnabled()) {
            logger.debug("stock requestVo: {}", requestVo);
        }

        StockResponseVo stock;
        try {
            stock = stockService.getStock(requestVo);
            if (logger.isDebugEnabled()) {
                logger.debug("stockResponseVo: {}", stock);
            }
            return stock.getQuantity();
        } catch (Exception e) {
            logger.error("查询库存出错: {}", e);
        }
        return 0;
    }

    @Override
    public TypeBProductStockVo getTypeBProductStock(ProductStockReqProductVo reqVo, String depotCode, String warehouseDepotCode) {
        if (logger.isDebugEnabled()) {
            logger.debug("getTypeBProductStock reqVo: {}, depotCode: {}", reqVo, depotCode);
        }

        List<String> productCodes = Lists.newArrayList();
        BatchStocksRequestVo depotStockReqVo = new BatchStocksRequestVo();
        BatchStocksRequestVo provinceStockReqVo = new BatchStocksRequestVo();
        if (reqVo.getRapidProduct()) {
            for (RapidProductItemVo itemVo : reqVo.getRapidProductItems()) {
                productCodes.add(itemVo.getProductCode());
            }
        } else {
            productCodes.add(reqVo.getProductCode());
        }

        if (CollectionUtils.isEmpty(productCodes)) {
            return new TypeBProductStockVo();
        } else {
            TypeBProductStockVo stockVo = new TypeBProductStockVo();
            stockVo.setDepotCode(depotCode);
            // 获取全省库存
            provinceStockReqVo.setDepotCode(depotCode);
            provinceStockReqVo.setProductCodes(productCodes);
            provinceStockReqVo.setIsProvince(Boolean.TRUE);
            List<StockResponseVo> provinceStockResponseVos = stockService.getStocks(provinceStockReqVo);
            if (StringUtils.isNotBlank(depotCode)) {
                // 获取门店可用库存
                depotStockReqVo.setDepotCode(depotCode);
                depotStockReqVo.setProductCodes(productCodes);
                depotStockReqVo.setIsProvince(Boolean.FALSE);
                List<StockResponseVo> depotStockResponseVos = stockService.getStocks(depotStockReqVo);
                stockVo.setDepotStock(this.getStock(reqVo, depotStockResponseVos));
                stockVo.setProvinceStock(this.getStock(reqVo, provinceStockResponseVos));
            } else {
                // 获取全省库存
                stockVo.setProvinceStock(this.getStock(reqVo, provinceStockResponseVos));
            }
            if (logger.isDebugEnabled()) {
                logger.debug("type B product stockVo: {}", stockVo);
            }
            return stockVo;
        }
    }

    private List<Integer> getStocks(List<ProductStockReqProductVo> reqVos, List<StockResponseVo> stockResponseVos) {
        List<Integer> stocks = Lists.newArrayList();
        Iterator<ProductStockReqProductVo> reqVoItr = reqVos.iterator();
        Iterator<StockResponseVo> stockResponseVoIterator = stockResponseVos.iterator();
        while (reqVoItr.hasNext()) {
            ProductStockReqProductVo reqVo = reqVoItr.next();
            if (reqVo.getRapidProduct()) {
                Integer stock = Integer.MAX_VALUE;
                for (RapidProductItemVo itemVo : reqVo.getRapidProductItems()) {
                    StockResponseVo itemStock = stockResponseVoIterator.next();
                    Integer itemProductStock = ValueUtils.getValue(itemStock.getQuantity()) / itemVo.getQuantity();
                    if (itemProductStock < stock) {
                        stock = itemProductStock;
                    }
                }
                stocks.add(stock);
            } else {
                StockResponseVo stock = stockResponseVoIterator.next();
                stocks.add(stock.getQuantity());
            }
        }
        return stocks;
    }

    private Integer getStock(ProductStockReqProductVo reqVo, List<StockResponseVo> stockResponseVos) {
        Integer stock;
        Iterator<StockResponseVo> stockResponseVoIterator = stockResponseVos.iterator();
        if (reqVo.getRapidProduct()) {
            stock = Integer.MAX_VALUE;
            for (RapidProductItemVo itemVo : reqVo.getRapidProductItems()) {
                StockResponseVo itemStock = stockResponseVoIterator.next();
                Integer itemProductStock = ValueUtils.getValue(itemStock.getQuantity()) / itemVo.getQuantity();
                if (itemProductStock < stock) {
                    stock = itemProductStock;
                }
            }
            return stock;
        } else {
            Preconditions.checkArgument(CollectionUtils.isNotEmpty(stockResponseVos) && stockResponseVos.size() == 1);
            return stockResponseVos.get(0).getQuantity();
        }
    }
}
