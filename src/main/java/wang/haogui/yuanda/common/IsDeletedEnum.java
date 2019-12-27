package wang.haogui.yuanda.common;


/**
 * 是否删除
 */
public enum IsDeletedEnum {

    //删除成功
    DeletedSuccess((boolean) true),
    //还原删除
    RecoverySuccess((boolean) false);

    /**
     * 审查的状态码
     */
    private boolean status;


    IsDeletedEnum(boolean status) {
        this.status=status;
    }
    public boolean getStatus(){
        return status;
    }
}
