package com.biz.gbck.smscenter.common;

/**
 * Function: Message POJO
 * Created by david-liu on 3/11/16.
 */
public class Message {

    private String destination;

    private String messageBody;

    private String alidayuTemlateCode;

    private String alidayuTemplateParams;

    public Message() {

    }

    public Message(String destination, String messageBody) {
        this.messageBody = messageBody;
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public String getAlidayuTemlateCode() {
        return alidayuTemlateCode;
    }

    public void setAlidayuTemlateCode(String alidayuTemlateCode) {
        this.alidayuTemlateCode = alidayuTemlateCode;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public String getAlidayuTemplateParams() {
        return alidayuTemplateParams;
    }

    public void setAlidayuTemplateParams(String alidayuTemplateParams) {
        this.alidayuTemplateParams = alidayuTemplateParams;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public  void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Message)) {
            return false;
        }
        return this.equals((Message)obj);
    }

    private boolean equals(Message message) {
        if (message.getMessageBody() == null) {
            if (message.getDestination() == null) {
                return this.messageBody == null && this.destination == null;
            } else {
                return this.messageBody == null && this.destination.equals(message.getDestination());
            }
        } else {
            if (message.getDestination() == null) {
                return this.destination == null && this.messageBody.equals(message.getMessageBody());
            } else {
                return this.destination.equals(message.getDestination()) && this.messageBody.equals(message.getMessageBody());
            }
        }
    }
}
