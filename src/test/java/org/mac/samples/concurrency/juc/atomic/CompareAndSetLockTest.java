/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.juc.atomic;

/**
 *
 * @author Mac
 * @create 2018-06-11 16:01
 **/

public class CompareAndSetLockTest {
    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    doSomething();
                }
            }).start();
        }
    }

    private static  final CompareAndSetLock lock = new CompareAndSetLock();

    public static void doSomething() {

        try {
            lock.tryLock();
            System.out.println(Thread.currentThread().getName() + " do something.");
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (CompareAndSetLock.TryLockFailException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
