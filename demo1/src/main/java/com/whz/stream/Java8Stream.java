package com.whz.stream;

import lombok.Data;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class Java8Stream {

    @Data
    public class Dish {
        private String name;
        private boolean vegetarian;
        private int calories;
        private Type type;
    }

    private List<String> beforeJava7(List<Dish> dishList) {
        List<Dish> lowCaloricDishes = new ArrayList<>();

        // 1. 筛选出卡路里小于400的菜肴
        for (Dish dish : dishList) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }
        // 2. 对筛选出的菜肴进行排序
        // 匿名类
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return Integer.compare(o1.getCalories(), o2.getCalories());
            }
        });
        // 3. 获取排序藕菜肴的名字
        List<String> lowCaloricDishesName = new ArrayList<>();
        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }

        return lowCaloricDishesName;
    }

    private List<String> afterJava8(List<Dish> dishList) {
        return dishList.stream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }

    private Map<Type, List<Dish>> beforeJdk8(List<Dish> dishList) {
        Map<Type, List<Dish>> result = new HashMap<>();

        for (Dish dish : dishList) {
            //不存在则初始化
            if (result.get(dish.getType())==null) {
                List<Dish> dishes = new ArrayList<>();
                dishes.add(dish);
                result.put(dish.getType(), dishes);
            } else {
                //存在则追加
                result.get(dish.getType()).add(dish);
            }
        }

        return result;
    }

    private Map<Type, List<Dish>> afterJdk8(List<Dish> dishList) {
        return dishList.stream().collect(Collectors.groupingBy(Dish::getType));
    }

    // 2. 什么是流
    // 流是从支持数据吃力操作的源生成的元素序列，源可以是数组、文件、集合、函数。流不是集合元素，它不是数据结构并不保存数据，它的主要目的在于计算

    // 3. 如何生成流

    // 4. 流的操作类型

}
