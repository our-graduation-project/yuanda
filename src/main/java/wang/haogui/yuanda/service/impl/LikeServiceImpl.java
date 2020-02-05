package wang.haogui.yuanda.service.impl;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.model.Answer;
import wang.haogui.yuanda.model.Article;
import wang.haogui.yuanda.service.LikeService;
import wang.haogui.yuanda.service.RedisSetService;
import wang.haogui.yuanda.utils.RedisKeyUtil;

import java.util.List;

/**
 * @author whg
 * @date 2019/12/31 10:07
 **/
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private RedisSetService redisSetService;

    /**
     * 获得某个人对某篇文章获得某个回答是否点赞的信息,0为没有进行任何操作，1表示点赞，-1表示点踩
     * @param userId
     * @param entityType
     * @param entityId
     * @return
     */
    public int getLikeStatus(Integer userId, int entityType, int entityId){

        //获得这篇文章的key
        String likeKey = RedisKeyUtil.getLikeKey(entityType, entityId);
//        System.out.println(userId.toString());
        Boolean isLikeKeymember = redisSetService.isMember(likeKey, userId.toString());
        if(isLikeKeymember){
            return 1;
        }
        Boolean isDisLikemember = redisSetService.isMember(RedisKeyUtil.getDisLikeKey(entityType, entityId), userId.toString());
        if(isDisLikemember){
            return -1;
        }
        return 0;

    }

    /**
     * 将其加入like中，返回喜欢的人数
     *
     * @param userId
     * @param entityType
     * @param entityId
     * @return
     */
    @Override
    public long like(int userId, int entityType, int entityId) {
        //加入like
        redisSetService.add(RedisKeyUtil.getLikeKey(entityType,entityId),userId);
        //移出dislike
        redisSetService.srem(RedisKeyUtil.getDisLikeKey(entityType,entityId),userId);
        return redisSetService.scard(RedisKeyUtil.getLikeKey(entityType,entityId));
    }

    /**
     * 将其加入dislike中,返回不喜欢的人数
     *
     * @param userId
     * @param entityType
     * @param entityId
     * @return
     */
    @Override
    public long disLike(int userId, int entityType, int entityId) {
        //加入dislike
        redisSetService.add(RedisKeyUtil.getDisLikeKey(entityType,entityId),userId);
        //移出like
        redisSetService.srem(RedisKeyUtil.getLikeKey(entityType,entityId),userId);
        return redisSetService.scard(RedisKeyUtil.getDisLikeKey(entityType,entityId));
    }

    /**
     * 获得redis中喜欢于不喜欢的数量
     *
     * @param entityType
     * @param list
     * @return
     */
    @Override
    public void getLikeAndDisLikeNumber(int entityType, List list) {
        if(entityType == 1){
            for (Object o :
                    list) {
                Article article = (Article) o;
                int scard = Math.toIntExact(redisSetService.scard(RedisKeyUtil.getLikeKey(entityType, article.getArticleId())));
                article.setAgreementNumber(scard+article.getAgreementNumber());
                int scard2 = Math.toIntExact(redisSetService.scard(RedisKeyUtil.getDisLikeKey(entityType, article.getArticleId())));
                article.setDisagreementNumber(scard2);
            }
        }else {
            for (Object o :
                    list) {
                Answer answer = (Answer) o;
                int scard = Math.toIntExact(redisSetService.scard(RedisKeyUtil.getLikeKey(entityType, answer.getAnswerId())));
                answer.setAgreeNumber(scard+answer.getAgreeNumber());
                int scard2 = Math.toIntExact(redisSetService.scard(RedisKeyUtil.getDisLikeKey(entityType, answer.getAnswerId())));
                answer.setDisagreeNumber(scard2);
            }
        }
    }

    /**
     * 获得喜欢的数量
     *
     * @param entityType
     * @param entityId
     * @return
     */
    @Override
    public Long getLikeNumber(int entityType, int entityId) {
        return redisSetService.scard(RedisKeyUtil.getLikeKey(entityType,entityId));
    }

    /**
     * 获得不喜欢的数量
     *
     * @param entityType
     * @param entityId
     * @return
     */
    @Override
    public Long getDisLikeNumber(int entityType, int entityId) {
        return redisSetService.scard(RedisKeyUtil.getDisLikeKey(entityType,entityId));
    }


}
