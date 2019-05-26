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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @auther mac
 * @date 2019-05-26
 */
public class CollectorIntroduce {

    public static void main(String[] args) {
        List<Apple> list = Arrays.asList(new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170)
                , new Apple("green", 150)
                , new Apple("yellow", 120)
                , new Apple("green", 170));

        Optional.ofNullable(groupWithTraditional(list)).ifPresent(System.out::println);
        System.out.println("----------------------------");
        Optional.ofNullable(groupWithLambda(list)).ifPresent(System.out::println);
        System.out.println("----------------------------");
        Optional.ofNullable(groupWithCollector(list)).ifPresent(System.out::println);
    }

    private static Map<String,List<Apple>> groupWithTraditional(List<Apple> apples) {
        Map<String,List<Apple>> group = new HashMap<>();
        for (Apple apple : apples) {
            String color = apple.getColor();
            List<Apple> list = group.get(color);
            if (null == list) {
                list = new ArrayList<>();
                list.add(apple);
                group.put(color,list);
            }
            list.add(apple);
        }
        return group;
    }

    private static Map<String,List<Apple>> groupWithLambda(List<Apple> apples) {
        Map<String,List<Apple>> group = new HashMap<>();
        apples.stream().forEach(apple -> {
            List<Apple> c = Optional.ofNullable(group.get(apple.getColor())).orElseGet(() -> {
                List<Apple> list = new ArrayList<>();
                group.put(apple.getColor(), list);
                return list;
            });
            c.add(apple);
        });
        return group;
    }

    private static Map<String,List<Apple>> groupWithCollector(List<Apple> apples) {
        return apples.stream().collect(Collectors.groupingBy(Apple::getColor));
    }

    static class Apple{
        private String color;
        private Integer weight;

        public Apple(String color, Integer weight) {
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
