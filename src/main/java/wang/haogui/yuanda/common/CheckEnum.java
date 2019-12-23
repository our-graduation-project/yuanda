package wang.haogui.yuanda.common;

/**
 *
 * 审核状态的状态码
 * @author whg
 * @date 2019/12/23 11:01
 **/
public enum  CheckEnum {

    //还没有审核
    HVAEDNOCHECK(0),

    //审核通过
    CHECKPASS(1),

    //审核失败
    CHECKFAILL(2);

    /**
     * 审查的状态码
     */
    private int status;

    CheckEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
