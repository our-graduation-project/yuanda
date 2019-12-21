package wang.haogui.yuanda.common;

/**
 * 排序的枚举
 * @author whg
 * @date 2019/12/21 11:16
 **/
public enum OrderEnum {

    DESC("desc"),

    ASC("asc");

    /**
     * 生效名
     */
    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    OrderEnum(String name){
        this.name = name;
    }
}
