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

package org.mac.sample.spring.annotation.extension.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Bean factory processor
 *
 *  *
 *
 * Spring的扩展
 *
 * Bean对象创建后，对象初始化前后
 * @see {@link BeanPostProcessor}
 *
 * BeanFactory标准初始化完成，所有的Bean定义被加载到BeanFactory，但是没有Bean实例被创建
 * @see {@link BeanFactoryPostProcessor}
 *
 * BeanFactoryPostProcessor 的执行
 *
 * @see {@link AbstractApplicationContext#refresh()}
 *
 * <pre>
 *    @Override
 *    public void refresh() throws BeansException, IllegalStateException {
 *    synchronized (this.startupShutdownMonitor) {
 *    // Prepare this context for refreshing.
 *    prepareRefresh();
 *
 *    // Tell the subclass to refresh the internal bean factory.
 *    ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
 *
 *    // Prepare the bean factory for use in this context.
 *    prepareBeanFactory(beanFactory);
 *
 *    try {
 *    // Allows post-processing of the bean factory in context subclasses.
 *    postProcessBeanFactory(beanFactory);
 *
 *    // Invoke factory processors registered as beans in the context.
 *    invokeBeanFactoryPostProcessors(beanFactory);
 *
 *    // Register bean processors that intercept bean creation.
 *    registerBeanPostProcessors(beanFactory);
 *
 *    // Initialize message source for this context.
 *    initMessageSource();
 *
 *    // Initialize event multicaster for this context.
 *    initApplicationEventMulticaster();
 *
 *    // Initialize other special beans in specific context subclasses.
 *    onRefresh();
 *
 *    // Check for listener beans and register them.
 *    registerListeners();
 *
 *    // Instantiate all remaining (non-lazy-init) singletons.
 *    finishBeanFactoryInitialization(beanFactory);
 *
 *    // Last step: publish corresponding event.
 *    finishRefresh();
 *    }
 *    catch (BeansException ex) {
 *    if (logger.isWarnEnabled()) {
 *    logger.warn("Exception encountered during context initialization - " +
 *              "cancelling refresh attempt: " + ex);
 *    }

 *    // Destroy already created singletons to avoid dangling resources.
 *    destroyBeans();

 *    // Reset 'active' flag.
 *    cancelRefresh(ex);
 *
 *    // Propagate exception to caller.
 *    throw ex;
 *    }
 *    finally {
 *        // Reset common introspection caches in Spring's core, since we
 *        // might not ever need metadata for singleton beans anymore...
 *        resetCommonCaches();
 *    }
 *    }
 *}
 * </pre>
 *
 * @see  {@link AbstractApplicationContext#invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory)}
 *
 *     // 查找 匹配 创建(getBean) BeanFactory中注册的BeanFactoryPostProcessors 并分类和排序 然后依次调用postProcessBeanFactory()方法
 *     @see {@link org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory, List)}
 *
 *         @see {@link org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors(Collection, ConfigurableListableBeanFactory)}
 *
 * @auther mac
 * @date 2018-10-22
 */
@Component
public class SimpleBeanFactoryProcessor implements BeanFactoryPostProcessor{

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("SimpleBeanFactoryProcessor.postProcessBeanFactory()...");

        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();

        System.out.println(Arrays.toString(beanDefinitionNames));
    }
}
