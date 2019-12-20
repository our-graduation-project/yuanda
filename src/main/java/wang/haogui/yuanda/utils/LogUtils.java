package wang.haogui.yuanda.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wang.haogui.yuanda.common.LogEnum;

/**
 * @author whg
 * @date 2019/12/9 23:39
 **/
public class LogUtils {
    /**
     * 获取业务日志logger
     *
     * @return
     */
    public static Logger getBussinessLogger() {
        return LoggerFactory.getLogger(LogEnum.BUSSINESS.getCategory());
    }

    /**
     * 获取平台日志logger
     *
     * @return
     */
    public static Logger getPlatformLogger() {
        return LoggerFactory.getLogger(LogEnum.PLATFORM.getCategory());
    }

    /**
     * 获取数据库日志logger
     *
     * @return
     */
    public static Logger getDBLogger() {
        return LoggerFactory.getLogger(LogEnum.DB.getCategory());
    }


    /**
     * 获取异常日志logger
     *
     * @return
     */
    public static Logger getExceptionLogger() {
        return LoggerFactory.getLogger(LogEnum.EXCEPTION.getCategory());
    }


    /**
     * 获取控制层日志logger
     *
     * @return
     */
    public static Logger getControllerLogger(){
        return LoggerFactory.getLogger(LogEnum.CONTROLLER.getCategory());
    }

    /**
     * 获取书本预期未还系统所进行的操作的日志logger
     *
     * @return
     */
    public static Logger getTimeOutTaskLogger(){
        return LoggerFactory.getLogger(LogEnum.TIMEOUTTASK.getCategory());
    }


}
