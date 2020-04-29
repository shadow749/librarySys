package com.school.library.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.school.library.bean.Admin;
import com.school.library.bean.Student;
import com.school.library.service.AdminService;
import com.school.library.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("login")   //请求映射
public class LoginController {

    @Resource
    StudentService studentService;

    @Resource
    AdminService adminService;

    @RequestMapping("register")
    @ResponseBody
    public Map register(HttpServletRequest request, Student student) {

        Map map = new HashMap();
        map.put("code",0);
        try {
            if(StringUtils.isEmpty(student.getStuNum())||
                    StringUtils.isEmpty(student.getPassword())||
                    StringUtils.isEmpty(student.getName())){
                map.put("msg","参数不能为空");
                return map;
            }
            List<Student> studentList = studentService.selectList(new EntityWrapper<Student>()
                    .eq("stu_Num", student.getStuNum()));
            if(studentList!=null && studentList.size()>0){
                map.put("msg","学号已存在，不能重复注册");
                return map;
            }

            student.setId(UUID.randomUUID().toString().replace("-",""));
            studentService.insert(student);
            map.put("code",1);
            map.put("msg","注册成功，请返回登录");
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("login")
    @ResponseBody
    public Map login(HttpServletResponse response, HttpServletRequest request,
                     String account,String password,String type) {

        Map map = new HashMap();
        map.put("code",0);
        try {
            map.put("msg","登录失败");
            request.getSession().setAttribute("type",type);
            if ("1".equals(type)) {//学生
                List<Student> studentList = studentService.selectList(new EntityWrapper<Student>()
                        .eq("stu_Num", account).eq("password", password));
                if(studentList!=null && studentList.size()>0){
                    map.put("code",1);
                    map.put("msg","");
                    map.put("url","/index");
                    request.getSession().setAttribute("stu",studentList.get(0));

                }
            }else if ("2".equals(type)) {//管理员
                List<Admin> adminList = adminService.selectList(new EntityWrapper<Admin>()
                        .eq("name", account).eq("password", password));
                if(adminList!=null && adminList.size()>0){
                    map.put("code",1);
                    map.put("msg","");
                    map.put("url","/index");
                    request.getSession().setAttribute("admin",adminList.get(0));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping("getSession")
    @ResponseBody
    public Map getInfo(HttpServletRequest request) {
        //从session里获取用户数据
        Map map = new HashMap();
        String type = (String)request.getSession().getAttribute("type");
        if(!StringUtils.isEmpty(type)){
            map.put("type",type);
            if ("1".equals(type)){
                Student stu = (Student)request.getSession().getAttribute("stu");
                map.put("name",stu.getStuNum());
            }else if ("2".equals(type)){
                Admin admin =(Admin) request.getSession().getAttribute("admin");
                map.put("name",admin.getName());
            }
        }
        return map;
    }

    @RequestMapping("logout")
    public String logout( HttpServletRequest request  ){

        request.getSession().removeAttribute("user");//删除session信息

        return "redirect:/";
    }
}
