package com.zhanghui.yx.controller;

import com.zhanghui.yx.entity.YxAdmin;
import com.zhanghui.yx.service.YxAdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * (YxAdmin)表控制层
 *
 * @author makejava
 * @since 2020-06-30 15:52:37
 */
@RestController
@RequestMapping("yxAdmin")
public class YxAdminController {
    /**
     * 服务对象
     */
    @Resource
    private YxAdminService yxAdminService;

    /**
     * 通过主键查询单条数据
     *
     * @param yxAdmin     实体类
     * @param code        验证码

     * @return map集合
     */
    @GetMapping("login")
    public Map<String, String> login(YxAdmin yxAdmin, String code) {
        Map<String, String> map = yxAdminService.login(yxAdmin, code);
        return map;
    }

    /**
     * 销毁session清除登录标识
     */

    @RequestMapping("removeSession")
    public Map<String, String> remove(HttpSession session) {
        session.removeAttribute("Admin");
        Map<String, String> map = new HashMap<String, String>();
        map.put("remove", "ok");
        return map;

    }


}