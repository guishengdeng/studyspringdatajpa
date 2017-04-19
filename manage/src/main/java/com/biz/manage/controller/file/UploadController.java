package com.biz.manage.controller.file;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.biz.core.ali.oss.config.OssConfig;
import com.biz.core.ali.oss.util.OssUtil;
import com.biz.core.util.ImageUtil;
import com.biz.support.web.handler.JSONResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
@RequestMapping("/upload")
public class UploadController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String UPLOAD_STREAM_PARAM = "base64stream"; //base64方式上传

	private static final String UPLOAD_NAME_PARAM = "key"; //文件方式上传

	@Autowired
	private OssConfig config;

	@Autowired
	private OSSClient ossClient;

	@RequestMapping(value = "fileUpload", method = RequestMethod.POST)
	public JSONResult fileUpload(MultipartFile file, HttpServletRequest request) {
		String key = request.getParameter(UPLOAD_NAME_PARAM);
		try {
			PutObjectRequest req = new PutObjectRequest(config.getBucketName(), key, file.getInputStream
					());
			OssUtil.putObject(ossClient, req);
			return new JSONResult(0,"上传成功");
		} catch (Exception e) {
			logger.error("上传失败", e);
			return new JSONResult(1,"上传失败");
		}
	}

	@RequestMapping(value = "streamUpload", method = RequestMethod.POST)
	@ResponseBody
	public JSONResult upload(HttpServletRequest request) {
		String base64stream = request.getParameter(UPLOAD_STREAM_PARAM);
		String key = UUID.randomUUID().toString();
		try {
			PutObjectRequest req = new PutObjectRequest(config.getBucketName(), key, ImageUtil.base64stream2image
					(base64stream));
			OssUtil.putObject(ossClient, req);
			return new JSONResult(0, "上传成功");
		} catch (Exception e) {
			logger.error("上传失败", e);
			return new JSONResult(1, "上传失败");
		}
	}


}
