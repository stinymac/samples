/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.samples.pattern.strategy;

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
