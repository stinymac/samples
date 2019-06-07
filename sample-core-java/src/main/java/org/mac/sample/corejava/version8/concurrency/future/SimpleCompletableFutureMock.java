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

public class SimpleCompletableFutureMock {
    public static void main(String[] args) {
        Future<String>  future = invoke(() -> {
            try {
                Thread.sleep(2000);
                return "work done";
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "work error";
            }
        });
        future.setCompletable(new Completable<String>() {
            @Override
            public void complete(String s) {
                System.out.println("complete:"+s);
            }

            @Override
            public void exception(Throwable cause) {
                cause.printStackTrace();
            }
        });
        // do other something
        for(int i = 0 ; i < 1000000000; i ++) {
            if (i % 100000000 == 0) {
                System.out.println("->: do other something");
            }
        }
    }

    public static <T> Future<T> invoke(Callable<T> callable) {

        AtomicReference<T> reference = new AtomicReference();
        AtomicBoolean isDone = new AtomicBoolean(false);

        final Future future = new Future<T>(){

            private Completable<T> completable;

            @Override
            public T get() {
                return reference.get();
            }

            @Override
            public boolean isDone() {
                return isDone.get();
            }

            @Override
            public void setCompletable(Completable<T> completable) {
                this.completable = completable;
            }

            @Override
            public Completable<T> getCompletable() {
                return this.completable;
            }
        };

        new Thread(() -> {
            try {
                T value = callable.action();
                reference.set(value);
                isDone.set(true);

                if (future.getCompletable() != null) {
                    future.getCompletable().complete(value);
                }
            }catch (Throwable cause) {
                if (future.getCompletable() != null) {
                    future.getCompletable().exception(cause);
                }
            }
        }).start();

        return future;
    }

    private interface Future<T> {
        T get();

        boolean isDone();

        void setCompletable(Completable<T> completable);

        Completable<T> getCompletable();
    }

    private interface Callable<T> {
        T action() throws InterruptedException;
    }

    public interface Completable<T> {
        void complete(T t);
        void exception(Throwable cause);
    }
}
