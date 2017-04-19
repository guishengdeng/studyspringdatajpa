package com.biz.gbck.dao.mysql.converter;

import com.biz.core.util.JsonUtil;
import com.biz.gbck.dao.mysql.po.vendor.VendorResource;
import java.util.Set;
import javax.persistence.AttributeConverter;

/**
 * @author yanweijin
 * @date 2017/3/17
 */
public class VendorResourceConverter implements AttributeConverter<Set<VendorResource>, String> {
    @Override
    public String convertToDatabaseColumn(Set<VendorResource> attribute) {
        if (attribute == null) return null;
        return JsonUtil.obj2Json(attribute);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<VendorResource> convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return JsonUtil.json2Obj(dbData, Set.class, VendorResource.class);
    }
}
