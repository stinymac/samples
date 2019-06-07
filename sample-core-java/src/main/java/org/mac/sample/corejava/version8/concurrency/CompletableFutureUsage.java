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

package org.mac.sample.corejava.version8.concurrency;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 *
 * @auther mac
 * @date 2019-06-07
 */

public class CompletableFutureUsage {

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {
       /* CompletableFuture<Double> doubleCompletableFuture = new CompletableFuture();

        new Thread(() -> {
            double value = doWork();
            System.out.println("value:"+value);
            doubleCompletableFuture.complete(value);
            System.out.println("work thread over at:"+System.currentTimeMillis());
        }).start();

        Thread.sleep(3000);

        doubleCompletableFuture.whenComplete((v,t) -> {
            Optional.ofNullable(v).ifPresent(System.out::println);
            Optional.ofNullable(t).ifPresent(Throwable::printStackTrace);
        });
        System.out.println("main thread over at:"+System.currentTimeMillis());

        CompletableFuture.supplyAsync(CompletableFutureUsage::doWork)
                .whenComplete((v,t) -> {
                    Optional.ofNullable(v).ifPresent(System.out::println);
                    Optional.ofNullable(t).ifPresent(Throwable::printStackTrace);
                });
        // 主线程退出 守护线程退出 System.out::println 没有机会执行
        System.out.println("main thread over at:"+System.currentTimeMillis());*/

        /*ExecutorService executor = Executors.newFixedThreadPool(2,r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(false);
            return thread;
        });
        CompletableFuture.supplyAsync(CompletableFutureUsage::doWork,executor)
                .whenComplete((v,t) -> {
                    Optional.ofNullable(v).ifPresent(d -> {
                        System.out.print("v:" + d);
                    });
                    Optional.ofNullable(t).ifPresent(Throwable::printStackTrace);
                });
        executor.shutdown();*/

       /* ExecutorService executor = Executors.newFixedThreadPool(2,r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(false);
            return thread;
        });

        CompletableFuture.supplyAsync(CompletableFutureUsage::doWork,executor)
                .thenApply(CompletableFutureUsage::multiply)
                .whenComplete((v,t)->Optional.ofNullable(v).ifPresent(System.out::println));

        executor.shutdown();*/

        ExecutorService executor = Executors.newFixedThreadPool(2,r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(false);
            return thread;
        });

       List<Integer> productIds = Arrays.asList(1,2,3,4,5);
       List<Double> result = productIds.stream()
               .map(i -> CompletableFuture.supplyAsync(() -> mockQueryProduct(i),executor))
               .map(completable -> completable.thenApply(CompletableFutureUsage::multiply))
               .map(CompletableFuture::join)
               .collect(Collectors.toList());
       Optional.ofNullable(result).ifPresent(System.out::println);

       executor.shutdown();
    }

    private static double mockQueryProduct(Integer i) {
        try {
            Thread.sleep(RANDOM.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return RANDOM.nextDouble();
    }

    private static double multiply(double value) {
        //System.out.println("value:"+value);
        try {
            Thread.sleep(RANDOM.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return 10 * value;
    }

    private static double doWork() {
        try {
            Thread.sleep(RANDOM.nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return RANDOM.nextDouble();
    }
}
