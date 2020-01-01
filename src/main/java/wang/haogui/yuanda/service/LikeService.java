package wang.haogui.yuanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.utils.RedisKeyUtil;

import java.util.List;

/**
 * 用于处理点赞信息
 * @author whg
 * @date 2019/12/30 11:23
 **/
public interface LikeService {

    /**
     * 获得某个人对某篇文章获得某个回答是否点赞的信息,0为没有进行任何操作，1表示点赞，-1表示点踩
     * @param userId
     * @param entityType 1表示文章，2表示问题
     * @param entityId
     * @return
     */
    int getLikeStatus(int userId,int entityType,int entityId);

    /**
     * 将其加入like中,返回喜欢的人数
     * @param userId
     * @param entityType 1表示文章，2表示问题
     * @param entityId
     * @return
     */
    long like(int userId,int entityType,int entityId);

    /**
     * 将其加入dislike中,返回不喜欢的人数
     * @param userId
     * @param entityType 1表示文章，2表示问题
     * @param entityId
     * @return
     */
    long disLike(int userId,int entityType,int entityId);

    /**
     * 获得redis中喜欢于不喜欢的数量
     * @param entityType
     * @param list
     * @return
     */
    void getLikeAndDisLikeNumber(int entityType, List list);
}
