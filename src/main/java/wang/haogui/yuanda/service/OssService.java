package wang.haogui.yuanda.service;


import wang.haogui.yuanda.confign.OssCallbackResult;
import wang.haogui.yuanda.confign.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author whg
 * @date 2019/12/16 16:09
 **/
public interface OssService {

    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
