 /*
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
  */
package org.mac.examples.concurrency.juc.utils.locks;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 * @author Mac
 * @create 2018-06-16 21:37
 **/

public class ReadWriteLockTest {

    private static final ReadWriteLock RWLOCk = new ReentrantReadWriteLock();
    private static final Lock RLOCK = RWLOCk.readLock();
    private static final Lock WLOCK = RWLOCk.writeLock();

    private static final List<Long> data = new ArrayList<>();

    public static void main(String[] args) {
        new Thread(()->write()).start();
        new Thread(()->read()).start();

        new Thread(()->write()).start();
        new Thread(()->write()).start();

        new Thread(()->read()).start();
        new Thread(()->read()).start();
    }

    public static void write() {
        try {
            WLOCK.lock();
            long v = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+" write:"+v);
            TimeUnit.SECONDS.sleep(1);
            data.add(v);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            WLOCK.unlock();
        }
    }

    public static long read() {
        try {
            RLOCK.lock();
            long t = data.get(0);
            System.out.println(Thread.currentThread().getName()+" read:"+t);
            return t;
        }finally {
            RLOCK.unlock();
        }
    }
}
