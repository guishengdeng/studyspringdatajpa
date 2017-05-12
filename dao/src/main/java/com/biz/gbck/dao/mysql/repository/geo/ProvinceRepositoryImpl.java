package com.biz.gbck.dao.mysql.repository.geo;


import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ProvinceRepositoryImpl extends CommonJpaRepositoryBean<ProvincePo, Integer> implements  ProvinceDao {

    @Autowired
    public ProvinceRepositoryImpl(EntityManager em) {
        super(ProvincePo.class, em);
    }

    public List<ProvincePo> findAllWithChild() {
        Query query = getEntityManager().createQuery("from ProvincePo l order by regionId,idx");
        List<ProvincePo> list = query.getResultList();
        for (ProvincePo po : list) {
            List<CityPo> cities = po.getCities();
            if (cities != null) {
                for (CityPo city : cities) {
                    List<DistrictPo> ds = city.getDistricts();
                    for (DistrictPo district : ds) {
                        district.getBusinesses();
                    }
                }
            }
        }
        return list;
    }

    public List<ProvincePo> findAll() {
        Query query = getEntityManager().createQuery("from ProvincePo l order by regionId,idx");
        return query.getResultList();
    }


    public List<ProvincePo> findByRegionIsNULL() {
        Query query = getEntityManager()
            .createQuery("from ProvincePo l where  l.region.id is null order by idx ");
        return query.getResultList();
    }

    public List<ProvincePo> findByRegionId(int regionId) {
        Query query = getEntityManager()
            .createQuery("from ProvincePo l where l.region.id =?1 order by regionId,idx")
            .setParameter(1, regionId);
        return query.getResultList();
    }


    public int updateWeigthAndIdx(int id, int weigth, int idx) {
        return getEntityManager()
            .createQuery("update ProvincePo r set r.weight =?2 ,r.idx=?3 where r.id=?1")
            .setParameter(2, weigth).setParameter(3, idx).setParameter(1, id).executeUpdate();
    }

}
