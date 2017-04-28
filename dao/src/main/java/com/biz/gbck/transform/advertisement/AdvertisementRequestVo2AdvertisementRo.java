package com.biz.gbck.transform.advertisement;

import com.biz.gbck.vo.advertisement.frontend.request.AdvertisementRequestVo;
import com.biz.gbck.dao.redis.ro.advertisement.AdvertisementRo;
import com.google.common.base.Function;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by xys on 2017/4/18.
 */
public class AdvertisementRequestVo2AdvertisementRo implements Function<AdvertisementRequestVo, AdvertisementRo> {

    @Override
    public AdvertisementRo apply(AdvertisementRequestVo advertisementRequestVo) {
        if (advertisementRequestVo == null) {
            return null;
        }
        AdvertisementRo advertisementRo = new AdvertisementRo();
        if (advertisementRequestVo.getBeginTimestamp() != null) {
            advertisementRo.setBeginTimestamp(StringToTimestamp(advertisementRequestVo.getBeginTimestamp()));
        }
        if (advertisementRequestVo.getEndTimestamp() != null) {
            advertisementRo.setEndTimestamp(StringToTimestamp(advertisementRequestVo.getEndTimestamp()));
        }
        advertisementRo.setId(advertisementRequestVo.getId());
        advertisementRo.setClickLink(advertisementRequestVo.getClickLink());
        advertisementRo.setPicturesLink(advertisementRequestVo.getPicturesLink());
        advertisementRo.setPriority(advertisementRequestVo.getPriority());
        advertisementRo.setResidenceTime(advertisementRequestVo.getResidenceTime());
        return advertisementRo;
    }

    public static Timestamp StringToTimestamp(String time) {
        try {
            String first = time.replaceAll(" ", "/");
            String second = first.replace(":", "/");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
            Date date = simpleDateFormat.parse(second);
            Long dateTime = date.getTime();
            Timestamp timestamp = new Timestamp(dateTime);
            return timestamp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
