package com.biz.service.org;

import com.biz.event.org.ShopTypeSyncEvent;
import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.dao.mysql.po.org.ShopTypePo;
import com.biz.gbck.dao.mysql.repository.org.ShopTypeRepository;
import com.biz.gbck.dao.redis.repository.org.ShopTypeRedisDao;
import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.enums.user.ShopTypeStatus;
import com.biz.gbck.transform.org.ShopTypePoToShopTypeRo;
import com.biz.service.CommonService;
import com.biz.service.org.interfaces.ShopTypeService;
import com.biz.vo.org.ShopTypeCreateReqVo;
import com.biz.vo.org.ShopTypeUpdateReqVo;
import com.google.common.collect.Lists;
import org.codelogger.utils.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;


@Service
@Transactional
public class ShopTypeServiceImpl  extends CommonService implements ShopTypeService {


    private static final Logger logger = LoggerFactory.getLogger(ShopTypeService.class);

    @Autowired
    private ShopTypeRepository shopTypeRepository;

    @Autowired
    private ShopTypeRedisDao shopTypeRedisDao;


    @Override
    public List<ShopTypeRo> findAllShopTypeRo() {
        List<ShopTypeRo> shopTypeRos = shopTypeRedisDao.findAll();
        if (CollectionUtils.isEmpty(shopTypeRos)) {
            List<ShopTypePo> all = shopTypeRepository.findAll();
            shopTypeRos = Lists.transform(all, new ShopTypePoToShopTypeRo());
            publishEvent(new ShopTypeSyncEvent(this));
        }
        return sortShopTypeRos(shopTypeRos);
    }

    @Override
    public List<ShopTypeRo> findAllShopTypeRo(ShopTypeStatus shopTypeStatus) {
        List<ShopTypeRo> shopTypeRos = shopTypeRedisDao.findAll(shopTypeStatus);
        if (CollectionUtils.isEmpty(shopTypeRos)) {
            List<ShopTypePo> all = shopTypeRepository.findAllByStatus(shopTypeStatus.getValue());
            shopTypeRos = Lists.transform(all, new ShopTypePoToShopTypeRo());
            publishEvent(new ShopTypeSyncEvent(this));
        }
        return sortShopTypeRos(shopTypeRos);
    }

    @Override
    public List<ShopTypeRo> sortShopTypeRos(List<ShopTypeRo> shopTypeRos) {
        List<ShopTypeRo> sortedShopTypes = newArrayList(shopTypeRos);
        Collections.sort(sortedShopTypes, new Comparator<ShopTypeRo>() {
            @Override public int compare(ShopTypeRo o1, ShopTypeRo o2) {
                return o1 == null || o1.getIdx() == null ?
                        -1 :
                        o2 == null || o2.getIdx() == null ? 1 : o1.getIdx().compareTo(o2.getIdx());
            }
        });
        return sortedShopTypes;
    }

    @Override
    public List<ShopTypePo> getAllShopTypePo(ShopTypeStatus shopTypeStatus) {
        return shopTypeRepository.findAllByStatus(shopTypeStatus.getValue());
    }

    @Override
    public ShopTypeRo findShopTypeRo(Long shopTypeId) {
        ShopTypeRo shopTypeRo = shopTypeRedisDao.findOne(shopTypeId);
        if (shopTypeRo == null) {
            ShopTypePo shopTypePo = shopTypeRepository.findOne(shopTypeId);
            if (shopTypePo != null) {
                return syncShopTypePoToRedis(shopTypePo);
            }
        }
        return shopTypeRo;
    }

    @Override
    public ShopTypePo findOne(Long id) {
        return  shopTypeRepository.findOne(id);
    }

    @Override
    public ShopTypeRo saveShopType(ShopTypeCreateReqVo shopTypeCreateReqVo) {
        logger.debug("Create shopType[{}]", shopTypeCreateReqVo);
        ShopTypePo shopTypePo = shopTypeCreateReqVo.toShopTypePo();
        ShopTypePo savedShopTypePo = shopTypeRepository.save(shopTypePo);
        return syncShopTypePoToRedis(savedShopTypePo);
    }

    @Override
    public ShopTypeRo updateShopType(ShopTypeUpdateReqVo shopTypeUpdateReqVo) throws CommonException {
        logger.debug("Update shopType[{}].", shopTypeUpdateReqVo);
        ShopTypePo shopType = shopTypeRepository.findOne(shopTypeUpdateReqVo.getId());
        if (shopType == null) {
            shopTypeRedisDao.delete(shopTypeUpdateReqVo.getId());
            throw new CommonException("Shop Type Not Exist");
        } else {
            shopType.setName(shopTypeUpdateReqVo.getName());
            shopType.setDescription(shopTypeUpdateReqVo.getDescription());
            shopType.setIdx(shopTypeUpdateReqVo.getIdx());
            ShopTypePo savedShopTypePo = shopTypeRepository.save(shopType);
            return syncShopTypePoToRedis(savedShopTypePo);
        }
    }

    @Override
    public Boolean disableShopType(Long shopTypeId) {
        logger.debug("Disable shopType[{}]", shopTypeId);
        shopTypeRepository.disableShopType(shopTypeId);
        ShopTypePo shopTypePo = shopTypeRepository.findOne(shopTypeId);
        syncShopTypePoToRedis(shopTypePo);
        shopTypeRedisDao.disableShopType(shopTypeId);
        return true;
    }

    @Override
    public void syncAllShopTypeFromMysqlToRedis(Integer pageSize) {
        Integer startPage = 0;
        Page<ShopTypePo> userPage = null;
        while (userPage == null || userPage.hasNext()) {
            Pageable page = new PageRequest(startPage, pageSize);
            userPage = shopTypeRepository.findAll(page);
            List<ShopTypePo> content = userPage.getContent();
            for (ShopTypePo shopTypePo : content) {
                syncShopTypePoToRedis(shopTypePo);
            }
        }
    }

    @Override
    public ShopTypeRo syncShopTypePoToRedis(ShopTypePo shopTypePo) {
        ShopTypeRo shopTypeRo = null;
        if (shopTypePo != null) {
            shopTypeRo = new ShopTypePoToShopTypeRo().apply(shopTypePo);
            shopTypeRedisDao.save(shopTypeRo);
        }
        return shopTypeRo;
    }

    @Override
    public List<ShopTypePo> findAllShopTypes() {
        return shopTypeRepository.findAll();
    }
}
