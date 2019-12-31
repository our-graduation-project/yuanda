package wang.haogui.yuanda.service;

import wang.haogui.yuanda.model.LabelConnection;

import java.util.List;

/**
 * 标签联系表
 * @author whg
 * @date 2019/12/23 12:05
 **/
public interface LabelConnectionService {

    /**
     * 添加一条标签联系
     * @return
     */
    boolean addLabelConnection(LabelConnection labelConnection);

    /**
     * 用过标签联系的id删除一条联系数据
     * @param id
     * @return
     */
    boolean deleteLabelConnection(int id);

    /**
     * 通过labelId，删除所有与labelid相关的labelConnection
     * @param labelId
     * @return
     */
    Boolean deleteBatchByLabelId(int labelId);


    /**
     * 批量加入LabelConnection
     * @param labelConnectionList
     * @return
     */
    int addBatch(List<LabelConnection> labelConnectionList);
}
