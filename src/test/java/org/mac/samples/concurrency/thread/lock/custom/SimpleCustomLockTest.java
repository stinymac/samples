/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.thread.lock.custom;

/**
 *
 * @author Mac
 * @create 2018-05-09 22:15
 **/

public class SimpleCustomLockTest {
    public static void main(String[] args) throws InterruptedException, Lock.IllegalOperationException {
        final SimpleCustomLock lock = new SimpleCustomLock();
        for (int i = 0; i < 5;i++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + " get lock.");
                        Thread.sleep(8000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            lock.unlock();
                            System.out.println(Thread.currentThread().getName() + " release lock.");
                        } catch (Lock.IllegalOperationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            },"T"+(i+1)).start();
        }
        Thread.sleep(100);
        try {
            System.out.println("-----");
            lock.lock(500);
            System.out.println(Thread.currentThread().getName() + " get lock.");
        } catch (Lock.TimeoutException e) {
            System.out.println(Thread.currentThread().getName() + ":"+ e.getMessage());
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " release lock.");
        }
    }
}
