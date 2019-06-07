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
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 *
 * @auther mac
 * @date 2019-06-07
 */

public class CompletableFutureInAction {

    public static void main(String[] args) throws InterruptedException {

        CompletableFuture.supplyAsync(() -> 1)
                .thenApply(i -> i * 10)
                .whenComplete((v,t) -> System.out.println(v));

        CompletableFuture.supplyAsync(() -> 1)
                .handle((v,t) -> v * 10)// t -> throwable
                .whenComplete((v,t) -> System.out.println(v))
                .thenRun(() -> System.out.println("---------"))
                .thenRunAsync(() -> System.out.println("---------"));

        CompletableFuture.supplyAsync(() -> 1)
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenCompose(i -> CompletableFuture.supplyAsync(() -> i * 10))
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenCombine(CompletableFuture.supplyAsync(() -> 2.0D),(r1,r2) -> r1 + r2)
                .thenAccept(System.out::println);

        CompletableFuture.supplyAsync(() -> 1)
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> 2.0D),(r1,r2) -> {
                    System.out.println(r1);
                    System.out.println(r2);
                });

        CompletableFuture.supplyAsync(() -> 1)
                .runAfterBoth(CompletableFuture.supplyAsync(() -> 2.0D),() -> System.out.println("run after both over "));

        CompletableFuture
                .supplyAsync(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName());
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 1;
                })
                .applyToEither(CompletableFuture.supplyAsync(() -> 2),r -> r * 10)// 有一个结束 即先结束的
                .thenAccept(System.out::println);

        CompletableFuture
                .supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName());
                    return 1;
                })
                .acceptEither(CompletableFuture.supplyAsync(() -> 2),System.out::println);// 有一个结束 即先结束的

        //其中一个结束后运行
        CompletableFuture
                .supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName());
                    return 12;
                }).runAfterEither(CompletableFuture.supplyAsync(() -> 2),() -> System.out.println("someone done"));

        List<CompletableFuture<Integer>> list = Arrays.asList(1,2,3,4,5).stream()
                .map(i -> CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(i * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })).collect(Collectors.toList());

        CompletableFuture.allOf(list.toArray(new CompletableFuture[list.size()]))
                .thenAccept((i) -> System.out.println("all done"));

        CompletableFuture.anyOf(list.toArray(new CompletableFuture[list.size()]))
                .thenAccept((i) -> System.out.println("someone done"));

        Thread.currentThread().join();
    }
}
