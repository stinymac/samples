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

package org.mac.sample.corejava.concurrency.juc.utils.count_down_latch;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Mac
 * @create 2018-06-13 23:47
 **/

public class ClassicUsage {

    public void case1() {
        final int [] data = new int[] {1,2,3,4,5,6,7,8,9,10};
        CountDownLatch latch = new CountDownLatch(data.length);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < data.length; i++) {
            final int index = i;
            executor.execute(()->{
               data[index] = (data[index]%2 == 0)?data[index]*2:data[index]*30;
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(data));
        executor.shutdown();
    }

    public void case2() {
        //ExecutorService executor = Executors.newFixedThreadPool(10);

        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 0 ; i < 10; i++) {
            new Thread(()->{
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }).start();
            latch.countDown();
        }
        //executor.shutdown();

    }
}
