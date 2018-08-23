/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
 */
package org.mac.examples.pattern.strategy;

import java.math.BigDecimal;

/**
 * Taxes calculative strategy interface
 *
 * @author Mac
 * @create 2018-05-06 16:56
 **/

public interface TaxesCalculativeStrategy {
    BigDecimal calculateTaxesOf(Salary salary);
}
