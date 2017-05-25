package com.biz.gbck.vo.platform;

/**
 * PlatFormRespVo
 *
 * @author guisheng.deng
 * @date 2017年05月19日
 * @reviewer
 * @description
 * @see
 */
public class PlatFormRespVo  {
    private Long id;
    private String  platFormName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlatFormName() {
        return platFormName;
    }

    public void setPlatFormName(String platFormName) {
        this.platFormName = platFormName;
    }
}