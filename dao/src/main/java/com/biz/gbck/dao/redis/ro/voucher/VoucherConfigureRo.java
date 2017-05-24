package com.biz.gbck.dao.redis.ro.voucher;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.biz.redis.annotation.Ro;
import com.biz.redis.annotation.RoSortedSet;
import com.biz.redis.bean.BaseRedisObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @ClassName: VoucherConfigureRo 
 * @Description: 优惠券配置RO
 *   
 */
@Ro(key = "ro:VoucherConfigureRo")
@RoSortedSet(key = "list", score = "createTimestamp")
public class VoucherConfigureRo extends BaseRedisObject<Long> implements Comparable<VoucherConfigureRo>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 2627212760718425495L;

	private static final Logger logger = LoggerFactory.getLogger(VoucherConfigureRo.class);
	
    public static final String SPLIT_PLACEHOLDER = ",";
    
    public static final String SPLIT_TYPE_QUANTITY = ":";
    
    
    /**
     * 动作类型
     */
    private String voucherconfigure;

    /**
     * 优惠券类型ID
     */
    private String voucherTypes;

    public String getVoucherconfigure() {
        return voucherconfigure;
    }

    public void setVoucherconfigure(String voucherconfigure) {
        this.voucherconfigure = voucherconfigure;
    }

    public String getVoucherTypes() {
        return voucherTypes;
    }

    public void setVoucherTypes(String v){
    	this.voucherTypes = v;
    }
    
    
    @Deprecated
    public List<Long> getVoucherTypeIds() {

        List<Long> voucherTypeIds = newArrayList();
        if(voucherTypes != null){
            String[] stringOfVoucherTypeIds = voucherTypes.split(SPLIT_PLACEHOLDER);
            for (String stringOfVoucherTypeId : stringOfVoucherTypeIds) {
                voucherTypeIds.add(Long.valueOf(stringOfVoucherTypeId));
            }
        }
        return voucherTypeIds;
    }

	public Map<Long,VoucherTypeWithQuantity> getVoucherTypeWithQuantity() {
		Map<Long,VoucherTypeWithQuantity> result = new LinkedHashMap<Long,VoucherTypeWithQuantity>();
		if (voucherTypes != null) {
			String[] stringOfVoucherTypeIds = voucherTypes.split(SPLIT_PLACEHOLDER);
			if (stringOfVoucherTypeIds != null && stringOfVoucherTypeIds.length > 0) {
				for (String stringOfVoucherTypeId : stringOfVoucherTypeIds) {
					if (StringUtils.isNotBlank(stringOfVoucherTypeId)) {
						String[] idWithQuantityArray = stringOfVoucherTypeId.split(SPLIT_TYPE_QUANTITY);
						try {
							if (idWithQuantityArray != null) {
								long voucherTypeId = Long.valueOf(idWithQuantityArray[0]);
								if (idWithQuantityArray.length == 2) {
									result.put(voucherTypeId,new VoucherTypeWithQuantity(voucherTypeId,Integer.parseInt(idWithQuantityArray[1])));
								} else if (idWithQuantityArray.length == 1) {
									result.put(voucherTypeId,new VoucherTypeWithQuantity(voucherTypeId));
								}
							}
						} catch (Exception e) {
							logger.error("getVoucherTypeWithQuantity", e);
						}
					}
				}
			}
		}
		return result;
	}
	
	private void join(Map<Long, VoucherTypeWithQuantity> map) {
		if (map != null && !map.isEmpty()) {
			StringBuffer stringBuffer = new StringBuffer();
			boolean isFirstVisited = true;
			for (VoucherTypeWithQuantity object : map.values()) {
				if (isFirstVisited) {
					isFirstVisited = false;
				} else {
					stringBuffer.append(SPLIT_PLACEHOLDER);
				}
				stringBuffer.append(object.toJoinStr(SPLIT_TYPE_QUANTITY));
			}
			voucherTypes = stringBuffer.toString();
		} else {
			voucherTypes = "";
		}
	}

    public void addVoucherTypes(Long voucherTypeId,int quantity) {
        if(voucherTypeId != null && quantity>0){
            Map<Long,VoucherTypeWithQuantity> map = this.getVoucherTypeWithQuantity();
            map.put(voucherTypeId, new VoucherTypeWithQuantity(voucherTypeId,quantity));
            join(map);
        }
    }

	public void removeVoucherType(Long voucherTypeId) {
		if (voucherTypeId != null) {
			Map<Long, VoucherTypeWithQuantity> map = this.getVoucherTypeWithQuantity();
			map.remove(voucherTypeId);
			join(map);
		}
	}

	public static void main(String[] args) {
		VoucherConfigureRo ro = new VoucherConfigureRo();
		ro.setVoucherTypes("1:1,2:3,3");
		Map<Long,VoucherTypeWithQuantity> result = ro.getVoucherTypeWithQuantity();
		for (VoucherTypeWithQuantity obj : result.values()) {
			System.out.println(obj.toString());
		}
		ro.removeVoucherType(1l);
		System.out.println(ro.voucherTypes);
		ro.addVoucherTypes(100L, 2);
		System.out.println(ro.voucherTypes);	

		ro.addVoucherTypes(1L, 2);
		System.out.println(ro.voucherTypes);	
		
		ro.addVoucherTypes(1L, 20);
		System.out.println(ro.voucherTypes);
	}

	@Override
	public int compareTo(VoucherConfigureRo o) {
		return 0;
	}
	
	
}
