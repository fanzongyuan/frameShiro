package com.frame.mapper.system;

import com.frame.model.User;

public interface SysUserMapper {

    /**
     * 根据主键获取用户信息
     * @param userId
     * @return
     */
    User selectByPrimaryKey(Integer userId);

    /**
     * 根据登录名获取用户信息
     * @param userName
     * @return
     */
    User selectUserByName(String userName);
}