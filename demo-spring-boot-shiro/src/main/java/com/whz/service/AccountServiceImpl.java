package com.whz.service;

import com.whz.entity.Account;
import com.whz.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @auther whz
 * @create 2022-03-02 15:59
 */
@Service
public class AccountServiceImpl implements AccountService{

//    @Autowired
    protected AccountMapper accountMapper;

    @Autowired(required = false)
    public AccountServiceImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    @Override
    public Account getAll() {
        return accountMapper.getAll();
    }
}
