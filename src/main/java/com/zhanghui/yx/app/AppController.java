package com.zhanghui.yx.app;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.zhanghui.yx.entity.CommonResult;
import com.zhanghui.yx.po.CateGoryPO;
import com.zhanghui.yx.po.VideoPO;
import com.zhanghui.yx.service.YxCategoryService;
import com.zhanghui.yx.service.YxVideoService;
import com.zhanghui.yx.util.LoginVo;
import com.zhanghui.yx.util.SendMessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @CrossOrigin :
 * origins  ： 允许可访问的域列表
 * maxAge：准备响应前的缓存持续的最大时间（以秒为单位）。
 */

@Slf4j
@RestController
@Scope("prototype") //多例模式
@RequestMapping("app") //前台接口规范
@CrossOrigin(origins = {"http://192.168.32.1:8989", "http://192.168.32.1:8888"}, allowCredentials = "true")
public class AppController {
    @Resource
    HttpSession session;
    @Resource
    LoginVo loginVo;
    @Resource
    YxVideoService yxVideoService;
    @Resource
    YxCategoryService yxCategoryService;


    /**
     * 用户登录注册
     *
     * @param phone 手机号
     * @return 前台需要的数据
     */

    @RequestMapping("getPhoneCode")
    public CommonResult getPhoneCode(String phone) {
        loginVo.setMobile(phone);
        SendSmsResponse sendSmsResponse = SendMessageUtil.sendSms(loginVo, session);
        String message = sendSmsResponse.getMessage();
        if ("OK".equals(message)) {
            return new CommonResult().success(phone, "发送成功", "100");
        } else {
            return new CommonResult().success(null, message, "104");
        }
    }


    /***查询所有*/

    @GetMapping("queryByReleaseTime")
    public CommonResult queryByReleaseTime() {
        List<VideoPO> videoPOS = yxVideoService.queryByReleaseTime();
        CommonResult commonResult = new CommonResult().queryByReleaseTime(videoPOS);
        return commonResult;
    }

    /**
     * 根据二级类别查询所对应的视频
     */
    @GetMapping("queryCateVideoList")
    public CommonResult queryCateVideoList(String cateId) {
        CommonResult commonResult = new CommonResult();
        List<VideoPO> videoPOS = yxVideoService.queryCateVideoList(cateId);
        commonResult.setData(videoPOS);
        return commonResult;
    }

    /**
     * 查询视频的具体数据
     */
    @GetMapping("queryByVideoDetail")
    public CommonResult queryByVideoDetail(String videoId, String cateId, String userId) {

        VideoPO videoPO = yxVideoService.queryByVideoDetail(videoId, cateId, userId);

        return new CommonResult().queryByReleaseTime(videoPO);
    }


    /**
     * 分类页面数据
     */
    @GetMapping("queryAllCategory")
    public CommonResult queryAllCategory() {
        CommonResult commonResult = new CommonResult();
        List<CateGoryPO> cateGoryPOS = yxCategoryService.queryAllCategory();
        commonResult.setData(cateGoryPOS);
        return commonResult;
    }


}
