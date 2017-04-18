package com.biz.gbck.vo.common.response;

import com.biz.gbck.vo.common.BaiduDecodeResultVo;
import java.io.Serializable;

/**
 * 百度地图经纬度查询地址RespVo
 *
 * @author david-liu
 * @date 2016年09月12日
 * @reviewer
 * @see
 */
public class BaiduDecodeRespVo implements Serializable {

    private static final long serialVersionUID = 755792166373967723L;

    private Integer status;

    private BaiduDecodeResultVo result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BaiduDecodeResultVo getResult() {
        return result;
    }

    public void setResult(BaiduDecodeResultVo result) {
        this.result = result;
    }
}
