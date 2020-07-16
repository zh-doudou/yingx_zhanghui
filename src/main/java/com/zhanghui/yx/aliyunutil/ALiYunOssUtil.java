package com.zhanghui.yx.aliyunutil;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;


/**
 * @author 张辉
 * @since 2020-07-03 20:41:15
 */

@Slf4j
public class ALiYunOssUtil {

    /**
     * 创建一个线程保证是同一个连接
     */
    private static ThreadLocal<OSSClient> tl = new ThreadLocal<OSSClient>();
    private volatile static OSSClient ossClient;

    private ALiYunOssUtil() {
    }

    /**
     * 多例
     *
     * @param aliYunVO 自定义实体类对象
     * @return OSS工具类实例
     */
    private static OSSClient getOSSClient(AliYunVO aliYunVO) {
        OSSClient ossClient = tl.get();
        if (ossClient == null) {
            ossClient = (OSSClient) new OSSClientBuilder().build(aliYunVO.getEndpoint(), aliYunVO.getAccessKeyId(), aliYunVO.getAccessKeySecret());
        }
        return ossClient;
    }

    /**
     * 创建存储空间
     */
    public static void createNewBucket(AliYunVO aliYunVO) {
        getOSSClient(aliYunVO);
        // 创建存储空间。
        ossClient.createBucket(aliYunVO.getBucketName());
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * 上传本地文件
     *
     * @param aliYunVO ：封装实例对象
     * @param folder   ；自定义文件夹
     */
    public static void upLoadLocalFile(AliYunVO aliYunVO, String folder) {
        //1.获取文件名
        String filename = aliYunVO.getHeadImg().getOriginalFilename();
        log.info("文件名为：{}", filename);
        //2.文件路径为：
        String localPath = aliYunVO.getLocalPath();
        ossClient = getOSSClient(aliYunVO);
        // 创建PutObjectRequest对象。
        String key = folder + "/" + System.currentTimeMillis();
        PutObjectRequest putObjectRequest = new PutObjectRequest(aliYunVO.getBucketName(), key, new File(localPath));
        ALiYunOssUtil.ossClient.putObject(putObjectRequest);
        //关闭OSSClient
        ALiYunOssUtil.ossClient.shutdown();
    }

    /**
     * 通过网络地址下载到本地
     *
     * @param aliYunVO 自定义实体类
     * @param folder   网络地址
     * @return 返回下载到本地的绝对路径
     */
    public static String DownloadLocal(AliYunVO aliYunVO, String folder) {
        //进行字符串的截取 得到子目录下级图片
        String objectName = folder.substring(folder.indexOf("com") + 4);
        OSSClient ossClient = getOSSClient(aliYunVO);
        //设置本地下载的路径
        String s = folder.split("/")[4];
        String localPath = "E:\\excel\\upload\\img\\" + s;
        // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(aliYunVO.getBucketName(), objectName), new File(localPath));
        // 关闭OSSClient。
        ossClient.shutdown();
        return localPath;
    }

    /**
     * 上传文件以字节流上传
     *
     * @param aliYunVO ：  自定义封装类
     * @param folder   ：自定义存储路径
     * @return 返回一个视频所在路径
     */
    public static String UpLoadByByte(AliYunVO aliYunVO, String folder) {
        // 获取OSSClient实例。
        ossClient = getOSSClient(aliYunVO);
        // 新建Byte数组。
        byte[] bytes = null;
        try {
            //将文件转化为字节数组
            bytes = aliYunVO.getHeadImg().getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取文件上传名
        String originalFilename = aliYunVO.getHeadImg().getOriginalFilename();
        //对源文件进行重新命名
        String newFileName = folder + "/" + System.currentTimeMillis() + originalFilename;
        //拼接最新网络地址供调用者使用
        String newPath = "https://yingx-zh.oss-cn-beijing.aliyuncs.com/" + newFileName;
        // 上传Byte数组。         存储的空间            文件重新命名         创建一个新的字节输出流
        ossClient.putObject(aliYunVO.getBucketName(), newFileName, new ByteArrayInputStream(bytes));
        // 关闭OSSClient。
        ossClient.shutdown();
        //返回调用者新的网络地址
        return newPath;
    }

    /**
     * 以网络流的方式进行上传
     *
     * @param aliYunVO 自定义实体类
     * @param folder   自定义文件夹
     * @return 返回   网络上传至oss存储空间网络路径
     */
    public static String UplodeByNetPath(AliYunVO aliYunVO, String folder) {
        // 获取OSSClient实例。
        ossClient = getOSSClient(aliYunVO);
        // 上传网络流。
        InputStream inputStream = null;
        try {
            //获取网络地址
            inputStream = new URL(aliYunVO.getNetAddress()).openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取文件名及其后缀
        String originalFilename = aliYunVO.getHeadImg().getOriginalFilename();
        //文件名截取文件名不要后缀
        String name = originalFilename.substring(0, originalFilename.indexOf("."));
        //进行最终路径拼接
        String ObjectName = folder + "/" + name + ".jpg";
        //设置上传的父级目录
        ossClient.putObject(aliYunVO.getBucketName(), ObjectName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
        String newCoverPath = "https://yingx-zh.oss-cn-beijing.aliyuncs.com/" + ObjectName;
        //返回文件存储的路径
        return newCoverPath;
    }

    /**
     * 删除文件
     *
     * @param aliYunVO:实例化对象
     * @param fileNane:数据库中存储的真实网络路径
     */
    public static void deleteFile(AliYunVO aliYunVO, String fileNane) {
        //进行字符串的截取 获取删除文件的父文件夹
        String objectName = fileNane.substring(fileNane.indexOf("com") + 4);
        ossClient = getOSSClient(aliYunVO);
        // 删除文件。如需删除文件夹，请将ObjectName设置为对应的文件夹名称。如果文件夹非空，则需要将文件夹下的所有object删除后才能删除该文件夹。
        ossClient.deleteObject(aliYunVO.getBucketName(), objectName);
        // 关闭OSSClient。`
        ossClient.shutdown();
    }

    /**
     * 视频截取
     * 返回截取的地址
     *
     * @param aliYunVO 自定义封装实体类
     * @return 返回视频截帧网络url路径
     */
    public static String videoCapture(AliYunVO aliYunVO) {
        String netAddress = aliYunVO.getNetAddress();
        //截取com后的字符串  video/xxx.mp4
        String objectName = netAddress.substring(netAddress.indexOf("com") + 4);
        // 创建OSSClient实例。
        OSSClient ossClient = getOSSClient(aliYunVO);
        // 设置视频截帧操作。
        String style = "video/snapshot,t_10000,f_jpg,w_800,h_600";
        // 指定默认过期时间为10分钟---调到贼拉长。
        Date expiration = new Date(System.currentTimeMillis() + 36000 * 1000 * 24 * 365 * 10);
        //                                                                   空间名字                文件相对路径
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(aliYunVO.getBucketName(), objectName, HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ALiYunOssUtil.ossClient.generatePresignedUrl(req);
        String s = signedUrl.toString();
        System.out.println(signedUrl);
        // 关闭OSSClient。
        ALiYunOssUtil.ossClient.shutdown();
        return s;
    }
}

