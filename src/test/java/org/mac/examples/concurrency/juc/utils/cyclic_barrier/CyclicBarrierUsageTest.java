 /*
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
  */
package org.mac.examples.concurrency.juc.utils.cyclic_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Mac
 * @create 2018-06-16 12:25
 **/

public class CyclicBarrierUsageTest {
    static class CyclicBarrierUsage{
        public void demo () {
            ExecutorService executor = Executors.newFixedThreadPool(10);

            CyclicBarrier barrier = new CyclicBarrier(10);
            // 9--一直等待
            for (int i = 0 ; i < 9; i++) {
                new Thread(()->{
                    try {
                        barrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName());
                }).start();
            }
            executor.shutdown();

        }

    }

    public void demo2 (){
        CyclicBarrier barrier = new CyclicBarrier(10,()->{
            System.out.println("call back");
        });

    }

    public static void main(String[] args) {
        CyclicBarrierUsage usage = new CyclicBarrierUsage();

        usage.demo();
    }
}
