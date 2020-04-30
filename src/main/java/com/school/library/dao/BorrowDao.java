package com.school.library.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.school.library.bean.Book;
import com.school.library.bean.Borrow;
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
public interface BorrowDao extends BaseMapper<Borrow> {
    List<Borrow> findPage(Pagination page, @Param("borrow") Borrow borrow);
}
