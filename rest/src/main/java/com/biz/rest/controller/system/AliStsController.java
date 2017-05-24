package com.biz.rest.controller.system;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import com.biz.core.ali.oss.config.OssConfig;
import com.biz.rest.controller.BaseRestController;
import com.biz.rest.vo.AliStsVO;
import org.codelogger.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by defei on 5/19/17.
 */
@RestController
@RequestMapping("sts")
public class AliStsController extends BaseRestController {

	public static final String REGION_CN_BEIJING = "cn-beijing";
	// 当前 STS API 版本
	public static final String STS_API_VERSION = "2015-04-01";

	@Autowired
	private OssConfig ossConfig;

	@RequestMapping("oss")
	public AliStsVO getOssToken() throws ClientException {
		if (logger.isDebugEnabled()) {
			logger.debug("received oss sts request.");
		}
		AssumeRoleResponse.Credentials newSts = getNewSts();
		return new AliStsVO(newSts.getAccessKeyId(), newSts.getAccessKeySecret(), newSts.getSecurityToken(), DateUtils
		  .getDateOfHoursBack(-8, DateUtils.getDateFromString(newSts.getExpiration(), "yyyy-MM-dd'T'HH:mm:ss'Z'")).getTime());
	}

	private AssumeRoleResponse.Credentials getNewSts() throws ClientException {
		DefaultProfile profile = DefaultProfile.getProfile(REGION_CN_BEIJING, ossConfig.getAccessKeyId(), ossConfig.getAccessSecret());

		DefaultAcsClient client = new DefaultAcsClient(profile);
		// 创建一个 AssumeRoleRequest 并设置请求参数
		final AssumeRoleRequest request = new AssumeRoleRequest();
		request.setVersion(STS_API_VERSION);
		request.setMethod(MethodType.POST);
		request.setProtocol(ProtocolType.HTTPS);
		request.setRoleArn("acs:ram::" + ossConfig.getUserId() + ":role/gbck-test");
		request.setRoleSessionName("gbck-test-sts");
		request.setPolicy("{\"Statement\":[{\n" + "      \"Action\": \"oss:*\",\n" + "      \"Effect\": \"Allow\",\n"
		  + "      \"Resource\": [\n" + "        \"acs:oss:*:*:gbck-test\",\n" + "        \"acs:oss:*:*:gbck-test/*\",\n"
		  + "        \"acs:oss:*:*:gbck-test-qualification\",\n" + "        \"acs:oss:*:*:gbck-test-qualification/*\"\n" + "      ]\n" + "    },\n"
		  + "    {\n" + "      \"Action\": \"oss:ListBuckets\",\n" + "      \"Effect\": \"Allow\",\n" + "      \"Resource\": \"acs:oss:*:*:*\"\n"
		  + "    }\n" + "  ],\n" + "  \"Version\": \"1\"\n" + "}");
		// 发起请求，并得到response
		final AssumeRoleResponse response = client.getAcsResponse(request);
		return response.getCredentials();
	}
}
