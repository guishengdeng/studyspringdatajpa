package com.biz.gbck.dao.mysql.repository.geo;

import com.biz.gbck.dao.mysql.po.geo.ProvincePo;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface ProvinceDao {

    public List<ProvincePo> findAll();

    public List<ProvincePo> findAllWithChild();

    public List<ProvincePo> findByRegionIsNULL();

    public List<ProvincePo> findByRegionId(int regionId);

    public int updateWeigthAndIdx(int id, int weigth, int idx);


}
