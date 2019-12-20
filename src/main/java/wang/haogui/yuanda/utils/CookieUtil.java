package wang.haogui.yuanda.utils;

public class CookieUtil {

    /**
     *
     * @param cookie
     * @param name
     * @return
     */
    public static String getCookiesValueByName(String cookie, String name){
//        System.out.println("cookie:"+cookie);
//        System.out.println("mach:"+name);
        String[] cookies = cookie.split(";");
        for (String m:cookies
                ) {
            String[] temp = m.split("=");
            if(temp.length>1){
                if(temp[0].contains(name)){
                    return temp[1];
                }
            }

        }
        return null;
    }
}
