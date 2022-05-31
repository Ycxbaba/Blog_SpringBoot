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
 * @TableName t_content
 */
@TableName(value ="t_content")
@Data
public class Content implements Serializable {
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
    private Integer blogId;

    /**
     *
     */
    private String content;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
