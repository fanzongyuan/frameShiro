package com.frame.service.system;

import com.frame.mapper.system.SysUserMapper;
import com.frame.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 *
 * @author FANZONGYUAN
 * @date 2015/12/4
 */
@Service
public class UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    public User selectUserByName(String userName){
        return sysUserMapper.selectUserByName(userName);
    }
}
