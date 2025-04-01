package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 摊位信息
 *
 * @author 
 * @email
 * @date 2021-05-07
 */
@TableName("taanwei")
public class TaanweiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public TaanweiEntity() {

	}

	public TaanweiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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

    @Override
    public String toString() {
        return "Taanwei{" +
            "id=" + id +
            ", taanweiName=" + taanweiName +
            ", taanweiTypes=" + taanweiTypes +
            ", zhuangtaiTypes=" + zhuangtaiTypes +
            ", quyuTypes=" + quyuTypes +
            ", taanweiContent=" + taanweiContent +
            ", createTime=" + createTime +
        "}";
    }
}
