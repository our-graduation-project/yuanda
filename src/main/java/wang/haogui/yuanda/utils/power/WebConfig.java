package wang.haogui.yuanda.utils.power;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import wang.haogui.yuanda.utils.power.handler.RightFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author whg
 * @date 2019/12/3 19:09
 **/
@Configuration
public class WebConfig {

    @Bean
    FilterRegistrationBean rightFilter() {
        FilterRegistrationBean filterReg = new FilterRegistrationBean(new RightFilter());
        //优先级
        filterReg.setOrder(70);
//        filterReg.setDispatcherTypes(DispatcherType.REQUEST);
        //匹配路径
        List<String> urlPatterns = new ArrayList<>();
//        urlPatterns.add("/*");
        filterReg.addUrlPatterns("*.do");
        //哪一个不拦截
        filterReg.addInitParameter("exclusions","/loginUser.do");
//        filterReg.setUrlPatterns(urlPatterns);
        System.out.println("权力过滤器====初始化");
        return filterReg;
    }

}
