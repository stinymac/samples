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
package org.mac.sample.corejava.concurrency.pattern.worker;

/**
 *
 * @author Mac
 * @create 2018-06-07 17:43
 **/

public class OneWorker extends Thread{
    private ProductionLine productionLine;

    public OneWorker(String name,ProductionLine productionLine) {
        super("name");
        this.productionLine = productionLine;
    }

    @Override
    public void run() {
        while(true) {
            ProductionLine.Product p = this.productionLine.StepOne();
            System.out.println("Step one->" + p);
        }
    }
}
