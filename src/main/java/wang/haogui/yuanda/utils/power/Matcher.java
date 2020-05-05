package wang.haogui.yuanda.utils.power;

/**
 * 权限类，应有的权限或者路劲
 * @author whg
 * @date 2019/12/2 22:45
 **/
public class Matcher {

    public Matcher(String[] hasAnyRole, String[] hasAnyRight, String[] hasRole, String[] hasRight) {
        this.hasAnyRole = hasAnyRole;
        this.hasAnyRight = hasAnyRight;
        this.hasRole = hasRole;
        this.hasRight = hasRight;
    }

    /**
     * 拦截 条件 有 任意 一个 角色 放行
     */
    private String[] hasAnyRole;

    /**
     * 有任意 一个 权限 放行
     */
    private String[] hasAnyRight;

    /**
     *  有 所有 的 角色 放行
     */
    private String[] hasRole;

    /**
     *  有 所有 的 权限 放行
     */
    private String[] hasRight;

    public String[] getHasAnyRole() {
        return hasAnyRole;
    }

    public void setHasAnyRole(String[] hasAnyRole) {
        this.hasAnyRole = hasAnyRole;
    }

    public String[] getHasAnyRight() {
        return hasAnyRight;
    }

    public void setHasAnyRight(String[] hasAnyRight) {
        this.hasAnyRight = hasAnyRight;
    }

    public String[] getHasRole() {
        return hasRole;
    }

    public void setHasRole(String[] hasRole) {
        this.hasRole = hasRole;
    }

    public String[] getHasRight() {
        return hasRight;
    }

    public void setHasRight(String[] hasRight) {
        this.hasRight = hasRight;
    }
}
