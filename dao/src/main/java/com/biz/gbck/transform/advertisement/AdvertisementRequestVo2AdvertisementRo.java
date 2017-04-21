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
public class AdvertisementRequestVo2AdvertisementRo implements Function<AdvertisementRequestVo,AdvertisementRo> {

    @Override
    public AdvertisementRo apply(AdvertisementRequestVo advertisementRequestVo) {
        if (advertisementRequestVo == null){
            return null;
        }
        AdvertisementRo advertisementRo = new AdvertisementRo();
        if(advertisementRequestVo.getBeginTimestamp() != null){
            advertisementRo.setBeginTimestamp(StringToTimestamp(advertisementRequestVo.getBeginTimestamp()));
        }
        if(advertisementRequestVo.getEndTimestamp()!= null){
            advertisementRo.setEndTimestamp(StringToTimestamp2(advertisementRequestVo.getEndTimestamp()));
        }
        advertisementRo.setId(advertisementRequestVo.getId());
        advertisementRo.setClickLink(advertisementRequestVo.getClickLink());
        advertisementRo.setPicturesLink(advertisementRequestVo.getPicturesLink());
        advertisementRo.setPriority(advertisementRequestVo.getPriority());
        advertisementRo.setResidenceTime(advertisementRequestVo.getResidenceTime());
        return advertisementRo;
    }

    public static Timestamp StringToTimestamp(String time)  {
        try {
            StringBuffer s=new StringBuffer();
            s.append(time);
            s.append("/00/00/01");
            String str= s.toString();
            SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy/HH/mm/ss");
            Date date =sdf.parse(str);
            Long l=  date.getTime();
            Timestamp aa=new Timestamp(l);
            return aa;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public static Timestamp StringToTimestamp2(String time)  {
        try {
            StringBuffer s=new StringBuffer();
            s.append(time);
            s.append("/23/59/59");
            String str= s.toString();
            SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy/HH/mm/ss");
            Date date =sdf.parse(str);
            Long l=  date.getTime();
            Timestamp aa=new Timestamp(l);
            return aa;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
