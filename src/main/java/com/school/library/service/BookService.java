package com.school.library.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.school.library.bean.Book;
import com.baomidou.mybatisplus.service.IService;
import com.school.library.bean.InputParam;
import com.school.library.bean.Student;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shadow
 * @since 2020-04-29
 */
public interface BookService extends IService<Book> {
    Page<Book> findPage(Book book, InputParam inputParam);
}
