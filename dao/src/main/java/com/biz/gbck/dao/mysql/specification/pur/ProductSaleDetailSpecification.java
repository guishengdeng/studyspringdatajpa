package com.biz.gbck.dao.mysql.specification.pur;

import com.biz.gbck.dao.mysql.po.purchase.PurchaseOrderItem;
import com.biz.gbck.vo.product.backend.ProductSaleDetailQueryReqVo;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ProductSaleDetailSpecification
 *
 * @author guisheng.deng
 * @date 2017年05月26日
 * @reviewer
 * @description
 * @see
 */
public class ProductSaleDetailSpecification implements Specification<PurchaseOrderItem>{

     private ProductSaleDetailQueryReqVo vo;

     public ProductSaleDetailSpecification(ProductSaleDetailQueryReqVo vo){
          this.vo = vo ;
     }

    @Override
    public Predicate toPredicate(Root<PurchaseOrderItem> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = Lists.newArrayList();
        if(StringUtils.isNotBlank(vo.getOrderCode())){// SELECT XXX FROM PurchaseOrderItem XXX JOIN purchaseOrder  WHERE orderCode = vo.getOrderCode()
            Predicate predicate = criteriaBuilder.equal(root.join("purchaseOrder").get("orderCode"),vo.getOrderCode().trim());
            predicates.add(predicate);
        }
        if(StringUtils.isNotBlank(vo.getProductName())){
            Predicate predicate = criteriaBuilder.equal(root.get("productItem").get("name"),vo.getProductName().trim());
            predicates.add(predicate);
        }
        if(StringUtils.isNotBlank(vo.getProductCode())){
            Predicate predicate = criteriaBuilder.equal(root.get("productItem").get("productCode"),vo.getProductCode().trim());
            predicates.add(predicate);
        }
        if(vo.getStatus() != null){
            Predicate predicate = criteriaBuilder.equal(root.join("purchaseOrder").get("status"),vo.getStatus());
            predicates.add(predicate);
        }
        if(StringUtils.isNotBlank(vo.getStartTimeStamp())&& StringUtils.isNotBlank(vo.getEndTimeStamp())){
            Timestamp startTime = this.StringToTimestamp(vo.getStartTimeStamp());
            Timestamp endTime = this.StringToTimestamp(vo.getEndTimeStamp());
            Predicate predicate = criteriaBuilder.between(root.join("purchaseOrder").get("createTimestamp"),startTime,endTime);
            predicates.add(predicate);
        }
        //出库日期动态查询
        /*if(vo.getDeliveryStartTimeStamp()!=null && vo.getDeliveryEndTimeStamp()!=null){
            //Timestamp startTime = this.StringToTimestamp(vo.getDeliveryStartTimeStamp());
            //Timestamp endTime = this.StringToTimestamp(vo.getDeliveryEndTimeStamp());
            //左连接
            //root.join(root.getModel().getSingularAttribute("sysUsersX",SysUsersX.class),JoinType.LEFT);
            //Predicate likeP1 = cb.like(root.get("name").as(String.class),"%"+sSearch+"%");
            Predicate predicate = criteriaBuilder.between(root.join("purchaseOrder").get("orderCode"),vo.getDeliveryStartTimeStamp(),vo.getDeliveryEndTimeStamp());
            predicates.add(predicate);
        }*/
        criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
         return criteriaQuery.getRestriction();
    }
    private  Timestamp StringToTimestamp(String time) {
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