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
package org.mac.sample.corejava.pattern.strategy;

import java.math.BigDecimal;

/**
 *
 * @author Mac
 * @create 2018-05-06 17:15
 **/

public class SimpleTaxesCalculativeStrategy implements TaxesCalculativeStrategy {

    private final BigDecimal BASIC_WAGE_TAX_RATE = new BigDecimal("0.1");//"0.1"使用字符串
    private final BigDecimal BONUS_TAX_RATE = new BigDecimal("0.2");

    public BigDecimal calculateTaxesOf(Salary salary) {

        BigDecimal basicWage = salary.getBasicWage();
        BigDecimal bonus = salary.getBonus();

        return basicWage.multiply(BASIC_WAGE_TAX_RATE).add(bonus.multiply(BONUS_TAX_RATE));
    }
}
