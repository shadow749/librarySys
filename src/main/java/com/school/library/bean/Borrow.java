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
public class Borrow implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    @TableField("borrow_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")//json日期格式转换
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//参数日期格式转换
    private Date borrowTime;

    @TableField("retrun_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")//json日期格式转换
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//参数日期格式转换
    private Date retrunTime;

    @TableField("user_id")
    private String userId;
    @TableField("user_type")
    private String userType;
    @TableField("lib_card")
    private String libCard;
    @TableField("stu_num")
    private String stuNum;

    @TableField("book_id")
    private Integer bookId;

    @TableField("book_code")
    private String bookCode;

    @TableField("book_name")
    private String bookName;
    private Integer num;

}
