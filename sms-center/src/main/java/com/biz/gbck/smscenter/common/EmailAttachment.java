package com.biz.gbck.smscenter.common;

/**
 * FileName: EmailAttachment
 * Description:
 * Author: david-liu
 * CreateTime: 2016-07-14 11:59
 */
public class EmailAttachment {
    /**
     * 附件文件名
     */
    private String attachmentName;

    private String attachmentFileType;

    private byte[] attachmentContent;

    public byte[] getAttachmentContent() {
        return attachmentContent;
    }

    public void setAttachmentContent(byte[] attachmentContent) {
        this.attachmentContent = attachmentContent;
    }

    public String getAttachmentFileType() {
        return attachmentFileType;
    }

    public void setAttachmentFileType(String attachmentFileType) {
        this.attachmentFileType = attachmentFileType;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }
}
