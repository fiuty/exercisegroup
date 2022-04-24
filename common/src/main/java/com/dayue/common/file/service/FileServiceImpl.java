package com.dayue.common.file.service;

import com.dayue.common.file.config.FileConfiguration;
import com.dayue.common.file.util.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author zhengdayue
 * @time 2022/4/24 22:30
 */
@Service
public class FileServiceImpl implements FileService {

    private static final Logger log = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private FileConfiguration fileConfiguration;

    @Override
    public void upLoadFiles(MultipartFile multipartFile) {
        checkRequestParam(multipartFile);
        FileUtils.saveFile(multipartFile, fileConfiguration.getPathPrefix(), multipartFile.getOriginalFilename());
    }

    private void checkRequestParam(MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            log.error("空文件");
        }
        String fileName = multipartFile.getOriginalFilename();
        if (StringUtils.isEmpty(fileName)){
            log.error("文件名为空");
        }
        // 上传文件总的最大值spring.servlet.multipart.max-request-size=500MB
        // 单个文件的最大值spring.servlet.multipart.max-file-size=500MB
        if (multipartFile.getSize() > fileConfiguration.getMaxSize()) {
            log.error("文件超过10M");
        }
    }

    @Override
    public void downloadFiles(String name, HttpServletResponse response) {
        OutputStream outputStream = null;
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        byte[] bytes = new byte[1024];
        // 获取输出流
        File file = new File(fileConfiguration.getPathPrefix() + "\\" + name);
        try {
            // 设置文件名
            response.setHeader("Content-Disposition", "attachment;filename=" +
                    new String(name.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            fileInputStream = new FileInputStream(file);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            outputStream = response.getOutputStream();
            int i = bufferedInputStream.read(bytes);
            while (i != -1) {
                outputStream.write(bytes, 0, i);
                i = bufferedInputStream.read(bytes);
            }
        } catch (IOException e) {
            log.error("io异常e：", e);
        } finally {
            try {
                if (fileInputStream!=null){
                    fileInputStream.close();
                }
                if (outputStream!=null){
                    outputStream.close();
                }
                if (bufferedInputStream!=null){
                    bufferedInputStream.close();
                }
            } catch (IOException e) {
                log.error("io异常e：", e);
            }
        }
    }
}
