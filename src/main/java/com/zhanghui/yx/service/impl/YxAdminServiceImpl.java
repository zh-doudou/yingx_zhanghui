package com.zhanghui.yx.service.impl;

import com.zhanghui.yx.dao.YxAdminDao;
import com.zhanghui.yx.entity.YxAdmin;
import com.zhanghui.yx.service.YxAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * (YxAdmin)表服务实现类
 *
 * @author makejava
 * @since 2020-06-30 15:52:37
 */
@Service("yxAdminService")
@Slf4j
@Transactional //事务
public class YxAdminServiceImpl implements YxAdminService {
    @Resource
    YxAdminDao yxAdminDao;
    @Resource
    HttpSession httpSession;


    @Override
    public Map<String, String> login(YxAdmin yxAdmin, String code) {
        log.info("前台数据为：{}", yxAdmin);
        log.info("前台验证码为：{}", code);
        HashMap<String, String> map = new HashMap<>();
        String codee = ((String) httpSession.getAttribute("randomString")).toLowerCase();
        String codeLower = code.toLowerCase();
        log.info("前台生成的验证码为:{}", codeLower);
        //判断验证码
        if (codee.equals(codeLower)) {
            //根据名字查询
            YxAdmin admin = yxAdminDao.queryByName(yxAdmin.getUsername());
            if (admin != null) {
                //判断密码是否正确
                if (admin.getPassword().equals(yxAdmin.getPassword())) {
                    httpSession.setAttribute("Admin", admin);
                    map.put("login", "success");
                } else {
                    map.put("login", "用户密码输入错误");
                }
            } else {
                map.put("login", "用户不存在");
            }
        } else {
            map.put("login", "验证码输入错误");
        }
        return map;

    }

}