package wang.haogui.yuanda.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {

    /**
     *  从token中拿到某些值
     * @param request
     * @param name
     * @return
     */
    public static Object getCookiesValueByName(HttpServletRequest request,String name){

        Cookie cookie = getToken(request);
        if(cookie == null){
            return null;
        }
        String string = cookie.getValue();
        return TokenUtil.getTokenValue(string, name);
    }

    public static Cookie getToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (Cookie c:cookies) {
            if("token".equals(c.getName())){
                cookie = c;
            }
        }
        return cookie;
    }

}
