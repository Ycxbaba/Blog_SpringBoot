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
 * @TableName t_blog
 */
@TableName(value ="t_blog")
@Data
public class Blog implements Serializable {
    /**
     * 博文id
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
    private Integer deleted;

    /**
     *
     */
    private String title;

    /**
     *
     */
    private Integer typeId;

    /**
     *
     */
    private Integer userId;

    /**
     *
     */
    private Integer views;

    /**
     *
     */
    private Integer likes;

    /**
     *
     */
    private Integer publish;

    /**
     *
     */
    private Integer original;

    /**
     *
     */
    private Integer commented;

    /**
     *
     */
    private Integer recommend;

    /**
     *
     */
    private String information;

    /**
     *
     */
    private String image;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private User user;

    @TableField(exist = false)
    private String typeName;
}
