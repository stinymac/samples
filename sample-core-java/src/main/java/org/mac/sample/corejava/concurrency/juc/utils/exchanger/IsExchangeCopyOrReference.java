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

package org.mac.sample.corejava.concurrency.juc.utils.exchanger;

import java.util.concurrent.Exchanger;

/**
 *
 * @author Mac
 * @create 2018-06-16 13:11
 **/

public class IsExchangeCopyOrReference {

    public void test() {
        Exchanger<Object> exchanger =new Exchanger<>();

        new Thread(()->{
            try {
                Object a = new Object();
                System.out.println("Thread A send Object a:"+a);
                Object r = exchanger.exchange(a);
                System.out.println("Thread "+Thread.currentThread().getName()+" receive:" +r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();

        new Thread(()->{
            try {
                Object b = new Object();
                System.out.println("Thread B send Object b:"+b);
                Object r = exchanger.exchange(b);
                System.out.println("Thread "+Thread.currentThread().getName()+" receive:"+r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }
}
