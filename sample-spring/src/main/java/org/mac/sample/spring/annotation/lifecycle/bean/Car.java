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

package org.mac.sample.spring.annotation.lifecycle.bean;

/**
 * A bean
 *
 * @auther mac
 * @date 2018-10-08
 */

public class Car {
    public Car() {
        System.out.println("->Car constructed");
    }

    public void init() {
        System.out.println("->Car initialize");
    }

    public void destroy() {
        System.out.println("->Car destroy");
    }
}
