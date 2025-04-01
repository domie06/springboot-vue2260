package com.entity.vo;

import com.entity.WodeEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 我的摊位
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 * @author 
 * @email
 * @date 2021-05-06
 */
@TableName("wode")
public class WodeVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

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

}
