package com.biz.gbck.dao.mysql.repository.geo;

import com.biz.gbck.dao.mysql.po.geo.BusinessPo;
import com.biz.support.jpa.repository.CommonJpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusinessRepository
        extends CommonJpaRepository<BusinessPo, Integer>, BusinessDao, JpaSpecificationExecutor<BusinessPo> {


    @Query("from BusinessPo p order by p.city.id")
    public List<BusinessPo> findOrderByCityId();

    @Query("from BusinessPo p where p.city.id=?1")
    public List<BusinessPo> findByCityId(int cityId);

    @Modifying
    @Query("update BusinessPo b set b.code = ?2 where b.id = ?1")
    public int updateCodeById(int objId, String code);

    /**
    *@param id
    *@param weigth
    *@param idx
    *@author defei
    *@date 2015年9月7日
    */

    @Modifying
    @Query("update BusinessPo c set c.weight = ?2, c.idx = ?3 where c.id= ?1")
    public int updateWeigthAndIdx(int id, int weigth, int idx);

    @Query("from BusinessPo p where p.city.id=?1 and p.weight>= ?2 ")
    public List<BusinessPo> findByCityIdAndWeight(int cityId, int weight);

    @Modifying
    @Query("Update BusinessPo set status=:status where id=:id") void disableBusiness(
            @Param("id") Integer id, @Param("status") Integer status);
}
