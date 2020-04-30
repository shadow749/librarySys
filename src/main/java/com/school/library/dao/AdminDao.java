package com.school.library.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.school.library.bean.Admin;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.school.library.bean.InputParam;
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
public interface AdminDao extends BaseMapper<Admin> {
    List<Admin> findPage(Pagination page, @Param("admin") Admin admin);
}
