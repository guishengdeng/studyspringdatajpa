package com.biz.manage.tag;

import com.biz.core.ali.oss.config.OssConfig;
import com.biz.core.ali.oss.util.OssUtil;
import com.biz.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Objects;

@Component
public class OssUrlTag extends TagSupport {

	private static final long serialVersionUID = -2123253186591293149L;

	private String objectName;

	private String type = "product";

	@Override
	public int doStartTag() throws JspException {

		String url = objectName;
		OssConfig ossConfig = SpringContextUtil.getBean(OssConfig.class);
		if (Objects.equals(type, "product")) {
			url = OssUtil.getOssResourceUri(ossConfig.getProductBucketName(), ossConfig.getRemoteEndpoint(), objectName);
		} else if (Objects.equals(type, "qualification") || Objects.equals(type, "audit")) {
			url = OssUtil.getOssResourceUri(ossConfig.getAuditBucketName(), ossConfig.getRemoteEndpoint(), objectName);
		}
		JspWriter out = pageContext.getOut();
		try {
			out.write(url);
			return SKIP_BODY;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	/**
	 * {@linkplain OssUrlTag#objectName}
	 */
	public void setObjectName(String objectName) {

		this.objectName = objectName;
	}

	/**
	 * {@linkplain OssUrlTag#type}
	 */
	public void setType(String type) {

		this.type = type;
	}
}