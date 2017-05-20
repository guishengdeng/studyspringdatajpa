package com.biz.soa.service.voucher.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biz.gbck.common.exception.CommonException;
import com.biz.gbck.common.model.voucher.VoucherConfigure;
import com.biz.gbck.dao.redis.repository.voucher.VoucherConfigureRedisDao;
import com.biz.gbck.dao.redis.repository.voucher.VoucherTypeRedisDao;
import com.biz.gbck.dao.redis.ro.voucher.VoucherConfigureRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeRo;
import com.biz.gbck.dao.redis.ro.voucher.VoucherTypeWithQuantity;
import com.biz.gbck.vo.voucher.VoucherCfgVo;
import com.biz.service.AbstractBaseService;
import com.biz.soa.service.voucher.VoucherConfigureService;

@Service
public class VoucherConfigureServiceImpl extends AbstractBaseService implements VoucherConfigureService {
	
	@Autowired 
	private VoucherConfigureRedisDao voucherConfigureRedisDao;
    @Autowired 
    private VoucherTypeRedisDao  voucherTypeRedisDao;

    public void save(VoucherConfigure voucherConfigure, Long voucherTypeId,int quantity) throws Exception {
        if(voucherTypeId == null){
            throw new CommonException("VoucherType can not be null.");
        }
        VoucherConfigureRo voucherConfigureRo = voucherConfigureRedisDao.getVoucherConfigureRo(voucherConfigure.getMark());
        if(voucherConfigureRo == null){
            voucherConfigureRo = new VoucherConfigureRo();
            voucherConfigureRo.setVoucherconfigure(voucherConfigure.getMark());
        }
        voucherConfigureRo.addVoucherTypes(voucherTypeId,quantity);
        voucherConfigureRedisDao.save(voucherConfigureRo);
    }

    public void delete(VoucherConfigure voucherConfigure, Long voucherTypeId){
        VoucherConfigureRo voucherConfigureRo =  voucherConfigureRedisDao.getVoucherConfigureRo(voucherConfigure.getMark());
        if(voucherConfigureRo != null){
            voucherConfigureRo.removeVoucherType(voucherTypeId);
            voucherConfigureRedisDao.save(voucherConfigureRo);
        }
    }

    /**
     * @param voucherconfigure
     * @return
     * @Description: 根据配置动作类型获取对应的优惠券类型
     */
    public VoucherConfigureRo getVoucherConfigureRo(String voucherconfigure) {
        return this.voucherConfigureRedisDao.getVoucherConfigureRo(voucherconfigure);
    }

    public List<VoucherCfgVo> findAll() {
        List<VoucherCfgVo> list = new ArrayList<>();
        VoucherConfigure[] voucherConfigures = VoucherConfigure.values();
        for (VoucherConfigure v : voucherConfigures) {
            VoucherConfigureRo ro = voucherConfigureRedisDao.getVoucherConfigureRo(v.getMark());
            if(ro==null){
                continue;
            }
            Map<Long,VoucherTypeWithQuantity> map = ro.getVoucherTypeWithQuantity();
            for(VoucherTypeWithQuantity voucherTypeWithQuantity:map.values()){
            	
            	VoucherTypeRo voucherTypeRo = voucherTypeRedisDao.getVoucherTypeRoById(voucherTypeWithQuantity.getId());
            	if(voucherTypeRo!=null){
	            	 VoucherCfgVo vo = new VoucherCfgVo();
	            	 vo.setVoucherconfigure(v.name());
	            	 vo.setVoucherconfigurename(v.getTitle());
	            	 
	            	 vo.setVoucherType(voucherTypeWithQuantity.getId());
	            	 vo.setVoucherTypename(voucherTypeRo.getName());
	            	 vo.setQuantity(voucherTypeWithQuantity.getQuantity());
	            	 list.add(vo);
            	}
            }
        }
        return list;
    }

}
