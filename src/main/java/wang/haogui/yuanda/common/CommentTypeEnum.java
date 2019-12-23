package wang.haogui.yuanda.common;

/**
 * 评论的评论状态码
 * @author whg
 * @date 2019/12/23 11:11
 **/
public enum CommentTypeEnum {

    //表示评论为评论文章
    ARTICLE((byte)0),

    //表示评论为评论问题
    ANSWER((byte)1),

    //表示评论为评论评论
    COMMENT((byte)2);

    private byte commentType;

    public byte getCommentType() {
        return commentType;
    }

    CommentTypeEnum(byte commentType) {
        this.commentType = commentType;
    }
}
