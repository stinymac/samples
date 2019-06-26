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

package org.mac.sample.corejava.pattern.strategy;

import java.math.BigDecimal;

/**
 *
 * @author Mac
 * @create 2018-05-06 17:10
 **/

public class TaxesCalculator {

    private final Salary salary;
    private final TaxesCalculativeStrategy strategy;

    public TaxesCalculator(Salary salary, TaxesCalculativeStrategy strategy) {
        this.salary = salary;
        this.strategy = strategy;
    }

    public BigDecimal calc(){
        return strategy.calculateTaxesOf(salary);
    }
}
