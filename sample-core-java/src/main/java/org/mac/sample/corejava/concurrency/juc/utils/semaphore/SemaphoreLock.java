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

package org.mac.sample.corejava.concurrency.juc.utils.semaphore;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Mac
 * @create 2018-06-16 13:36
 **/

public class SemaphoreLock {
    private final Semaphore semaphore = new Semaphore(1);

    //private Thread lockHolder;

    public void lock() throws InterruptedException {
        semaphore.acquire();
    }

    public void unlock() {
        semaphore.release();
    }
}
