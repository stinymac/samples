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
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * 函数式接口 @see {java.lang.FunctionalInterface}
 *
 * 接口里面只能有一个抽象方法。这种类型的接口也称为SAM接口，
 * 即Single Abstract Method interfaces。
 *
 *  -Java 8为函数式接口引入的新注解@FunctionalInterface，主要用于编译错误检查。
 *  函数式接口里允许定义默认方法
 *  函数式接口里允许定义静态方法
 *  函数式接口里允许定义java.lang.Object里的public方法
 *
 * JDK中的函数式接口举例
 *
 * java.lang.Runnable,
 * java.awt.event.ActionListener,
 * java.util.Comparator,
 * java.util.concurrent.Callable
 * java.util.function包下的接口，如Consumer、Predicate、Supplier等
 *
 * Lambda表达式语法
 * (parameters) -> expression
 * 或
 * (parameters) -> {statements;}
 *
 *
 *
 * @auther mac
 * @date 2019-05-19
 */

public class LambdaExpressionSimpleUsages {

    public static void main(String[] args) throws InterruptedException {
        new Thread (
                ()->{System.out.println(Thread.currentThread().getName());}
        ).start();

        new Thread (
                ()->System.out.println(Thread.currentThread().getName())
        ).start();

        Runnable runnable = ()->{};
        //Thread.currentThread().join();

        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("red",200D));
        apples.add(new Apple("green",150D));

        Collections.sort(apples,(o1,o2)->{
            return o1.getColor().compareTo(o2.getColor());
        });
        System.out.println(apples);

        Collections.sort(apples,(o1,o2) -> o1.getColor().compareTo(o2.getColor()));
        System.out.println(apples);

        Function<String,Integer> f = s -> s.length();
        Predicate<Apple> p = apple -> "green".equals(apple.getColor());
    }

    static class Apple {

        private String color;
        private Double weight;

        public Apple(String color, Double weight) {
            this.color = color;
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public Double getWeight() {
            return weight;
        }

        public void setWeight(Double weight) {
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
