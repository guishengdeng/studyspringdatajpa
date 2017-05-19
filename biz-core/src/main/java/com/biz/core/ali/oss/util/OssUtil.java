package com.biz.core.ali.oss.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.biz.core.ali.oss.BucketResVo;
import com.biz.core.ali.oss.config.OssConfig;
import com.biz.core.exceptions.BizSystemException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Oss工具类
 */
public class  OssUtil {
    private static Logger logger = LoggerFactory.getLogger(OssUtil.class);

    @Autowired
    private static OssConfig config;
    /**
     * oss服务端异常公共方法
     *
     * @param oe oss异常
     * @throws BizSystemException 业务系统异常
     */
    private static void OSSExceptionLoggerPrint(OSSException oe) throws BizSystemException {
        logger.error("oss服务端异常...\n");
        logger.error("errorCode:\n", oe.getErrorCode());
        logger.error("errorMsg:\n", oe.getErrorMessage());
        logger.error("requestId:\n", oe.getRequestId());
        logger.error("hostId:\n", oe.getHostId());
        throw new BizSystemException(oe.getErrorMessage(), oe);
    }

    /**
     * oss客户端异常处理公共方法
     *
     * @param ce oss客户端异常
     * @throws BizSystemException 业务系统异常
     */
    private static void clientExceptionLoggerPrint(ClientException ce) throws BizSystemException {
        logger.error("oss客户端异常...\n");
        logger.error("errorCode:\n", ce.getErrorCode());
        logger.error("errorMsg:\n", ce.getErrorMessage());
        throw new BizSystemException(ce.getErrorMessage(), ce);
    }

    /**
     * put上传object
     *
     * @param client OSS客户端
     * @param putObjectRequest put上传请求对象
     * @throws BizSystemException 业务系统异常
     * @throws IOException IO异常
     */
    public static void putObject(OSSClient client, PutObjectRequest putObjectRequest) throws BizSystemException, IOException {
        try {
            logger.debug("上传object到名为：" + putObjectRequest.getBucketName() + "的bucket");
            PutObjectResult putObjectResult = client.putObject(putObjectRequest);
            if (putObjectRequest.getCallback() != null) {//如果设置了回调函数
                // 读取上传回调返回的消息内容
                byte[] buffer = new byte[1024];
                putObjectResult.getCallbackResponseBody().read(buffer);
                // 一定要close，否则会造成连接资源泄漏
                putObjectResult.getCallbackResponseBody().close();
            }
        } catch (OSSException oe) {
            OSSExceptionLoggerPrint(oe);
        } catch (ClientException ce) {
            clientExceptionLoggerPrint(ce);

        } finally {
//            client.shutdown();
        }
    }


    /**
     * 获取资源路径
     *
     * @param bucketName bucket名称
     * @param endPoint 资源终端地址
     * @param key 资源名称
     */
    public static String getOssResourceUri(String bucketName, String endPoint, String key) {
    	if(StringUtils.isNotBlank(key)){
    		return String.format("http://%s.%s/%s", bucketName, endPoint, key);
    	}
    	return null;
    }
    
    /**
     * 获取资源路径前缀
     * 
     * 例如https://www.1919.cn.images/
     * 
     * @param bucketName bucket名称
     * @param endPoint 资源终端地址
     * @return 返回资料路径前缀URL
     */
    public static String getOssResourceUriPrefix(String bucketName, String endPoint){
    	return String.format("https://%s.%s/", bucketName, endPoint);
    }

    /**
     *获取隔壁仓库Oss上传bucket集合
     */
    public static List<BucketResVo> getOssBuckets() {
        List<BucketResVo> bucketResVos=newArrayList();
        bucketResVos.add(new BucketResVo("product",config.getProductBucketName()));
        bucketResVos.add(new BucketResVo("audit",config.getAuditBucketName()));
        return bucketResVos;
    }
    
    
    public static void main(String[] args) {

        // key指的是 保存在oss上后的路径+文件名
        // filePath 指的是上传的文件路径
        OSSClient client;

        //参数设置
        //关于这个endPoint，可以参考：http://bbs.aliyun.com/read/149100.html?spm=5176.7189909.0.0.YiwiFw
        String endpoint = "oss-cn-beijing.aliyuncs.com";//青岛的接口
        String accessKeyId = "LTAIK2hRIQcKMXKK";
        String accessKeySecret = "7y9MufvKxd3zsrFyTKZDzFKYr18xZj";
        String bucketName = "1919-bbc-test";
        String key = "test.jpg";//保存在oss上的文件名
        String filePath = "/Users/david-liu/Downloads/images/dota/images.jpeg";//本地或者服务器上文件的路径
        boolean flag = false;
        try {
            client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

            // 获取指定文件的输入流
            File file = new File(filePath);
            InputStream content = new FileInputStream(file);
            // 创建上传Object的Metadata
            ObjectMetadata meta = new ObjectMetadata();
            // 必须设置ContentLength
            meta.setContentLength(file.length());
            // 上传Object.
            PutObjectResult result = client.putObject(bucketName, key, content, meta);
            if (file.isFile() && file.exists()) {
                //                file.delete();
                flag = true;
            }
        } catch (OSSException oe) {
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
            flag = false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        // 打印ETag
        //        System.out.println(result.getETag());
    }

}

