package com.school.library.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.school.library.bean.Book;
import com.school.library.bean.InputParam;
import com.school.library.bean.Student;
import com.school.library.dao.BookDao;
import com.school.library.service.BookService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shadow
 * @since 2020-04-29
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements BookService {

    @Override
    public Page<Book> findPage(Book book, InputParam inputParam) {
        Page<Book> page = new Page<>(inputParam.getPage(),inputParam.getSize());// 当前页，总条数 构造 page 对象
        return page.setRecords(this.baseMapper.findPage(page,book));
    }
}
