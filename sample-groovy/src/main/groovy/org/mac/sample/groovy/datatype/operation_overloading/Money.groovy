package org.mac.sample.groovy.datatype.operation_overloading

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

class Money {

    private int amount
    private String currency

    Money (int amountValue,String currencyValue) {
        amount = amountValue
        currency = currencyValue
    }
    boolean equals (Object other) {
        if (null == other) return false
        if (! (other instanceof Money)) return false
        if (other.currency != currency) return false
        return amount == other.amount
    }
    int hashCode() {
        return amount.hashCode() + currency.hashCode()
    }

    Money plus (Money other) {
        if (null == other) return null
        if (other.currency != currency) {
            throw new IllegalArgumentException("Can't add $other.currency to $currency")
        }
        return new Money(other.amount + amount,currency)
    }
}


