 /*
  *      (             |"|           !!!       #   ___                             o
  *      _)_          _|_|_       `  _ _  '    #  <_*_>             ,,,         ` /_\ '       __MMM__
  *     (o o)         (o o)      -  (OXO)  -   #  (o o)            (o o)       - (o o) -       (o o)
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo--8---(_)--Ooo----ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  *
  * 虽不能至,心向往之。(Although I can't, my heart is longing for it.)
  *
  *
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  */
package org.mac.sample.corejava.concurrency.pattern.future;

/**
 *
 * @author Mac
 * @create 2018-05-25 20:44
 **/

public class FutureExecutor {
    public<T> Future<T> execute (FutureRunnable<T> task) {
        AsynFuture<T> aysnFuture = new AsynFuture<T>();
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
