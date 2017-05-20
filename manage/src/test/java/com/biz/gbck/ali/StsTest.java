package com.biz.gbck.ali;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.auth.Credential;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.sts.model.v20150401.AssumeRoleRequest;
import com.aliyuncs.sts.model.v20150401.AssumeRoleResponse;
import org.codelogger.utils.DateUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created by defei on 5/12/17.
 */
public class StsTest {

	public static final String REGION_CN_BEIJING = "cn-beijing";
	// 当前 STS API 版本
	public static final String STS_API_VERSION = "2015-04-01";

	@Ignore
	@Test
	public void testSTS() throws ClientException {
		DefaultProfile profile = DefaultProfile.getProfile(REGION_CN_BEIJING, "LTAIcBsZ7zY66tku", "MoEHemVQhg84mDvcdkVkix2GfCUiVB");

		DefaultAcsClient client = new DefaultAcsClient(profile);
		// 创建一个 AssumeRoleRequest 并设置请求参数
		final AssumeRoleRequest request = new AssumeRoleRequest();
		request.setVersion(STS_API_VERSION);
		request.setMethod(MethodType.POST);
		request.setProtocol(ProtocolType.HTTPS);
		request.setRoleArn("acs:ram::1526565267726025:role/gbck-test");
		request.setRoleSessionName("testSTS");
		request.setPolicy("{\n" + "  \"Statement\": [\n" + "    {\n" + "      \"Action\": \"oss:*\",\n" + "      \"Effect\": \"Allow\",\n"
		  + "      \"Resource\": [\n" + "        \"acs:oss:*:*:gbck-test\",\n" + "        \"acs:oss:*:*:gbck-test/*\",\n"
		  + "        \"acs:oss:*:*:gbck-test-qualification\",\n" + "        \"acs:oss:*:*:gbck-test-qualification/*\"\n" + "      ]\n" + "    },\n"
		  + "    {\n" + "      \"Action\": \"oss:ListBuckets\",\n" + "      \"Effect\": \"Allow\",\n" + "      \"Resource\": \"acs:oss:*:*:*\"\n"
		  + "    }\n" + "  ],\n" + "  \"Version\": \"1\"\n" + "}");
		// 发起请求，并得到response
		final AssumeRoleResponse response = client.getAcsResponse(request);

		System.out.println("Expiration: " + response.getCredentials().getExpiration());
		System.out.println("SecurityToken: " + response.getCredentials().getSecurityToken());
		System.out.println("AccessKeyId: " + response.getCredentials().getAccessKeyId());
		System.out.println("AccessKeySecret: " + response.getCredentials().getAccessKeySecret());
	}

	@Ignore
	@Test
	public void test1() throws ParseException {

		String time = "2017-05-19T08:09:44Z";
		Date date = DateUtils.getDateOfHoursBack(-8, DateUtils.getDateFromString(time, "yyyy-MM-dd'T'HH:mm:ss'Z'"));
		System.out.println(DateUtils.getDateFormat(date, "yyyy0-MM-dd HH:mm:ss"));
	}

}
