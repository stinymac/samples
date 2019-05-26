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

package org.mac.sample.corejava.version8.optional;

import java.util.Optional;

/**
 *
 * @auther mac
 * @date 2019-05-26
 */
public class OptionalInAction {

    public static void main(String[] args) {
        System.out.println(getInsuranceNameWithOptional(null));

        Optional.ofNullable(getInsuranceNameWithOptional(null))
                .ifPresent(System.out::println);
    }

    public static String getInsuranceNameWithOptional(Person person){
        /*Optional.ofNullable(person)
                .map(Person::getCar);*/ // Optional<Optional<Car>>
        return Optional.ofNullable(person)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }
}
