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

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 *
 * @auther mac
 * @date 2019-06-02
 */

public class ParallelStreamUsages {

    /**
     * sumWithPlainLoop cost time:36 ms
     * sumWithStream cost time:1224 ms
     * sumWithLongStreamAndParallelStream cost time:29 ms
     *
     * 适用Fork/Join parallel的stream source
     *
     * ArrayList
     * IntStream.rang
     * HashSet
     * TreeSet
     *
     * 不适用Fork/Join parallel的stream source
     *
     * LinkedList
     * Stream.iterator
     *
     * @param args
     */
    public static void main(String[] args) {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "8");
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println("sumWithPlainLoop cost time:"+measure(ParallelStreamUsages::sumWithPlainLoop,100000000) + " ms");
        //System.out.println("sumWithStream cost time:"+measure(ParallelStreamUsages::sumWithStream,100000000) + " ms");
        //System.out.println("sumWithParallelStream cost time:"+measure(ParallelStreamUsages::sumWithParallelStream,100000000) + " ms");
        //System.out.println("sumWithParallelStreamAndUnboxing cost time:"+measure(ParallelStreamUsages::sumWithParallelStreamAndUnboxing,100000000) + " ms");
        System.out.println("sumWithLongStreamAndParallelStream cost time:"+measure(ParallelStreamUsages::sumWithLongStreamAndParallelStream,100000000) + " ms");
    }

    public static long measure(Function<Long,Long> sum,long limit) {
        Long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long startTimestamp = System.currentTimeMillis();
            sum.apply(limit);
            long timeCost = System.currentTimeMillis() -startTimestamp;

            fastest = timeCost < fastest ? timeCost : fastest;
        }
        return fastest;
    }

    public static long sumWithPlainLoop(long limit) {
        long result = 0L;
        for (long i = 0; i < limit; i++) {
            result += i;
        }
        return result;
    }

    public static long sumWithStream(long limit) {
        return Stream.iterate(0L,i -> i + 1L).limit(limit).reduce(0L,Long::sum);
    }

    public static long sumWithParallelStream(long limit) {
        return Stream.iterate(0L,i -> i + 1L).parallel().limit(limit).reduce(0L,Long::sum);
    }

    public static long sumWithParallelStreamAndUnboxing(long limit) {
        return Stream.iterate(0L,i -> i + 1L).mapToLong(Long::longValue).parallel().limit(limit).reduce(0L,Long::sum);
    }

    public static long sumWithLongStreamAndParallelStream(long limit) {
        return LongStream.rangeClosed(0,limit).parallel().sum();
    }
}
