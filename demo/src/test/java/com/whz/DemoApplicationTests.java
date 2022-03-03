package com.whz;

import com.whz.config.MyTestConfig;
import com.whz.entity.Car;
import com.whz.entity.Driver;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private Car car;

    @Autowired
    private Driver driver;

    @Autowired
    MyTestConfig myTestConfig;

    @Test
    void contextLoads() {
        boolean result1 = driver.getCar() == car;
        System.out.println(result1 ? "同一个car" : "不同的car");
//        boolean result = myTestConfig.driver().getCar() == myTestConfig.car();
//        System.out.println(result ? "同一个car" : "不同的car");
    }

}
