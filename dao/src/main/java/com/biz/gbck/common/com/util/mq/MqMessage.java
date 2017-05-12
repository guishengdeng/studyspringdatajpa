package com.biz.gbck.common.com.util.mq;


import com.biz.gbck.common.com.setvice.mq.Message;

/**
 * Created by david-liu on 2016/03/29 10:55.
 */
public class MqMessage {

    public static final class Builder {

        private Message message = new Message();

        public Builder() {

        }
        public MqMessage.Builder setMessageDestination(String destination) {
            this.message.setDestination(destination);
            return this;
        }

        public MqMessage.Builder setMessageBody(String messageBody) {
            this.message.setMessageBody(messageBody);
            return this;
        }

        public MqMessage.Builder setAlidayuTemplateCode(String alidayuTemplateCode) {
            this.message.setAlidayuTemlateCode(alidayuTemplateCode);
            return this;
        }

        public MqMessage.Builder setAlidayuTemplateParams(String alidayuTemplateParams) {
            this.message.setAlidayuTemplateParams(alidayuTemplateParams);
            return this;
        }

        public Message build() {
            return this.message;
        }
    }
}
