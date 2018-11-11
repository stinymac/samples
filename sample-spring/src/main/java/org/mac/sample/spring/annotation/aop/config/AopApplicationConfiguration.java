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

package org.mac.sample.spring.annotation.aop.config;

import org.aopalliance.intercept.MethodInvocation;
import org.mac.sample.spring.annotation.aop.aspect.ConsoleLogAspect;
import org.mac.sample.spring.annotation.aop.aspect.LogAspect;
import org.mac.sample.spring.annotation.aop.service.Calculator;
import org.mac.sample.spring.annotation.aop.service.SimpleService;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.aspectj.annotation.BeanFactoryAspectJAdvisorsBuilder;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.DefaultAdvisorChainFactory;
import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.aop.framework.ProxyCreatorSupport;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.aop.framework.adapter.DefaultAdvisorAdapterRegistry;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.aop.interceptor.ExposeInvocationInterceptor;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.Method;
import java.util.List;

/**
 *
 * @auther mac
 * @date 2018-10-10
 *
 * @EnableAspectJAutoProxy 注解在容器启动时向容器注册名为internalAutoProxyCreator
 * 类型为AnnotationAwareAspectJAutoProxyCreator的 SmartInstantiationAwareBeanPostProcessor Bean实例
 *
 * @see {@link EnableAspectJAutoProxy,org.springframework.context.annotation.AspectJAutoProxyRegistrar}
 *
 * 通过注解@Import(AspectJAutoProxyRegistrar.class)向容器中注入Bean
 * @see {@link org.springframework.context.annotation.AspectJAutoProxyRegistrar#registerBeanDefinitions(AnnotationMetadata, BeanDefinitionRegistry)}
 *
 * AopConfigUtils.registerAspectJAnnotationAutoProxyCreatorIfNecessary(registry);
 * @see {@link org.springframework.aop.config.AopConfigUtils#registerOrEscalateApcAsRequired}
 * 向容器中注入AutoProxyCreatorBean @see {@link AnnotationAwareAspectJAutoProxyCreator}
 *
 * AnnotationAwareAspectJAutoProxyCreator 的层次结构
 *
 * AnnotationAwareAspectJAutoProxyCreator
 *   ->AspectJAwareAdvisorAutoProxyCreator
 *     ->AbstractAdvisorAutoProxyCreator
 *       ->AbstractAutoProxyCreator
 *         //ProxyProcessorSupport extends ProxyConfig implements Ordered, BeanClassLoaderAware, AopInfrastructureBean
 *         ->ProxyProcessorSupport implements SmartInstantiationAwareBeanPostProcessor, BeanFactoryAware
 * ----------------------------------------------------------------------------------------------------------------
 *
 * Spring AOP的实现流程
 *
 * 1.创建IoC容器
 * ApplicationContext context = new AnnotationConfigApplicationContext(AopApplicationConfiguration.class);
 *
 * public AnnotationConfigApplicationContext(Class<?>... annotatedClasses) {
 *  this();
 *  // BeanDefinition 解析注册
 *  register(annotatedClasses);
 *
 *  @see {@link AbstractApplicationContext#refresh()}
 *  refresh();
 * }
 *
 * 2.注册代理创建的BeanPostProcessors(由EnableAspectJAutoProxy启动)
 *
 * 创建容器的流程中，其中一个流程为
 *
 * @see {@link  AbstractApplicationContext#registerBeanPostProcessors(ConfigurableListableBeanFactory)}
 * // Register bean processors that intercept bean creation.
 * registerBeanPostProcessors(beanFactory);
 *
 * 即向容器中注册BeanPostProcessors
 * 以注解驱动启动Spring容器，默认会向容器中注册名为
 *
 * // priorityOrderedPostProcessors
 * org.springframework.context.annotation.internalAutowiredAnnotationProcessor //@see{@link RequiredAnnotationBeanPostProcessor}
 * // priorityOrderedPostProcessors
 * org.springframework.context.annotation.internalRequiredAnnotationProcessor  //@see{@link AutowiredAnnotationBeanPostProcessor}
 * // priorityOrderedPostProcessors
 * org.springframework.context.annotation.internalCommonAnnotationProcessor    //@see{@link CommonAnnotationBeanPostProcessor}
 * // orderedPostProcessors
 * org.springframework.aop.autoconfig.internalAutoProxyCreator(启用了AOP功能)       //@see{@link AnnotationAwareAspectJAutoProxyCreator}
 * // nonOrderedPostProcessors (普通的PostProcessors,这里为0)
 *
 * @see {@link org.springframework.context.support.PostProcessorRegistrationDelegate#registerBeanPostProcessors(ConfigurableListableBeanFactory, AbstractApplicationContext)}
 *
 *     BeanPostProcessor:AnnotationAwareAspectJAutoProxyCreator的创建
 *     实例化Bean对象
 *     @see {@link AbstractBeanFactory#doGetBean(String, Class, Object[], boolean)}
 *        @see {@link AbstractAutowireCapableBeanFactory#createBean(String, RootBeanDefinition, Object[])}
 *            @see {@link AbstractAutowireCapableBeanFactory#doCreateBean(String, RootBeanDefinition, Object[])}
 *                @see {@link AbstractAutowireCapableBeanFactory#createBeanInstance(String, RootBeanDefinition, Object[])}
 *                    @see {@link AbstractAutowireCapableBeanFactory#initializeBean(String, Object, RootBeanDefinition)}
 *
 *     AnnotationAwareAspectJAutoProxyCreator实例化后，执行populateBean
 *     @see {@link AbstractAutowireCapableBeanFactory#populateBean(String, RootBeanDefinition, BeanWrapper)}
 *     执行initializeBean
 *     @see {@link AbstractAutowireCapableBeanFactory#initializeBean(String, Object, RootBeanDefinition)}
 *         @see {@link AbstractAutowireCapableBeanFactory#invokeAwareMethods(String, Object)}
 *         @see {@link AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization(Object, String)}
 *             @see {@link AnnotationAwareAspectJAutoProxyCreator#postProcessBeforeInitialization(Object, String)}
 *         @see {@link AbstractAutowireCapableBeanFactory#invokeInitMethods(String, Object, RootBeanDefinition)}
 *         @see {@link AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization(Object, String)}
 *             @see {@link AnnotationAwareAspectJAutoProxyCreator#postProcessAfterInitialization(Object, String)}
 *
 * 完成后向BeanFactory注册一个AnnotationAwareAspectJAutoProxyCreator的Bean实例
 *
 * 3.容器中代理Bean对象创建
 *
 * 注册BeanPostprocessor后 下一个流程为代理Bean对象(all remaining (non-lazy-init) singletons) 的初始化和创建
 *
 * @see {@link AbstractApplicationContext#refresh()}
 * // Instantiate all remaining (non-lazy-init) singletons.
 * finishBeanFactoryInitialization(beanFactory);
 *
 * @see {@link AbstractApplicationContext#finishBeanFactoryInitialization(ConfigurableListableBeanFactory)}
 *     @see {@link DefaultListableBeanFactory#preInstantiateSingletons()}
 *         @see {@link AbstractBeanFactory#getBean(String)}
 *             @see {@link AbstractBeanFactory#doGetBean(String, Class, Object[], boolean)}
 *                 首先从缓存中取 ,之前注册到容器中的BeanPostProcessor(如:AnnotationAwareAspectJAutoProxyCreator等)已存在于缓存中
 *                 // Eagerly(眼巴巴) check singleton cache for manually registered singletons.
 *                 Object sharedInstance = getSingleton(beanName);
 *                 无缓存则创建Bean实例
 *                 @see {@link AbstractAutowireCapableBeanFactory#createBean(String, RootBeanDefinition, Object[])}
 *                     AnnotationAwareAspectJAutoProxyCreator 创建代理对象返回 (向容器中注入代理对象的扩展点？)
 *                     // Give BeanPostProcessors a chance to return a proxy instead of the target bean instance.
 *                     Object bean = resolveBeforeInstantiation(beanName, mbdToUse);
 *
 *                     @see {@link AbstractAutowireCapableBeanFactory#resolveBeforeInstantiation(String, RootBeanDefinition)}
 *
 *                         bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *                         if (bean != null) {
 *                             bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                         }
 *                         @see {@link AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization(Object, String)}
 *                         //取容器中全部的BeanPostProcessors
 *                         for (BeanPostProcessor bp : getBeanPostProcessors()) {
 *                              // AnnotationAwareAspectJAutoProxyCreator即是InstantiationAwareBeanPostProcessor类型
 *                              // 能在Bean对象实例化前后执行，因而有机会创建代理对象返回
 *                              if (bp instanceof InstantiationAwareBeanPostProcessor) {
 *                                  InstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;
 *                                  Object result = ibp.postProcessBeforeInstantiation(beanClass, beanName);
 *                                  if (result != null) {
 *                                      return result;
 *                                  }
 *                              }
 *                         }
 *
 *                         @see {@link AbstractAutoProxyCreator#postProcessBeforeInstantiation(Class, String)}
 *
 *                         if (!StringUtils.hasLength(beanName) || !this.targetSourcedBeans.contains(beanName)) {
 * 　　　　　　　　　　　　　　　　　if (this.advisedBeans.containsKey(cacheKey)) {
 * 　　　　　　　　　　　　　　　　　　　return null;
 * 　　　　　　　　　　　　　　　　　}
 * 　　　　　　　　　　　　　　　　　if (isInfrastructureClass(beanClass) || shouldSkip(beanClass, beanName)) {
 * 　　　　　　　　　　　　　　　　　　　　　this.advisedBeans.put(cacheKey, Boolean.FALSE);
 * 　　　　　　　　　　　　　　　　　　　　　return null;
 * 　　　　　　　　　　　　　　　　　}
 * 　　　　　　　　　　　　　}
 *                         1判断当前bean是否在advisedBeans中（保存了所有需要增强bean）
 * 		                   2判断当前bean是否是基础类型的Advice、Pointcut、Advisor、AopInfrastructureBean，或者是否是切面（@Aspect）
 * 		                   3是否需要跳过
 *
 * 		                   // Create proxy here if we have a custom TargetSource.
 *                         // Suppresses unnecessary default instantiation of the target bean:
 *                         // The TargetSource will handle target instances in a custom fashion.
 *                         TargetSource targetSource = getCustomTargetSource(beanClass, beanName);
 *                         if (targetSource != null) {
 *                            　　if (StringUtils.hasLength(beanName)) {
 *                            　　　　this.targetSourcedBeans.add(beanName);
 *                         　　　　}
 *                         　　　　Object[] specificInterceptors = getAdvicesAndAdvisorsForBean(beanClass, beanName, targetSource);
 *                         　　　　Object proxy = createProxy(beanClass, beanName, specificInterceptors, targetSource);
 *                         　　　　this.proxyTypes.put(cacheKey, proxy.getClass());
 *                         　　　　return proxy;
 *                         }
 *
 *                 若没有生成代理对象则创建对象即正常的Bean实例化流程
 *
 *                 @see {@link AbstractAutowireCapableBeanFactory#doCreateBean(String, RootBeanDefinition, Object[])}
 *                    @see {@link AbstractAutowireCapableBeanFactory#createBeanInstance(String, RootBeanDefinition, Object[])}
 *                    @see {@link AbstractAutowireCapableBeanFactory#populateBean(String, RootBeanDefinition, BeanWrapper)}
 *                    @see {@link AbstractAutowireCapableBeanFactory#initializeBean(String, Object, RootBeanDefinition)}
 *
 *                        @see {@link AbstractAutowireCapableBeanFactory#invokeAwareMethods(String, Object)}
 *                        //应用 BeanPostProcessors Before Initialization
 *                        @see {@link AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization(Object, String)}
 *                            @see {@link AbstractAutoProxyCreator#postProcessBeforeInitialization(Object, String)}
 *
 *                        //应用 BeanPostProcessors After Initialization 对目标对象进行代理,织入切面通知,生成代理对象
 *                        @see {@link AbstractAutowireCapableBeanFactory#invokeInitMethods(String, Object, RootBeanDefinition)}
 *                        @see {@link AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization(Object, String)}
 *                            @see {@link AbstractAutoProxyCreator#postProcessAfterInitialization(Object, String)}
 *                                @see {@link AbstractAutoProxyCreator#wrapIfNecessary(Object, String, Object)}
 *
 *                                // Create proxy if we have advice.
 *                                Object[] specificInterceptors = getAdvicesAndAdvisorsForBean(bean.getClass(), beanName, null);
 *
 *                                    @see {@link AbstractAdvisorAutoProxyCreator#getAdvicesAndAdvisorsForBean(Class, String, TargetSource)}
 *                                        @see {@link AbstractAdvisorAutoProxyCreator#findEligibleAdvisors(Class, String)}
 *                                            @see {@link AnnotationAwareAspectJAutoProxyCreator#findCandidateAdvisors()}
 *                                                @see {@link BeanFactoryAspectJAdvisorsBuilder#buildAspectJAdvisors()}
 *                                                // 将切面类构建为Advisor对象返回
 *                                                List<Advisor> advisors = new ArrayList<>();
 *                                                for (String aspectName : aspectNames) {
 *                                                    List<Advisor> cachedAdvisors = this.advisorsCache.get(aspectName);
 *                                                    if (cachedAdvisors != null) {
 *                                                        advisors.addAll(cachedAdvisors);
 *                                                     }
 *                                                     else {
 *                                                         MetadataAwareAspectInstanceFactory factory = this.aspectFactoryCache.get(aspectName);
 *                                                         advisors.addAll(this.advisorFactory.getAdvisors(factory));
 *                                                     }
 *                                                }
 *                                                return advisors;
 *                                            // 从返回的advisors中查找可以应用到当前对象的advisor
 *                                            @see {@link AbstractAdvisorAutoProxyCreator#findAdvisorsThatCanApply(List, Class, String)}
 *
 *                                            ProxyCreationContext.setCurrentProxiedBeanName(beanName);
 *                                            try {
 *                                                return AopUtils.findAdvisorsThatCanApply(candidateAdvisors, beanClass);
 *                                            }
 *                                            finally {
 *                                                ProxyCreationContext.setCurrentProxiedBeanName(null);
 *                                            }
 *                                                @see {@link AopUtils#canApply(Pointcut, Class, boolean)}
 *                                            //对查找到的合适的Advisors进行排序
 *                                            eligibleAdvisors = sortAdvisors(eligibleAdvisors);
 *
 *                                //创建代理对象
 *                                this.advisedBeans.put(cacheKey, Boolean.TRUE);
 *                                Object proxy = createProxy(
 *                                bean.getClass(), beanName, specificInterceptors, new SingletonTargetSource(bean));
 *                                this.proxyTypes.put(cacheKey, proxy.getClass());
 *                                return proxy;
 *
 *                                    @see {@link AbstractAutoProxyCreator#createProxy(Class, String, Object[], TargetSource)}
 *                                    //首先将目标对象本身的类信息保存
 *                                    //构建代理创建工厂并设置属性
 *                                    //从代理工厂中获取代理对象
 *                                    proxyFactory.getProxy(getProxyClassLoader());
*                                        @see {@link ProxyFactory#getProxy(ClassLoader)}
 *                                           @see {@link ProxyCreatorSupport#createAopProxy()}
 *                                               @see {@link DefaultAopProxyFactory#createAopProxy(AdvisedSupport)}
 *
 *                                               if (autoconfig.isOptimize() || autoconfig.isProxyTargetClass() || hasNoUserSuppliedProxyInterfaces(autoconfig)) {
 *                                                   Class<?> targetClass = autoconfig.getTargetClass();
 *                                                   if (targetClass == null) {
 *                                                       throw new AopConfigException("TargetSource cannot determine target class: " +
 *                                                                 "Either an interface or a target is required for proxy creation.");
 *                                                   }
 *                                                   if (targetClass.isInterface() || Proxy.isProxyClass(targetClass)) {
 *                                                       return new JdkDynamicAopProxy(autoconfig);
 *                                                   }
 *                                                   return new ObjenesisCglibAopProxy(autoconfig);
 *                                               }
 *                                               else {
 *                                                   return new JdkDynamicAopProxy(autoconfig);
 *                                               }
 *                 至此容器中的一个代理Bean对象创建完成
 *
 * 4.代理Bean对象创建后,代理对象实现AOP的调用过程
 *
 *    容器中的代理对象，在目标方法被调用时拦截调用
 *    @see {@link org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor#intercept(Object, Method, Object[], MethodProxy)}
 *
 *    // 获取目标方法的拦截器链
 *    List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 *        @see {@link AdvisedSupport#getInterceptorsAndDynamicInterceptionAdvice(Method, Class)}
 *            @see {@link DefaultAdvisorChainFactory#getInterceptorsAndDynamicInterceptionAdvice(Advised, Method, Class)}
 *            遍历全部的增强器Advisor,如果与目标方法匹配，将增强器中的通知，封装为MethodInterceptor数组返回
 *                @see {@link DefaultAdvisorAdapterRegistry#getInterceptors(Advisor)}
 *
 *    // 没有拦截链直接执行目标方法,有则执行链
 *    // We need to create a method invocation...
 *    retVal = new CglibMethodInvocation(proxy, target, method, args, targetClass, chain, methodProxy).proceed();
 *        @see {@link ReflectiveMethodInvocation#proceed()}
 *
 *        //We start with an index of -1 and increment early.
 *        // 若 interceptorsAndDynamicMethodMatchers.size() = 0  没有拦截器 直接执行目标方法
 *        // 或 拦截器执行完毕 执行目标方法
 *        if (this.currentInterceptorIndex == this.interceptorsAndDynamicMethodMatchers.size() - 1) {
 *            return invokeJoinpoint();
 *        }
 *        // 先自增 取下一个拦截器
 *        Object interceptorOrInterceptionAdvice = this.interceptorsAndDynamicMethodMatchers.get(++this.currentInterceptorIndex);
 *        if (interceptorOrInterceptionAdvice instanceof InterceptorAndDynamicMethodMatcher) {
 *            // Evaluate dynamic method matcher here: static part will already have
 *            // been evaluated and found to match.
 *            InterceptorAndDynamicMethodMatcher dm = (InterceptorAndDynamicMethodMatcher) interceptorOrInterceptionAdvice;
 *            if (dm.methodMatcher.matches(this.method, this.targetClass, this.arguments)) {
 *                return dm.interceptor.invoke(this);
 *            }
 *            else {
 *                // Dynamic matching failed.
 *                // Skip this interceptor and invoke the next in the chain.
 *                // 递归
 *                return proceed();
 *            }
 *        }
 *        else {
 *             // It's an interceptor, so we just invoke it: The pointcut will have
 *            // been evaluated statically before this object was constructed.
 *
 *            // 递归 @see {@link ExposeInvocationInterceptor#invoke(MethodInvocation)}
 *            return ((MethodInterceptor) interceptorOrInterceptionAdvice).invoke(this);
 *        }
 *
 *    拦截器链的触发过程
 * 		如果没有拦截器执行执行目标方法，或者拦截器的索引和拦截器数组-1大小一样（指定到了最后一个拦截器）执行目标方法；
 * 		链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成返回以后再来执行；
 * 	    拦截器链的机制，保证通知方法与目标方法的执行顺序；
 *
 */
@EnableAspectJAutoProxy
@Configuration
public class AopApplicationConfiguration {
    @Bean
    public Calculator calculator() {
        return new Calculator();
    }
    @Bean
    public ConsoleLogAspect consoleLogAspect(){
        return new ConsoleLogAspect();
    }
    @Bean
    public SimpleService simpleService() {
        return new SimpleService();
    }
    @Bean
    public LogAspect logAspect(){
        return new LogAspect();
    }
}
