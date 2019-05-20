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
import java.util.Map;
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
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        System.out.println(menu.stream().collect(Collectors.toMap(Dish::getName, dish -> dish)));
        System.out.println(menu.stream().collect(Collectors.toMap(Dish::getName, Function.identity())));

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

        Stream dishStream = Stream.of(new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        dishStream.forEach(System.out::println);
    }

    /**
     * 低卡路里的食物名称清单
     *
     * @param dishes
     * @return
     */
    private static List<String> getDishNameLowCalories(List<Dish> dishes) {
        List<Dish> lowCalories = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getCalories() < LOW_CALORIES_DISH_THRESHOLD) {
                lowCalories.add(dish);
            }
        }

        //sort
        Collections.sort(lowCalories,(o1,o2) -> o1.getCalories() - o2.getCalories());

        List<String> lowCaloriesDishNames = new ArrayList<>(lowCalories.size());
        for (Dish dish : lowCalories) {
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
    private static List<String> getDishNameLowCaloriesWithStream(List<Dish> dishes) {
        return dishes.stream()
                .filter((dish) -> dish.getCalories() < LOW_CALORIES_DISH_THRESHOLD)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(toList());
    }
}
