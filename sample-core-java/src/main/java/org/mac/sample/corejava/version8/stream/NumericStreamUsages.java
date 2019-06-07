package org.mac.sample.corejava.version8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 *
 * @auther mac
 * @date 2019-05-26
 */
public class NumericStreamUsages {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7);

        System.out.println(list.stream().filter(i -> i > 3).reduce(0,Integer::sum));
        // int (32bit) 类型的stream
        IntStream intStream = list.stream().mapToInt(i -> i.intValue());
        System.out.println(intStream.filter(i -> i > 3).reduce(0,Integer::sum));

        // 1 - 100 中和9满足勾股定理的数
        int a = 9;
        IntStream.rangeClosed(1,100)
                 .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                 .boxed().map(b -> new int[]{a,b,(int)Math.sqrt(a * a + b * b)})
                 .forEach(arr -> System.out.println("a="+arr[0]+",b="+arr[1]+",c="+arr[2]));

        IntStream.rangeClosed(1,100)
                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                .mapToObj(b -> new int[]{a,b,(int)Math.sqrt(a * a + b * b)})
                .forEach(arr -> System.out.println("a="+arr[0]+",b="+arr[1]+",c="+arr[2]));

    }
}
