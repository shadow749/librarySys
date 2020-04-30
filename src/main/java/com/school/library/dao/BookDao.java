package com.school.library.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.school.library.bean.Book;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.school.library.bean.Student;
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
public interface BookDao extends BaseMapper<Book> {
    List<Book> findPage(Pagination page, @Param("book") Book book);
}
