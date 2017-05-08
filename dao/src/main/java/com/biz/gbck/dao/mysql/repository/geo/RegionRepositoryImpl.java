package com.biz.gbck.dao.mysql.repository.geo;

import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.gbck.dao.mysql.po.geo.RegionPo;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class RegionRepositoryImpl
        extends CommonJpaRepositoryBean<RegionPo, Integer> implements  RegionDao{

    @Autowired
    public RegionRepositoryImpl(EntityManager em) {
        super(RegionPo.class, em);
    }


    public List<RegionPo> findAllWithChild() {
        Query query = getEntityManager().createQuery("from RegionPo l order by idx");
        List<RegionPo> regions = query.getResultList();
        for (RegionPo region : regions) {
            List<ProvincePo> list = region.getProvinces();
            for (ProvincePo po : list) {
                List<CityPo> cities = po.getCities();
                if (cities != null) {
                    for (CityPo city : cities) {
                        List<DistrictPo> ds = city.getDistricts();
                        for (DistrictPo district : ds) {
                            int size = district.getBusinesses().size();
                        }
                    }
                }
            }
        }
        return regions;
    }


    public int updateWeigthAndIdx(int id, int weigth, int idx) {
        return getEntityManager()
            .createQuery("update RegionPo r set r.weight =?2 ,r.idx=?3 where r.id=?1")
            .setParameter(2, weigth).setParameter(3, idx).setParameter(1, id).executeUpdate();
    }

}
