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

package org.mac.sample.corejava.concurrency.juc.atomic;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author Mac
 * @create 2018-06-11 22:11
 **/

public class AtomicFieldUpdater {

    private volatile Object object;

    AtomicReferenceFieldUpdater<AtomicFieldUpdater,Object> updater = AtomicReferenceFieldUpdater.newUpdater(AtomicFieldUpdater.class,Object.class,"object");

    public void update (Object newValue) {
        updater.compareAndSet(this,object,newValue);
    }

    public Object get() {
        return updater.get(this);
    }
}
