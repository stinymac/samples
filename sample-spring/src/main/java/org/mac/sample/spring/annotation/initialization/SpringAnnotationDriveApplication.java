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

package org.mac.sample.spring.annotation.initialization;

import org.mac.sample.spring.annotation.initialization.config.ApplicationConfiguration;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.ObjectUtils;

/**
 * Main
 *
 * Spring的初始化流程
 *
 * <pre>
 *     public AnnotationConfigApplicationContext(Class<?>... annotatedClasses) {
 *         this();
 *         register(annotatedClasses);
 *         refresh();
 *     }
 * </pre>
 *
 * @see AbstractApplicationContext#refresh()
 *
 * 1.准备
 * // Prepare this context for refreshing.
 * prepareRefresh();
 *
 * @see AbstractApplicationContext#prepareRefresh()
 *
 * // Initialize any placeholder(占位符) property sources in the context environment
 * initPropertySources();// 默认为空 留给子类实现
 *
 * // Validate that all properties marked as required are resolvable
 * @see org.springframework.core.env.ConfigurablePropertyResolver#setRequiredProperties
 * getEnvironment().validateRequiredProperties();

 * // Allow for the collection of early ApplicationEvents,
 * // to be published once the multicaster is available...
 * this.earlyApplicationEvents = new LinkedHashSet<>();
 *
 * 2.获取BeanFactory
 *
 * // Tell the subclass to refresh the internal bean factory.
 * ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
 *
 * @see AbstractApplicationContext#obtainFreshBeanFactory()
 *     @see GenericApplicationContext#refreshBeanFactory();
 *
 *     //为容器设置ID
 *
 *     this.beanFactory.setSerializationId(getId());
 *         @see ObjectUtils#identityToString(Object)
 *         // beanFactory 在new AbstractApplicationContext时调用父类GenericApplicationContext的
 *         // 初始化方法构建
 *         //   public GenericApplicationContext() {
 *         //     this.beanFactory = new DefaultListableBeanFactory();
 *         //   }
 *
 *     // 返回BanFactory 即默认创建的DefaultListableBeanFactory
 *     getBeanFactory()
 *
 * 3.BeanFactory的准备工作(对BeanFactory进行一些设置)
 * // Prepare the bean factory for use in this context.
 * prepareBeanFactory(beanFactory);
 *
 * @see AbstractApplicationContext#prepareBeanFactory(ConfigurableListableBeanFactory)
 *     1) 设置类加载器、Bean表达式解析器、Property编辑器注册器
 *     2) 设置BeanPostProcessor [ApplicationContextAwareProcessor]
 *     3) 设置依赖注入忽略的接口类型[EnvironmentAware、ResourceLoaderAware...]
 *     4) 注册可解析的自动注入接口类型 ，即可以在任意组件中注入[BeanFactory、ResourceLoader、ApplicationEventPublisher、ApplicationContext]
 *     5) 设置BeanPostProcessor [ApplicationListenerDetector]
 *     6) 添加装载时织入处理器(对AspectJ的支持)
 *     7) 注册默认的环境对象[environment、systemProperties、systemEnvironment]
 *
 * 4.BeanFactory 准备完成后的后置处理工作
 * // Allows post-processing of the bean factory in context subclasses.
 * postProcessBeanFactory(beanFactory); //默认为空 子类实现
 *
 * 5.调用BeanFactoryPostProcessors (BeanFactoryPostProcessors
 *   在BeanFactory标准初始化(即之前4步 所有的Bean定义被加载到BeanFactory但是没有Bean实例被创建)后执行)
 * // Invoke factory processors registered as beans in the context.
 * invokeBeanFactoryPostProcessors(beanFactory);
 *
 *
 * @auther mac
 * @date 2018-10-31
 */

public class SpringAnnotationDriveApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        context.close();
    }
}
