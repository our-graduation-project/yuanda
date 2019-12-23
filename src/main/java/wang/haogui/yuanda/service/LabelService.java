package wang.haogui.yuanda.service;

import com.github.pagehelper.PageInfo;
import wang.haogui.yuanda.model.Label;

/**
 * @author whg
 * @date 2019/12/23 11:56
 **/
public interface LabelService {

    /**
     * 添加标签
     * @param label
     * @return
     */
    Boolean addLabel(Label label);

    /**
     * 删除标签
     * @param labelId
     * @return
     */
    Boolean deleteLabel(int labelId);

    /**
     * 查询标签，通过page，limit
     * @param page
     * @param limit
     * @return
     */
    PageInfo<Label> selectLabel(int page, int limit);

}
