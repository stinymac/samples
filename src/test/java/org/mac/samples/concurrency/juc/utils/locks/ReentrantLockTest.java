 /*
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
  */
package org.mac.samples.concurrency.juc.utils.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Mac
 * @create 2018-06-16 21:01
 **/

public class ReentrantLockTest {

    private final static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
       // new Thread(()->useLock()).start();
       // new Thread(()->useLock()).start();

        /*Thread t1 = new Thread(()->useLockInterruptibly());
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(()->useLockInterruptibly());
        t2.start();
        TimeUnit.SECONDS.sleep(1);

        t2.interrupt();*/

        Thread t1 = new Thread(()->useTryLock());
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(()->useTryLock());
        t2.start();


    }

    public static void useLock() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" running...");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void useLockInterruptibly() {
        try {
            lock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName()+" running...");
            while (true) {

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void useTryLock() {
        if (lock.tryLock()) {
            try {
                System.out.println(Thread.currentThread().getName()+" running...");
                while (true) {

                }
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("I ["+Thread.currentThread().getName()+"] can not get lock.");
        }
    }
}
