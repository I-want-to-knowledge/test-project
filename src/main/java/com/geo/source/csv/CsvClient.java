package com.geo.source.csv;

import com.geo.source.csv.annotation.CsvCell;
import com.geo.source.csv.annotation.CsvRow;
import com.geo.source.csv.dto.CsvFileInfo;
import com.geo.source.csv.service.FieldAdaptor;
import com.geo.source.csv.service.impl.DefaultFieldAdaptorImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.http.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * 生成csv文件
 *
 * @author YanZhen
 * @date 2020/03/16 11:04
 **/
public class CsvClient {
    private static Logger log = LoggerFactory.getLogger(CsvClient.class);

    /**
     * csv导出最大行数
     */
    public static final int MAX_ROW_NUMBER = 100_000;

    /**
     * 数据库一次输出最大条数
     */
    public static final int MAX_SQL_NUMBER = 1000;

    /**
     * Excel正确显示数字的最大位数
     */
    public static final int MAX_EXCEL_SHOW_BITS = 11;

    /**
     * 纯数字正则表达式
     */
    public static final String NUMBER_EXPRESSION = "^[0-9]*$";

    /**
     * 获取csv文件流
     *
     * @param ts  数据源
     * @param <T> 数据源类型
     * @return csv文件流
     */
    public static <T> CsvFileInfo getCsvByte(List<T> ts) {
        return getCsvByte(ts, null);
    }

    /**
     * 获取csv文件信息
     *
     * @param <T>      ts 数据源
     * @param fileName 文件名
     * @param titleMap 标题名
     * @return 编码后字符串
     */
    public static <T> CsvFileInfo getCsvByteByMap(List<Map<String, T>> ts, String fileName, Map<String, String> titleMap) {
        return doExportByMap(ts, fileName, titleMap);
    }

    /**
     * 获取csv文件流
     *
     * @param ts       数据源
     * @param fieldNos 数据源中需要筛选的字段编号
     * @param <T>      数据源类型
     * @return csv文件流
     */
    public static <T> CsvFileInfo getCsvByte(List<T> ts, List<Short> fieldNos) {
        if (ts == null || ts.isEmpty()) {
            log.warn("csv导出失败，无数据导出！");
            return null;
        } else if (ts.size() > MAX_ROW_NUMBER) {
            log.warn("csv导出失败，数据量太大（最大十万条，实际{}条）！", ts.size());
            return null;
        }

        // 对象
        final Class<?> tClazz = ts.get(0).getClass();

        // 获取文件名
        String fileName;
        // 扩展名
        String ext = ".csv";
        // 文件全称
        String fileExtName;
        if (tClazz.isAnnotationPresent(CsvRow.class)) {
            final CsvRow csvRow = tClazz.getDeclaredAnnotation(CsvRow.class);
            fileName = csvRow.value() + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            fileExtName = fileName + ext;
        } else {
            throw new IllegalArgumentException("类上缺少@CsvRow注释");
        }

        // 获取字段值
        final List<Field> fields = getFields(fieldNos, tClazz);
        if (fields == null || fields.isEmpty()) {
            throw new IllegalArgumentException("字段上缺少@CsvCell注释");
        }

        final StringJoiner csvFile = new StringJoiner("\r\n");
        // csv数据标题
        final StringJoiner titleRow = new StringJoiner(",");
        for (Field field : fields) {
            titleRow.add(field.getDeclaredAnnotation(CsvCell.class).title());
        }
        csvFile.merge(titleRow);

        // 生成csv格式的数据
        for (T t : ts) {
            final StringJoiner row = new StringJoiner(",");
            for (Field field : fields) {
                field.setAccessible(true);
                row.add(getFieldValue(t, field));
            }
            csvFile.merge(row);
        }

        final byte[] b = csvFile.toString().getBytes(StandardCharsets.UTF_8);

        // 赋值文件信息
        return new CsvFileInfo(b, b.length, fileExtName);
    }

    /**
     * 获取对象中的字段值，可筛选对象中的字段（需要使用@CsvCell注解，指定字段编号）
     *
     * @param fieldNos 字段编号
     * @param tClazz   对象类
     * @return 筛选后字段
     */
    private static List<Field> getFields(List<Short> fieldNos, Class<?> tClazz) {
        final Field[] declaredFields = tClazz.getDeclaredFields();
        final List<Field> fields;
        // 字段全部显示
        if (fieldNos == null || fieldNos.isEmpty()) {
            fields = Arrays.stream(declaredFields)
                    .filter(field -> field.isAnnotationPresent(CsvCell.class))
                    .sorted(Comparator.comparingInt(o -> o.getDeclaredAnnotation(CsvCell.class).index()))
                    .collect(Collectors.toList());

            // 自定义显示的字段
        } else {
            fields = Arrays.stream(declaredFields)
                    .filter(field -> field.isAnnotationPresent(CsvCell.class) && fieldNos.contains(field.getDeclaredAnnotation(CsvCell.class).fieldNo()))
                    .sorted(Comparator.comparingInt(o -> o.getDeclaredAnnotation(CsvCell.class).index()))
                    .collect(Collectors.toList());
        }
        return fields;
    }

    /**
     * 获取对象的字段值
     *
     * @param <T>   t 数据源对象
     * @param field 对象的字段信息
     * @return 字段对应的值
     */
    private static <T> String getFieldValue(T t, Field field) {
        try {
            final CsvCell csvCell = field.getDeclaredAnnotation(CsvCell.class);
            final FieldAdaptor fieldAdaptor = csvCell.valueAdaptor().newInstance();
            String value = fieldAdaptor.process(field.get(t));

            // csv都转成字符串格式
            return symbolManipulation(value);
        } catch (IllegalAccessException | InstantiationException e) {
            log.error("字段获取错误！", e);
            return "";
        }
    }

    /**
     * 字段值中的格式处理
     *
     * @param value 字段值
     * @return 处理后的格式
     */
    private static String symbolManipulation(String value) {
        final String s = "\"";
        final String d = ",";
        final boolean b = value.contains(s);
        // 有双引号时要替换成两个
        if (b) {
            final String s1 = "\"\"";
            value = value.replace(s, s1);
        }

        // 有英文逗号、双引号时要两边加双引号
        if (b || value.contains(d)) {
            value = s + value + s;
        }
        return value;
    }

    /**
     * 针对Map对象的导出
     *
     * @param ts       数据源
     * @param fileName 文件名
     * @param titleMap 标题信息
     * @param <T>      只支持进本类型，（Number、String、Date、..）
     * @return CSV的文件信息
     */
    private static <T> CsvFileInfo doExportByMap(List<Map<String, T>> ts, String fileName, Map<String, String> titleMap) {
        Asserts.notNull(titleMap, "Title");
        fileName = Objects.toString(fileName, "");
        if (CollectionUtils.isEmpty(ts)) {
            log.warn("csv导出失败，无数据导出！");
            return null;
        } else if (ts.size() > MAX_ROW_NUMBER) {
            log.warn("csv导出失败，数据量太大（最大十万条，实际{}条）！", ts.size());
            return null;
        }

        // 扩展名
        String ext = ".csv";
        // 文件名
        fileName += new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        // 文件全称
        String fileExtName = fileName + ext;

        // csv文件内容
        final StringJoiner csvFile = new StringJoiner("\r\n");

        // csv数据标题
        // key值
        final Set<String> keys = ts.get(0).keySet();
        final StringJoiner titleRow = new StringJoiner(",");
        for (String field : keys) {
            titleRow.add(titleMap.get(field));
        }
        csvFile.merge(titleRow);

        // 生成csv格式的数据
        FieldAdaptor adaptor = new DefaultFieldAdaptorImpl();
        for (Map<String, T> t : ts) {
            final StringJoiner row = new StringJoiner(",");
            t.forEach((k, v) -> row.add(symbolManipulation(adaptor.process(v))));
            csvFile.merge(row);
        }

        // 文件的byte信息
        final byte[] b = csvFile.toString().getBytes(StandardCharsets.UTF_8);

        // 设置文件格式
        return new CsvFileInfo(b, b.length, fileExtName);
    }
}
