package com.geo.source.bar.code;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * 条形码测试
 *
 * @author YanZhen
 * @date 2020/07/28 11:02
 */
public class BarCodeTest {
    public static void main(String[] args) {
        BufferedImage encode = BarCodeUtil.encode("123456789012345678", BarCodeUtil.GREY_COLOR);
        if (encode == null) {
            System.out.println("转换失败");
            return;
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(encode, "png", outputStream);
            byte[] bytes = outputStream.toByteArray();
            String s = Base64.getEncoder().encodeToString(bytes);

            System.out.println("响应结果：" + s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
