package com.school.library.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.school.library.bean.Admin;
import com.baomidou.mybatisplus.service.IService;
import com.school.library.bean.InputParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shadow
 * @since 2020-04-29
 */
public interface AdminService extends IService<Admin> {
    Page<Admin> findPage(Admin admin,InputParam inputParam);
}
