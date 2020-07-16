package com.zhanghui.yx.aspect;

import com.alibaba.fastjson.JSON;
import com.zhanghui.yx.eChartsVO.PushStatVO;
import com.zhanghui.yx.entity.YxAdmin;
import com.zhanghui.yx.entity.YxLog;
import com.zhanghui.yx.service.YxLogService;
import com.zhanghui.yx.service.YxUserService;
import io.goeasy.GoEasy;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Configuration
@Aspect
@Slf4j
public class LogAspect {
    @Resource
    private HttpSession session;
    @Resource
    private YxLogService yxLogService;
    @Resource
    private YxUserService yxUserService;


    @AfterReturning("@annotation(com.zhanghui.yx.aspect.MyPush)")
    public void addMyPush() {
        System.out.println("后置通知执行");
        //查询所有之后 剋是推送
        List<PushStatVO> pushStatVOS = yxUserService.selectAllBySexAndCreateDate();
        //创建goeasy对象  配置参数：地区地址    appkey
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io", "BC-2557e7bba400456192dad9d9386f117e");
        //发送消息-->讲json对象  转化为字符串对象
        String content = JSON.toJSONString(pushStatVOS);
        goEasy.publish("zh_channel", content);
    }

    /**
     * 记录管理员的日志
     *
     * @param proceedingJoinPoint 连接点
     * @Around :环绕通知
     * @annotation： 根据注解切
     */
    @Around("@annotation(com.zhanghui.yx.aspect.MyLog)")
    public Object addLoges(ProceedingJoinPoint proceedingJoinPoint) {
        log.info("-----------------进入环绕通知---------");
        YxAdmin admin = (YxAdmin) session.getAttribute("Admin");
        log.info("操作的用户为:{}", admin.getUsername());
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        //获取方法
        Method method = signature.getMethod();
        //获取方法名字
        //获取方法上的注解
        MyLog myLog = method.getAnnotation(MyLog.class);
        //获取注解的内容
        String value = myLog.value();
        log.info("注解内容为:{}", value);
        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        log.info("操作的方法名为:{}", methodName);
        //放行
        Object proceed = null;
        try {
            proceed = proceedingJoinPoint.proceed();
            String messages = "sussess";
            YxLog yxLog = new YxLog(UUID.randomUUID().toString().replace("-", ""), admin.getUsername(), new Date(), value + "(" + methodName + ")", messages);
            yxLogService.insert(yxLog);
            //设置数据存放到数据库
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            String messages = "error";
            YxLog yxLog = new YxLog(UUID.randomUUID().toString().replace("-", ""), admin.getUsername(), new Date(), value + "(" + methodName + ")", messages);
            yxLogService.insert(yxLog);
        }
        return proceed;
    }

}



