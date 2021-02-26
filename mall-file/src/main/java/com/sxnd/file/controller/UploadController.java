package com.sxnd.file.controller;

import com.sxnd.mall.result.ResultInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UploadController {

    @Value("${file.update.path}")
    private String path;
    @Value("${file.image.src}")
    private String src;

    @PostMapping("upload")
    public ResultInfo uploadFile(MultipartFile file) {
        ;

        //判断上传的文件是否为空
        if (file.isEmpty()) {
            return new ResultInfo(1, "文件为空");
        }
        //获取上次的文件名
        String filename = file.getOriginalFilename();
        //文件重命名
        String newname = this.rename(filename);
        //将文件保存到文件夹中
        File file1 = new File(path, newname);
        if (!file1.exists()) {
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //将上传的文件写入新文件中
        try {
            file.transferTo(file1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, Object> data = new HashMap<>();
        data.put("src", src + newname);
        return new ResultInfo(data);
    }

    private String rename(String oldName) {
        String suffix = oldName.substring(oldName.lastIndexOf("."), oldName.length());
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid + suffix;
    }


}
