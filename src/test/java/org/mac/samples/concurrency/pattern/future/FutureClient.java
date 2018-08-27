/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.pattern.future;

/**
 *
 * @author Mac
 * @create 2018-05-25 21:09
 **/

public class FutureClient {
    public static void main(String[] args) throws InterruptedException {
        FutureExecutor executor = new FutureExecutor();
        /*Future<String> future = executor.execute(new FutureRunnable<String>() {
            @Override
            public String call() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "work done";
            }
        });
        System.out.println("======do other======");

        Thread.sleep(1000);

        System.out.println("======do other over======");
        System.out.println(future.get());*/

        executor.execute(new FutureRunnable<String>() {
            @Override
            public String call() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "work done";
            }
        }, new Callback<String>() {
            @Override
            public void accept(String result) {
                System.out.println(result);
            }
        });
    }
}
