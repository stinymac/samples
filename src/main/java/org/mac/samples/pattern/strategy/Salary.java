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
 * salary entity
 *
 * @author Mac
 * @create 2018-05-06 17:05
 **/

public class Salary {

    private BigDecimal basicWage;
    private BigDecimal bonus;

    public Salary(BigDecimal basicWage, BigDecimal bonus) {
        this.basicWage = basicWage;
        this.bonus = bonus;
    }

    public BigDecimal getBasicWage() {
        return basicWage;
    }

    public BigDecimal getBonus() {
        return bonus;
    }
}
