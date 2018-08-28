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
