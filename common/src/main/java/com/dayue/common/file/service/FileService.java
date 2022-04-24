package com.dayue.common.file.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author zhengdayue
 * @time 2022/4/24 22:30
 */
public interface FileService {

    void upLoadFiles(MultipartFile file);

    void downloadFiles(String name, HttpServletResponse response);
}
