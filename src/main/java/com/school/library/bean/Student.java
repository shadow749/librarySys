package com.school.library.bean;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author shadow
 * @since 2020-04-29
 */
@Data
@ToString
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    @TableField("stu_num")
    private String stuNum;
    private String name;
    private String password;


    @TableField("create_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")//json日期格式转换
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//参数日期格式转换
    private Date createTime;

    private String college;
    @TableField("lib_card")
    private String libCard;
    private String phone;


}
