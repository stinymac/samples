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

package org.mac.sample.spring.annotation.extension.processor;

import org.mac.sample.spring.annotation.extension.bean.Dog;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Bean definition registry post processor
 *
 * BeanDefinitionRegistryPostProcessor是BeanFactoryPostProcessor的子接口
 *
 * 其在Context中所有的注册的Bean定义将要被加载时执行，所以它在BeanFactoryPostProcessor#postProcessBeanFactory
 * 之前执行
 *
 *
 * @see {@link org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory, List)}
 *
 *     //在{@link org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors(Collection, ConfigurableListableBeanFactory)}之前执行
 *     @see {@link org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanDefinitionRegistryPostProcessors(Collection, BeanDefinitionRegistry)}
 *
 * @auther mac
 * @date 2018-10-24
 */
@Component
public class SimpleBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor{

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("PostProcessBeanDefinitionRegistry()执行时容器中Bean的数量:"+registry.getBeanDefinitionCount());

        if (registry instanceof DefaultListableBeanFactory){
            DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) registry;
            defaultListableBeanFactory.registerAlias("cat","cat-1");
            defaultListableBeanFactory.canonicalName("");
        }
        System.err.println("-->");
        System.err.println(registry.getBeanDefinition("cat"));

        RootBeanDefinition rootBeanDefinition  = (RootBeanDefinition)registry.getBeanDefinition("cat");


        //Class<?> beanClass = rootBeanDefinition.getBeanClass();
        //System.err.println(rootBeanDefinition.getBeanClassName());
        BeanDefinitionHolder holder = rootBeanDefinition.getDecoratedDefinition();
        System.out.println("-->"+holder);





        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Dog.class).getBeanDefinition();
        registry.registerBeanDefinition("dog",beanDefinition);

        System.err.println(registry);
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor()执行时容器中Bean的数量:"+beanFactory.getBeanDefinitionCount());
        System.err.println(beanFactory.getBean("cat-1"));

        System.err.println(beanFactory.getBeanDefinition("cat"));
        //System.err.println(beanFactory.getBeanDefinition("cat-1"));
        RootBeanDefinition rootBeanDefinition  = (RootBeanDefinition)beanFactory.getBeanDefinition("cat");

        BeanDefinitionHolder holder = rootBeanDefinition.getDecoratedDefinition();
        System.out.println("-->"+holder);

        ConfigurationClassPostProcessor configurationClassPostProcessor = beanFactory.getBean(ConfigurationClassPostProcessor.class);

        if (configurationClassPostProcessor != null) {
            //configurationClassPostProcessor.setBeanNameGenerator(beanName);
        }

        System.err.println(beanFactory);
    }
}
