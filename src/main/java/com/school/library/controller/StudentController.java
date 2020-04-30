package com.school.library.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.school.library.bean.Admin;
import com.school.library.bean.InputParam;
import com.school.library.bean.Result;
import com.school.library.bean.Student;
import com.school.library.service.AdminService;
import com.school.library.service.StudentService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/do/user")
public class StudentController {
    @Resource
    StudentService studentService;
    @RequestMapping("list")
    @ResponseBody
    public Map list(Student student , InputParam inputParam ){
        Page<Student> result = studentService.findPage(student,inputParam);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", result.getRecords());
        map.put("total",result.getTotal());
        return map;
    }

    @RequestMapping("edit")
    @ResponseBody
    public Map edit( Student student ){
        Map map = new HashMap();
        map.put("code",0);
        if(StringUtils.isEmpty(student.getStuNum())
                ||StringUtils.isEmpty(student.getPassword())){
            map.put("msg","学号与密码不能为空");
            return map;
        }
        try {
            if(!StringUtils.isEmpty(student.getId())){
                studentService.updateById(student);
                map.put("msg","修改成功");
            }else{
                List<Student> list = studentService.selectList(new EntityWrapper<Student>()
                        .eq("stu_num", student.getStuNum()));
                if(list!=null && list.size()>0){
                    map.put("msg","学号已存在，不能重复注册");
                    return map;
                }
                studentService.insert(student);
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
    public Map<String, Object> dele(HttpServletRequest request, @PathVariable("id") String id) {

        studentService.deleteById(id);
        return Result.sucJsonResp(null);

    }
}

