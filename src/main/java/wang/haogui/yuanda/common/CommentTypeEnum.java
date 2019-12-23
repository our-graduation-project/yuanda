package wang.haogui.yuanda.common;

/**
 * 评论的评论状态码
 * @author whg
 * @date 2019/12/23 11:11
 **/
public enum CommentTypeEnum {

    //表示评论为评论文章
    ARTICLE(0),

    //表示评论为评论问题
    ANSWER(1),

    //表示评论为评论评论
    COMMENT(2);

    private int commentType;

    public int getCommentType() {
        return commentType;
    }

    CommentTypeEnum(int commentType) {
        this.commentType = commentType;
    }
}
