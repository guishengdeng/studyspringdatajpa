package com.biz.gbck.vo.stock;

import com.biz.gbck.enums.oss.OssType;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by lei on 7/26/16.
 */
public class MnsOssMessageVo implements Serializable {
    private static final long serialVersionUID = -5624414887665739429L;

    @NotNull(message = "oss bucket不能为空")
    private String bucket;

    @NotNull(message = "oss key不能为空")
    private String key;

    @NotNull(message = "oss md5不能为空")
    private String md5;

    private OssType type;

    @NotNull(message = "oss time不能为空")
    private Timestamp time;

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public OssType getType() {
        return type;
    }

    public void setType(OssType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }


}


//{"bucket":"stock-all","key":"stock.txt","md5":"687c515999667582efc38df13c7920db","time":1469255588873}

