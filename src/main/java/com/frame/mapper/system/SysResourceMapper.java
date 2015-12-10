package com.frame.mapper.system;

import com.frame.model.SysResource;

public interface SysResourceMapper {
    SysResource selectByPrimaryKey(String resourceId);
}