package com.geo.source.testmain.fast_dfs;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import java.io.IOException;

/**
 * @Author Administrator
 * @Date 2019/07/15 18:46
 * @Desriotion TODO
 **/
@Slf4j
public class Java_Tools {
    public static void main(String[] args) {
        String img = "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";
        uploadFile(img.getBytes(), "png", "img.png");
    }

    private static String uploadFile(byte[] byteFile, String extFile, String fileName) {

        // 拼接服务区的文件路径
        StringBuffer sbPath = new StringBuffer();
        sbPath.append("http://180.167.180.242:14002/image");
        try {
            // 初始化文件资源
            ClientGlobal.initByProperties("application.properties");

            // 链接FastDFS服务器，创建tracker和Stroage
            TrackerClient trackerClient = new TrackerClient();
            TrackerServer trackerServer = trackerClient.getConnection();

            String storageServerIp = getStorageServerIp(trackerClient, trackerServer);
            StorageServer storageServer = getStorageServer(storageServerIp);
            StorageClient storageClient = new StorageClient(trackerServer, storageServer);

            //利用字节流上传文件
//            NameValuePair[] nvps = new NameValuePair[1];
//            nvps[0] = new NameValuePair(file_Name, ext_file);
            String[] strings = storageClient.upload_file(byteFile, extFile, null);

            sbPath.append(StringUtils.join("/", strings));
            log.debug("上传路径=" + sbPath.toString());
        } catch (IOException | MyException e) {
            e.printStackTrace();
        }
        return sbPath.toString();
    }

    /**
     * 得到Storage服务
     *
     * @param storageIp
     * @return 返回Storage服务
     */
    private static StorageServer getStorageServer(String storageIp) {
        StorageServer storageServer = null;
        if (storageIp != null && !("").equals(storageIp)) {
            try {
                // ip port store_path下标
                storageServer = new StorageServer(storageIp, 23000, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.debug("——storage server生成");
        return storageServer;
    }

    /**
     * 获得可用的storage IP
     *
     * @param trackerClient
     * @param trackerServer
     * @return 返回storage IP
     */
    private static String getStorageServerIp(TrackerClient trackerClient, TrackerServer trackerServer) {
        String storageIp = null;
        if (trackerClient != null && trackerServer != null) {
            try {
                StorageServer storageServer = trackerClient.getStoreStorage(trackerServer, "group1");
                storageIp = storageServer.getSocket().getInetAddress().getHostAddress();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.debug("——获取组中可用的storage IP——" + storageIp);
        return storageIp;
    }
}
