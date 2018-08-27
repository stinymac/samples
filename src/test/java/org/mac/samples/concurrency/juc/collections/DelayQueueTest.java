/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.juc.collections;

import java.util.Iterator;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Mac
 * @create 2018-06-18 18:43
 **/

public class DelayQueueTest {
    public static void main(String[] args) throws InterruptedException {
        //<E extends Delayed>
        DelayQueue<DelayedObject<String>> delayQueue = new DelayQueue();
        delayQueue.add(new DelayedObject<>("A",1000));

        /*long startTimeMillis = System.currentTimeMillis();
        System.out.println(delayQueue.peek());
        System.out.println(System.currentTimeMillis()-startTimeMillis);*///1

       /* long startTimeMillis = System.currentTimeMillis();
        System.out.println(delayQueue.take());//blocking
        System.out.println(System.currentTimeMillis()-startTimeMillis);*///1015

        /*delayQueue.add(new DelayedObject<>("B",100000));

        Iterator<DelayedObject<String>> it = delayQueue.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().getElement());// no delay
        }*/

       /* TimeUnit.SECONDS.sleep(2);
        System.out.println(delayQueue.peek().getElement());//A
        System.out.println(delayQueue.take().getElement());*///A

        delayQueue.add(new DelayedObject<>("B",2000));
        delayQueue.add(new DelayedObject<>("C",100));
        delayQueue.add(new DelayedObject<>("D",900));

        Iterator<DelayedObject<String>> it = delayQueue.iterator();
        while (it.hasNext()) {
            System.out.println(it.next().getElement());// C D A B
        }

    }

    static class DelayedObject<E> implements Delayed {

        private final E e;
        private final long expireTime;

        public DelayedObject(E e, long delay) {
            this.e = e;
            this.expireTime
                    = System.currentTimeMillis()+delay;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = expireTime - System.currentTimeMillis();
            return unit.convert(diff,TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            DelayedObject that = (DelayedObject) o;
            long thatExpireTime =  that.getExpireTime();
            if (this.expireTime < thatExpireTime) {
                return -1;
            } else if (this.expireTime > thatExpireTime) {
                return 1;
            } else {
                return 0;
            }
        }

        public E getElement() {
            return e;
        }

        public long getExpireTime() {
            return expireTime;
        }
    }
}
