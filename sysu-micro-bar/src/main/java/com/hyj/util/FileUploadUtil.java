
package com.hyj.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


/**
 * Created by Administrator on 2016/5/26 0026.
 */

public class FileUploadUtil {
    private static final Logger logger = LoggerFactory
            .getLogger(FileUploadUtil.class);



    public static boolean uploadFiles(MultipartFile[] files, String rootPath) {
        logger.info(rootPath);
        for (MultipartFile file : files) {
            logger.info("文件长度: " + file.getSize());
            logger.info("文件类型: " + file.getContentType());
            logger.info("文件名称: " + file.getName());
            logger.info("文件原名: " + file.getOriginalFilename());
            logger.info("========================================");
            try {
                byte[] bytes = file.getBytes();
                File dir = new File(rootPath + File.separator + "multipart");
                if (!dir.exists())
                    dir.mkdirs();
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + file.getOriginalFilename());
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                logger.info("Server File Location="
                        + serverFile.getAbsolutePath());
            } catch (Exception e) {
                logger.error("You failed to upload => " + e.getMessage());
                return false;
            }
        }
        return true;
    }
}
