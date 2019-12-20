package wang.haogui.yuanda.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wang.haogui.yuanda.service.RedisStringService;

/**
 * 注册使用的工具类
 * @author whg
 * @date 2019/12/12 23:26
 **/
@Component
public class RegisterComponent {

    private RegisterComponent(){

    }

    @Autowired
    private RedisStringService redisStringService;

    @Autowired
    private SendRegisterToQueue sendRegisterToQueue;

    /**
     * 向邮箱发送一个随机email
     * @param email
     * @return
     */
    public String sendCodeToEmail(String email){
        String code = (int)(Math.random() * 100000) + "";
        System.out.println(sendRegisterToQueue + "----");
        sendRegisterToQueue.sendToQueue(code, email);
        redisStringService.set(email,code);
        redisStringService.expire(email,300);
        return code;
    }

    /**
     * 通过邮箱获得发送的验证码
     * 验证码过期返回null
     * @param email
     * @return
     */
    public String getCodeByEmail(String email){
        String code = redisStringService.get(email);
        return code;
    }
}
