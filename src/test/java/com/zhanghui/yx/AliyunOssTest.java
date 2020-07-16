package com.zhanghui.yx;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.net.URL;
import java.util.Date;

@Slf4j
public class AliyunOssTest {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private String endpoint = "https://oss-cn-beijing.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。
    // 强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
    private String accessKeyId = "LTAI4GKk9sRLQBP25dBYBmkE";
    private String accessKeySecret = "M7B1dHqF5leZOYrHfxZVRyfofG9sfp";
    private String bucketName = "yingx-zh";

    @Test
    public void deleteFile() {

        String bucketName = "yingx-zh";  //存储空间名
        String objectName = "cccc/1593830947244图片1.png";  //文件名

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(bucketName, objectName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void video() {
        String bucketName = "yingx-zh";  //存储空间名
        String objectName = "video/15938507686345.mp4";  //文件名
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        // 设置视频截帧操作。
        String style = "video/snapshot,t_10000,f_jpg,w_800,h_600";
        // 指定过期时间为10分钟。
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10);
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        String s = signedUrl.toString();
        System.out.println(signedUrl);
        // 关闭OSSClient。
        ossClient.shutdown();
        log.info("----------------{}", signedUrl);
        log.info("----s------------{}", s);
    }

}

