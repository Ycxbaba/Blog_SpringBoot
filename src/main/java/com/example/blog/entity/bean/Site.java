package com.example.blog.entity.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 *
 * @TableName t_site
 */
@TableName(value ="t_site")
@Data
public class Site implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private String information;

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
    private String cip;

    /**
     *
     */
    private Integer users;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String avatar;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String github;

    /**
     *
     */
    private String address;

    /**
     *
     */
    private String stack;

    /**
     *
     */
    private String ongoing;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private List<String> stacks;

    @TableField(exist = false)
    private List<String> ongoings;

}
