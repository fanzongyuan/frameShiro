package com.frame.mapper.system;

import com.frame.model.SysDictionary;

public interface SysDictionaryMapper {
    SysDictionary selectByPrimaryKey(Long id);
}