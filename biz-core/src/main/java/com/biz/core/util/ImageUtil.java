package com.biz.core.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * 图片处理工具类
 */
public class ImageUtil {

	public static InputStream base64stream2image(String base64stream) throws IOException {
		byte[] bytes = Base64.getDecoder().decode(base64stream.split(",")[1]);
		return new ByteArrayInputStream(bytes);
	}
}
