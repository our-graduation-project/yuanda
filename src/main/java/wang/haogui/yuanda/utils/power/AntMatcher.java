package wang.haogui.yuanda.utils.power;

import java.lang.annotation.*;

/**
 * @author whg
 * @createTime 2019.12.01.23:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
@Repeatable(AntMatchers.class)
public @interface AntMatcher{
    // 拦截 路径
    String path()  default "";
    // 拦截 条件 有 任意 一个 角色 放行
    String[] hasAnyRole() default "";
    // 有任意 一个 权限 放行
    String[] hasAnyRight()  default "";
    // 有 所有 的 角色 放行
    String[] hasRole()  default "";
    // 有 所有 的 权限 放行
    String[] hasRight()  default "";
}