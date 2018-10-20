/*
 *      (             |"|           !!!       #   ___                             o
 *      _)_          _|_|_       `  _ _  '    #  <_*_>             ,,,         ` /_\ '       __MMM__
 *     (o o)         (o o)      -  (OXO)  -   #  (o o)            (o o)       - (o o) -       (o o)
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo--8---(_)--Ooo----ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 *
 *
 *                    !!!         |                             |"|            _             o          _     _
 *    __MMM__      `  _ _  '      |.===.         ,,,,,         _|_|_         _|_|_        ` /_\ '     o' \,=./ `o
 *     (o o)      -  (OXO)  -     {}o o{}       /(o o)\        (o o)         (o o)       - (o o) -       (o o)
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 *
 * 虽不能至,心向往之。(Although it is not possible, my heart is longing for it.)
 *
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 */

package org.mac.sample.spring.annotation.lifecycle.config;

import org.mac.sample.spring.annotation.lifecycle.bean.Car;
import org.mac.sample.spring.annotation.lifecycle.bean.Cat;
import org.mac.sample.spring.annotation.lifecycle.bean.CustomBeanPostProcessor;
import org.mac.sample.spring.annotation.lifecycle.bean.Dog;
import org.mac.sample.spring.annotation.lifecycle.bean.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration
 *
 * @auther mac
 * @date 2018-10-08
 */
@Configuration
@PropertySource(value = {"classpath:/cat.properties"})
@Import(CustomBeanPostProcessor.class)
public class LifeCycleApplicationConfiguration {
    //单实例：容器关闭的时候销毁
    //多实例：容器不会管理这个bean；容器不会调用销毁方法
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Car car(){
        return new Car();
    }

    @Bean
    public House house(){
        return new House();
    }

    @Bean
    public House smallHouse(@Autowired Dog dog){
        return new House(dog);
    }


    @Primary
    @Bean
    public Dog dog(){
        return new Dog("spike",18);
    }
    @Bean
    public Dog goofyDog(){
        return new Dog("bolt",18);
    }

    @Bean
    public Cat cat(){
        return new Cat();
    }
}
