package com.biz.gbck.dao.mysql.repository.voucher;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.biz.gbck.common.dao.mysql.CommonManageAbleDao;
import com.biz.gbck.dao.mysql.po.voucher.VoucherConstant;
import com.biz.gbck.dao.mysql.po.voucher.VoucherPo;
import com.biz.gbck.vo.voucher.UserVoucherStatisticResultVo;

@Repository("voucherRepositoryImpl") 
public class VoucherRepositoryImpl extends CommonManageAbleDao implements VoucherDao {
    @Override 
    public Map<VoucherConstant.VoucherUseStatus, List<VoucherPo>> listVoucherByUseStatus(
        int userId) {
        return null;
    }

    @SuppressWarnings("unchecked")
	@Override 
	public List<UserVoucherStatisticResultVo> findAllUserVouchers(Map<String, Object> searchParams) {
        String sql =
            "SELECT \n" + "    vv.bindingtime AS time,\n" + "    ur.mobile AS user_mobile,\n"
                + "    ur.id AS user_id,\n" + "    gp.name AS province_name,\n"
                + "    st.name AS shop_type_name,\n" + "    vt.name AS voucher_type_name,\n"
                + "    vt.id AS voucher_type_id,\n" + "    vt.issuecount AS voucher_issue_count\n"
                + "FROM vou_voucher vv\n" + "LEFT JOIN usr_user ur ON vv.bindinguserid = ur.id\n"
                + "LEFT JOIN shop sp ON ur.shopId = sp.id\n"
                + "LEFT JOIN shop_type st ON sp.shopTypeId = st.id\n"
                + "LEFT JOIN geo_province gp ON sp.provinceId = gp.id\n"
                + "LEFT JOIN vou_type vt ON vt.id = vv.voucherTypeId\n" + "WHERE 1 = 1\n";

        StringBuilder sb = new StringBuilder();
        if (searchParams.get("startTime") != null) {
            sb.append("AND vv.bindingtime >= :startTime\n");
        }

        if (searchParams.get("endTime") != null) {
            sb.append("AND vv.bindingtime <= :endTime\n");
        }

        if (searchParams.get("provinceId") != null) {
            sb.append("AND gp.id = :provinceId\n");
        }

        if (searchParams.get("shopTypeId") != null) {
            sb.append("AND st.id = :shopTypeId\n");
        }

        if (searchParams.get("voucherTypeName") != null) {
            sb.append("AND vt.name = :voucherTypeName");
        }

        Query query = getEntityManager()
            .createNativeQuery(sql.concat(sb.toString()), "UserVoucherStatsBinding");
        if (searchParams.get("startTime") != null) {
            query.setParameter("startTime", searchParams.get("startTime"));
        }

        if (searchParams.get("endTime") != null) {
            query.setParameter("endTime", searchParams.get("endTime"));
        }

        if (searchParams.get("provinceId") != null) {
            query.setParameter("provinceId", searchParams.get("provinceId"));
        }

        if (searchParams.get("shopTypeId") != null) {
            query.setParameter("shopTypeId", searchParams.get("shopTypeId"));
        }

        if (searchParams.get("voucherTypeName") != null) {
            query.setParameter("voucherTypeName", searchParams.get("voucherTypeName"));
        }
        return query.getResultList();
    }
}
