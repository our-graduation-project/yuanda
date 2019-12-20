package wang.haogui.yuanda.utils;

/**
 * 在ckeditor富文本里面上传文件的返回值必须遵循这个规则
 * @author whg
 * @date 2019/12/18 23:02
 **/
public class UploadImageResModel {

    public UploadImageResModel(Integer uploaded, String fileName, String url) {
        this.uploaded = uploaded;
        this.fileName = fileName;
        this.url = url;
    }

    /**
     * 1成功，0失败
     */
    private Integer uploaded;

    private String fileName;

    private String url;

    public Integer getUploaded() {
        return uploaded;
    }

    public void setUploaded(Integer uploaded) {
        this.uploaded = uploaded;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static UploadImageResModel getSuccessResult(String fileName, String url){
        return new UploadImageResModel(1,fileName,url);
    }

}
