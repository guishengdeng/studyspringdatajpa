package com.biz.gbck.dao.mysql.repository.geo;



import com.biz.gbck.dao.mysql.po.geo.DistrictPo;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class DistrictRepositoryImpl extends CommonJpaRepositoryBean<DistrictPo, Integer> implements DistrictDao {

    @Autowired
    public DistrictRepositoryImpl(EntityManager em) {
        super(DistrictPo.class, em);
    }


    public List<DistrictPo> findAll() {
        Query query = getEntityManager().createQuery("from DistrictPo l order by cityId,idx");
        return query.getResultList();
    }


    public List<DistrictPo> findAllDistrictByCityId(int cityId) {
        Query query =
            getEntityManager().createQuery("from DistrictPo l where cityId=?1 order by cityId,idx")
                .setParameter(1, cityId);
        return query.getResultList();
    }

}
