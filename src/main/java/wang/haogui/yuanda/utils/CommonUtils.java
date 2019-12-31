package wang.haogui.yuanda.utils;

import wang.haogui.yuanda.common.OrderEnum;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

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
        if(orderEnum !=null || !"".equals(order)){

            str += orderEnum.getName();
        }else {
            str += OrderEnum.DESC.getName();
        }
        return str;
    }


    /**
     * 将字符类型的升降序变成枚举类
     * @param order 字符型升降序
     * @return 枚举类升降序
     */
    public static OrderEnum isOrderEnum(String order){

        OrderEnum orderEnum = null;
        if( "asc".equals(order)){
            orderEnum =OrderEnum.ASC;
        }else {
            orderEnum =OrderEnum.DESC;
        }
        return orderEnum;
    }

    /**
     *拿出request里面toke存储的id
     * @param request 传入的request
     * @return 返回用户id
     */
    public static int getTokenId(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (Cookie c:cookies){
            if(c.getName().equals("token")){
                cookie = c;
            }
        }
        String s = cookie.getValue();
        if(s==null ||"".equals(s)){
            return 0;
        }
        int tokenValue = (int) TokenUtil.getTokenValue(s, "userId");
        return tokenValue;
    }
}
