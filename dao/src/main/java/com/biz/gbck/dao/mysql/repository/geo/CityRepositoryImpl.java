package com.biz.gbck.dao.mysql.repository.geo;



import com.biz.gbck.dao.mysql.po.geo.CityPo;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class CityRepositoryImpl extends CommonJpaRepositoryBean<CityPo, Integer> implements CityDao {



    @Autowired
    public CityRepositoryImpl(EntityManager em) {
        super(CityPo.class, em);
    }

    public List<CityPo> findAll() {
        Query query = getEntityManager().createQuery("from CityPo l order by provinceId,idx");
        return query.getResultList();
    }


    public List<CityPo> findAllCitysByProvinceId(int provinceId) {
        Query query = getEntityManager()
            .createQuery("from CityPo l where provinceId = ?1 order by provinceId,idx")
            .setParameter(1, provinceId);
        return query.getResultList();
    }


}
