package com.school.library.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.school.library.bean.Admin;
import com.school.library.bean.InputParam;
import com.school.library.bean.Student;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shadow
 * @since 2020-04-29
 */
public interface StudentService extends IService<Student> {
    Page<Student> findPage(Student student, InputParam inputParam);
}
