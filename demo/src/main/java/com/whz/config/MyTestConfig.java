package com.whz.config;

import com.whz.entity.Driver;
import com.whz.entity.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @auther whz
 * @create 2022-03-02 09:42
 */
@Configuration
//@Component
public class MyTestConfig {

    @Bean
    public Driver driver() {
        Driver driver = new Driver();
        driver.setId(1L);
        driver.setName("driver");
        driver.setCar(car());
        return driver;
    }

    @Bean
    public Car car() {
        Car car = new Car();
        car.setId(1L);
        car.setName("car");
        return car;
    }
}
