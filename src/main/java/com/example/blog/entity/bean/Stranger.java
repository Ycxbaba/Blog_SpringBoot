package com.example.blog.entity.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName t_stranger
 */
@TableName(value ="t_stranger")
@Data
public class Stranger implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private String ip;

    /**
     * 
     */
    private Integer deleted;

    /**
     * 
     */
    private Integer count;

    /**
     * 
     */
    private String city;

    /**
     * 
     */
    private String province;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}