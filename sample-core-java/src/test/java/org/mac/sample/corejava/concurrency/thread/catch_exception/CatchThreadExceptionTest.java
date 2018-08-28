/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.thread.catch_exception;

/**
 *
 * @author Mac
 * @create 2018-05-10 10:23
 **/

public class CatchThreadExceptionTest {
    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                int i = 10/0;
            }
        },"T");

        t.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName());
                System.out.println(e);
            }
        });

        t.start();
    }
}
