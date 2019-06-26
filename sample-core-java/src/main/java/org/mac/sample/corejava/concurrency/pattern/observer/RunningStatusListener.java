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
