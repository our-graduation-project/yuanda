package wang.haogui.yuanda.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wang.haogui.yuanda.mapper.LabelConnectionMapper;
import wang.haogui.yuanda.model.Label;
import wang.haogui.yuanda.model.LabelConnection;
import wang.haogui.yuanda.model.LabelConnectionExample;
import wang.haogui.yuanda.model.LabelExample;
import wang.haogui.yuanda.service.LabelConnectionService;

import java.util.List;

/**
 * @author whg
 * @date 2019/12/23 12:06
 **/
@Service
public class LabelConnectionServiceImpl implements LabelConnectionService {

    @Autowired
    private LabelConnectionMapper labelConnectionMapper;

    /**
     * 添加一条标签联系
     *
     * @param labelConnection
     * @return
     */
    @Override
    public boolean addLabelConnection(LabelConnection labelConnection) {

        if(labelConnection.getId() != null && labelConnection.getId() > 0){
            labelConnection.setId(0);
        }
        return labelConnectionMapper.insert(labelConnection) > 0 ? true : false;
    }

    /**
     * 用过标签联系的id删除一条联系数据
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteLabelConnection(int id) {
        LabelConnection labelConnection = new LabelConnection();
        labelConnection.setIsDeleted(true);
        labelConnection.setId(id);
        return labelConnectionMapper.updateByPrimaryKeySelective(labelConnection) > 0 ? true :false;
    }

    /**
     * 通过labelId，删除所有与labelid相关的labelConnection
     *
     * @param labelId
     * @return
     */
    @Override
    public Boolean deleteBatchByLabelId(int labelId) {
        LabelConnection labelConnection = new LabelConnection();
        labelConnection.setIsDeleted(true);
        labelConnection.setLabelId(labelId);
//        int i = labelConnectionMapper.deleteBatchByLabelId(labelConnection);
//        return i > 0 ? true : false;
        return true;
    }

    /**
     * 批量加入LabelConnection
     *
     * @param labelConnectionList
     * @return
     */
    @Override
    public int addBatch(List<LabelConnection> labelConnectionList) {
        if(labelConnectionList == null || labelConnectionList.isEmpty()){
            return 0;
        }
        return labelConnectionMapper.insertBatch(labelConnectionList);
    }
}
