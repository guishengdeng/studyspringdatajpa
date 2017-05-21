package com.biz.gbck.dao.mysql.specification.order;

import com.biz.gbck.dao.mysql.po.order.Order;
import com.biz.gbck.dao.mysql.po.org.PartnerPo;
import com.biz.gbck.dao.mysql.po.org.PlatformPo;
import com.biz.gbck.dao.mysql.repository.org.PartnerRepository;
import com.biz.gbck.dao.mysql.repository.org.PlatformRepository;
import com.biz.gbck.vo.order.req.OrderQueryReqVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import sun.plugin.services.PlatformService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * OrderSearchSpecification
 *
 * @author guisheng.deng
 * @date 2017年05月18日
 * @reviewer
 * @description
 * @see
 */
public class OrderSearchSpecification implements Specification<Order> {
    private OrderQueryReqVo vo;
    private List<PlatformPo> platformPos;
    private List<PartnerPo> partnerPos;
    public OrderSearchSpecification(OrderQueryReqVo vo,List<PlatformPo> platformPos,List<PartnerPo> partnerPos){
        this.vo = vo;
        this.platformPos = platformPos;
        this.partnerPos = partnerPos;
    }

    @Override
    public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates= Lists.newArrayList();
        if(StringUtils.isNotBlank(vo.getBeginTimestamp()) && StringUtils.isNotBlank(vo.getEndTimestamp())){
            //将String转成Timestamp
            /*Timestamp startTime = Timestamp.valueOf(vo.getBeginTimestamp());
            Timestamp endTime = Timestamp.valueOf(vo.getEndTimestamp());*/
            Timestamp startTime = this.StringToTimestamp(vo.getBeginTimestamp());
            Timestamp endTime = this.StringToTimestamp(vo.getEndTimestamp());
            Predicate predicate = criteriaBuilder.between(root.<Timestamp>get("createTimestamp"),startTime,endTime);
            predicates.add(predicate);
        }

        if(StringUtils.isNotBlank(vo.getPartnerName())){
            CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("sellerId"));
            //List<Long> idList = partnerRepository.getIdsByNameLike(vo.getPartnerName());
            if(partnerPos != null){
                for(PartnerPo item : partnerPos){
                    in.value(item.getId());
                }
            }
            predicates.add(in);
            /*PartnerPo po = partnerRepository.findByName(vo.getPartnerName());
            Predicate predicate = criteriaBuilder.equal(root.get("sellerId"), partnerPo.getId());
            predicates.add(predicate);*/
        }
        if(StringUtils.isNotBlank(vo.getPlatFormCompanyName())){
            //List<Long> idList = platformRepository.getIdsByNameLike(vo.getPlatFormCompanyName());
            CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("companyId"));
            if(platformPos != null){
                for(PlatformPo item : platformPos){
                    in.value(item.getId());
                }
            }
             predicates.add(in);
           /* PlatformPo po = platformRepository.findByName(vo.getPlatFormCompanyName());
            Predicate predicate = criteriaBuilder.equal(root.get("companyId"), platformPo.getId());
            predicates.add(predicate);*/
        }
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
        return criteriaQuery.getRestriction();
    }
    public  Timestamp StringToTimestamp(String time) {
        try {
            String first = time.replaceAll(" ", "/");
            String second = first.replace(":", "/");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/HH/mm/ss");
            Date date = simpleDateFormat.parse(second);
            Long dateTime = date.getTime();
            Timestamp timestamp = new Timestamp(dateTime);
            return timestamp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}