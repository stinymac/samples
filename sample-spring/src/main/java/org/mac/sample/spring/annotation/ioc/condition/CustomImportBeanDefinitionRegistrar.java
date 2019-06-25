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

package org.mac.sample.spring.annotation.ioc.condition;

import org.mac.sample.spring.annotation.ioc.bean.Breeder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * A custom import bean definition registrar
 *
 * @auther mac
 * @date 2018-10-07
 */

public class CustomImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{
    /**
     * importingClassMetadata：当前类的注解信息
     * BeanDefinitionRegistry:BeanDefinition注册类；
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean cat = registry.containsBeanDefinition("org.mac.sample.spring.annotation.ioc.bean.Cat");
        boolean dog = registry.containsBeanDefinition("org.mac.sample.spring.annotation.ioc.bean.Dog");
        if(cat && dog){
            //指定Bean定义信息；（Bean的类型，Bean。。。）
            RootBeanDefinition breederBeanDefinition = new RootBeanDefinition(Breeder.class);
            //注册一个Bean，指定bean名
            registry.registerBeanDefinition("breeder", breederBeanDefinition);
        }

    }
}
