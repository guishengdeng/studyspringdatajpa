package com.biz.gbck.vo.oms.event;

import com.biz.gbck.enums.oms.InvalidOmsDataType;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 对于中台过来的对象没有通过校验的结果Vo
 *
 * @author zhangcheng
 * @date 2017/1/7
 * @reviewer
 * @see
 */
public class InvalidOmsMessageVo implements Serializable {

    private static final long serialVersionUID = 5849244061802476563L;

    /**
     * 校验结果
     */
    private boolean valid;

    /**
     * 未通过校验vo的类型
     */
    private InvalidOmsDataType type;

    /**
     * 未通过校验oms消息(将Vo序列化进行实现)
     */
    private String content;

    /**
     * 未通过检验的消息集合
     */
    private List<String> messages;

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public InvalidOmsDataType getType() {
        return type;
    }

    public void setType(InvalidOmsDataType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
