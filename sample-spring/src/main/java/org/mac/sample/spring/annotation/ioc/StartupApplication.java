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

package org.mac.sample.spring.annotation.ioc;

import org.mac.sample.spring.annotation.ioc.config.ApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * main
 * @auther mac
 * @date 2018-10-06
 */
public class StartupApplication {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName:beanNames) {
            if("animalFactoryBean".equals(beanName)) {
                //获取工厂Bean本身
                System.err.println(beanName+"-->"+context.getBean("&"+beanName));
            }
            System.err.println(beanName+"-->"+context.getBean(beanName));
        }
    }
}
