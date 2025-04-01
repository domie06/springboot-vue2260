package com.entity.view;

import com.entity.WodeEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 我的摊位
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 * @author 
 * @email
 * @date 2021-05-06
 */
@TableName("wode")
public class WodeView extends WodeEntity implements Serializable {
    private static final long serialVersionUID = 1L;



		//级联表 taanwei
			/**
			* 摊位编号
			*/
			private String taanweiName;
			/**
			* 摊位类型
			*/
			private Integer taanweiTypes;
				/**
				* 摊位类型的值
				*/
				private String taanweiValue;
			/**
			* 摊位状态
			*/
			private Integer zhuangtaiTypes;
				/**
				* 摊位状态的值
				*/
				private String zhuangtaiValue;
			/**
			* 使用用户
			*/
			private Integer yonghuId;
			/**
			* 摊位区域
			*/
			private Integer quyuTypes;
				/**
				* 摊位区域的值
				*/
				private String quyuValue;
			/**
			* 详情信息
			*/
			private String taanweiContent;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 性别
			*/
			private Integer sexTypes;
				/**
				* 性别的值
				*/
				private String sexValue;
			/**
			* 身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 手机号
			*/
			private String yonghuPhone;
			/**
			* 邮箱
			*/
			private String yonghuEmail;
			/**
			* 照片
			*/
			private String yonghuPhoto;

	public WodeView() {

	}

	public WodeView(WodeEntity wodeEntity) {
		try {
			BeanUtils.copyProperties(this, wodeEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

















				//级联表的get和set taanwei
					/**
					* 获取： 摊位编号
					*/
					public String getTaanweiName() {
						return taanweiName;
					}
					/**
					* 设置： 摊位编号
					*/
					public void setTaanweiName(String taanweiName) {
						this.taanweiName = taanweiName;
					}
					/**
					* 获取： 摊位类型
					*/
					public Integer getTaanweiTypes() {
						return taanweiTypes;
					}
					/**
					* 设置： 摊位类型
					*/
					public void setTaanweiTypes(Integer taanweiTypes) {
						this.taanweiTypes = taanweiTypes;
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
					* 获取： 摊位状态
					*/
					public Integer getZhuangtaiTypes() {
						return zhuangtaiTypes;
					}
					/**
					* 设置： 摊位状态
					*/
					public void setZhuangtaiTypes(Integer zhuangtaiTypes) {
						this.zhuangtaiTypes = zhuangtaiTypes;
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
					* 获取： 使用用户
					*/
					public Integer getYonghuId() {
						return yonghuId;
					}
					/**
					* 设置： 使用用户
					*/
					public void setYonghuId(Integer yonghuId) {
						this.yonghuId = yonghuId;
					}
					/**
					* 获取： 摊位区域
					*/
					public Integer getQuyuTypes() {
						return quyuTypes;
					}
					/**
					* 设置： 摊位区域
					*/
					public void setQuyuTypes(Integer quyuTypes) {
						this.quyuTypes = quyuTypes;
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
					/**
					* 获取： 详情信息
					*/
					public String getTaanweiContent() {
						return taanweiContent;
					}
					/**
					* 设置： 详情信息
					*/
					public void setTaanweiContent(String taanweiContent) {
						this.taanweiContent = taanweiContent;
					}





				//级联表的get和set yonghu
					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 性别
					*/
					public Integer getSexTypes() {
						return sexTypes;
					}
					/**
					* 设置： 性别
					*/
					public void setSexTypes(Integer sexTypes) {
						this.sexTypes = sexTypes;
					}


						/**
						* 获取： 性别的值
						*/
						public String getSexValue() {
							return sexValue;
						}
						/**
						* 设置： 性别的值
						*/
						public void setSexValue(String sexValue) {
							this.sexValue = sexValue;
						}
					/**
					* 获取： 身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}
					/**
					* 获取： 照片
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 照片
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}




}
