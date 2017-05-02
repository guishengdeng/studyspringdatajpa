package com.biz.gbck.vo.org;

import com.biz.gbck.dao.mysql.po.org.CompanyGroupPo;
import org.codelogger.utils.CollectionUtils;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author: liubin
 * @date 4/27/17 14:34
 */
public class CompanyGroupReqVo {

    private Long id;

    @NotNull(message = "名字不能为空")
    @NotBlank(message = "名字不能为空")
    private String name;

    @NotNull(message = "编码不能为空")
    @NotBlank(message = "编码不能为空")
    private String code;

    private List<Long> childrenIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Long> getChildrenIds() {
        return childrenIds;
    }

    public void setChildrenIds(List<Long> childrenIds) {
        this.childrenIds = childrenIds;
    }

}
