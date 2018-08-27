/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.concurrency.pattern.worker;

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
