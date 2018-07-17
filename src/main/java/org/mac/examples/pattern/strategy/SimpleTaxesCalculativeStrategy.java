/*
 * Copyright 2018-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mac.examples.pattern.strategy;

import java.math.BigDecimal;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-05-06 17:15
 **/

public class SimpleTaxesCalculativeStrategy implements TaxesCalculativeStrategy{

    private final BigDecimal BASIC_WAGE_TAX_RATE = new BigDecimal("0.1");//"0.1"使用字符串
    private final BigDecimal BONUS_TAX_RATE = new BigDecimal("0.2");

    public BigDecimal calculateTaxesOf(Salary salary) {

        BigDecimal basicWage = salary.getBasicWage();
        BigDecimal bonus = salary.getBonus();

        return basicWage.multiply(BASIC_WAGE_TAX_RATE).add(bonus.multiply(BONUS_TAX_RATE));
    }
}
