package wang.haogui.yuanda.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {

    /**
     *
     * @param request
     * @param name
     * @return
     */
    public static Object getCookiesValueByName(HttpServletRequest request,String name){
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (Cookie c:cookies) {
            if("token".equals(c.getName())){
                cookie = c;
            }
        }
        String string = cookie.getValue().toString();
        return TokenUtil.getTokenValue(string, name);
    }

}
