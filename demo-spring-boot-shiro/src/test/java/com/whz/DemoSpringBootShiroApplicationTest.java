package com.whz;

import com.whz.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @auther whz
 * @create 2022-03-02 16:01
 */
@SpringBootTest
public class DemoSpringBootShiroApplicationTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void test(){
        if(accountService.getAll() != null) {
            System.out.println("1111111111");
        }
    }
}
