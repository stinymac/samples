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

package org.mac.sample.spring.annotation.ioc.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * Factory bean
 *
 * @auther mac
 * @date 2018-10-07
 */

public class AnimalFactoryBean<T extends Animal> implements FactoryBean{
    @Override
    public Animal getObject() throws Exception {
        return new Cat();
    }

    @Override
    public Class<Animal> getObjectType() {
        return Animal.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
