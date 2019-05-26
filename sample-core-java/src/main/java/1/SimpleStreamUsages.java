/*
 *          (          (
 *          )\ )  (    )\   )  )     (
 *  (  (   (()/( ))\( ((_| /( /((   ))\
 *  )\ )\   ((_))((_)\ _ )(_)|_))\ /((_)
 * ((_|(_)  _| (_))((_) ((_)__)((_|_))
 * / _/ _ \/ _` / -_|_-< / _` \ V // -_)
 * \__\___/\__,_\___/__/_\__,_|\_/ \___|
 *
 * 东隅已逝，桑榆非晚。(The time has passed,it is not too late.)
 * 虽不能至，心向往之。(Although I can't, my heart is longing for it.)
 *
 */

package org.mac.sample.corejava.version8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Stream 使用
 *
 * @auther mac
 * @date 2019-05-19
 */
public class SimpleStreamUsages {
    private static final int LOW_CALORIES_DISH_THRESHOLD = 400;

    public static void main(String[] args) {
        List<org.mac.sample.corejava.version8.stream.Dish> menu = Arrays.asList(
                new org.mac.sample.corejava.version8.stream.Dish("pork", false, 800, org.mac.sample.corejava.version8.stream.Dish.Type.MEAT),
                new org.mac.sample.corejava.version8.stream.Dish("beef", false, 700, org.mac.sample.corejava.version8.stream.Dish.Type.MEAT),
                new org.mac.sample.corejava.version8.stream.Dish("chicken", false, 400, org.mac.sample.corejava.version8.stream.Dish.Type.MEAT),
                new org.mac.sample.corejava.version8.stream.Dish("french fries", true, 530, org.mac.sample.corejava.version8.stream.Dish.Type.OTHER),
                new org.mac.sample.corejava.version8.stream.Dish("rice", true, 350, org.mac.sample.corejava.version8.stream.Dish.Type.OTHER),
                new org.mac.sample.corejava.version8.stream.Dish("season fruit", true, 120, org.mac.sample.corejava.version8.stream.Dish.Type.OTHER),
                new org.mac.sample.corejava.version8.stream.Dish("pizza", true, 550, org.mac.sample.corejava.version8.stream.Dish.Type.OTHER),
                new org.mac.sample.corejava.version8.stream.Dish("prawns", false, 300, org.mac.sample.corejava.version8.stream.Dish.Type.FISH),
                new org.mac.sample.corejava.version8.stream.Dish("salmon", false, 450, org.mac.sample.corejava.version8.stream.Dish.Type.FISH));

        System.out.println(menu.stream().collect(Collectors.toMap(org.mac.sample.corejava.version8.stream.Dish::getName, dish -> dish)));
        System.out.println(menu.stream().collect(Collectors.toMap(org.mac.sample.corejava.version8.stream.Dish::getName, Function.identity())));

        System.out.println(getDishNameLowCalories(menu));
        System.out.println(getDishNameLowCaloriesWithStream(menu));

        System.out.println("================");

        System.out.println(menu.stream().filter(dish -> {
            System.out.println("filtering->:"+dish.getName());
            return dish.getCalories() < LOW_CALORIES_DISH_THRESHOLD;
        }).map(dish -> {
            System.out.println("map->:"+dish.getName());
            return dish.getName();
        }).collect(toList()));

        System.out.println("================");

        Stream dishStream = Stream.of(new org.mac.sample.corejava.version8.stream.Dish("prawns", false, 300, org.mac.sample.corejava.version8.stream.Dish.Type.FISH),
                new org.mac.sample.corejava.version8.stream.Dish("salmon", false, 450, org.mac.sample.corejava.version8.stream.Dish.Type.FISH));
        dishStream.forEach(System.out::println);
    }

    /**
     * 低卡路里的食物名称清单
     *
     * @param dishes
     * @return
     */
    private static List<String> getDishNameLowCalories(List<org.mac.sample.corejava.version8.stream.Dish> dishes) {
        List<org.mac.sample.corejava.version8.stream.Dish> lowCalories = new ArrayList<>();
        for (org.mac.sample.corejava.version8.stream.Dish dish : dishes) {
            if (dish.getCalories() < LOW_CALORIES_DISH_THRESHOLD) {
                lowCalories.add(dish);
            }
        }

        //sort
        Collections.sort(lowCalories,(o1,o2) -> o1.getCalories() - o2.getCalories());

        List<String> lowCaloriesDishNames = new ArrayList<>(lowCalories.size());
        for (org.mac.sample.corejava.version8.stream.Dish dish : lowCalories) {
            lowCaloriesDishNames.add(dish.getName());
        }

        return lowCaloriesDishNames;
    }

    /**
     * Stream的方式
     *
     * @param dishes
     * @return
     */
    private static List<String> getDishNameLowCaloriesWithStream(List<org.mac.sample.corejava.version8.stream.Dish> dishes) {
        return dishes.stream()
                .filter((dish) -> dish.getCalories() < LOW_CALORIES_DISH_THRESHOLD)
                .sorted(Comparator.comparing(org.mac.sample.corejava.version8.stream.Dish::getCalories))
                .map(org.mac.sample.corejava.version8.stream.Dish::getName)
                .collect(toList());
    }
}
