package wang.haogui.yuanda.utils;

import wang.haogui.yuanda.common.OrderEnum;

/**
 * @author 向清润
 * @createTime 2019.12.23.9:59
 */
public class CommonUtils {

    /**
     * 拼接排序字段
     * @param order 需要排序的列
     * @param orderEnum 枚举类（升序还是降序）
     * @return 拼接的字符串
     */
    public static String orderStr(String order, OrderEnum orderEnum){
        String str = null;
        str="`"+order+"` ";
        if(orderEnum !=null){

            str += orderEnum.getName();
        }else {
            str += OrderEnum.DESC.getName();
        }
        return str;
    }
}
