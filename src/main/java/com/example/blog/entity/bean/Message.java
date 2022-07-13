package com.example.blog.entity.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 * @TableName t_message
 */
@TableName(value ="t_message")
@Data
public class Message implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     *
     */
    private String value;

    /**
     *
     */
    private String avatar;

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
    private Integer deleted;

    /**
     *
     */
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     *
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
