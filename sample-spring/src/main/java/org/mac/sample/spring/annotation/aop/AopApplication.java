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

package org.mac.sample.spring.annotation.aop;

import org.mac.sample.spring.annotation.aop.config.AopApplicationConfiguration;
import org.mac.sample.spring.annotation.aop.service.Calculator;
import org.mac.sample.spring.annotation.aop.service.SimpleService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * main
 *
 * @auther mac
 * @date 2018-10-10
 */
public class AopApplication {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AopApplicationConfiguration.class);

        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName:beanNames) {
            System.err.println(beanName+"------>"+context.getBean(beanName));
        }
        Calculator calculator = context.getBean(Calculator.class);
        calculator.add(100,119);
        //calculator.div(1,0);
        SimpleService simpleService = context.getBean(SimpleService.class);
        simpleService.doBusiness("1",new Integer[]{2,1,2});
    }
}
