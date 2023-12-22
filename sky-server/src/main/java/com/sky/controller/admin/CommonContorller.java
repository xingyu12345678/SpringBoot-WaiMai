package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 */
@RequestMapping("/admin/common")
@RestController
@Slf4j
@Api(tags = "图片上传")
public class CommonContorller {

    @Value("${reggie.path}")
    private String basePath;

    @PostMapping("/upload")
    @ApiOperation("图片上传")
    public Result<String> upload(MultipartFile file){
        log.info("图片上传：{}",file);


        try {
        //原始文件名
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        //使用uuid重新生成文件名
        String fileName = UUID.randomUUID().toString() + suffix;

        //判断目录是否存在
        File file1 = new File(basePath);
        if (!file1.exists()){
            file1.mkdirs();
        }

        String filePath = basePath + fileName;
            File filePath2 =  new File(filePath);

            file.transferTo(filePath2);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }


        return Result.error(MessageConstant.UPLOAD_FAILED);
    }

}
