package com.school.library.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.school.library.bean.Admin;
import com.school.library.bean.InputParam;
import com.school.library.dao.AdminDao;
import com.school.library.service.AdminService;
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
public class AdminServiceImpl extends ServiceImpl<AdminDao, Admin> implements AdminService {

    @Override
    public Page<Admin> findPage(Admin admin, InputParam inputParam) {
        Page<Admin> page = new Page<>(inputParam.getPage(),inputParam.getSize());// 当前页，总条数 构造 page 对象
        return page.setRecords(this.baseMapper.findPage(page,admin));
    }
}
