package com.biz.gbck.dao.mysql.repository.geo;

import com.biz.gbck.dao.mysql.po.geo.RegionPo;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface RegionDao {

    public List<RegionPo> findAllWithChild();

    public int updateWeigthAndIdx(int id, int weigth, int idx);

}
