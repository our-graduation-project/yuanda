package wang.haogui.yuanda.common;

/**
 *
 * @author whg
 * @date 2019/12/9 23:38
 **/
public enum LogEnum {

    BUSSINESS("bussiness"),

    PLATFORM("platform"),

    DB("db"),

    EXCEPTION("exception"),

    CONTROLLER("controller"),

    TIMEOUTTASK("timeouttask")
    ;


    private String category;


    LogEnum(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

}
