package com.biz.soa.product.service.interfaces.impl.init;

import com.biz.gbck.dao.mysql.po.product.master.Product;
import com.biz.gbck.dao.mysql.po.product.meta.ApartTag;
import com.biz.gbck.dao.mysql.po.product.meta.Brand;
import com.biz.gbck.dao.mysql.po.product.meta.Category;
import com.biz.gbck.dao.mysql.po.tag.SaleTag;
import com.biz.gbck.dao.mysql.repository.brand.BrandRepository;
import com.biz.gbck.dao.mysql.repository.category.CategoryRepository;
import com.biz.gbck.dao.mysql.repository.product.ProductRepository;
import com.biz.gbck.dao.redis.repository.product.*;
import com.biz.gbck.dao.redis.ro.product.master.ProductRO;
import com.biz.gbck.dao.redis.ro.product.meta.BrandRo;
import com.biz.gbck.dao.redis.ro.product.meta.CategoryRo;
import com.biz.gbck.dao.redis.ro.product.price.PriceGroupRO;
import com.biz.gbck.dao.redis.ro.product.price.PriceRO;
import com.biz.gbck.enums.product.SaleStatusEnum;
import com.biz.gbck.transform.product.Brand2BrandRo;
import com.biz.service.IdService;
import com.biz.soa.product.service.interfaces.init.InitService;
import com.google.common.collect.Lists;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import org.codelogger.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

/**
 * Created by david-liu on 2017/05/16 09:36.
 */
@Service
public class InitServiceImpl implements InitService {

    private final static Logger logger = LoggerFactory.getLogger(InitServiceImpl.class);

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandRedisDao brandRedisDao;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryRedisDao categoryRedisDao;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRedisDao productRedisDao;

    @Autowired
    private PriceGroupRedisDao priceGroupRedisDao;

    @Autowired
    private IdService idService;

    @Autowired
    private PriceRedisDao priceRedisDao;

    @Override
    public void initBrand() {
        StopWatch stopWatch = new StopWatch("init brand");
        stopWatch.start("get brand pos from db");
        List<Brand> brands = brandRepository.findAll();
        stopWatch.stop();
        stopWatch.start("transform brand po to ro, and save ros");
        List<BrandRo> brandRos = brands.stream().map(brand -> new Brand2BrandRo().apply(brand)).collect(Collectors.toList());
        brandRedisDao.save(brandRos);
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
    }

    @Override
    public void initCategory() {
        StopWatch stopWatch = new StopWatch("init category");
        stopWatch.start("get category pos from db");
        List<Category> categories = categoryRepository.findByDeleteFlag(Boolean.FALSE);
        stopWatch.stop();
        stopWatch.start("transform brand po to ro, and save ros");
        List<CategoryRo> categoryRos = categories.stream().map(category -> {
            CategoryRo categoryRo = new CategoryRo();
            categoryRo.setId(category.getId());
            categoryRo.setName(category.getName());
            categoryRo.setIdx(category.getIdx());
            categoryRo.setLogo(category.getLogo());
            categoryRo.setStatus(category.getStatus());
            return categoryRo;
        }).collect(Collectors.toList());
        categoryRedisDao.save(categoryRos);
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
    }

    @Override
    public void initProduct() {
        StopWatch stopWatch = new StopWatch("init product");
        stopWatch.start("get products from db");
        List<Product> products = productRepository.findByDeleteFlag(Boolean.FALSE);
        stopWatch.stop();
        stopWatch.start("transform product from po to ro");
        List<ProductRO> productROS = products.stream().map(product -> {
            productRedisDao.appendOnSaleProductIds(product.getId());
            ProductRO productRO = new ProductRO();
            productRO.setId(product.getId());
            productRO.setProductCode(product.getProductCode());
            productRO.setName(product.getName());
            productRO.setSubTitle(product.getSubTitle());
            productRO.setI18nCode(product.getI18nCode());
            productRO.setBreif(product.getBreif());
            if (Objects.nonNull(product.getBrand())) {
                productRO.setBrandId(product.getBrand().getId());
                productRO.setBrandName(product.getBrand().getName());
            }
            if (Objects.nonNull(product.getCategory())) {
                productRO.setCategoryId(product.getCategory().getId());
                productRO.setCategoryName(product.getCategory().getName());
            }
            productRO.setLogo(product.getLogo());
            productRO.setImages(CollectionUtils.join(product.getImages(), ","));
            productRO.setIntroImages(CollectionUtils.join(product.getIntroImages(), ","));
            if (Objects.nonNull(product.getSaleTags()) && CollectionUtils.isNotEmpty(product.getSaleTags())) {
                String saleTagIds = CollectionUtils.join(product.getSaleTags().stream().map(SaleTag::getId).collect(Collectors.toList()), ",");
                String saleTagNames = CollectionUtils.join(product.getSaleTags().stream().map(SaleTag::getName).collect(Collectors.toList()), ",");
                productRO.setSaleTagIds(saleTagIds);
                productRO.setSaleTags(saleTagNames);
            }
            if (Objects.nonNull(product.getApartTags())) {
                String apartTagIds = CollectionUtils.join(product.getApartTags().stream().map(ApartTag::getId).collect(Collectors.toList()), ",");
                productRO.setApartTagIds(apartTagIds);
            }
            if (CollectionUtils.isNotEmpty(product.getProperties())) {
                productRO.setPropertiesJson(product.getPropertyJson());
            }
            productRO.setWeight(product.getWeight());
            productRO.setMinQuantity(product.getMinQuantity());
            productRO.setSeoTitle(product.getSeoTitle());
            productRO.setSeoKeywords(product.getSeoKeywords());
            productRO.setSeoDescription(product.getSeoDescription());
            productRO.setSaleStatus(SaleStatusEnum.ON_SALE.getValue());
            productRO.setOnSaleTime(new Timestamp(System.currentTimeMillis()));
            productRO.setRapidProduct(product.getIsRapidProduct());
            productRO.setControlByQRCode(Boolean.FALSE);
            productRO.setCircularFlow(Boolean.FALSE);
            return productRO;
        }).collect(Collectors.toList());
        stopWatch.stop();
        stopWatch.start("save ro");
        productRedisDao.save(productROS);
        stopWatch.stop();
        logger.info(stopWatch.prettyPrint());
    }

    @Override
    public void initPrice() {
        // 初始化一个价格组
        PriceGroupRO priceGroupRO = new PriceGroupRO();
        priceGroupRO.setPriceGroupId(1L);
        priceGroupRO.setSellerId(1L);
        priceGroupRO.setId(1L);
        priceGroupRedisDao.save(priceGroupRO);
        // 选出所有的商品
        List<Product> products = productRepository.findByDeleteFlag(Boolean.FALSE);
        List<Long> productIds = products.stream().map(Product::getId).collect(Collectors.toList());
        Random random = new Random();
        List<PriceRO> priceROS = Lists.newArrayList();
        productIds.forEach(productId -> {
            PriceRO priceRO = new PriceRO();
            priceRO.setId(String.format("%s%s", productId, 1L));
            priceRO.setCompanyGroupId(1L);
            priceRO.setDynamicAveragePrice(1);
            priceRO.setProductId(productId);
            priceRO.setCompanyGroupId(1L);
            priceRO.setPriceId(idService.nextId());
            priceRO.setPriceGroupId(1L);
            priceRO.setPurchasePrice(1);
            priceRO.setSalePrice(random.nextInt(1000));
            priceROS.add(priceRO);
        });
        priceRedisDao.save(priceROS);
    }

    public static void main(String[] args) {
        System.out.println(new Random().nextInt(1000));
    }
}
