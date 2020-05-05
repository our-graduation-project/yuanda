package wang.haogui.yuanda.utils.power.handler;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import wang.haogui.yuanda.utils.TokenUtil;
import wang.haogui.yuanda.utils.power.*;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author whg
 * @date 2019/12/2 22:24
 *
 *
 *  在@AntMatcher中配置拦截路径就会对其进行拦截，若token中有hasAnyRight的权限则可以访问
 *  ，若token为空则未登录跳入登陆界面 ForwardPathToLogin() 里面的路径或方法
 *  ，否则将会被拦截下来(但拦截后应该如何对其进行处理，由自己决定 107行处编写拦截后的逻辑)
 *
 *  @ForwardPathToLogin 里面填写如果未登录应该跳转的路径或方法
 **/
@AntMatcher(path = "/admin/*",hasAnyRight = "1")
@AntMatcher(path = "/person/*",hasAnyRight = "0")
@ForwardPathToUserLogin(path = "/person/login.html")
@ForwardPathToAdminLogin(path = "/admin/login.html")
public class RightFilter implements Filter {

    /**
     * string 为匹配的路径，Matcher 为匹配的基本规则
     */
    Map<String, Matcher> rightMap = new HashMap<>();

    AtomicReference<AntPathMatcher> antPathMatcher = new AtomicReference<>();

    /**
     * 跳转路径
     */
    String pathToUserLogin = null;

    String pathToAdminLogin = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Annotation[] annotations = RightFilter.class.getAnnotations();
        AntMatchers myMatchers = null;
        for (int i = 0; i < annotations.length; i++) {
            if (annotations[i] instanceof AntMatchers){
                myMatchers = (AntMatchers)annotations[i];
                for (AntMatcher matcher:
                        myMatchers.value()) {
                    rightMap.put(matcher.path(),new Matcher(matcher.hasAnyRole(),matcher.hasAnyRight(),matcher.hasRole(),matcher.hasRight()));
                }
            }else if(annotations[i] instanceof ForwardPathToUserLogin){
                pathToUserLogin = ((ForwardPathToUserLogin)annotations[i]).path();
            }else if(annotations[i] instanceof ForwardPathToAdminLogin){
                pathToAdminLogin = ((ForwardPathToAdminLogin)annotations[i]).path();
            }
        }
        System.out.println("map初始化成功：" + rightMap.size());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //yuanda
        String contextPath = request.getContextPath();
        // 请求的完整路径 例如/yuanda/admin/login.html
        String requestURI = request.getRequestURI();
        // 请求路径 例如/admin/login.html
        String uri = requestURI.substring(contextPath.length());
        Cookie token = null;
        Matcher matcher = null;

        //登录页面直接放行
        if(isLoginHtml(uri)){
            System.out.println("登录页面直接放行");
            filterChain.doFilter(request, response);
            return;
        }

        //表示为要验证权限的路径
        Boolean isFalg = false;
        //查看是不是要拦截的路径
        for (Map.Entry<String, Matcher> entry:
                    rightMap.entrySet()) {
//            System.out.println(uri + "--匹配--" + entry.getKey());

            //路径匹配
            if (getAnt().match(entry.getKey(), uri)) {
                isFalg = true;
//                System.out.println("路径匹配成功");
                //获得token
                token = getTokenInCookie(request);
                //获得进入次方法的所需权力类
                matcher = entry.getValue();
                break;
            }
        }

        //不属于拦截路径
        if(!isFalg){
            //放行
            filterChain.doFilter(request,response);
            return;
        }

        //数据拦截路径但未登陆，跳转到登陆界面
        if(token == null) {
            //记录他想要跳转的界面方便登陆后直接进入他想进入的页面
            request.setAttribute("lastPage", request.getRequestURL());
            //跳到后台登陆的界面
            //如果路径访问时admin则跳入后台登录界面，否则跳入前端登录界面
            if(uri.contains("admin")){
                response.sendRedirect("/yuanda"+pathToAdminLogin);
            }else{
                response.sendRedirect("/yuanda"+pathToUserLogin);
            }
            return;
        }

        //拥有此权力进行跳转
        if (hasThisPower(matcher, token.getValue())) {
            filterChain.doFilter(request, response);
            return;
        } else{//没有此权力，该怎么处理自己想
            System.out.println(" 没有访问权限 ");
            PrintWriter writer = response.getWriter();
            writer.print("<h1>sorry, you cannot come in here, you did not have power</h1>");
            writer.print("<h2>After three seconds, redirect to login</h2>");
            response.setHeader("refresh","3;/yuanda"+pathToAdminLogin);
            return;
        }


    }

    /**
     * 是否为登录界面,或者登录路径
     * @param uri
     * @return
     */
    private Boolean isLoginHtml(String uri){
        if(uri.contains(pathToUserLogin)||uri.contains(pathToAdminLogin)||uri.contains("admin/loginAdmin")){
            return true;
        }
        return false;
    }

    /**
     * 在token中拿到token
     * @param request
     * @return
     */
    private Cookie getTokenInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Cookie token = null;
        if(cookies != null){
            //获得token
            for (Cookie cookie :
                    cookies) {
                if (cookie.getName().equals("token")){
                    token = cookie;
                    break;
                }
            }
        }
        return token;
    }

    /**
     * 看此用户是否拥有此权限
     * 目前仅仅验证了权限Right，没有角色验证，角色验证自己加
     * @param matcher
     * @param token
     * @return
     */
    private Boolean hasThisPower(Matcher matcher, String token) {
        String[] hasAnyRight = matcher.getHasAnyRight();
        List userRight = (List) TokenUtil.getTokenValue(token, "right");

        //只要有一个权限就能访问
        if(hasAnyRight != null){
            for (String right :
                    hasAnyRight) {

                if(userHaveRight(userRight,right)){
                    return true;
                }
            }
        }
        //拥有所有权限才能通过
        String[] hasRight = matcher.getHasRight();
        if(hasAnyRight != null){
            for (String right :
                    hasRight) {
                if(!userRight.contains(right)){
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 用户是否拥有某个权限
     * @param userRight 用户权限列表
     * @param right 用户权限
     * @return
     */
    private Boolean userHaveRight(List userRight, String right){
        for (Object o: userRight
        ) {
            if (right.equals(o+"")) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {

    }


    /**
     * 获得解析路径ant风格的类
     * @return
     */
    public AntPathMatcher getAnt(){
        if( antPathMatcher.get() == null){
            antPathMatcher.compareAndSet(null,new AntPathMatcher());
        }
        return antPathMatcher.get();
    }
}
