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
