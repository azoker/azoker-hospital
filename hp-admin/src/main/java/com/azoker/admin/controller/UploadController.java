package com.azoker.admin.controller;

import com.azoker.result.Result;
import com.azoker.utils.TencentCOS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by zxd on 2023/7/7
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    private Logger log= LoggerFactory.getLogger(UploadController.class);

    //配置上传文件的后缀
    private static final List<String> EXT_LIT= Arrays.asList(".jpg",".png",".gif");


    @RequestMapping(value = "/image",method = RequestMethod.POST)
    public Result upload(@RequestParam MultipartFile image) throws IOException {

        //1.获取原来的名字
        String originalFilename = image.getOriginalFilename();
        log.debug("originalFilename:{}",originalFilename);

        //2.获取后缀
        String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
        log.debug("上传文件的后缀:{}",extName);

        //3.判断类型是否合法
        if(!EXT_LIT.contains(extName)){
            log.info("上传图片格式不合法!");
            return Result.buildFail(20001,"上传图片格式不合法!");
        }

        //4.生成新的名字
        String picName = UUID.randomUUID().toString().replace("-", "")+extName;
        log.debug("生成的图片名称为:{}",picName);

        //5.创建临时File对象
        File tempFile = File.createTempFile("image-", picName);

        //6.把图片存储到临时的File对象中
        image.transferTo(tempFile);

        //7.把临时的File对象通过腾讯云api上传到腾讯服务器
        String fileName = TencentCOS.uploadfile(tempFile, "hospital");

        //8.删除临时的file对象
        if(tempFile.exists()){
            tempFile.delete();
        }

        //9.返回图片的访问地址
        return Result.buildSuccess("https://tenement-1324629567.cos.ap-chengdu.myqcloud.com/"+fileName);
    }



}
