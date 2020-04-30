package com.school.library.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.school.library.bean.*;
import com.school.library.service.BookService;
import com.school.library.service.BorrowService;
import com.school.library.util.DateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shadow
 * @since 2020-04-29
 */
@Controller
@RequestMapping("/do/book")
public class BookController {

    @Resource
    BookService bookService;

    @Resource
    BorrowService borrowService;

    @RequestMapping("list")
    @ResponseBody
    public Map list( Book book , InputParam inputParam ){
        Page<Book> result = bookService.findPage(book,inputParam);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", result.getRecords());
        map.put("total",result.getTotal());
        return map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public Map edit( Book book ){
        Map map = new HashMap();
        map.put("code",0);
        if(StringUtils.isEmpty(book.getName())
                ||StringUtils.isEmpty(book.getCode())){
            map.put("msg","书名与条形码不能为空");
            return map;
        }
        try {
            List<Book> list = bookService.selectList(new EntityWrapper<Book>()
                    .eq("code", book.getCode()));
            if(!StringUtils.isEmpty(book.getId())){
                if(list!=null && list.size()>0){
                    if(list.get(0).getCode().equals(book.getCode())
                            && !list.get(0).getId().equals(book.getId())){
                        map.put("msg","条形码已被其他图书使用，无法修改");
                        return map;
                    }
                }
                bookService.updateById(book);
                map.put("msg","修改成功");
            }else{

                if(list!=null && list.size()>0){
                    map.put("msg","条形码已被其他图书使用，无法新增");
                    return map;
                }
                book.setOperaTime(new Date());
                bookService.insert(book);
                map.put("msg","新增成功");
            }
            map.put("code",1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg",e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> dele(HttpServletRequest request, @PathVariable("id") Integer id) {

        bookService.deleteById(id);
        return Result.sucJsonResp(null);

    }


    @RequestMapping("borrow")
    @ResponseBody
    public Map borrow(   HttpServletRequest request,Integer bookId,String time){
        Map map = new HashMap();
        map.put("code",0);
         try {
             Book book = bookService.selectById(bookId);
             if(book.getStatue().equals("2")){
                 map.put("msg","图书已借出");
                 return map;
             }
             Date nowDate = new Date();
             Date rTime = DateUtils.stringToDate(time);

             if(rTime.getTime()<nowDate.getTime()){
                 map.put("msg","还书日期不能早于今天");
                 return map;
             }
             if((rTime.getTime()-nowDate.getTime())>(1000*60*60*24*15)){
                 map.put("msg","还书日期不能超过15天");
                 return map;
             }


             String type = (String)request.getSession().getAttribute("type");
             if(!StringUtils.isEmpty(type)){
                 Borrow borrow = new Borrow();
                 borrow.setId(UUID.randomUUID().toString().replaceAll("-",""));
                 borrow.setBorrowTime(nowDate);
                 borrow.setRetrunTime(rTime);
                 borrow.setBookId(book.getId());
                 borrow.setBookName(book.getName());
                 borrow.setBookCode(book.getCode());

                 map.put("type",type);
                 if ("1".equals(type)){
                     Student stu = (Student)request.getSession().getAttribute("stu");
                     borrow.setUserId(stu.getId());
                     borrow.setUserType("1");
                     borrow.setLibCard(stu.getLibCard());
                     borrow.setStuNum(stu.getStuNum());
                 }else if ("2".equals(type)){
                     Admin admin =(Admin) request.getSession().getAttribute("admin");
                     borrow.setUserId(admin.getId()+"");
                     borrow.setUserType("2");
                 }
                 //插入借阅信息
                 borrowService.insert(borrow);

                 //修改图书状态
                 Book book1 = new Book();
                 book1.setId(bookId);
                 book1.setStatue("2");
                 bookService.updateById(book1);
                 map.put("code",1);
                 map.put("msg","借阅成功");
             }
          }catch (Exception e){
             e.printStackTrace();
             map.put("msg",e.getMessage());
          }
        return map;
    }
}

