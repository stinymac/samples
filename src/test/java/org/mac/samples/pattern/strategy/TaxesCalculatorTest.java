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
 * @create 2018-05-06 17:25
 **/

public class TaxesCalculatorTest {
    public static void main(String[] args) {
        Salary salary = new Salary(new BigDecimal("20000"),new BigDecimal("8000"));
        TaxesCalculativeStrategy strategy = new SimpleTaxesCalculativeStrategy();
        System.out.println(new TaxesCalculator(salary,strategy).calc());
    }
}
