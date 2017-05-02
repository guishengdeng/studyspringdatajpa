package com.biz.gbck.dao.mysql.repository.org;

import com.biz.gbck.dao.mysql.po.org.ShopPo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface ShopDao {

    //List<ShopPo> searchShops(SearchShopReqVo vo);

    //Page<ShopPo> searchPageShops(final ShopQueryReqVo vo, Pageable pageable);

    Long findMaxUserId();

    //List<ShopExportVo> findShopInfoByStartTimeAndEndTime(String startTime, String endTime);

    //List<ShopAuditStatisticResultVo> findShopAuditStatisticBy(Timestamp endTime, Map<String, Object> searchParams);

    //List<DailyStatNewShopResultVo> findDailyNewShopCountBy(String startTime, String endTime);

    //int getShopCountBy(String provinceName, Long shopTypeId, Timestamp endTime);

    //List<ShopsInfoExportVo> findShopsInfoBetween(Timestamp startTime, Timestamp endTime);
}
