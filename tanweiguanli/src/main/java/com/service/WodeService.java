package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.utils.PageUtils;
import com.entity.WodeEntity;
import java.util.Map;

/**
 * 我的摊位 服务类
 * @author 
 * @since 2021-05-07
 */
public interface WodeService extends IService<WodeEntity> {

    /**
    * @param params 查询参数
    * @return 带分页的查询出来的数据
    */
     PageUtils queryPage(Map<String, Object> params);
}