 /*
  *       ___        |
  *      /_\ `*      |.===.         ,,,,,
  *     (o o)        {}o o{}       /(o o)\
  * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
  *
  * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
  */
package org.mac.samples.concurrency.juc.utils.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Mac
 * @create 2018-06-16 12:48
 **/

public class ExchengerTest {
    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(()->{
            try {
                String result = exchanger.exchange("from thread A");
                System.out.println(Thread.currentThread().getName()+" get return:" +result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
                String result = exchanger.exchange("from thread B");
                System.out.println(Thread.currentThread().getName()+" get return:"+result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();

        new Thread(()->{
            try {
                String result = exchanger.exchange("from thread C");
                System.out.println(Thread.currentThread().getName()+" get return:"+result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
    }
}
