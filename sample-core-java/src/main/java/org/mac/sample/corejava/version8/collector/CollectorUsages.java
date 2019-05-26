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

package org.mac.sample.corejava.version8.collector;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 *
 * @auther mac
 * @date 2019-05-26
 */
public class CollectorUsages {

    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400,  Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350,  Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));

        Optional.ofNullable(menu.stream().collect(Collectors.averagingDouble(Dish::getCalories)))
                .ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(Collectors.collectingAndThen(Collectors.averagingInt(Dish::getCalories),aver -> "The average of Calories is:"+aver)))
                .ifPresent(System.out::println);

        List<Dish> meats = menu.stream().filter(dish -> Dish.Type.MEAT.equals(dish)).collect(Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList));
        //meats.add(new Dish("cake", true, 550, Dish.Type.OTHER));

        Optional.ofNullable(menu.stream().collect(Collectors.counting())).ifPresent(System.out::println);


        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType))).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.counting()))).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType,Collectors.averagingInt(Dish::getCalories)))).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(Collectors.groupingBy(Dish::getType, TreeMap::new,Collectors.averagingInt(Dish::getCalories)))).ifPresent(System.out::println);

        Optional.ofNullable(menu.stream().collect(Collectors.summarizingInt(Dish::getCalories))).ifPresent(System.out::println);
    }



    static class Dish{

        private final String name;
        private final boolean vegetarian;
        private final int calories;
        private final Dish.Type type;

        public Dish(String name, boolean vegetarian, int calories, Dish.Type type) {
            this.name = name;
            this.vegetarian = vegetarian;
            this.calories = calories;
            this.type = type;
        }


        public String getName() {
            return name;
        }

        public boolean isVegetarian() {
            return vegetarian;
        }

        public int getCalories() {
            return calories;
        }

        public Dish.Type getType() {
            return type;
        }

        public enum Type {MEAT, FISH, OTHER}


        @Override
        public String toString() {
            return "Dish{" +
                    "name='" + name + '\'' +
                    ", vegetarian=" + vegetarian +
                    ", calories=" + calories +
                    ", type=" + type +
                    '}';
        }
    }
}
