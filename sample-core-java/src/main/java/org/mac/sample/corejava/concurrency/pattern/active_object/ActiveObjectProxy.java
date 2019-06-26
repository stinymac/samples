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
 * @create 2018-06-08 15:20
 **/

public class ActiveObjectProxy implements ActiveObject{

    private final Servant servant;
    private final ActivationQueue queue;
    private final Scheduler scheduler;

    public ActiveObjectProxy(Servant servant,  ActivationQueue queue) {
        this.servant = servant;
        this.queue = queue;

        this.scheduler = new Scheduler(queue);
        this.scheduler.start();
    }

    @Override
    public Result<String> buildString(char fillChar, int count) {
        FutureResult futureResult = new FutureResult();
        BuildStringMethod buildStringMethod = new BuildStringMethod(servant,futureResult,fillChar,count);
        queue.put(buildStringMethod);
        return futureResult;
    }

    @Override
    public void displayString(String text) {
        queue.put(new DisplayStringMethod(servant, text));
    }
}
