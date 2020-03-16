package com.geo.source.csv.dto;

import lombok.Data;

import java.util.Base64;

/**
 * 上传fastDfs的文件信息
 * @author yan zhen
 * @date 2020/02/10 19:08
 */
@Data
public class CsvFileInfo {

    /**
     * 文件流
     */
    private byte[] fileContent;

    /**
     * base64文件流
     */
    private String fileContentEncode;

    /**
     * 文件大小
     */
    private long fileSize;

    /**
     * 文件名
     */
    private String fileName;

    public CsvFileInfo(byte[] fileContent, long fileSize, String fileName) {
        this.fileContent = fileContent;
        this.fileSize = fileSize;
        this.fileName = fileName;
        this.fileContentEncode = Base64.getEncoder().encodeToString(fileContent);
    }
}
