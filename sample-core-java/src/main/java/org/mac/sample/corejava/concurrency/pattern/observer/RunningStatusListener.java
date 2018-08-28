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
package org.mac.sample.corejava.concurrency.pattern.observer;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-23 16:45
 **/

public class RunningStatusListener implements LifeCycleListener{
    @Override
    public void onEvent(LifeCycleEvent event) {
        System.out.println(event.getThread().getName()+ " running status:"+event.getStatus());
        if (event.getCause() != null) {
            System.out.println(event.getThread().getName()+ " error cause "+event.getCause());
        }
    }
}
