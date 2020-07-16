package com.yangbin.service;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by
 *
 * @author 風起雲落乀
 * @Date 2020/7/10/010
 * @Time 18:11
 */
@Service
public class UploadService {
    @Autowired
    private FastFileStorageClient storageClient;
    private static final List<String> CONTENT_TYPES=
            Arrays.asList("image/jpeg", "image/gif","application/x-jpg");
    public String upload(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
       //效验文件类型
        String contentType = file.getContentType();
        if(!CONTENT_TYPES.contains(contentType)){
            System.out.println("文件类型不合法:"+originalFilename);
            return null;
        }
        try {
            BufferedImage read = ImageIO.read(file.getInputStream());
            if(read == null){
                System.out.println("文件内容不合法"+originalFilename);
                return null;
            }
            /*//保存到服务器
            file.transferTo(new File("E:"+File.separator+"images"+File.separator+originalFilename));
       //生成url返回
            return "http://image.leyou.com/"+originalFilename;*/
//获取文件后缀
            String s = StringUtils.substringAfterLast(originalFilename, ".");
            StorePath storePath = this.storageClient.uploadFile(file.getInputStream(),file.getSize(),s,null);
        return "http://image.leyou.com/"+storePath.getFullPath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
