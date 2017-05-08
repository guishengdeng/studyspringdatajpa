package com.biz.gbck.dao.mysql.repository.geo;


import com.biz.gbck.common.bean.PageControl;
import com.biz.gbck.common.dao.mysql.CommonManageAbleDao;
import com.biz.gbck.dao.mysql.po.geo.BusinessPo;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;


public class BusinessRepositoryImpl
        extends CommonJpaRepositoryBean<BusinessPo, Integer> implements BusinessDao{

    @Autowired
    public BusinessRepositoryImpl(EntityManager em) {
        super(BusinessPo.class, em);
    }

    public void findAll(PageControl pc) {
        Query queryResult = getEntityManager().createQuery("from BusinessPo l order by l.id desc");
        pc.setList(queryResult.setFirstResult(pc.getBegin()).setMaxResults(pc.getPageSize())
            .getResultList());
    }


    public int findAllCount() {
        Query queryCount = getEntityManager().createQuery("select count(l) from BusinessPo l ");
        Number count = (Number) queryCount.getSingleResult();
        return count == null ? 0 : count.intValue();
    }


    public List<BusinessPo> findAll() {
        Query query = getEntityManager().createQuery("from BusinessPo order by district.id,idx");
        return query.getResultList();
    }

    public List<BusinessPo> findAllBusinessByDistrictId(int findId) {
        Query query = getEntityManager()
            .createQuery("from BusinessPo where district.id=?1 order by district.id ,idx")
            .setParameter(1, findId);
        return query.getResultList();
    }


    public BusinessPo update(BusinessPo businessPo) {
        return getEntityManager().merge(businessPo);
    }

}
