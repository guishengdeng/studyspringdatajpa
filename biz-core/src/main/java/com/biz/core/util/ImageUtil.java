package com.biz.core.util;

import com.google.common.base.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 图片处理工具类
 */
public class ImageUtil {

    public static Base64stream2imageTransResult base64stream2image(String base64stream) throws IOException {
        String[] streamArray = base64stream.split(",");
        Preconditions.checkArgument(streamArray.length == 2);
        byte[] bytes = Base64.getDecoder().decode(streamArray[1]);
        Pattern pattern = Pattern.compile("(gif|bmp|png|jpg|jpeg)");
        Matcher matcher = pattern.matcher(streamArray[0]);
        boolean matched = matcher.find();
        Preconditions.checkArgument(matched, String.format("未知的图片类型: %s", matcher.group()));
        Base64stream2imageTransResult result = new Base64stream2imageTransResult();
        result.imagePattern = matcher.group();
        result.stream = new ByteArrayInputStream(bytes);
        return result;
    }

    public static class Base64stream2imageTransResult {
        public InputStream stream;

        public String imagePattern;
    }
}
