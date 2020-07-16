package com.zhanghui.yx.service;

import com.zhanghui.yx.entity.YxAdmin;

import java.util.Map;

/**
 * (YxAdmin)表服务接口
 *
 * @author makejava
 * @since 2020-06-30 15:52:37
 */
public interface YxAdminService {


    /**
     * 管理员登录
     *
     * @param yxAdmin 用户对象
     * @param code    验证码
     * @return 实例对象
     */
    Map<String, String> login(YxAdmin yxAdmin, String code);


}