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


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法推导
 *
 * @auther mac
 * @date 2019-05-19
 */
public class MethodReference {

    public static void main(String[] args) {
        Consumer<String> consumer = (t) -> System.out.println(t);
        consumerFun(consumer,"Hell Java!");

        consumerFun(System.out::println,"Hell World!");

        Function<String,Integer> f = Integer::parseInt;
        System.out.println(f.apply("121"));
        BiFunction<String,Integer,Character> biFunction = String::charAt;
        System.out.println(biFunction.apply("Hell",0));

        String s = new String("Hello");
        Function<Integer,Character> charAtFunc = s::charAt;
        System.out.println(charAtFunc.apply(1));

        Supplier<String> stringSupplier = String::new;
        System.out.println(stringSupplier.get());

        BiFunction<String,Integer,Apple> appleBiFunction = Apple::new;
        System.out.println(appleBiFunction.apply("Red",100));

        List<Apple> apples = Arrays.asList(new Apple("Green",100),new Apple("Red",200),new Apple("A",150));
        /**
         * @see Comparator#comparing(java.util.function.Function)
         */
        apples.sort(Comparator.comparing(Apple::getColor));
        System.out.println(apples);
    }

    private static <T> void consumerFun (Consumer<T> consumer, T t) {
        consumer.accept(t);
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
