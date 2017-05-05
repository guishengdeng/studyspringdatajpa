package com.biz.gbck.dao.mysql.repository.org;

import com.biz.core.util.DateUtil;
import com.biz.gbck.common.vo.statistic.DailyStatNewShopResultVo;
import com.biz.gbck.common.vo.statistic.ShopAuditStatisticResultVo;
import com.biz.gbck.dao.mysql.po.demo.CatPO;
import com.biz.gbck.dao.mysql.po.org.ShopPo;
import com.biz.gbck.dao.mysql.po.salearea.SaleAreaPo;
import com.biz.gbck.dao.mysql.repository.demo.CatDao;
import com.biz.gbck.vo.org.ShopExportVo;
import com.biz.gbck.vo.org.ShopsInfoExportVo;
import com.biz.gbck.vo.search.SearchShopReqVo;
import com.biz.gbck.vo.search.ShopQueryReqVo;
import com.biz.support.jpa.repository.CommonJpaRepositoryBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.codelogger.utils.ValueUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by J on 2016/3/28.
 */
@Repository("shopRepositoryImp")
public class ShopRepositoryImpl extends CommonJpaRepositoryBean<ShopPo, Long> implements ShopDao {


    @Autowired
    ShopTypeRepository shopTypeRepository;

    @Autowired
    ShopPoRepository shopPoRepository;



    @Autowired
    public ShopRepositoryImpl(EntityManager entityManager) {
        super(ShopPo.class, entityManager);
    }


        public List<ShopPo> searchShops(SearchShopReqVo vo) {
        List<Predicate> predicates = new ArrayList<>();
        Root<ShopPo> root;
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<ShopPo> criteriaQuery = cb.createQuery(ShopPo.class);
        root = criteriaQuery.from(ShopPo.class);
        //        if (ValueUtils.getValue(vo.getId()) != 0) {
        //            predicates.add(cb.equal(root.get("id"), vo.getId()));
        //        }
        //like 1239186%
        if (StringUtils.isNoneBlank(vo.getMobile())) {
            predicates.add(cb.equal(root.<String>get("mobile"), vo.getMobile()));

        }
        if (CollectionUtils.isNotEmpty(vo.getShopTypes())) {
            List<Integer> iterator = vo.getShopTypes();
            CriteriaBuilder.In in = cb.in(root.get("shopType"));
            for (int i = 0; i < iterator.size(); i++) {
                in.value(iterator.get(i));
            }
            predicates.add(in);
        }
        /**
         * 区域
         */
//        if (CollectionUtils.isNotEmpty(vo.getSaleAreas())) {
//            Metamodel m = getEntityManager().getMetamodel();
//            List<Integer> iterator = vo.getSaleAreas();
//            EntityType<ShopPo> shop = m.entity(ShopPo.class);
//            Join<ShopPo, SaleAreaPo> t = root.join(shop.getList("saleAreas", SaleAreaPo.class));
//            CriteriaBuilder.In in = cb.in(t.get("id"));
//            for (int i = 0; i < iterator.size(); i++) {
//                in.value(iterator.get(i));
//            }
//            predicates.add(in);
//        }
//        if (CollectionUtils.isNotEmpty(vo.getPriceTags())) {
//            Metamodel m = getEntityManager().getMetamodel();
//            List<Integer> iterator = vo.getPriceTags();
//            EntityType<ShopPo> shop = m.entity(ShopPo.class);
//            Join<ShopPo, PriceTagPo> t = root.join(shop.getList("priceTags", PriceTagPo.class));
//            CriteriaBuilder.In in = cb.in(t.get("id"));
//            for (int i = 0; i < iterator.size(); i++) {
//                in.value(iterator.get(i));
//            }
//            predicates.add(in);
//        }
//        if (CollectionUtils.isNotEmpty(vo.getBusinessTags())) {
//            Metamodel m = getEntityManager().getMetamodel();
//            List<Integer> iterator = vo.getBusinessTags();
//            EntityType<ShopPo> shop = m.entity(ShopPo.class);
//            Join<ShopPo, BusinessTagPo> t =
//                root.join(shop.getList("businessTags", BusinessTagPo.class));
//            CriteriaBuilder.In in = cb.in(t.get("id"));
//            for (int i = 0; i < iterator.size(); i++) {
//                in.value(iterator.get(i));
//            }
//            predicates.add(in);
//        }
        //        if (ValueUtils.getValue(vo.getId()) == 0) {
        //            predicates.add(cb.equal(root.get("status"), ShopStatus.VIP_1.getValue()));
        //        }
        /**
         * 省市区查询条件
         */
        if (ValueUtils.getValue(vo.getDistrictId()) != 0) {
            predicates.add(cb.equal(root.get("district"), vo.getDistrictId()));
        } else if ((ValueUtils.getValue(vo.getCityId()) != 0)) {
            predicates.add(cb.equal(root.get("city"), vo.getCityId()));
        } else if ((ValueUtils.getValue(vo.getProvinceId()) != 0)) {
            predicates.add(cb.equal(root.get("province"), vo.getProvinceId()));
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        Query q = getEntityManager().createQuery(criteriaQuery);
        List<ShopPo> result = q.getResultList();
        return result;
    }

    @Override
    public Long findMaxUserId() {
        Query query = getEntityManager().createQuery("SELECT id FROM ShopPo ORDER BY id DESC");
        try {
            Object id = query.setMaxResults(1).getSingleResult();
            return id == null ? 0L : Long.valueOf(id.toString());
        } catch (NoResultException e) {
            return 0L;
        }
    }

    /**
     * 分页查找商户
     */
    public Page<ShopPo> searchPageShops(final ShopQueryReqVo vo, Pageable pageable) {
        return shopPoRepository.findAll(new Specification<ShopPo>() {
            @Override
            public Predicate toPredicate(Root<ShopPo> root, CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (ValueUtils.getValue(vo.getId()) != 0L) {
                    predicates.add(cb.equal(root.get("id"), vo.getId()));
                }
                if (vo.getDetailAuditStatus() != null && vo.getDetailAuditStatus() != -1) {
                    predicates
                        .add(cb.equal(root.get("detailAuditStatus"), vo.getDetailAuditStatus()));
                }
                if (StringUtils.isNoneBlank(vo.getMobile())) {
                    predicates.add(cb.equal(root.<String>get("mobile"), vo.getMobile()));

                }
                if (CollectionUtils.isNotEmpty(vo.getShopTypes())) {
                    List<Integer> iterator = vo.getShopTypes();
                    CriteriaBuilder.In in = cb.in(root.get("shopType"));
                    for (int i = 0; i < iterator.size(); i++) {
                        in.value(iterator.get(i));
                    }
                    predicates.add(in);
                }
                /**
                 * 区域
                 */
//                if (CollectionUtils.isNotEmpty(vo.getSaleAreas())) {
//                    Metamodel m = getEntityManager().getMetamodel();
//                    List<Integer> iterator = vo.getSaleAreas();
//                    EntityType<ShopPo> shop = m.entity(ShopPo.class);
//                    Join<ShopPo, SaleAreaPo> t =
//                        root.join(shop.getList("saleAreas", SaleAreaPo.class));
//                    CriteriaBuilder.In in = cb.in(t.get("id"));
//                    for (int i = 0; i < iterator.size(); i++) {
//                        in.value(iterator.get(i));
//                    }
//                    predicates.add(in);
//                }
//                if (CollectionUtils.isNotEmpty(vo.getPriceTags())) {
//                    Metamodel m = getEntityManager().getMetamodel();
//                    List<Integer> iterator = vo.getPriceTags();
//                    EntityType<ShopPo> shop = m.entity(ShopPo.class);
//                    Join<ShopPo, PriceTagPo> t =
//                        root.join(shop.getList("priceTags", PriceTagPo.class));
//                    CriteriaBuilder.In in = cb.in(t.get("id"));
//                    for (int i = 0; i < iterator.size(); i++) {
//                        in.value(iterator.get(i));
//                    }
//                    predicates.add(in);
//                }
//                if (CollectionUtils.isNotEmpty(vo.getBusinessTags())) {
//                    Metamodel m = getEntityManager().getMetamodel();
//                    List<Integer> iterator = vo.getBusinessTags();
//                    EntityType<ShopPo> shop = m.entity(ShopPo.class);
//                    Join<ShopPo, BusinessTagPo> t =
//                        root.join(shop.getList("businessTags", BusinessTagPo.class));
//                    CriteriaBuilder.In in = cb.in(t.get("id"));
//                    for (int i = 0; i < iterator.size(); i++) {
//                        in.value(iterator.get(i));
//                    }
//                    predicates.add(in);
//                }
                /**
                 * 营业执照id
                 */
                if(StringUtils.isNotBlank(vo.getBusinessLicenceId())){
                    predicates.add(cb.like(root.<String>get("businessLicenceId"), new StringBuffer()
                            .append(vo.getBusinessLicenceId()).append("%").toString()));
                }
                /**
                 * 省市区查询条件
                 */
                if (ValueUtils.getValue(vo.getDistrictId()) != 0) {
                    predicates.add(cb.equal(root.get("district"), vo.getDistrictId()));
                } else if ((ValueUtils.getValue(vo.getCityId()) != 0)) {
                    predicates.add(cb.equal(root.get("city"), vo.getCityId()));
                } else if ((ValueUtils.getValue(vo.getProvinceId()) != 0)) {
                    predicates.add(cb.equal(root.get("province"), vo.getProvinceId()));
                }

                if (StringUtils.isNotBlank(vo.getDepotId())) {
                    predicates.add(cb.equal(root.get("depot").get("id"), vo.getDepotId()));
                }
                if (ValueUtils.getValue(vo.getDepotType()) != 0) {
                    predicates.add(cb.equal(root.get("depot").get("depotType"), vo.getDepotType()));
                }

                if (ValueUtils.getValue(vo.getCompanyId()) != 0L) {
                    predicates.add(cb.equal(root.get("depot").get("company").get("id"), vo.getCompanyId()));
                }

                // 将所有条件用 and 联合起来
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));


            }
        }, pageable);

    }

    public List<ShopExportVo> findShopInfoByStartTimeAndEndTime(String startTime, String endTime) {
        if (StringUtils.isNotEmpty(startTime)) {
            startTime = "0000-00-00 00:00:00";
        }
        Number a = null;
        if (StringUtils.isNotEmpty(endTime)) {
            endTime = DateUtil.now().toString();
        }
        String sqlStr =
            "select s.mobile,s.name , s.createTime , s.qualificationAuditStatus ,s.inviterCode, de.name , gp.name  from ShopPo s left join DepotEmployeePo de on s.invitercode = de.id left join ProvincePo gp on s.provinceId = gp.id where s.createTime >='"
                + startTime + "' and s.createTime <='" + endTime + "' order by s.createTime desc";
        Query query = getEntityManager().createQuery(sqlStr);
        try {
            List<ShopExportVo> result = query.getResultList();
            return result;
        } catch (NoResultException e) {
            return new ArrayList<>();
        }


    }

    @Override
    public List<ShopAuditStatisticResultVo> findShopAuditStatisticBy(Timestamp endTime,
                                                                     Map<String, Object> searchParams) {
        StringBuilder sb = new StringBuilder();

        if (searchParams.get("provinceId") != null) {
            sb.append(" AND sp.provinceId = :provinceId");
        }
        if (searchParams.get("depotId") != null && StringUtils.isNotBlank(searchParams.get("depotId").toString())) {
            sb.append(" AND sp.assartDepotId = :depotId");
        }

        String sql = "SELECT gp.id AS province_id,\n" + "       gp.name AS province_name,\n"
            + "       dt.name AS depot_name,\n" + "       SUM(CASE\n"
            + "         WHEN sp.qualificationauditstatus = '10' THEN 1\n" + "         ELSE 0\n"
            + "       END) AS register_not_complete,\n" + "       SUM(CASE\n"
            + "         WHEN sp.qualificationauditstatus < '10' THEN 1\n" + "         ELSE 0\n"
            + "       END) AS audit_not_pass,\n" + "       SUM(CASE\n"
            + "         WHEN sp.qualificationauditstatus <= '30' AND  sp.qualificationauditstatus >= '20' THEN 1\n"
            + "         ELSE 0\n" + "       END) AS audit_pass\n" + "FROM shop sp\n"
            + "LEFT JOIN geo_province gp ON gp.id = sp.provinceId\n"
            + "LEFT JOIN depot dt ON sp.assartDepotId = dt.id\n"
            + "WHERE sp.createtime <= :endTime\n" + " %s "
            + "GROUP BY province_id, province_name, depot_name\n";

        Query query = getEntityManager()
            .createNativeQuery(String.format(sql, sb.toString()), "ShopAuditStatisticBinding");
        query.setParameter("endTime", endTime);

        if (searchParams.get("provinceId") != null) {
            query.setParameter("provinceId", searchParams.get("provinceId"));
        }
        if (searchParams.get("depotId") != null && StringUtils.isNotBlank(searchParams.get("depotId").toString())) {
            query.setParameter("depotId", searchParams.get("depotId"));
        }
        return query.getResultList();
    }

    @Override
    public List<DailyStatNewShopResultVo> findDailyNewShopCountBy(String startTime,
                                                                  String endTime) {
        String sql = "SELECT date_format(sp.createtime, '%Y-%m-%d') AS d_day,\n"
            + "\tsp.provinceId AS province_id,\n"
            + "\tsp.shopTypeId AS shop_type_id,\n"
            + "\tCOUNT(sp.id) AS day_new_shop_count\n"
            + "FROM shop sp\n"
            + "WHERE sp.createtime >= :startTime\n"
            + "AND sp.createtime <= :endTime\n"
            + "AND sp.qualificationauditstatus > '20'\n"
            + "GROUP BY d_day, province_id, shop_type_id";
        Query query = getEntityManager().createNativeQuery(sql, "DailyNewShopBinding");
        query.setParameter("startTime", startTime).setParameter("endTime", endTime);
        return query.getResultList();
    }

    @Override
    public int getShopCountBy(String provinceName, Long shopTypeId, Timestamp endTime) {
        String baseSql = "SELECT \n" + "    COUNT(*) AS shop_count\n" + "FROM shop sp\n"
            + "LEFT JOIN shop_type st ON sp.shopTypeId = st.id\n"
            + "LEFT JOIN geo_province gp ON gp.id = sp.provinceId\n"
            + "WHERE sp.qualificationAuditStatus > 20\n"
            + "AND sp.createtime <= :endTime\n";

        StringBuilder sb = new StringBuilder();

        if (provinceName == null) {
            sb.append("AND gp.name IS NULL\n");
        } else {
            sb.append("AND gp.name = :provinceName\n");
        }

        if (shopTypeId == null) {
            sb.append("AND st.id IS NULL\n");
        } else {
            sb.append("AND st.id = :shopTypeId\n");
        }

        String sql = baseSql.concat(sb.toString());
        Query query = getEntityManager().createNativeQuery(sql).setParameter("endTime", endTime);
        if (provinceName != null) {
            query.setParameter("provinceName", provinceName);
        }

        if (shopTypeId != null) {
            query.setParameter("shopTypeId", shopTypeId);
        }

        return ((Number)query.getSingleResult()).intValue();
    }

    @Override
    public List<ShopsInfoExportVo> findShopsInfoBetween(Timestamp startTime, Timestamp endTime) {
        String sql = "SELECT\n" +
                "    sp.id AS 'shop_id',\n" +
                "    usr.mobile AS 'user_mobile',\n" +
                "    st.name AS 'shop_type',\n" +
                "    gp.name AS 'shop_province',\n" +
                "    usr.createtime AS 'user_register_time',\n" +
                "    usr.lastlogintime AS 'user_lastlogintime',\n" +
                "    CASE \n" +
                "        WHEN sp.qualificationauditstatus = '0' THEN '审核未通过'\n" +
                "        WHEN sp.qualificationauditstatus = '5' THEN '审核资料已过期'\n" +
                "        WHEN sp.qualificationauditstatus = '10' THEN '等待上传资料'\n" +
                "        WHEN sp.qualificationauditstatus = '20' THEN '等待审核'\n" +
                "        WHEN sp.qualificationauditstatus = '23' THEN '原资料生效，新的详细资料审核失败'\n" +
                "        WHEN sp.qualificationauditstatus = '25' THEN '原资料生效，并已提交新的资料，等待验证'\n" +
                "        WHEN sp.qualificationauditstatus = '30' THEN '审核通过'\n" +
                "    END AS 'shop_status',\n" +
                "    sp.invitercode AS 'shop_invite_code',\n" +
                "    de.name AS 'shop_inviter_name',\n" +
                "    ad.name AS 'shop_assart_depot_name',\n" +
                "    pd.name AS 'shop_price_depot_name',\n" +
                "    dd.name AS 'shop_deliver_depot_name',\n" +
                "    sp.name AS 'shop_name',\n" +
                "    gp.name AS 'province_name',\n" +
                "    gc.name AS 'city_name',\n" +
                "    gd.name AS 'district_name',\n" +
                "    sp.shopaddress AS 'shop_address'\n" +
                "FROM usr_user usr\n" +
                "LEFT JOIN shop sp ON usr.shopId = sp.id\n" +
                "LEFT JOIN shop_type st ON st.id = sp.shopTypeId \n" +
                "LEFT JOIN depot_employee de ON de.id = sp.invitercode\n" +
                "LEFT JOIN depot ad ON sp.assartDepotId = ad.id\n" +
                "LEFT JOIN depot pd ON sp.depotId = pd.id\n" +
                "LEFT JOIN depot dd ON sp.deliveryDepotId = dd.id\n" +
                "LEFT JOIN geo_province gp ON gp.id = sp.provinceId\n" +
                "LEFT JOIN geo_city gc ON gc.id = sp.cityId\n" +
                "LEFT JOIN geo_district gd ON gd.id = sp.districtId\n" +
                "WHERE usr.createtime >= :startTime\n"+
                "AND usr.createtime <= :endTime\n"+
                "ORDER BY usr.createtime DESC";
        Query query = getEntityManager().createNativeQuery(sql,"shopsInfo");
        query.setParameter("startTime", startTime).setParameter("endTime", endTime);
        return query.getResultList();
    }

}

