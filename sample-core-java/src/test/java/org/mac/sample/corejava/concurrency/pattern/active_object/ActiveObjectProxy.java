/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
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
