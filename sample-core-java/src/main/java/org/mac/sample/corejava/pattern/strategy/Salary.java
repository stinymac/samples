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
