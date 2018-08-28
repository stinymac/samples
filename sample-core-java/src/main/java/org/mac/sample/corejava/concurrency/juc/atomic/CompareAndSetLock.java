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
package org.mac.sample.corejava.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author Mac
 * @create 2018-06-11 15:43
 **/

public class CompareAndSetLock {

    private final AtomicInteger lock = new AtomicInteger(0);

    private Thread lockHolder;

    public void tryLock() throws TryLockFailException {
        boolean success = lock.compareAndSet(0,1);
        if (success) {
            lockHolder = Thread.currentThread();
        } else {
            throw new TryLockFailException(Thread.currentThread().getName()+" get lock fail.");
        }
    }

    public void unlock () {
        if (0 == lock.get()) {
            return;
        }
        if (Thread.currentThread() == lockHolder) {
            lock.compareAndSet(1,0);
        }
    }

    class TryLockFailException extends Exception {
        public TryLockFailException (String msg) {
            super(msg);
        }
    }
}
