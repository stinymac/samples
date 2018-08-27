/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.juc.atomic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *
 * @author Mac
 * @create 2018-06-11 17:52
 **/

public class AtomicStampedReferenceTest {

    public static void main(String[] args) throws InterruptedException {
        AtomicStampedReference asr = new AtomicStampedReference(100,0);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                boolean success = asr.compareAndSet(100,101,asr.getStamp(),asr.getStamp()+1);
                System.out.println(success);
                success = asr.compareAndSet(101,100,asr.getStamp(),asr.getStamp()+1);
                System.out.println(success);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = asr.getStamp();
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(asr.getReference());
                boolean success = asr.compareAndSet(100,105,stamp,stamp+1);
                System.out.println(Thread.currentThread().getName()+":"+ success);
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }
}
