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
            ProductionLine.Product p = this.productionLine.stepOne();
            System.out.println("Step one->" + p);
        }
    }
}
