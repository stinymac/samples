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
public class OptionalUsages {

    public static void main(String[] args) {
        // 创建Optional
        Optional<Insurance> optional = Optional.empty();
        // optional.get();

        Optional<Insurance> insuranceOptional = Optional.of(new Insurance());
        insuranceOptional.get();

        Optional<Insurance> nullAbleOptional = Optional.ofNullable(null);
        nullAbleOptional.orElseGet(Insurance::new);
        nullAbleOptional.orElse(new Insurance());
        //nullAbleOptional.orElseThrow(RuntimeException::new);

        System.out.println(getInsuranceName(null));
        System.out.println(getInsuranceNameWithOptional(null));

    }

    public static String getInsuranceName(Insurance insurance) {
        if (null == insurance) {
            return "unknown";
        }

        return insurance.getName();
    }

    public static String getInsuranceNameWithOptional(Insurance insurance) {
        return Optional.ofNullable(insurance).map(Insurance::getName).orElse("unknown");
    }
}
