package wang.haogui.yuanda.utils.power.handler;

import org.springframework.util.AntPathMatcher;
import wang.haogui.yuanda.utils.TokenUtil;
import wang.haogui.yuanda.utils.power.AntMatcher;
import wang.haogui.yuanda.utils.power.AntMatchers;
import wang.haogui.yuanda.utils.power.ForwardPathToLogin;
import wang.haogui.yuanda.utils.power.Matcher;


import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
//@AntMatcher(path = "/restUsers.do",hasAnyRight = {"root1","lll"})
@AntMatcher(path = "/admin/**",hasAnyRight = "root")
@AntMatcher(path = "/person/**",hasAnyRight = "user")
//@AntMatcher(path = "/rest*.do",hasAnyRight = {"root1","lll"})
@ForwardPathToLogin(path = "/person/login.html")
public class RightFilter implements Filter {

    Map<String, Matcher> rightMap = new HashMap<>();

    AtomicReference<AntPathMatcher> antPathMatcher = new AtomicReference<>();

    //跳转路径
    String pathToLogin = null;

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
            }else if(annotations[i] instanceof ForwardPathToLogin){
                pathToLogin = ((ForwardPathToLogin)annotations[i]).path();
            }
        }
        System.out.println("map初始化成功：" + rightMap.size());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String contextPath = request.getContextPath();
        String requestURI = request.getRequestURI();
        String uri = requestURI.substring(contextPath.length());
        System.out.println("contextPath = " + contextPath  + ",requestURI = " + requestURI + ", uri=" + uri);
        Cookie token = null;
        Matcher matcher = null;
        Boolean isFalg = false;//表示为要验证权限的路径
        //查看是不是要拦截的路径
        for (Map.Entry<String, Matcher> entry:
                    rightMap.entrySet()) {
            System.out.println(uri + "--匹配--" + entry.getKey());
            if (getAnt().match(entry.getKey(), uri)) {//路径匹配
                isFalg = true;
                System.out.println("路径匹配成功");
                token = getTokenInCookie(request);//获得token
                matcher = entry.getValue();//获得进入次方法的所需权力类
            }
        }

        if(!isFalg){//不属于拦截路径
            //放行
            filterChain.doFilter(request,response);
            return;
        }

        if(token == null) {//数据拦截路径但未登陆，跳转到登陆界面
            //记录他想要跳转的界面方便登陆后直接进入他想进入的页面
            request.setAttribute("lastPage", request.getRequestURL());
            //跳到后台登陆的界面
            //重定向，可自行更改页面，这里跳转的是方法,因为thyplate不能直接访问
            request.getRequestDispatcher(pathToLogin).forward(request, response);
            //转发
            //response.sendRedirect("login");
            return;
        }

        if (hasThisPower(matcher, token.getValue())) {//拥有此权力进行跳转
            System.out.println("有权利");
            filterChain.doFilter(request, response);
//            return;
        } else{//没有此权力，该怎么处理自己想


            return;
        }





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

        List right1 = (List) TokenUtil.getTokenValue(token, "right");
        if(hasAnyRight != null){//只要有一个权限就能访问
            for (String right :
                    hasAnyRight) {
                if(right1.contains(right)){
                    return true;
                }
            }
        }
        String[] hasRight = matcher.getHasRight();//拥有所有权限才能通过
        if(hasAnyRight != null){
            for (String right :
                    hasRight) {
                if(!right1.contains(right)){
                    return false;
                }
            }
            return true;
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
