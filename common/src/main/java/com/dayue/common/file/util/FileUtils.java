package com.dayue.common.file.util;

import com.dayue.common.file.service.FileServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author zhengdayue
 * @time 2022/4/24 22:37
 */
public class FileUtils {

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    /**
     * 保存文件
     *
     * @param multipartFile spring multipartFile
     * @param parent 上级目录
     * @param child 子目录
     */
    public static void saveFile(MultipartFile multipartFile, String parent, String child) {
        File file = new File(parent, child);
        if (!file.getParentFile().exists()) {
            // mkdirs和mkdir有区别，mkdirs支持创建多级目录
            boolean mkdir = file.getParentFile().mkdirs();
            if (!mkdir) {
                log.error("创建目录失败");
                return;
            }
        }
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.error("io异常e：", e);
        }
    }
}
