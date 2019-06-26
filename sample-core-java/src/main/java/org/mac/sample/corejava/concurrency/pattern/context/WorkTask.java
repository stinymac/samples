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
 * @create 2018-06-06 11:21
 **/

public class WorkTask implements Runnable{
    @Override
    public void run() {
        WorkTaskPart1 part1 = new WorkTaskPart1();
        WorkTaskPart2 part2 = new WorkTaskPart2();

        part1.doPart1();
        part2.doPart2();

        Context context = Context.getContext();

        System.out.println(context.get("id") + "->" + context.get("name"));
    }
}
