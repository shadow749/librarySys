package com.school.library.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.school.library.bean.Admin;
import com.school.library.bean.InputParam;
import com.school.library.bean.Student;
import com.school.library.dao.StudentDao;
import com.school.library.service.StudentService;
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
public class StudentServiceImpl extends ServiceImpl<StudentDao, Student> implements StudentService {

    @Override
    public Page<Student> findPage(Student student, InputParam inputParam) {
        Page<Student> page = new Page<>(inputParam.getPage(),inputParam.getSize());// 当前页，总条数 构造 page 对象
        return page.setRecords(this.baseMapper.findPage(page,student));
    }
}
