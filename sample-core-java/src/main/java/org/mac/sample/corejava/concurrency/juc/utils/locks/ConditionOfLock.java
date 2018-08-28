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
package org.mac.sample.corejava.concurrency.juc.utils.locks;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Mac
 * @create 2018-06-16 22:54
 **/

public class ConditionOfLock {

    private final static ReentrantLock LOCk = new ReentrantLock();
    private final static Condition P_CONDITION = LOCk.newCondition();
    private final static Condition C_CONDITION = LOCk.newCondition();

    private final static LinkedList<Long> TIMESTAMP_POOL = new LinkedList<>();

    private static final int MAX_CAPACITY =100;

    public void produce () {
        try {
            LOCk.lock();
            while (TIMESTAMP_POOL.size() > MAX_CAPACITY) {
                P_CONDITION.await();
            }
            long v = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName()+" produce =>"+v);
            TIMESTAMP_POOL.addLast(v);
            C_CONDITION.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCk.unlock();
        }
    }

    public void consume () {
        try {
            LOCk.lock();
            while (TIMESTAMP_POOL.isEmpty()) {
                C_CONDITION.await();
            }
            long v = TIMESTAMP_POOL.getFirst();
            System.out.println(Thread.currentThread().getName()+" consume =>"+v);
            P_CONDITION.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCk.unlock();
        }
    }
}
