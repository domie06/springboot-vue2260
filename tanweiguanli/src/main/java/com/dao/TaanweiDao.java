package com.dao;

import com.entity.TaanweiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.TaanweiView;

/**
 * 摊位信息 Dao 接口
 *
 * @author 
 * @since 2021-05-07
 */
public interface TaanweiDao extends BaseMapper<TaanweiEntity> {

   List<TaanweiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
