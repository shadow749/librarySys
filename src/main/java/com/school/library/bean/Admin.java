package com.school.library.bean;

import lombok.Data;
import lombok.ToString;

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
public class Admin implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String password;
    private String email;
    private String phone;
    /**
     * 职称
     */
    private String title;
    private String department;
    private String sex;
    /**
     * 职位
     */
    private String position;

}
