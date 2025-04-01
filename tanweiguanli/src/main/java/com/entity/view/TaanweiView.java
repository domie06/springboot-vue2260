package com.entity.view;

import com.entity.TaanweiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 摊位信息
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-05-07
 */
@TableName("taanwei")
public class TaanweiView extends TaanweiEntity implements Serializable {
    private static final long serialVersionUID = 1L;
		/**
		* 摊位类型的值
		*/
		private String taanweiValue;
		/**
		* 摊位状态的值
		*/
		private String zhuangtaiValue;
		/**
		* 摊位区域的值
		*/
		private String quyuValue;



	public TaanweiView() {

	}

	public TaanweiView(TaanweiEntity taanweiEntity) {
		try {
			BeanUtils.copyProperties(this, taanweiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 摊位类型的值
			*/
			public String getTaanweiValue() {
				return taanweiValue;
			}
			/**
			* 设置： 摊位类型的值
			*/
			public void setTaanweiValue(String taanweiValue) {
				this.taanweiValue = taanweiValue;
			}
			/**
			* 获取： 摊位状态的值
			*/
			public String getZhuangtaiValue() {
				return zhuangtaiValue;
			}
			/**
			* 设置： 摊位状态的值
			*/
			public void setZhuangtaiValue(String zhuangtaiValue) {
				this.zhuangtaiValue = zhuangtaiValue;
			}
			/**
			* 获取： 摊位区域的值
			*/
			public String getQuyuValue() {
				return quyuValue;
			}
			/**
			* 设置： 摊位区域的值
			*/
			public void setQuyuValue(String quyuValue) {
				this.quyuValue = quyuValue;
			}









}
