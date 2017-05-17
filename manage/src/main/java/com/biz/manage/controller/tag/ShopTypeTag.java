package com.biz.manage.controller.tag;


import com.biz.gbck.dao.mysql.po.org.ShopTypePo;
import com.biz.gbck.dao.redis.ro.org.ShopTypeRo;
import com.biz.gbck.enums.user.ShopTypeStatus;
import com.biz.service.org.interfaces.ShopTypeService;
import com.biz.util.SpringContextUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

/**
 * Created by dylan on
 */
public class ShopTypeTag extends TagSupport {

    private static final long serialVersionUID = -2123253186591293149L;

    private Long shopTypeId;

    @Override public int doStartTag() throws JspException {

        ShopTypeService shopTypeService = SpringContextUtil.getBean(ShopTypeService.class);
        List<ShopTypeRo> allNormalShopTypes =
            shopTypeService.findAllShopTypeRo(ShopTypeStatus.NORMAL);
        ShopTypePo selectedShopType = shopTypeService.findOne(shopTypeId);
        JspWriter out = pageContext.getOut();
        try {
            for (ShopTypeRo normalShopType : allNormalShopTypes) {
                if (Objects.equals(shopTypeId, normalShopType.getId()) || (selectedShopType != null
                    && Objects
                    .equals(selectedShopType.getStatus(), ShopTypeStatus.NORMAL.getValue())
                    && Objects.equals(selectedShopType.getName(), normalShopType.getName()))) {
                    out.print(
                        format("<option value=\"%s\" selected >%s</option>", normalShopType.getId(),
                            normalShopType.getName()));
                } else {
                    out.print(format("<option value=\"%s\" >%s</option>", normalShopType.getId(),
                        normalShopType.getName()));
                }
            }
            return SKIP_BODY;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.doStartTag();
    }


    public void setShopTypeId(Long shopTypeId) {
        this.shopTypeId = shopTypeId;
    }
}