package wang.haogui.yuanda.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.mapper.MessageMapper;
import wang.haogui.yuanda.model.Message;
import wang.haogui.yuanda.model.MessageExample;
import wang.haogui.yuanda.service.MessageService;
import wang.haogui.yuanda.utils.LogUtils;

import java.util.List;

/**
 * @Author: Zxw
 * @Description:
 * @Date: Created in 17:08 2019/12/30
 * @Modifued By:
 */

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    /**
     * 查询通知
     *
     * @param userId
     * @return
     */
    @Override
    public PageInfo searchMessage(int userId,int status, int page, int limit) {

        MessageExample messageExample = new MessageExample();
        messageExample.or().andIsDeletedEqualTo(false)
                .andIsReadEqualTo((byte) status).andMessageTargetEqualTo(userId);
        PageHelper.startPage(page,limit);
        List<Message> messages = messageMapper.selectByExample(messageExample);
        PageInfo pageInfo = new PageInfo(messages,3);
        return pageInfo;
    }

    /**
     * 查询通知数量（返回已读通知数或者未读通知数）
     *
     * @param userId
     * @return
     */
    @Override
    public int searchMessageNotReadNumber(int userId,int status) {
        MessageExample messageExample = new MessageExample();
        messageExample.or().andIsDeletedEqualTo(false)
                .andIsReadEqualTo((byte) status).andMessageTargetEqualTo(userId);
        List<Message> messages = messageMapper.selectByExample(messageExample);
        //如果查到的列表为空则返回0 表示该种通知数量为0
        if(messages.isEmpty()){
            return 0;
        }
        return messages.size();
    }

    /**
     * 添加消息通知
     *
     * @param message
     * @return
     */
    @Override
    public boolean addMessage(Message message) {

        int insert = messageMapper.insert(message);
        if(insert <= 0){
            LogUtils.getDBLogger().info("添加通知失败！");
            throw new RuntimeException("添加通知失败！");
        }

        return true;
    }

    /**
     * 修改通知状态
     *
     * @param messageId
     * @return
     */
    @Override
    public boolean updateMessage(int messageId) {

        Message message = new Message();
        message.setIsRead((byte) 1);
        message.setMessageId(messageId);
        int i = messageMapper.updateByPrimaryKeySelective(message);
        if(i <= 0){
            LogUtils.getDBLogger().info("更新通知失败！");
            throw new RuntimeException("更新通知失败！");
        }
        return true;
    }

    /**
     * 删除通知
     *
     * @param messageId
     * @return
     */
    @Override
    public boolean delMessage(int messageId) {
        Message message = new Message();
        message.setIsDeleted(true);
        message.setMessageId(messageId);
        int i = messageMapper.updateByPrimaryKeySelective(message);
        if(i <= 0){
            LogUtils.getDBLogger().info("删除通知失败！");
            throw new RuntimeException("删除通知失败！");
        }
        return true;
    }
}