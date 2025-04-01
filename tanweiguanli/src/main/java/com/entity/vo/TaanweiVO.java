package com.entity.vo;

import com.entity.TaanweiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 摊位信息
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email
 * @date 2021-05-07
 */
@TableName("taanwei")
public class TaanweiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 摊位编号
     */

    @TableField(value = "taanwei_name")
    private String taanweiName;


    /**
     * 摊位类型
     */

    @TableField(value = "taanwei_types")
    private Integer taanweiTypes;


    /**
     * 摊位状态
     */

    @TableField(value = "zhuangtai_types")
    private Integer zhuangtaiTypes;


    /**
     * 摊位区域
     */

    @TableField(value = "quyu_types")
    private Integer quyuTypes;


    /**
     * 详情信息
     */

    @TableField(value = "taanwei_content")
    private String taanweiContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：摊位编号
	 */
    public String getTaanweiName() {
        return taanweiName;
    }


    /**
	 * 获取：摊位编号
	 */

    public void setTaanweiName(String taanweiName) {
        this.taanweiName = taanweiName;
    }
    /**
	 * 设置：摊位类型
	 */
    public Integer getTaanweiTypes() {
        return taanweiTypes;
    }


    /**
	 * 获取：摊位类型
	 */

    public void setTaanweiTypes(Integer taanweiTypes) {
        this.taanweiTypes = taanweiTypes;
    }
    /**
	 * 设置：摊位状态
	 */
    public Integer getZhuangtaiTypes() {
        return zhuangtaiTypes;
    }


    /**
	 * 获取：摊位状态
	 */

    public void setZhuangtaiTypes(Integer zhuangtaiTypes) {
        this.zhuangtaiTypes = zhuangtaiTypes;
    }
    /**
	 * 设置：摊位区域
	 */
    public Integer getQuyuTypes() {
        return quyuTypes;
    }


    /**
	 * 获取：摊位区域
	 */

    public void setQuyuTypes(Integer quyuTypes) {
        this.quyuTypes = quyuTypes;
    }
    /**
	 * 设置：详情信息
	 */
    public String getTaanweiContent() {
        return taanweiContent;
    }


    /**
	 * 获取：详情信息
	 */

    public void setTaanweiContent(String taanweiContent) {
        this.taanweiContent = taanweiContent;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
