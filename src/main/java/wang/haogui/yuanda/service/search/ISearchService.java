package wang.haogui.yuanda.service.search;

/**
 * @author whg
 * @date 2019/12/23 19:53
 **/
public interface ISearchService {

    /**
     * 索引目标文章
     * @param articleId
     */
    void index(int articleId);

    /**
     * 移除房源文章
     * @param articleId
     */
    void remove(int articleId);
}
