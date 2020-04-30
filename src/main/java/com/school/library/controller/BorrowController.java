package com.school.library.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.school.library.bean.Book;
import com.school.library.bean.Borrow;
import com.school.library.bean.InputParam;
import com.school.library.bean.Student;
import com.school.library.service.BookService;
import com.school.library.service.BorrowService;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shadow
 * @since 2020-04-29
 */
@Controller
@RequestMapping("/do/borrow")
public class BorrowController {

    @Resource
    BorrowService borrowService;

    @Resource
    BookService bookService;

    @RequestMapping("list")
    @ResponseBody
    public Map list(Borrow borrow , InputParam inputParam ){
        Page<Borrow> result = borrowService.findPage(borrow,inputParam);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", result.getRecords());
        map.put("total",result.getTotal());
        return map;
    }
    @RequestMapping("mineList")
    @ResponseBody
    public Map mineList(HttpServletRequest request, Borrow borrow , InputParam inputParam ){
        Student stu = (Student)request.getSession().getAttribute("stu");
        borrow.setUserId(stu.getId());
        Page<Borrow> result = borrowService.findPage(borrow,inputParam);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", result.getRecords());
        map.put("total",result.getTotal());
        return map;
    }

    @RequestMapping("returnBook")
    @ResponseBody
    public Map returnBook( String borrowId,Integer bookId  ){
        Map map = new HashMap();
        map.put("code",0);
        map.put("msg","还书失败");
         try {
             Book book = bookService.selectById(bookId);
             Borrow borrow = borrowService.selectById(borrowId);
             if(book !=null){
                Book book1 = new Book();
                book1.setId(bookId);
                book1.setStatue("1");
                bookService.updateById(book1);
             }
             if(borrow !=null){
                 borrowService.deleteById(borrowId);
             }

             map.put("code",1);
             map.put("msg","还书成功");
          }catch (Exception e){
             e.printStackTrace();
             map.put("msg",e.getMessage());
          }
        return map;
    }
}

