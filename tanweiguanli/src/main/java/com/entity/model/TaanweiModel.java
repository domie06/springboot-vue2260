package com.entity.model;

import com.entity.TaanweiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 摊位信息
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 * @author 
 * @email
 * @date 2021-05-07
 */
public class TaanweiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 摊位编号
     */
    private String taanweiName;


    /**
     * 摊位类型
     */
    private Integer taanweiTypes;


    /**
     * 摊位状态
     */
    private Integer zhuangtaiTypes;


    /**
     * 摊位区域
     */
    private Integer quyuTypes;


    /**
     * 详情信息
     */
    private String taanweiContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：摊位编号
	 */
    public String getTaanweiName() {
        return taanweiName;
    }


    /**
	 * 设置：摊位编号
	 */
    public void setTaanweiName(String taanweiName) {
        this.taanweiName = taanweiName;
    }
    /**
	 * 获取：摊位类型
	 */
    public Integer getTaanweiTypes() {
        return taanweiTypes;
    }


    /**
	 * 设置：摊位类型
	 */
    public void setTaanweiTypes(Integer taanweiTypes) {
        this.taanweiTypes = taanweiTypes;
    }
    /**
	 * 获取：摊位状态
	 */
    public Integer getZhuangtaiTypes() {
        return zhuangtaiTypes;
    }


    /**
	 * 设置：摊位状态
	 */
    public void setZhuangtaiTypes(Integer zhuangtaiTypes) {
        this.zhuangtaiTypes = zhuangtaiTypes;
    }
    /**
	 * 获取：摊位区域
	 */
    public Integer getQuyuTypes() {
        return quyuTypes;
    }


    /**
	 * 设置：摊位区域
	 */
    public void setQuyuTypes(Integer quyuTypes) {
        this.quyuTypes = quyuTypes;
    }
    /**
	 * 获取：详情信息
	 */
    public String getTaanweiContent() {
        return taanweiContent;
    }


    /**
	 * 设置：详情信息
	 */
    public void setTaanweiContent(String taanweiContent) {
        this.taanweiContent = taanweiContent;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
