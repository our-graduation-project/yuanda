package wang.haogui.yuanda.service;

import com.github.pagehelper.PageInfo;
import wang.haogui.yuanda.model.Message;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 16:57 2019/12/30
 * @Modifued By:
 */
public interface MessageService {

    /**
     * 查询通知
     * @param userId
     * @return
     */
    PageInfo searchMessage(int userId,int status, int page, int limit);


    /**
     * 查询通知数量（返回已读通知数或者未读通知数）
     *
     * @param userId
     * @return
     */
    int searchMessageNotReadNumber(int userId,int status);

    /**
     * 添加消息通知
     * @param message
     * @return
     */
    boolean addMessage(Message message);

    /**
     * 修改通知状态
     * @param messageId
     * @return
     */
    boolean updateMessage(int messageId);

    /**
     * 删除通知
     * @param messageId
     * @return
     */
    boolean delMessage(int messageId);

}
