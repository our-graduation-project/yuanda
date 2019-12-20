package wang.haogui.yuanda.utils.power;

import java.lang.annotation.*;

/**
 * @author whg
 * @createTime 2019.12.01.23:27
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
public @interface AntMatchers {
    AntMatcher[] value();
}

