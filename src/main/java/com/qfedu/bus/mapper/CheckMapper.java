package com.qfedu.bus.mapper;

import com.qfedu.bus.domain.Check;
import com.qfedu.bus.domain.CheckVo;

import java.util.List;

/**
 * @Author:BlueArc
 * @organization: 春的笑2.0
 * @Version: 1.0
 */
public interface CheckMapper {
    void addCheck(CheckVo checkVo);

    List<Check> queryAllCheck(CheckVo checkVo);

    void updateCheck(CheckVo checkVo);

    void deleteCheckById(String checkid);
}
