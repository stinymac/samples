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

package org.mac.sample.corejava.concurrency.pattern.observer;

/**
 *
 * @author Mac
 * @create 2018-05-23 16:33
 **/

public abstract class ObservableRunnable implements Runnable{
    protected final LifeCycleListener listener;

    protected  ObservableRunnable(final LifeCycleListener listener) {
        this.listener = listener;
    }

    protected synchronized void notifyListener(LifeCycleEvent event){
        listener.onEvent(event);
    }
}
