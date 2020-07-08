package com.geo.source.csv;

import com.geo.source.csv.annotation.CsvCell;
import com.geo.source.csv.annotation.CsvRow;
import com.geo.source.csv.dto.CsvFileInfo;
import lombok.Data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author YanZhen
 * @date 2020/03/16 11:45
 **/
public class CsvTest {
    /**
     * 模拟数据库
      */
    static Map<String, Integer> DATA_MAP = new HashMap<String, Integer>() {{
        for (int i = 0; i < 100000; i++) {
            put("k"+i, i);
        }
    }};

    public static void main(String[] args) {
        long s = System.currentTimeMillis();
        method1();
//        method2();
        System.out.println(System.currentTimeMillis() - s);
    }

    /**
     * 针对数据源是map的接口
     */
    private static void method2() {
        // 获取数据库数据
        final List<Map<String, Object>> csvObjs = new ArrayList<>();
        DATA_MAP.forEach((s, o) -> {
            final Map<String, Object> obj = new LinkedHashMap<>(2);
            obj.put("k", s);
            obj.put("v", o);
            csvObjs.add(obj);
        });

        Map<String, String> titleMap = new HashMap<>(2);
        titleMap.put("k", "键");
        titleMap.put("v", "值");

        // 导出
        // byte数据
        final CsvFileInfo csvInfo = CsvClient.getCsvByteByMap(csvObjs, "今天", titleMap);
        // 写入文件
        putFile(csvInfo);
    }

    /**
     * 字段测试
     */
    private static void method1() {
        // 获取数据库数据
        final List<CsvObj> csvObjs = new ArrayList<>();
        DATA_MAP.forEach((s, o) -> {
            final CsvObj obj = new CsvObj();
            obj.setK(s);
            obj.setV(o);
            csvObjs.add(obj);
        });

        // 全字段输出文件
        csvExport1(csvObjs);
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // k字段输出文件
        csvExport2(csvObjs);
    }

    /**
     * 自定义字段导出
     * @param csvObjs 数据源
     */
    private static void csvExport2(List<CsvObj> csvObjs) {
        // 获取csv文件流
        final CsvFileInfo csvInfo = CsvClient.getCsvByte(csvObjs, Collections.singletonList((short) 1));
        if (csvInfo == null) {
            System.out.println("No data");
            return;
        }

        // 写入文件
        putFile(csvInfo);
    }

    /**
     * 全字段导出
     * @param csvObjs 数据源
     */
    private static void csvExport1(List<CsvObj> csvObjs) {
        // 获取csv文件流
        final CsvFileInfo csvInfo = CsvClient.getCsvByte(csvObjs);
        if (csvInfo == null) {
            System.out.println("No data");
            return;
        }

        // 写入文件
        putFile(csvInfo);
    }

    /**
     * 写入文件
     * @param csvInfo 文件内容
     */
    private static void putFile(CsvFileInfo csvInfo) {
        try (final FileChannel channel = new FileOutputStream("D:/test/" + csvInfo.getFileName(), true).getChannel()) {
            final ByteBuffer byteBuffer = ByteBuffer.wrap(csvInfo.getFileContent());
            channel.write(byteBuffer);
        } catch (FileNotFoundException e) {
            System.err.println("写入异常：");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Data
    @CsvRow("测试数据")
    static
    class CsvObj {
        @CsvCell(title = "键", index = 1, fieldNo = 1)
        private String k;
        @CsvCell(title = "值", index = 2, fieldNo = 2)
        private Integer v;
    }
}
