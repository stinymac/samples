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
