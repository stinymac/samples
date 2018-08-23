 /*
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
  */

 package org.mac.examples.concurrency.juc.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Mac
 * @create 2018-06-26 20:48
 **/

public class CompletableFutureTest {

    public static void main(String[] args) {
        CompletableFuture.runAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).whenComplete((v,t)->{
            System.out.println("Done");
        });

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
