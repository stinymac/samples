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

package org.mac.sample.corejava.concurrency.pattern.active_object;

/**
 *
 * @author Mac
 * @create 2018-06-08 15:17
 **/

public class Scheduler extends Thread{

    private ActivationQueue queue;

    public Scheduler(ActivationQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            queue.take().execute();
        }
    }
}
