package com.biz.soa.product.service.backend;

import com.alibaba.fastjson.JSON;
import com.biz.core.util.DateUtil;
import com.biz.gbck.dao.mysql.po.product.GroupProduct;
import com.biz.gbck.dao.mysql.po.product.GroupProductItem;
import com.biz.gbck.dao.mysql.po.product.MnsProduct;
import com.biz.gbck.dao.mysql.po.product.Product;
import com.biz.gbck.dao.mysql.repository.product.GroupProductRepository;
import com.biz.gbck.dao.mysql.repository.product.MnsProductRepository;
import com.biz.gbck.dao.mysql.repository.product.ProductRepository;
import com.biz.gbck.enums.CommonStatusEnum;
import com.biz.gbck.enums.oms.InvalidOmsDataType;
import com.biz.gbck.enums.oms.InvalidOmsMessageType;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.enums.product.VendorTypeEnum;
import com.biz.gbck.vo.oms.event.InvalidOmsMessageVo;
import com.biz.gbck.vo.product.backend.*;
import com.biz.gbck.vo.product.event.UpdateMnsProductEventVo;
import com.biz.gbck.vo.product.mns.MnsGroupProductItemVo;
import com.biz.gbck.vo.product.mns.MnsGroupProductVo;
import com.biz.gbck.vo.product.mns.MnsProductVo;
import com.biz.message.MessageService;
import com.biz.message.SimpleBizMessage;
import com.biz.message.queue.BizBaseQueue;
import com.biz.service.AbstractBaseService;
import com.biz.service.product.backend.MnsProductService;
import com.biz.gbck.transform.product.MnsProductVo2MnsProduct;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 对接中台商品主数据服务
 *
 * @author zhangcheng
 * @date 2017/1/5
 * @reviewer
 * @see
 */
@Service
public class MnsProductServiceImpl extends AbstractBaseService implements MnsProductService {

    /**
     * 日期格式转换（线程安全）
     */
    FastDateFormat dateFormat = FastDateFormat.getInstance("yyyy-MM-dd");
    @Autowired
    private MnsProductRepository mnsProductRepository;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private GroupProductRepository groupProductRepository;

    /**
     * 校验中台商品对象中的参数
     *
     * @return 未通过校验的中台对象
     * @author zhangcheng
     */
    private InvalidOmsMessageVo validMnsProductVo(MnsProductVo vo) {
        logger.debug("开始校验中台商品Vo中的参数，{}", vo);
        InvalidOmsMessageVo invalidOmsMessageVo = new InvalidOmsMessageVo();
        invalidOmsMessageVo.setType(InvalidOmsDataType.OMS_PRODUCT);
        List<String> messages = Lists.newArrayList();
        if (StringUtils.isBlank(vo.getMatnr())) {
            messages.add(InvalidOmsMessageType.PRODUCT_CODE_EMPTY.getDescription());
            invalidOmsMessageVo.setValid(false);
        }
        if (StringUtils.isBlank(vo.getMaktx())) {
            messages.add(InvalidOmsMessageType.PRODUCT_DESCRIPTION_INVALID.getDescription());
            invalidOmsMessageVo.setValid(false);
        }
        if (StringUtils.isBlank(vo.getEan11())) {
            messages.add(InvalidOmsMessageType.PRODUCT_MAIN_BAR_CODE_INVALID.getDescription());
            invalidOmsMessageVo.setValid(false);
        }
        if (StringUtils.isBlank(vo.getBclass())) {
            messages.add(InvalidOmsMessageType.PRODUCT_BIG_CATEGORY_INVALID.getDescription());
            invalidOmsMessageVo.setValid(false);

        }
        if (StringUtils.isBlank(vo.getSclass())) {
            messages.add(InvalidOmsMessageType.PRODUCT_SMALL_CATEGORY_INVALID.getDescription());
            invalidOmsMessageVo.setValid(false);
        }
        if (StringUtils.isBlank(vo.getZzmlfs())) {
            messages.add(InvalidOmsMessageType.PRODUCT_GROSS_PROFIT_INVALID.getDescription());
            invalidOmsMessageVo.setValid(false);
        }
        if (CollectionUtils.isEmpty(messages)) {
            invalidOmsMessageVo.setValid(Boolean.TRUE);
            logger.info("该中台商品对象校验通过，{}", invalidOmsMessageVo);
            return invalidOmsMessageVo;
        }
        //将未通过校验的Vo序列化为json字符串
        invalidOmsMessageVo.setContent(JSON.toJSONString(vo));
        invalidOmsMessageVo.setMessages(messages);
        if (logger.isDebugEnabled()) {
            logger.debug("未通过校验Vo序列化为:{}", JSON.toJSONString(vo));
        }
        return invalidOmsMessageVo;
    }

    /**
     * 校验中台组合商品Vo
     *
     * @return 未通过校验的中台对象
     * @auther zhangcheng
     */
    private InvalidOmsMessageVo validMnsGroupProductVo(MnsGroupProductVo vo) {
        logger.debug("开始校验中台组合商品Vo中的参数，{}", vo);
        InvalidOmsMessageVo invalidOmsMessageVo = new InvalidOmsMessageVo();
        invalidOmsMessageVo.setType(InvalidOmsDataType.OMS_GROUP_PRODUCT);
        List<String> messages = Lists.newArrayList();
        if (StringUtils.isBlank(vo.getBzno())) {
            messages.add(InvalidOmsMessageType.GROUP_PRODUCT_CODE_EMPTY.getDescription());
            invalidOmsMessageVo.setValid(false);
        }
        if (StringUtils.isBlank(vo.getBztxt())) {
            messages.add(InvalidOmsMessageType.GROUP_PRODUCT_DESCRIPTION_INVALID.getDescription());
            invalidOmsMessageVo.setValid(false);
        }
        if (CollectionUtils.isEmpty(vo.getSkus())) {
            messages.add(InvalidOmsMessageType.GROUP_PRODUCT_ITEM_INVALID.getDescription());
            invalidOmsMessageVo.setValid(false);
        } else {
            for (MnsGroupProductItemVo itemVo : vo.getSkus()) {
                Product exitedProduct = productRepository.findByProductCode(itemVo.getMatnr());
                if (exitedProduct == null) {
                    messages.add(InvalidOmsMessageType.GROUP_PRODUCT_ITEM_NONE_EXISTENCE.getDescription()
                            + "_商品编码为：" + itemVo.getMatnr());
                }
                invalidOmsMessageVo.setValid(false);
            }
        }
        if (CollectionUtils.isEmpty(messages)) {
            invalidOmsMessageVo.setValid(Boolean.TRUE);
            logger.info("该中台商品对象校验通过，{}", invalidOmsMessageVo);
            return invalidOmsMessageVo;
        }
        //将未通过校验的Vo序列化为json字符串
        invalidOmsMessageVo.setContent(JSON.toJSONString(vo));
        invalidOmsMessageVo.setMessages(messages);
        if (logger.isDebugEnabled()) {
            logger.debug("未通过校验Vo序列化为:{}", JSON.toJSONString(vo));
        }
        logger.debug("校验中台商品Vo结束");
        return invalidOmsMessageVo;
    }

    /**
     * 将没有通过校验的中台Vo发送到指定的队列
     *
     * @author zhangcheng
     */
    private void sendInvalidVoToMessage(InvalidOmsMessageVo messageVo) {
        messageService.sendMessage(BizBaseQueue.MQ_INVALID_OMS_MSG_QUEUE, SimpleBizMessage.newMessage(messageVo));
    }

    /**
     * 构建PageRequest分页对象
     *
     * @return PageRequest
     */
    private PageRequest buildPageRequest(SearchPageVo vo) {
        return new PageRequest(vo.getPageIndex() - 1, vo.getPageSize());
    }

    /**
     * 分页对象转换为BootstrapTablePageResult
     */
    private BootstrapTablePageResult<MnsProductResponseVo> buildPageResult(Page<MnsProduct> mnsProducts) {
        List<MnsProduct> resultMnsProducts = mnsProducts.getContent();
        List<MnsProductResponseVo> mnsProductResponseVos = Lists.newArrayList();
        BootstrapTablePageResult<MnsProductResponseVo> result = new BootstrapTablePageResult<>();
        if (CollectionUtils.isNotEmpty(resultMnsProducts)) {
            for (MnsProduct mnsProduct : resultMnsProducts) {
                MnsProductResponseVo responseVo = new MnsProductResponseVo();
                responseVo.setId(String.valueOf(mnsProduct.getId()));
                responseVo.setProductCode(mnsProduct.getMatnr());
                responseVo.setName(mnsProduct.getMaktx());
                responseVo.setBaseUnit(mnsProduct.getMeins());
                responseVo.setBrand(mnsProduct.getZzbrand());
                responseVo.setLastModifyTime(dateFormat.format(mnsProduct.getLastmodifytime()));
                if (mnsProduct.getStatus() == CommonStatusEnum.ENABLE) {
                    responseVo.setStatus("未操作");
                } else {
                    responseVo.setStatus("已操作");
                }
                mnsProductResponseVos.add(responseVo);
            }
            result.setRows(mnsProductResponseVos);
            result.setTotal((int) mnsProducts.getTotalElements());
            return result;
        }
        return null;
    }

    @Override
    public BootstrapTablePageResult<MnsProductResponseVo> findAllMnsProducts(SearchPageVo vo) {
        PageRequest pageRequest = this.buildPageRequest(vo);
        //分页查询所有的中台推送过来的商品
        Page<MnsProduct> mnsProductsPage = mnsProductRepository.findAllByLastModifyTime(pageRequest);
        return this.buildPageResult(mnsProductsPage);
    }

    @Override
    public BootstrapTablePageResult<MnsProductResponseVo> findByProductCode(SearchPageVo vo) {
        PageRequest pageRequest = this.buildPageRequest(vo);
        //根据中台编码模糊查询中台商品
        Page<MnsProduct> mnsProductPage = mnsProductRepository.findBySkuLike(vo.getSearchValue(), pageRequest);
        return this.buildPageResult(mnsProductPage);
    }

    /**
     * 修改中台商品的状态步骤：
     * 1.根据前端传递过来的商品id在mns_product表中查询出相对应中台商品信息
     * 2.获取到该中台商品的中台编码
     * 3.然后再根据中台编码到pro_product表中查询是否有该商品存在
     * 4.如果该商品在pro_product表中存在，则将UpdateMnsProductStatusResponseVo中code设置为：“1”，msg设置为：“该商品已经上架”，
     * 以此来提示用户，并且在将MnsProduct中的状态更改为：“已操作”
     * 5.如果该商品在pro_product表不存在，则创建一个新的Product对象，并且将MnsProduct中的name、productCode和i18nCode设置到
     * Product对象中，并且在将MnsProduct中的状态更改为：“已操作”，将UpdateMnsProductStatusResponseVo中code设置为：“0”，msg
     * 设置为：“该商品状态修改成功”，以此来提示用户
     */
    @Override
    @Transactional
    public UpdateMnsProductStatusResponseVo updateStatus(UpdateMnsProductStatusReqVo reqVo) {
        MnsProduct mnsProduct = mnsProductRepository.findOne(Long.valueOf(reqVo.getId()));
        if (mnsProduct != null && mnsProduct.getStatus() == CommonStatusEnum.ENABLE) {
            Product existedProduct = productRepository.findByProductCode(mnsProduct.getMatnr());
            if (existedProduct != null) {
                UpdateMnsProductStatusResponseVo statusResponseVo = new UpdateMnsProductStatusResponseVo();
                statusResponseVo.setCode(1);
                statusResponseVo.setMessage("该商品已经上架");
                mnsProduct.setStatus(CommonStatusEnum.DISABLE);
                mnsProductRepository.save(mnsProduct);
                return statusResponseVo;
            } else {
                Product product = new Product();
                product.setId(idService.nextId());
                product.setName(mnsProduct.getMaktx());
                product.setProductCode(mnsProduct.getMatnr());
                product.setProductType(VendorTypeEnum.TYPE_B);
                if (StringUtils.isNotBlank(mnsProduct.getEan11())) {
                    product.setI18nCode(mnsProduct.getEan11());
                }
                productRepository.save(product);
                UpdateMnsProductStatusResponseVo statusResponseVo = new UpdateMnsProductStatusResponseVo();
                statusResponseVo.setCode(0);
                statusResponseVo.setMessage("该商品状态修改成功");
                statusResponseVo.setProductId(String.valueOf(product.getId()));
                return statusResponseVo;
            }
        }
        UpdateMnsProductStatusResponseVo statusResponseVo = new UpdateMnsProductStatusResponseVo();
        statusResponseVo.setCode(3);
        statusResponseVo.setMessage("未找到该中台商品");
        return statusResponseVo;
    }

    /**
     * 转换和保存中台商品主数据
     * 1.首先调用私有方法validMnsProductVo校验中台商品主数据对象
     * 2.如果该中台商品主数据对象没有通过校验，则将该对象发送到指定
     * 的队列里
     * 3.如果该中台商品主数据对象通过了校验，则开始转换并保存中台商
     * 品数据信息
     * （1）首先将中台商品主数据对象利用MnsProductVo2MnsProduct对象
     * 中的apply方法转换为mnsProduct（Po）信息
     * （2）再调用MySQL中的findBySku方法，根据中台商品主数据中的中台
     * 商品编码查询到本地相对应的mnsProductInDB商品信息
     * （3）对查询出来的mnsProductInDB信息进行非空判断：
     * <1>如果mnsProductInDB不为空的话，并且该中台商品对象与本地
     * mnsProductInDB相比为同一个商品，则不保存该中台商品主数
     * 据
     * <2>如果该中台商品对象与本地mnsProductInDB相比不是同一个商
     * 品，则获取到mnsProductInDB商品的id，并将该id设置到mnsProduct
     * 中
     * <3>如果mnsProductInDB为空的话，则调用idService手动设置id
     * (4)保存该中台商品主数据
     *
     * @author zhangcheng
     */
    @Override
    @Transactional
    public void trans(MnsProductVo mnsProductVo) {
        logger.debug("开始转换商品信息, MnsProductVo --> MnsProduct");
        InvalidOmsMessageVo invalidOmsMessageVo = this.validMnsProductVo(mnsProductVo);
        if (!invalidOmsMessageVo.isValid()) {
            //将未通过校验的vo发送到指定的RabbitMQ队列里
            logger.debug("start sending message");
            this.sendInvalidVoToMessage(invalidOmsMessageVo);
            logger.debug("Send message end");
        } else {
            logger.debug("--------------开始转换并保存中台商品数据信息--------------");
            MnsProduct mnsProduct = new MnsProductVo2MnsProduct().apply(mnsProductVo);
            logger.debug("MnsProductVo to MnsProduct:{}", mnsProduct.toString());
            MnsProduct mnsProductInDB = mnsProductRepository.findBySku(mnsProduct.getMatnr());
            if (mnsProductInDB != null) {
                if (Objects.equals(mnsProduct, mnsProductInDB)) {
                    logger.debug("数据库已存在该商品, 不保存. 商品编码: {}", mnsProduct.getMatnr());
                    return;
                }
                mnsProduct.setId(mnsProductInDB.getId());
            } else {
                mnsProduct.setId(idService.nextId());
            }
            mnsProductRepository.save(mnsProduct);
        }
    }

    /**
     * 将中台商品主数据转换为业务需要的商品数据
     * 1.首先通过中台商品编码查询是否已经存在的product
     * 2.对于查询出来的product进行非空判断
     * 3.如果该商品存在的话，将中台商品的最后更新时间设置到UpdateTimestamp字段
     * 4.如果该商品不存在的话，则手动一个新的id，并且设置ProductType、Name
     * productCode、i18nCode和CreateTimestamp字段到product中
     * 5.最后保存到该product对象
     *
     * @author zhangcheng
     */
    @Override
    @Transactional
    public void transToBusinessProduct(UpdateMnsProductEventVo eventVo) {
        //首先通过中台商品编码查询是否已经存在的product
        Product product = productRepository.findByProductCode(eventVo.getProductCode());
        if (product != null) {
            //如果该商品已经存在
            product.setUpdateTimestamp(eventVo.getLastUpdateTime());
        } else {
            //如果该商品不存在
            product.setId(idService.nextId());
            product.setProductType(VendorTypeEnum.TYPE_B);
            product.setName(eventVo.getName());
            product.setProductCode(eventVo.getProductCode());
            product.setI18nCode(eventVo.getProductMainBarcode());
            product.setCreateTimestamp(DateUtil.now());
        }
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void translateMnsGroupProduct(MnsGroupProductVo mnsGroupProductVo) {
        InvalidOmsMessageVo messageVo = this.validMnsGroupProductVo(mnsGroupProductVo);
        if (!messageVo.isValid()) {
            //将未通过校验的vo发送到指定的RabbitMQ队列里
            logger.debug("start sending message {}", messageVo);
            this.sendInvalidVoToMessage(messageVo);
            logger.debug("Send message end");
        } else {
            logger.info("中台对接[组合商品] --> 开始处理中台组合商品，商品中台编码为：[{}]", mnsGroupProductVo.getBzno());
            Product product = productRepository.findByProductCode(mnsGroupProductVo.getBzno());
            if (product == null) {
                logger.info("中台对接[组合商品] --> BBC商品主数据中, 中台商品编码为[{}]的商品数据不存在, 开始创建", mnsGroupProductVo.getBzno());
                product = new Product();
                product.setId(idService.nextId());
                product.setCreateTimestamp(new Timestamp(System.currentTimeMillis()));
                product.setProductCode(mnsGroupProductVo.getBzno());
                product.setTypeBSaleStatus(SaleStatusEnum.OFF_SALE);
                product.setProductType(VendorTypeEnum.TYPE_B);
                product.setIsRapidProduct(Boolean.TRUE);
            }
            product.setName(mnsGroupProductVo.getBztxt() == null ? "" : mnsGroupProductVo.getBztxt());
            product.setUpdateTimestamp(new Timestamp(System.currentTimeMillis()));
            productRepository.save(product);
            logger.info("中台对接[组合商品] --> 创建或者更新BBC商品主数据成功, 商品Id[{}]", product.getId());
            GroupProduct groupProduct = groupProductRepository.findByProductId(product.getId());
            if (groupProduct == null) {
                groupProduct = new GroupProduct();
                groupProduct.setId(idService.nextId());
                groupProduct.setItemList(Lists.<GroupProductItem>newArrayList());
            }
            groupProduct.setProduct(product);
            List<Product> itemProducts = Lists.newArrayList();
            List<String> itemProductCodes = Lists.newArrayList();
            for (MnsGroupProductItemVo itemVo : mnsGroupProductVo.getSkus()) {
                Product itemProduct = productRepository.findByProductCode(itemVo.getMatnr());
                if (itemProduct == null || itemVo.getMeins() == null) {
                    logger.error("中台对接[组合商品] --> 创建BBC组合商品数据[{}]失败, 失败商品中台编码[{}]",
                            mnsGroupProductVo, JSON.toJSONString(itemVo.getMatnr()));
                    return;
                } else {
                    itemProductCodes.add(itemVo.getMatnr());
                    itemProducts.add(itemProduct);
                }
            }
            Preconditions.checkArgument(itemProducts.size() == mnsGroupProductVo.getSkus().size());
            boolean hasChanged = false;
            for (int i = 0; i < itemProducts.size(); i++) {
                Product itemProduct = itemProducts.get(i);
                MnsGroupProductItemVo itemVo = mnsGroupProductVo.getSkus().get(i);
                GroupProductItem item;
                if (groupProduct.containsItem(itemProduct.getProductCode())) {
                    item = groupProduct.getItem(itemProduct.getProductCode());
                    item.setProduct(itemProduct);
                    int quantity = itemVo.getMenge().intValue();
                    if (!Objects.equals(quantity, item.getQuantity())) {
                        hasChanged = true;
                    }
                    item.setQuantity(itemVo.getMenge().intValue());
                } else {
                    hasChanged = true;
                    item = new GroupProductItem();
                    item.setId(idService.nextId());
                    item.setParent(groupProduct);
                    item.setProduct(itemProduct);
                    item.setQuantity(itemVo.getMenge().intValue());
                    groupProduct.addItem(item);
                }
            }
            groupProduct = groupProductRepository.save(groupProduct);
            if (hasChanged) {
                logger.info("中台对接[组合商品] --> 检测到组合商品数据[{}]已经发生改变, 将BBC组合商品下架", product.getId());
                product.setTypeBSaleStatus(SaleStatusEnum.OFF_SALE);
                product.setGroupProduct(groupProduct);
                productRepository.save(product);
            }
            logger.info("中台对接[组合商品] --> 创建BBC组合商品主数据成功, 商品Id[{}]", groupProduct.getId());
        }
    }
}
