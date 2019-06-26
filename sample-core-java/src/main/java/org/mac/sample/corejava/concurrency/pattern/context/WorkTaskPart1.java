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

package org.mac.sample.corejava.concurrency.pattern.context;

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
