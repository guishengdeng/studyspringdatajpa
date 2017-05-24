package com.biz.soa.service.voucher;

import java.util.Collection;
import java.util.List;
import java.util.Map;


import com.biz.gbck.dao.mysql.po.voucher.VoucherTypePo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.vo.voucher.VoucherTypeVo;

public interface VoucherTypeService{

    public void save(VoucherTypeVo voucherTypeVo);

    public List<VoucherTypePo> allVoucherTypes();

    public VoucherTypePo getVoucherTypeById(Long id);

    public VoucherTypeRo getVoucherTypeRoById(Long id);

    public void addVoucherTypeIssueCount(Long id, int addIssueCount);

    public void deleteVoucherType(Long id);

    public List<VoucherTypeRo> allVoucherTypesInApp();

    public Map<Long, List<Long>> dividVoucherTypeIdsByCategory(Collection<Long> ids);
    
    /**
     * 
     * @Description: 修改优惠券
     * @param voucherTypeVo
     */
    public void update(VoucherTypeVo voucherTypeVo);

}
