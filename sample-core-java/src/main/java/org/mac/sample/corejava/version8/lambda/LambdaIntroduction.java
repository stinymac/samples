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

package org.mac.sample.corejava.version8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Lambda表达式使用
 *
 * @auther mac
 * @date 2019-05-19
 */
public class LambdaIntroduction {

    private static List<Apple> filter(List<Apple> source, Predicate<Apple> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : source) {
            if (predicate.test(apple)) {
                result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> filterByWeight(List<Apple> source, LongPredicate predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : source) {
            if (predicate.test(apple.getWeight())) {
                result.add(apple);
            }
        }
        return result;
    }

    private static List<Apple> filterByBiPredicate(List<Apple> source, BiPredicate<String,Long> predicate) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : source) {
            if (predicate.test(apple.getColor(),apple.getWeight())) {
                result.add(apple);
            }
        }
        return result;
    }

    private static void consumer(List<Apple> source, Consumer<Apple> consumer) {
        for (Apple apple : source) {
            consumer.accept(apple);
        }
    }

    private static void biConsumer(List<Apple> source, String prefix,BiConsumer<String,Apple> consumer) {
        for (Apple apple : source) {
            consumer.accept(prefix,apple);
        }
    }

    private static String fun(Apple apple, Function<Apple,String> f) {
       return f.apply(apple);
    }

    private static Apple biFun(String color,Long weight,BiFunction<String,Long,Apple> f) {
        return f.apply(color,weight);
    }

    private static Apple createApple(Supplier<Apple> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {
        List<Apple> apples = Arrays.asList(new Apple("green",120L),new Apple("red",150L));
        System.out.println(filter(apples,(apple)->"green".equals(apple.getColor())));
        System.out.println(filterByWeight(apples,weight -> weight > 100));
        System.out.println(filterByBiPredicate(apples,(color,weight) -> "green".equals(color) && weight > 100));
        System.out.println("---------------------------------------------------");
        consumer(apples,(apple) -> System.out.println(apple));
        biConsumer(apples,"C:",(prefix,apple) -> System.out.println(prefix+apple.getColor()+"-"+apple.getWeight()));
        System.out.println("---------------------------------------------------");
        System.out.println(fun(new Apple("red",200L),(apple -> apple.toString())));

        IntFunction<Double> function = i -> i  * 1000D;
        System.out.println(function.apply(12));

        System.out.println(biFun("green",50L,(color,weight) -> new Apple(color,weight)));

        Supplier<String> stringSupplier = String::new;
        System.out.println(stringSupplier.get().getClass());

        System.out.println(createApple(() -> new Apple("gree",300L)));
    }


    static class Apple{
        private String color;
        private Long weight;

        public Apple(String color, Long weight) {
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Long getWeight() {
            return weight;
        }

        public void setWeight(Long weight) {
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
