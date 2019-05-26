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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 *
 * @auther mac
 * @date 2019-05-25
 */
public class StreamFunctionOperation {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,6,7,2,1);

        System.out.println(list.stream().filter(i -> i%2 == 0).collect(toList()));
        System.out.println(list.stream().distinct().collect(toList()));
        System.out.println(list.stream().skip(5).collect(toList()));
        System.out.println(list.stream().limit(5).collect(toList()));

        System.out.println(list.stream().map(i -> i * 2).collect(toList()));

        System.out.println(list.stream().allMatch(i -> i > 0));
        System.out.println(list.stream().allMatch(i -> i > 10));
        System.out.println(list.stream().anyMatch(i -> i > 6));
        System.out.println(list.stream().noneMatch(i -> i < 0));

        Optional<Integer> optional = list.stream().filter(i -> i % 2 == 0).findAny();
        System.out.println(optional.get());
        Optional<Integer> opt = list.stream().filter(i -> i > 10).findAny();
        System.out.println(opt.orElse(-1));

        System.out.println(list.stream().filter(i -> i % 2 == 0).findFirst().get());

        System.out.println(list.stream().reduce(0, (i,j) -> i + j));
        list.stream().reduce(Integer::max).ifPresent(System.out::println);
        list.stream().reduce(Integer::min).ifPresent(System.out::println);
        list.stream().reduce( (i,j) -> i + j).ifPresent(System.out::println);
        list.stream().reduce((i,j) -> i > j ? i : j).ifPresent(System.out::println);

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

       menu.stream().map(org.mac.sample.corejava.version8.stream.Dish::getName).forEach(System.out::println);

       String[] words = new String[]{"Hello","World"};
       Stream<String[]> stream = Arrays.stream(words).map(w -> w.split(""));
       stream.flatMap(Arrays::stream).distinct().forEach(System.out::println);
    }
}
