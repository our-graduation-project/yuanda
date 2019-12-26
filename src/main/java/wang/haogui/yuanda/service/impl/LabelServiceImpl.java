package wang.haogui.yuanda.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.mapper.LabelConnectionMapper;
import wang.haogui.yuanda.mapper.LabelMapper;
import wang.haogui.yuanda.model.Label;
import wang.haogui.yuanda.model.LabelExample;
import wang.haogui.yuanda.service.LabelConnectionService;
import wang.haogui.yuanda.service.LabelService;
import wang.haogui.yuanda.utils.LogUtils;

import java.util.List;

/**
 * @author whg
 * @date 2019/12/23 11:58
 **/
@Service
public class LabelServiceImpl implements LabelService {

    @Autowired
    private LabelMapper labelMapper;

    @Autowired
    private LabelConnectionService labelConnectionService;

    /**
     * 添加标签
     *
     * @param label
     * @return
     */
    @Override
    public Boolean addLabel(Label label) {
        if(label.getLabelId() != null && label.getLabelId() > 0){
            label.setLabelId(0);
        }
        return labelMapper.insert(label) > 0 ? true : false;
    }

    /**
     * 删除标签
     *
     * @param labelId
     * @return
     */
    @Override
    public Boolean deleteLabel(int labelId) {
        Label label = new Label();
        label.setLabelId(labelId);
        label.setIsDeleted(true);
        Boolean isSuccess = labelMapper.updateByPrimaryKeySelective(label) > 0 ? true : false;
        Boolean batch = false;
        if(isSuccess){
            batch = labelConnectionService.deleteBatchByLabelId(labelId);
        }

        //当出现错误日志记录一下，再返回
        if( !(batch || isSuccess)){
            LogUtils.getDBLogger().info( "批量更新label数据时出现错误:labelId:" + labelId);
            throw new RuntimeException("批量更新label数据时出现错误:");
        }
        return true;
    }

    /**
     * 查询标签，通过page，limit
     *
     * @param page
     * @param limit
     * @return
     */
    @Override
    public PageInfo selectLabel(int page, int limit) {
        LabelExample labelExample = new LabelExample();
        labelExample.or().andIsDeletedEqualTo(Boolean.FALSE);
        PageHelper.startPage(page,limit);
        List<Label> labels = labelMapper.selectByExample(labelExample);
        PageInfo pageInfo = new PageInfo(labels);
        return pageInfo;
    }

    /**
     * 通过联系表中的的类型(文章，或问题)与id 查询她所拥有的所有标签
     *
     * @param page
     * @param limit
     * @param connectionId
     * @param connectionType
     * @return
     */
    @Override
    public PageInfo<Label> selectLabelByIdAndType(int page, int limit, int connectionId, int connectionType) {
        PageHelper.startPage(page,limit);
        List<Label> labels = labelMapper.selectLabelNameByConnectionId(connectionId, connectionType);
        PageInfo pageInfo = new PageInfo(labels,5);
        return pageInfo;
    }
}
