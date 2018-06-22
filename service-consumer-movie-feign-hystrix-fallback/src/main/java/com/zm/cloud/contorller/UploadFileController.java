package com.zm.cloud.contorller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class UploadFileController {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileController.class);

    @ResponseBody
    @RequestMapping("upload")
    public String upload(@RequestParam(value = "file") MultipartFile file) throws IOException {
        logger.info("upload file");
        File f = new File(file.getOriginalFilename());

        FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(f));

        logger.info("upload success");
        return f.getAbsolutePath();
    }
}
