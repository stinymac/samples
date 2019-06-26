/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.sample.corejava.concurrency.pattern.future;

/**
 *
 * @author Mac
 * @create 2018-05-25 20:44
 **/

public class FutureExecutor {
    public<T> Future<T> execute (FutureRunnable<T> task) {
        AsyncFuture<T> aysnFuture = new AsyncFuture<T>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                T result = task.call();
                aysnFuture.set(result);
            }
        }).start();
        return aysnFuture;
    }

    public<T> void execute (FutureRunnable<T> task,Callback<T> call) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                T result = task.call();
                call.accept(result);
            }
        }).start();

    }
}
