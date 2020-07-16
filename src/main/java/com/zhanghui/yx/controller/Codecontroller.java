package com.zhanghui.yx.controller;
import com.zhanghui.yx.util.CaptchaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RequestMapping("code")
@Controller
public class Codecontroller {
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    @ResponseBody
    public void  captcha(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CaptchaUtil.outputCaptcha(request,response);
    }
}
