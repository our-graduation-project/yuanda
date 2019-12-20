package wang.haogui.yuanda.confign;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import wang.haogui.yuanda.utils.LogUtils;

/**
 * @author whg
 * @date 2019/12/10 9:36
 **/
@Aspect
@Component
public class WebLogAspect {

    //Controller层切点
    @Pointcut("execution (* cn.hnist.bookmanager.controller..*.*(..))")
    public void controllerAspect() {
    }

    /**
     * 前置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) {

    }

    /**
     * 后置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @After("controllerAspect()")
    public void after(JoinPoint joinPoint) {

        /* HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
          HttpSession session = request.getSession();  */
        //读取session中的用户
        // User user = (User) session.getAttribute("user");
        //请求的IP
        //String ip = request.getRemoteAddr();

        //*========控制台输出=========*//
        System.out.println("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));


        //*========数据库日志=========*//
        LogUtils.getControllerLogger().info("请求方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
    }

    /**
     *
     *
     * 异常通知 用于拦截记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {

        /*========控制台输出=========*/
        System.out.println("异常代码:" + e.getClass().getName());
        System.out.println("异常信息:" + e.getMessage());
        System.out.println("异常方法:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));

        /*==========记录本地异常日志==========*/
        LogUtils.getExceptionLogger().error("异常方法:{}异常代码:{}异常信息:{}参数:{}", joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage());

    }


}
