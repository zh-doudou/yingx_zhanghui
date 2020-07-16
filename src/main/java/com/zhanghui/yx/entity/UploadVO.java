package com.zhanghui.yx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadVO {
    /**
     * 文件路路径
     */

    private String path;
    /**
     * 接收的文件
     */
    private MultipartFile headImg;
    /**
     * 用户id
     */
    private String id;
    /**
     *
     * */
}
