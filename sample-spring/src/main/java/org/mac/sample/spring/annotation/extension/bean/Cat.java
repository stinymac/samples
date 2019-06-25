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

package org.mac.sample.spring.annotation.extension.bean;

/**
 * Bean
 *
 * @auther mac
 * @date 2018-10-22
 */
public class Cat {

    private int id;
    private String name;

    public Cat(){

    }

    public Cat(int id, String name) {
        System.out.println("Class Cat Constructor execute......");
        this.id = id;
        this.name = name;
    }

    public void init() {
        System.out.println("Class Cat init execute......");
    }

    public void destroy() {
        System.out.println("Class Cat destroy execute......");
    }
}
