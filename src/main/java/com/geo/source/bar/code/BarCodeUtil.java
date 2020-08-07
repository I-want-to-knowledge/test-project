package com.geo.source.bar.code;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.oned.Code128Writer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 条形码工具
 *
 * @author YanZhen
 * @date 2020/07/28 12:23
 */
public class BarCodeUtil {
    public static int BLACK_COLOR = MatrixToImageConfig.BLACK;  //黑色
    public static int GREY_COLOR = 0xA9A9A9A9;  //灰色
    public static int WHITE_COLOR = MatrixToImageConfig.WHITE; //白色（背景色默认）

    /**
     * 线条色，背景色
     */
    private static final int onColor = BLACK_COLOR, offColor = WHITE_COLOR;

    /**
     * 生成条形码，线条默认黑色
     *
     * @param contents 数据内容，只支持纯数字
     * @return 图片信息
     */
    public static BufferedImage encode(String contents) {
        return encode(contents, onColor);
    }

    /**
     * 生成条形码，指定线条颜色
     *
     * @param contents  数据内容，只支持纯数字
     * @param lineColor 线条颜色
     * @return 图片信息
     */
    public static BufferedImage encode(String contents, int lineColor) {
        try {
            return encode(contents, lineColor, offColor);
        } catch (IllegalArgumentException e) {
            // 转换内容有问题
            return null;
        }
    }

    /**
     * 为生成的条形码添加水印
     * @param contents 内容
     * @param lineColor 线条颜色
     * @return 条形码
     */
    public static BufferedImage markEncode(String contents, int lineColor) {
        BufferedImage image;
        try {
            image = encode(contents, lineColor, offColor);
            if (image == null) {
                return null;
            }
        } catch (IllegalArgumentException e) {
            // 转换内容有问题
            return null;
        }

        return mark(image, lineColor == GREY_COLOR ? Color.GRAY : Color.BLACK, contents);
    }

    /**
     * 条形码编码
     *
     * @param contents 数字串
     * @return 图片
     */
    private static BufferedImage encode(String contents, int lineColor, int backgroundColor) throws IllegalArgumentException {
        //配置条码参数
        Map<EncodeHintType, Object> hints = new HashMap<>();
        //设置条码两边空白边距为0，默认为10，如果宽度不是条码自动生成宽度的倍数则MARGIN无效
        hints.put(EncodeHintType.MARGIN, 0);

        //为了无边距，需设置宽度为条码自动生成规则的宽度
        int width = new Code128Writer().encode(contents).length;
        //前端可控制高度，不影响识别
        int height = 150;
        //条码放大倍数
        int codeMultiples = 5;
        //获取条码内容的宽，不含两边距，当EncodeHintType.MARGIN为0时即为条码宽度
        int codeWidth = width * codeMultiples;

        /* ZXing 条码边距及总宽度-默认计算规则
        codeWidth: 自定义的条码宽度
        fullWidth: 条码根据编码内容自动生成编码数组长度(new Code128Writer().encode(contents).length)+边距MARGIN
        outputWidth: codeWidth 与 fullWidth 的最大值
        //放大倍数(取整)
        int multiple = outputWidth / fullWidth;
        //边距
        int leftPadding = (outputWidth - (inputWidth * multiple)) / 2;
        生成条码长度为: outputWidth + 2 * leftPadding
         */

        try {

            // 图像数据转换，使用了矩阵转换 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,
                    BarcodeFormat.CODE_128, codeWidth, height, hints);
//            MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream("d:/code39.png"));

            // 转图片，并指定颜色
            return MatrixToImageWriter.toBufferedImage(bitMatrix, new MatrixToImageConfig(lineColor, backgroundColor));
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析条形码
     *
     * @param imgPath 图片地址
     * @return 结果字符串
     */
    public static String decode(String imgPath) {
        BufferedImage image;
        Result result;
        try {
            image = ImageIO.read(new File(imgPath));
            if (image == null) {
                throw new RuntimeException("the decode image may be not exists.");
            }
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
            result = new MultiFormatReader().decode(bitmap, null);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 图片添加水印
     *
     * @param srcImg           需要添加水印的图片的路径
     * @param markContentColor 水印文字的颜色
     * @param waterMarkContent 水印的文字
     */
    private static BufferedImage mark(BufferedImage srcImg, Color markContentColor, String waterMarkContent) {
        // 读取原图片信息
        int srcImgWidth = srcImg.getWidth(null);
        int srcImgHeight = srcImg.getHeight(null);
        // 加水印
        BufferedImage bufImg = new BufferedImage(srcImgWidth, srcImgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bufImg.createGraphics();
        g.drawImage(srcImg, 0, 0, srcImgWidth, srcImgHeight, null);
        // Font font = new Font("Courier New", Font.PLAIN, 12);
        Font font = new Font("宋体", Font.PLAIN, 15);
        g.setColor(Color.WHITE); // 根据图片的背景设置水印颜色
//        g.setBackground(Color.BLACK);// 字体白色背景
//        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 1));

        g.setFont(font);
        // 水印宽和高
        int watermarkLength = getWatermarkLength(waterMarkContent, g);
        int height = g.getFontMetrics(g.getFont()).getHeight();
        // 显示x轴位置
        int x = (srcImgWidth - watermarkLength) / 2;
        g.fillRect(x, srcImgHeight - height, watermarkLength, height);
        g.setColor(markContentColor);
        g.drawString(waterMarkContent, x, srcImgHeight);
        g.dispose();

        return bufImg;
    }

    /**
     * 获取水印文字总长度
     *
     * @param waterMarkContent 水印的文字
     * @param g
     * @return 水印文字总长度
     */
    private static int getWatermarkLength(String waterMarkContent, Graphics2D g) {
        g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
        return g.getFontMetrics(g.getFont()).charsWidth(waterMarkContent.toCharArray(), 0, waterMarkContent.length());
    }
}
