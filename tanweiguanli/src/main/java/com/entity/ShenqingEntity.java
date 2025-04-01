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
 * 摊位申请
 *
 * @author 
 * @email
 * @date 2021-05-06
 */
@TableName("shenqing")
public class ShenqingEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShenqingEntity() {

	}

	public ShenqingEntity(T t) {
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
     * 申请用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 申请摊位
     */
    @TableField(value = "taanwei_id")

    private Integer taanweiId;


    /**
     * 审核状态
     */
    @TableField(value = "shenhe_types")

    private Integer shenheTypes;


    /**
     * 申请类型
     */
    @TableField(value = "shenqing_types")

    private Integer shenqingTypes;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 设置：申请用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：申请用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：申请摊位
	 */
    public Integer getTaanweiId() {
        return taanweiId;
    }


    /**
	 * 获取：申请摊位
	 */

    public void setTaanweiId(Integer taanweiId) {
        this.taanweiId = taanweiId;
    }
    /**
	 * 设置：审核状态
	 */
    public Integer getShenheTypes() {
        return shenheTypes;
    }


    /**
	 * 获取：审核状态
	 */

    public void setShenheTypes(Integer shenheTypes) {
        this.shenheTypes = shenheTypes;
    }
    /**
	 * 设置：申请类型
	 */
    public Integer getShenqingTypes() {
        return shenqingTypes;
    }


    /**
	 * 获取：申请类型
	 */

    public void setShenqingTypes(Integer shenqingTypes) {
        this.shenqingTypes = shenqingTypes;
    }
    /**
	 * 设置：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        return "Shenqing{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", taanweiId=" + taanweiId +
            ", shenheTypes=" + shenheTypes +
            ", shenqingTypes=" + shenqingTypes +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
