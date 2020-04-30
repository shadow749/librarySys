package com.school.library.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.school.library.bean.Admin;
import com.school.library.bean.Student;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shadow
 * @since 2020-04-29
 */
public interface StudentDao extends BaseMapper<Student> {
    List<Student> findPage(Pagination page, @Param("stu") Student student);
}
