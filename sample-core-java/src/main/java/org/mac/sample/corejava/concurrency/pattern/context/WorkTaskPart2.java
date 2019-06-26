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

public class WorkTaskPart2 {
    public void doPart2() {
        Context ctx = Context.getContext();

        String  threadName = Thread.currentThread().getName();
        String name = (String) ctx.get("name");


        System.out.println("Thread named "+ threadName + " get name value:"+name);


        String id = "0000000000"+Thread.currentThread().getId();
        System.out.println("Thread named "+ threadName + " put id value:"+id);
        ctx.put("id",id);

    }
}
