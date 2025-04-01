package com.dao;

import com.entity.WodeEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WodeView;

/**
 * 我的摊位 Dao 接口
 *
 * @author 
 * @since 2021-05-06
 */
public interface WodeDao extends BaseMapper<WodeEntity> {

   List<WodeView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
