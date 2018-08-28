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
 * @create 2018-05-25 20:48
 **/

public class AsynFuture<T> implements Future<T>{

    private volatile T result;
    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            while (result == null) {
                this.wait();
            }
        }
        return result;
    }

    public void set (T result) {
        synchronized (this) {
           this.result = result;
           this.notifyAll();
        }
    }
}
