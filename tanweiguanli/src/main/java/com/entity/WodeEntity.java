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
 * 我的摊位
 *
 * @author 
 * @email
 * @date 2021-05-06
 */
@TableName("wode")
public class WodeEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public WodeEntity() {

	}

	public WodeEntity(T t) {
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
     * 摊主
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 摊位编号
     */
    @TableField(value = "taanwei_id")

    private Integer taanweiId;


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
	 * 设置：摊主
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：摊主
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：摊位编号
	 */
    public Integer getTaanweiId() {
        return taanweiId;
    }


    /**
	 * 获取：摊位编号
	 */

    public void setTaanweiId(Integer taanweiId) {
        this.taanweiId = taanweiId;
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
        return "Wode{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", taanweiId=" + taanweiId +
            ", createTime=" + createTime +
        "}";
    }
}
