package com.geo.source.testmain.publictest.csv;

import org.apache.commons.beanutils.BeanUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YanZhen
 * @date 2020/07/06 15:34
 */
public class StreamCsvTest {
    @SuppressWarnings("rawtypes")
    public static File createCSVFile(List exportData, LinkedHashMap map, String outPutPath, String fileName) {
        File csvFile = null;
        BufferedWriter csvFileOutputStream = null;
        try {
            File file = new File(outPutPath);
            if (!file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }
            //定义文件名格式并创建
            csvFile = File.createTempFile(fileName, ".csv", new File(outPutPath));
            System.out.println("csvFile：" + csvFile);
            FileOutputStream fileOutputStream = new FileOutputStream(csvFile);
            //加入bom 否则生成的csv文件 用excel乱码
            fileOutputStream.write(new byte[]{(byte) 0xEF, (byte) 0xBB, (byte) 0xBF});
            // UTF-8使正确读取分隔符","
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream
                    , "utf-8");
            csvFileOutputStream = new BufferedWriter(outputStreamWriter, 1024);
            System.out.println("csvFileOutputStream：" + csvFileOutputStream);
            // 写入文件头部
            for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext(); ) {
                java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator.next();
                csvFileOutputStream.write((String) propertyEntry.getValue() != null ? (String) propertyEntry.getValue() : "");
                if (propertyIterator.hasNext()) {
                    csvFileOutputStream.write(",");
                }
            }
            csvFileOutputStream.newLine();
            // 写入文件内容
            for (Iterator iterator = exportData.iterator(); iterator.hasNext(); ) {
                Object row = (Object) iterator.next();
                for (Iterator propertyIterator = map.entrySet().iterator(); propertyIterator.hasNext(); ) {
                    java.util.Map.Entry propertyEntry = (java.util.Map.Entry) propertyIterator
                            .next();
                    //如果是空值则进行处理用 "-" 号填充 否则会抛空指针
                    String va = "-";
                    String property = BeanUtils.getProperty(row, (String) propertyEntry.getKey());
                    if (property != null && !property.equals("")) {
                        csvFileOutputStream.write((String) property);
                    } else {
                        csvFileOutputStream.write((String) va);
                    }
                    if (propertyIterator.hasNext()) {
                        csvFileOutputStream.write(",");
                    }
                }
                if (iterator.hasNext()) {
                    csvFileOutputStream.newLine();
                }
            }
            csvFileOutputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvFileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return csvFile;
    }

    /**
     * java生成csv文件
     */
    public static void createCsvFile(){
        List exportData = new ArrayList<Map>();
        Map row = new LinkedHashMap<String, String>();
        row.put("1",1);
        row.put("2",2);
        row.put("3",3);
        row.put("4",4);
        exportData.add(row);
        //设置列名
        LinkedHashMap map = new LinkedHashMap();
        map.put("1","姓名");
        map.put("2","年龄");
        map.put("3","性别");
        map.put("4","证件");
        //文件名=生产的文件名称
        String fileName = "文件名称";
        String path="D:/test/";
        File file = createCSVFile(exportData, map, path, fileName);
    }

    public static void main(String[] args) {
        createCsvFile();
    }
}

