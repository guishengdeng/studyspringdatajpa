package com.biz.gbck.dao.mysql.repository.notice;


import com.biz.gbck.dao.mysql.po.info.NoticePo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NoticeRepository extends JpaRepository<NoticePo, Long>, NoticeDao {


    List<NoticePo> findByOrderByCreateTimeDesc(Pageable pageable);

}
