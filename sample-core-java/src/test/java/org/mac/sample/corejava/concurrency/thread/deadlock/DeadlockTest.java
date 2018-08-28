/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.thread.deadlock;

/**
 *
 * @author Mac
 * @create 2018-05-09 14:37
 **/

public class DeadlockTest {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                //do some thing (file、 connection、network )
            }
        }));
        Object lockOne = new Object();
        Object lockOther = new Object();

        final OneTask oneTask = new OneTask(lockOne);
        final OtherTask otherTask = new OtherTask(oneTask,lockOther);
        oneTask.setOtherTask(otherTask);

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    oneTask.one();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    otherTask.other();
                }
            }
        });
        t1.start();
        t2.start();
    }
}
