package com.biz.gbck.vo.stock;

import com.biz.gbck.enums.oss.OssType;
import java.io.Serializable;
import java.sql.Timestamp;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 中台门店全量库存Vo
 *
 * @author lei
 * @date 2016年12月12日
 * @reviewer
 * @see
 */
public class MnsFullDepotStockVo implements Serializable {
    private static final long serialVersionUID = -4927404922951168817L;

    /**
     * 门店编号
     */
    private String mcuCode;

    /**
     * 商品编号
     */
    private String productCode;

    /**
     * 库存
     */
    private long stock;

    /**
     * 更新时间
     */
    private Timestamp time;

    /**
     * 版本
     */
    private Integer version;

    /**
     * 库存类型
     */
    private OssType type;

    public OssType getType() {
        return type;
    }

    public void setType(OssType type) {
        this.type = type;
    }

    public String getMcuCode() {
        return mcuCode;
    }

    public void setMcuCode(String mcuCode) {
        this.mcuCode = mcuCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = new Timestamp(time.getTime() * 1000);
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    //    public static void main(String[] args) {
    //        String a = "{\"mcuCode\":\"X002\",\"productCode\":\"1009625\",\"stock\":6,\"time\":1469269890,
    // \"version\":1}";
    //        DepotStockVo depotStockVo = JsonUtil.json2Obj(a, DepotStockVo.class);
    //        System.out.println(depotStockVo);
    //    }
}
