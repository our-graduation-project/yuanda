package wang.haogui.yuanda.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author whg
 * @date 2019/11/14 9:20
 **/
public class TokenUtil {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
//        List<Right> list = new ArrayList();

//        map.put("rights",list);
        map.put("userId",1);
        map.put("username","admin");
        String token = becomeToken(map);
        System.out.println(token);

        System.out.println("-----------------------------------------");
        System.out.println("isMyToken: " + isMyToken(token));

        System.out.println("-------------------------------------------");
        System.out.println(getTokenValue(token,"rights"));
    }

    //HMACSHA256的密钥
    private final static String SECRET= "wang.haoguisecret";

    //安全Base64加密的格式
    private static final String ENCODING = "UTF-8";

    private static JSONObject preJson = new JSONObject();

    static {
        preJson.put("alg"," HS256");
        preJson.put("typ","jwt");
    }

    public static Object getTokenValue(String token, String key){
        String[] split = token.split("\\.");
        if(split.length != 3){
            return false;
        }
        String base64Loader = split[1];
        String loader = null;
        try {
            loader = decode(base64Loader);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(loader);
        JSONObject jsonObject = JSON.parseObject(loader);
        return jsonObject.get(key);
    }

    /**
     * 生成token
     * @param map
     * @return
     */
    public static String becomeToken(Map map){
        JSONObject jsonObject = new JSONObject(map);
        jsonObject.put("time",new Date().toString().trim());
        String pre = null;
        String loader = null;
        String autograph = null;
        try {
            pre = encode(preJson.toJSONString()).trim();
            //System.out.println("pre : " + pre);
            loader = encode(jsonObject.toJSONString()).trim();
            //System.out.println("loader : " + loader);
            autograph = HMACSHA256(pre + "." + loader).trim();
            //System.out.println("autograph : " + autograph);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pre+"."+loader+"."+autograph;
    }

    /**
     * 验证是不是发布的token
     * @param token
     * @return
     */
    public static Boolean isMyToken(String token){
        if(token == null || token.equals("")){
            return false;
        }
        String[] split = token.split("\\.");
        if(split.length != 3){
            return false;
        }
        String autograph = null;
        try {
             autograph = HMACSHA256(split[0]+"."+split[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(autograph.equals(split[2])){
            return true;
        }
        return false;
    }


    /**
     * 将一个字符串使用HMACSHA256加密，密钥为secret
     * @param data
     * @return
     * @throws Exception
     */
    private static String HMACSHA256(String data) throws Exception {

        Mac sha256_HMAC = Mac.getInstance("HmacSHA256");

        SecretKeySpec secret_key = new SecretKeySpec(SECRET.getBytes("UTF-8"), "HmacSHA256");

        sha256_HMAC.init(secret_key);

        byte[] array = sha256_HMAC.doFinal(data.getBytes("UTF-8"));

        StringBuilder sb = new StringBuilder();

        for (byte item : array) {

            sb.append(Integer.toHexString((item & 0xFF) | 0x100).substring(1, 3));

        }

        return sb.toString().toUpperCase();
    }



    /**
     * URLBase64加密
     */
    public static String encode(String data) throws UnsupportedEncodingException {
        byte[] encodedByte = Base64.encodeBase64URLSafe(data.getBytes(ENCODING));
        return new String(encodedByte, ENCODING);
    }
    /**
     * URLBase64解密
     */
    public static String decode(String data) throws UnsupportedEncodingException {
        byte[] decodedByte = Base64.decodeBase64(data.getBytes(ENCODING));
        return new String(decodedByte, ENCODING);
    }


}
