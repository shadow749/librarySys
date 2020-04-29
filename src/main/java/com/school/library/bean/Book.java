package com.school.library.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
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
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 库键码
     */
    @TableField("storage_num")
    private String storageNum;
    /**
     * 登录号
     */
    @TableField("input_num")
    private String inputNum;
    /**
     * 条形码
     */
    private String code;
    /**
     * 管藏地址
     */
    private String address;
    /**
     * 虚拟库室
     */
    private String virtual;
    /**
     * 状态
     */
    private String statue;
    /**
     * 处理日期
     */
    @TableField("opera_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")//json日期格式转换
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")//参数日期格式转换
    private Date operaTime;
    @TableField("admin_id")
    private String adminId;
    /**
     * 索书号
     */
    @TableField("book_num")
    private String bookNum;
    /**
     * 实洋
     */
    private String shiyang;
    /**
     * 备注
     */
    private String remark;
    private String name;
    private String author;

}
