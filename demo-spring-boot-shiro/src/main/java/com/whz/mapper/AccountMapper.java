package com.whz.mapper;

import com.whz.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @auther whz
 * @create 2022-03-02 15:45
 */
@Mapper
public interface AccountMapper {

    Account getAll();
}
