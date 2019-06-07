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

package org.mac.sample.corejava.version8.concurrency.future;


import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @auther mac
 * @date 2019-06-07
 */

public class SampleFuture {

    public static void main(String[] args) {
        long workStartTimestamp =  System.currentTimeMillis();
        System.out.println("work start at : " + workStartTimestamp);
        Future<String> future = invoke(() -> {
            try {
                Thread.sleep(5000);
                return "work done";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "work error";
            }
        });
        // do other something
        long otherSomethingStartTimestamp = System.currentTimeMillis();
        for(int i = 0 ; i < 1000000000; i ++) {
            if (i % 100000000 == 0) {
                System.out.println("->: do other something");
            }
        }
        System.out.println("other something done at:"+ System.currentTimeMillis() + " time cost :"+(System.currentTimeMillis() - otherSomethingStartTimestamp) + " ms");
        while (!future.isDone()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(future.get() + " at :" + System.currentTimeMillis() + " time cost:"+ (System.currentTimeMillis() - workStartTimestamp));
    }

    public static <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> reference = new AtomicReference();
        AtomicBoolean isDone = new AtomicBoolean(false);

        new Thread(() -> {
            T value = callable.action();
            reference.set(value);
            isDone.set(true);
        }).start();

        return new Future<T>(){

            @Override
            public T get() {
                return reference.get();
            }

            @Override
            public boolean isDone() {
                return isDone.get();
            }
        };
    }

    private interface Future<T> {
        T get();
        boolean isDone();
    }

    private interface Callable<T> {
        T action();
    }
}
