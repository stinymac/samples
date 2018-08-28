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
package org.mac.sample.corejava.concurrency.thread.threadpool;

/**
 *
 * @author Mac
 * @create 2018-05-10 16:43
 **/
public interface ThreadPool {

    public void submit(Runnable task);

    public int getPoolThreadNum();


    public int getWaitingTaskNum();

    public void shutdown() throws InterruptedException;

    public interface RejectionPolicy {
        public void reject()  throws RejectException;
    }

    public static class RejectException extends RuntimeException{
        public RejectException (String message) {
            super(message);
        }
    }
}
