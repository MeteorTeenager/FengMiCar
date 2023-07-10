package com.qfedu.bus.service;

import com.qfedu.bus.domain.CheckVo;
import com.qfedu.sys.utils.DataGridView;

import java.util.Map;

/**
 * @Author:BlueArc
 * @organization: 春的笑2.0
 * @Version: 1.0
 */
public interface ICheckService {
    Map<String, Object> initCheckFormData(String rentid);

    void saveCheck(CheckVo checkVo);

    DataGridView loadAllCheck(CheckVo checkVo);

    void updateCheck(CheckVo checkVo);

    void deleteCheck(String checkId);

    void deleteBatchCheck(String[] ids);
}
