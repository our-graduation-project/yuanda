package wang.haogui.yuanda.service;

import com.github.pagehelper.PageInfo;
import org.hibernate.validator.constraints.EAN;
import wang.haogui.yuanda.model.Label;

import java.util.List;

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

    /**
     * 通过联系表中的的类型(文章，或问题)与id 查询她所拥有的所有标签
     * @param page
     * @param limit
     * @param connectionId
     * @param connectionType
     * @return
     */
    PageInfo<Label> selectLabelByIdAndType(int page, int limit, int connectionId, int connectionType);

    /**
     * 批量增加label
     * @param list
     * @return
     */
    int addBatchLabel(List list);

    /**
     * 通过标签名查找id
     * @param labelName
     * @return
     */
    List<Label> getIdByLabelName(String labelName);
}
