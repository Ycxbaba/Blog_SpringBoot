package com.example.blog.entity.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 *
 * @TableName t_comment
 */
@TableName(value ="t_comment")
@Data
public class Comment implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private Date updateTime;

    /**
     *
     */
    private Date createTime;

    /**
     *
     */
    private Integer deleted;

    /**
     *
     */
    private Integer author;

    /**
     *
     */
    private Integer parentId;

    /**
     *
     */
    private String content;

    /**
     *
     */
    private String nickname;

    /**
     *
     */
    private String qq;

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
    private Integer blogId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String parentName;

    @TableField(exist = false)
    private String blogTitle;

    @TableField(exist = false)
    private Integer count;


    @TableField(exist = false)
    private List<Comment> childrenComment;
}
