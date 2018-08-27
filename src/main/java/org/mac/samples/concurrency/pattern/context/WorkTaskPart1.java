/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.pattern.context;

/**
 *
 * @author Mac
 * @create 2018-06-06 11:22
 **/

public class WorkTaskPart1 {
    public void doPart1() {
        Context ctx = Context.getContext();

        String  threadName = Thread.currentThread().getName();
        String value = threadName +" mac";

        System.out.println("Thread named "+ threadName + " put name value:"+value);

        ctx.put("name",value);
    }
}
