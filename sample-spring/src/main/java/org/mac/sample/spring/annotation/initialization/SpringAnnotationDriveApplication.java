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

package org.mac.sample.spring.annotation.initialization;

import org.mac.sample.spring.annotation.initialization.config.ApplicationConfiguration;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.DefaultLifecycleProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * Main
 *
 * Spring Application Context的初始化流程(注解驱动)
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
 * @see org.springframework.context.support.PostProcessorRegistrationDelegate#invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory, List)
 *
 * 1) // Invoke BeanDefinitionRegistryPostProcessors first, if any. 首先调用BeanDefinitionRegistryPostProcessors
 * <pre>
 *     // 通常 此时容器中Bean还未初始化 beanFactoryPostProcessors.size == 0
 *     for (BeanFactoryPostProcessor postProcessor : beanFactoryPostProcessors) {
 * 				if (postProcessor instanceof BeanDefinitionRegistryPostProcessor) {
 * 					BeanDefinitionRegistryPostProcessor registryProcessor =
 * 							(BeanDefinitionRegistryPostProcessor) postProcessor;
 *
 * 					//
 * 					registryProcessor.postProcessBeanDefinitionRegistry(registry);
 * 					registryProcessors.add(registryProcessor);
 * 				}
 * 				else {
 * 					regularPostProcessors.add(postProcessor);
 * 				}
 * 	  }
 * </pre>
 *
 * 2) 调用BeanDefinitionRegistryPostProcessors
 *
 * First, invoke the BeanDefinitionRegistryPostProcessors that implement PriorityOrdered.
 *
 * // 首先从容器中取 BeanDefinitionRegistryPostProcessor 定义
 * String[] postProcessorNames =
 * 					beanFactory.getBeanNamesForType(BeanDefinitionRegistryPostProcessor.class, true, false);
 * <pre>
 *     for (String ppName : postProcessorNames) {
 * 			if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
 * 		        //	创建实例
 * 				currentRegistryProcessors.add(beanFactory.getBean(ppName, BeanDefinitionRegistryPostProcessor.class));
 * 				processedBeans.add(ppName);
 * 			}
 * 	   }
 * </pre>
 *
 * 对implement PriorityOrdered的BeanDefinitionRegistryPostProcessors排序后调用postProcessBeanDefinitionRegistry()
 *
 * 对implement Ordered的BeanDefinitionRegistryPostProcessors做同样的操作 (筛选、创建实例、排序、调用postProcessBeanDefinitionRegistry())
 *
 * 剩下的普通的BeanDefinitionRegistryPostProcessors做同上的操作
 *
 * 3) 调用BeanFactoryPostProcessors(逻辑同调用BeanDefinitionRegistryPostProcessors)
 *
 * 4) 清空Bean Metadata缓存
 * // Clear cached merged bean definitions since the post-processors might have
 * // modified the original metadata, e.g. replacing placeholders in values...
 * beanFactory.clearMetadataCache();
 *
 * 6.注册Bean后置处理器
 *
 * @see BeanPostProcessor
 * @see DestructionAwareBeanPostProcessor
 * @see InstantiationAwareBeanPostProcessor
 * @see org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor
 * @see org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor
 *
 * // Register bean processors that intercept bean creation.
 * registerBeanPostProcessors(beanFactory);
 *
 * @see org.springframework.context.support.PostProcessorRegistrationDelegate#registerBeanPostProcessors(ConfigurableListableBeanFactory, AbstractApplicationContext)
 *
 * 类似BeanFactoryPostProcessors的处理逻辑
 *
 * 最后注册  detecting inner beans as ApplicationListeners 的Bean后置处理器
 * // Re-register post-processor for detecting inner beans as ApplicationListeners,
 * // moving it to the end of the processor chain (for picking up proxies(获取代理) etc).
 * beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(applicationContext));
 *
 * 7.初始化message资源
 *
 * // Initialize message source for this context.
 * initMessageSource();
 *
 * @see AbstractApplicationContext#initMessageSource()
 *
 * 容器中注册有messageBean 创建实例 初始化
 *
 * 没有 // Use empty MessageSource to be able to accept getMessage calls.
 * DelegatingMessageSource dms = new DelegatingMessageSource();
 *
 * 8.初始化事件广播器
 *
 * // Initialize event multicaster for this context.
 * initApplicationEventMulticaster();
 *
 * @see AbstractApplicationContext#initApplicationEventMulticaster()
 *
 * Uses SimpleApplicationEventMulticaster if none defined in the context.
 *
 * 9.初始化子类的Bean
 *
 * // Initialize other special beans in specific context subclasses.
 * onRefresh();
 *
 * @see AbstractApplicationContext#onRefresh()
 *
 * 留给子类实现，这儿什么都不做
 *
 * 10.注册监听器
 * // Check for listener beans and register them.
 * registerListeners();
 *
 * @see AbstractApplicationContext#registerListeners()
 *
 * 1) 首先将statically specified listeners注册到事件广播器
 * // Register statically specified listeners first.
 * for (ApplicationListener<?> listener : getApplicationListeners()) {
 * 	 getApplicationEventMulticaster().addApplicationListener(listener);
 * }
 * 2) 取容器中的监听器注册到事件广播器(Do not initialize FactoryBeans)
 *
 * 3) 发布早期事件
 * // Publish early application events now that we finally have a multicaster...
 * Set<ApplicationEvent> earlyEventsToProcess = this.earlyApplicationEvents;
 * 	  this.earlyApplicationEvents = null;
 * 	  if (earlyEventsToProcess != null) {
 * 		for (ApplicationEvent earlyEvent : earlyEventsToProcess) {
 * 			getApplicationEventMulticaster().multicastEvent(earlyEvent);
 * 		}
 * }
 *
 * 11.初始化所有非懒加载的单例bean
 *
 * // Instantiate all remaining (non-lazy-init) singletons.
 * finishBeanFactoryInitialization(beanFactory);
 *
 * @see AbstractApplicationContext#finishBeanFactoryInitialization(ConfigurableListableBeanFactory)
 *
 * 1) 初始化类型转化Bean
 *
 * 2) 初始化内置的解析相关的Bean
 * <pre>
 *  // Register a default embedded value resolver if no bean post-processor
 * 	// (such as a PropertyPlaceholderConfigurer bean) registered any before:
 * 	// at this point, primarily for resolution in annotation attribute values.
 * 	if (!beanFactory.hasEmbeddedValueResolver()) {
 * 	    beanFactory.addEmbeddedValueResolver(strVal -> getEnvironment().resolvePlaceholders(strVal));
 * 	}
 * </pre>
 *
 * 3) 初始化 LoadTimeWeaverAware beans
 *
 * 4) 废弃 temporary ClassLoader
 * // Stop using the temporary ClassLoader for type matching.
 * beanFactory.setTempClassLoader(null);
 *
 * 5) 冻结配置
 * // Allow for caching all bean definition metadata, not expecting further changes.
 * beanFactory.freezeConfiguration();
 *
 * 6) 初始化剩余的单例Bean
 * // Instantiate all remaining (non-lazy-init) singletons.
 * beanFactory.preInstantiateSingletons();
 *
 * @see DefaultListableBeanFactory#preInstantiateSingletons()
 *
 * <pre>
 *     List<String> beanNames = new ArrayList<>(this.beanDefinitionNames);
 *
 * 		// Trigger initialization of all non-lazy singleton beans...
 * 		for (String beanName : beanNames) {
 * 			RootBeanDefinition bd = getMergedLocalBeanDefinition(beanName);
 * 			if (!bd.isAbstract() && bd.isSingleton() && !bd.isLazyInit()) {
 * 				if (isFactoryBean(beanName)) {
 * 					Object bean = getBean(FACTORY_BEAN_PREFIX + beanName);
 * 					if (bean instanceof FactoryBean) {
 * 						final FactoryBean<?> factory = (FactoryBean<?>) bean;
 * 						boolean isEagerInit;
 * 						if (System.getSecurityManager() != null && factory instanceof SmartFactoryBean) {
 * 							isEagerInit = AccessController.doPrivileged((PrivilegedAction<Boolean>)
 * 											((SmartFactoryBean<?>) factory)::isEagerInit,
 * 									getAccessControlContext());
 * 						}
 * 						else {
 * 							isEagerInit = (factory instanceof SmartFactoryBean &&
 * 									((SmartFactoryBean<?>) factory).isEagerInit());
 * 						}
 * 						if (isEagerInit) {
 * 							getBean(beanName);
 * 						}
 * 					}
 * 				}
 * 				else {
 * 					getBean(beanName);
 * 				}
 * 			}
 * 		}
 *
 * 		// Trigger post-initialization callback for all applicable beans...
 * 		for (String beanName : beanNames) {
 * 			Object singletonInstance = getSingleton(beanName);
 * 			if (singletonInstance instanceof SmartInitializingSingleton) {
 * 				final SmartInitializingSingleton smartSingleton = (SmartInitializingSingleton) singletonInstance;
 * 				if (System.getSecurityManager() != null) {
 * 					AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
 * 						smartSingleton.afterSingletonsInstantiated();
 * 						return null;
 * 					}, getAccessControlContext());
 * 				}
 * 				else {
 * 					smartSingleton.afterSingletonsInstantiated();
 * 				}
 * 			}
 * 		}
 * </pre>
 * ------------------------------------------------------------------------------------------------
 * getBean() 过程
 *
 * @see AbstractBeanFactory#doGetBean(String, Class, Object[], boolean)
 *
 * // 取缓存中的单例Bean (之前的过程若已创建了Bean则会保存到缓存)
 * // Eagerly check singleton cache for manually registered singletons.
 * Object sharedInstance = getSingleton(beanName);
 *
 * @see DefaultSingletonBeanRegistry#getSingleton(String, boolean)
 *
 * 缓存中没有取到 创建Bean
 *
 * 首先标记Bean未已创建
 * markBeanAsCreated(beanName);
 *
 * <pre>
 *     protected void markBeanAsCreated(String beanName) {
 * 		if (!this.alreadyCreated.contains(beanName)) {
 * 			synchronized (this.mergedBeanDefinitions) { // DCL
 * 				if (!this.alreadyCreated.contains(beanName)) {
 * 					// Let the bean definition get re-merged now that we're actually creating
 * 					// the bean... just in case some of its metadata changed in the meantime.
 * 					clearMergedBeanDefinition(beanName);
 * 					this.alreadyCreated.add(beanName);
 * 				}
 * 			}
 * 		}
 * 	  }
 * </pre>
 *
 * 创建Bean的依赖Bean
 *
 * <pre>
 *     final RootBeanDefinition mbd = getMergedLocalBeanDefinition(beanName);
 * 				checkMergedBeanDefinition(mbd, beanName, args);
 *
 * 			// Guarantee initialization of beans that the current bean depends on.
 * 			String[] dependsOn = mbd.getDependsOn();
 * 			if (dependsOn != null) {
 * 				for (String dep : dependsOn) {
 * 					if (isDependent(beanName, dep)) {//循环依赖
 * 							throw new BeanCreationException(mbd.getResourceDescription(), beanName,
 * 									"Circular depends-on relationship between '" + beanName + "' and '" + dep + "'");
 * 					}
 * 					registerDependentBean(dep, beanName);
 * 					try {
 * 							getBean(dep);
 * 					}
 * 					catch (NoSuchBeanDefinitionException ex) {
 * 							throw new BeanCreationException(mbd.getResourceDescription(), beanName,
 * 									"'" + beanName + "' depends on missing bean '" + dep + "'", ex);
 * 					}
 * 			}
 * 	   }
 * </pre>
 *
 * 创建Bean实例
 *
 * @see AbstractAutowireCapableBeanFactory#createBean(String, RootBeanDefinition, Object[])
 *
 * // Give BeanPostProcessors a chance to return a proxy instead of the target bean instance.//代理
 * Object bean = resolveBeforeInstantiation(beanName, mbdToUse);
 *
 * $即如果有InstantiationAwareBeanPostProcessor类型的BeanPostProcessors(例如 @see {@link org.springframework.context.annotation.ConfigurationClassPostProcessor.ImportAwareBeanPostProcessor)} 则执行处理器
 * @see AbstractAutowireCapableBeanFactory#resolveBeforeInstantiation(String, RootBeanDefinition)
 *
 * 没有返回代理对象 doCreateBean
 * @see AbstractAutowireCapableBeanFactory#doCreateBean(String, RootBeanDefinition, Object[])
 *     @see AbstractAutowireCapableBeanFactory#createBeanInstance(String, RootBeanDefinition, Object[])
 *
 *     使用工厂方法创建Bean实例
 *     if (mbd.getFactoryMethodName() != null) {
 * 			return instantiateUsingFactoryMethod(beanName, mbd, args);
 * 	   }
 * 	   否则 使用自动注入构造器创建Bean
 *
 * 	   否则使用无参构造器创建Bean
 *     @see AbstractAutowireCapableBeanFactory#instantiateBean(String, RootBeanDefinition)
 *     <pre>
 *         Object beanInstance;
 * 			final BeanFactory parent = this;
 * 			if (System.getSecurityManager() != null) {
 * 				beanInstance = AccessController.doPrivileged((PrivilegedAction<Object>) () ->
 * 						getInstantiationStrategy().instantiate(mbd, beanName, parent),
 * 						getAccessControlContext());
 * 			}
 * 			else {
 * 				beanInstance = getInstantiationStrategy().instantiate(mbd, beanName, parent);
 * 			}
 * 			BeanWrapper bw = new BeanWrapperImpl(beanInstance);
 * 			initBeanWrapper(bw);
 * 			return bw;
 *     </pre>
 *
 *     $执行MergedBeanDefinitionPostProcessors
 *     <pre>
 *      // Allow post-processors to modify the merged bean definition.
 * 		synchronized (mbd.postProcessingLock) {
 * 			if (!mbd.postProcessed) {
 * 				try {
 * 					applyMergedBeanDefinitionPostProcessors(mbd, beanType, beanName);
 * 				}
 * 				catch (Throwable ex) {
 * 					throw new BeanCreationException(mbd.getResourceDescription(), beanName,
 * 							"Post-processing of merged bean definition failed", ex);
 * 				}
 * 				mbd.postProcessed = true;
 * 			}
 * 		}
 *     </pre>
 *
 *    急切地缓存单例以便能够解析循环引用即使由BeanFactoryAware等生命周期接口触发。
 *
 *    初始化创建的Bean实例
 *    <pre>
 *       Object exposedObject = bean;
 * 		try {
 * 			populateBean(beanName, mbd, instanceWrapper);
 * 			exposedObject = initializeBean(beanName, exposedObject, mbd);
 * 		}
 *    </pre>
 *    为Bean的属性赋值
 *    @see AbstractAutowireCapableBeanFactory#populateBean(String, RootBeanDefinition, BeanWrapper)
 *
 *    赋值前 执行 InstantiationAwareBeanPostProcessor的postProcessAfterInstantiation判断是否继续填充属性值 若不需要就直接返回
 *
 *    做自动注入
 *    <pre>
 *        if (mbd.getResolvedAutowireMode() == AUTOWIRE_BY_NAME || mbd.getResolvedAutowireMode() == AUTOWIRE_BY_TYPE) {
 * 			MutablePropertyValues newPvs = new MutablePropertyValues(pvs);
 * 			// Add property values based on autowire by name if applicable.
 * 			if (mbd.getResolvedAutowireMode() == AUTOWIRE_BY_NAME) {
 * 				autowireByName(beanName, mbd, bw, newPvs);
 * 			}
 * 			// Add property values based on autowire by type if applicable.
 * 			if (mbd.getResolvedAutowireMode() == AUTOWIRE_BY_TYPE) {
 * 				autowireByType(beanName, mbd, bw, newPvs);
 * 			}
 * 			pvs = newPvs;
 * 		  }
 *    </pre>
 *
 *    $执行后置处理器(InstantiationAwareBeanPostProcessor)和依赖检查
 *    <pre>
 *      boolean hasInstAwareBpps = hasInstantiationAwareBeanPostProcessors();
 * 		boolean needsDepCheck = (mbd.getDependencyCheck() != AbstractBeanDefinition.DEPENDENCY_CHECK_NONE);
 *
 * 		PropertyDescriptor[] filteredPds = null;
 * 		if (hasInstAwareBpps) {
 * 			if (pvs == null) {
 * 				pvs = mbd.getPropertyValues();
 * 			}
 * 			for (BeanPostProcessor bp : getBeanPostProcessors()) {
 * 				if (bp instanceof InstantiationAwareBeanPostProcessor) {
 * 					InstantiationAwareBeanPostProcessor ibp = (InstantiationAwareBeanPostProcessor) bp;
 * 					PropertyValues pvsToUse = ibp.postProcessProperties(pvs, bw.getWrappedInstance(), beanName);
 * 					if (pvsToUse == null) {
 * 						if (filteredPds == null) {
 * 							filteredPds = filterPropertyDescriptorsForDependencyCheck(bw, mbd.allowCaching);
 * 						}
 * 						pvsToUse = ibp.postProcessPropertyValues(pvs, filteredPds, bw.getWrappedInstance(), beanName);
 * 						if (pvsToUse == null) {
 * 							return;
 * 						}
 * 					}
 * 					pvs = pvsToUse;
 * 				}
 * 			}
 * 		}
 * 		if (needsDepCheck) {
 * 			if (filteredPds == null) {
 * 				filteredPds = filterPropertyDescriptorsForDependencyCheck(bw, mbd.allowCaching);
 * 			}
 * 			checkDependencies(beanName, mbd, filteredPds, pvs);
 * 		}
 *
 *
 *    </pre>
 *
 *    最后为属性赋值(setter 等)
 *    if (pvs != null) {
 *  	applyPropertyValues(beanName, mbd, bw, pvs);
 *    }
 *
 *    执行初始化
 *    @see AbstractAutowireCapableBeanFactory#initializeBean(String, Object, RootBeanDefinition)
 *
 *        调用invokeAwareMethods(beanName, bean);
 *        调用执行Bean的BeanPostProcessors.postProcessorsBeforeInitialization()
 *
 *        <pre>
 *            Object wrappedBean = bean;
 * 		      if (mbd == null || !mbd.isSynthetic()) {
 * 			      wrappedBean = applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 * 		      }
 *
 * 		      try {
 * 			      invokeInitMethods(beanName, wrappedBean, mbd);
 * 		      }
 * 		      catch (Throwable ex) {
 * 			      throw new BeanCreationException(
 * 					(mbd != null ? mbd.getResourceDescription() : null),
 * 					beanName, "Invocation of init method failed", ex);
 * 		      }
 * 		      if (mbd == null || !mbd.isSynthetic()) {
 * 			      wrappedBean = applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 * 		      }
 *
 * 		      return wrappedBean;
 *        </pre>
 *
 *        执行Bean的初始化方法
 *
 *        调用执行Bean的BeanPostProcessors.postProcessorsAfterInitialization()
 *
 *  注册Bean的销毁方法
 *  // Register bean as disposable.
 * 	try {
 * 	    registerDisposableBeanIfNecessary(beanName, bean, mbd);
 * 	}
 *
 *  完成后返回Bean对象实例
 * ------------------------------------------------------------------------------------------------
 *
 * 12.发布相应的事件
 * // Last step: publish corresponding event.
 * finishRefresh();
 *
 * @see AbstractApplicationContext#finishRefresh()
 *
 * <pre>
 *      // Clear context-level resource caches (such as ASM metadata from scanning).
 * 		clearResourceCaches();
 *
 *      // 没有则new 默认的DefaultLifecycleProcessor
 *      // Uses DefaultLifecycleProcessor if none defined in the context.
 * 		// Initialize lifecycle processor for this context.
 * 		initLifecycleProcessor();
 *
 *      @see DefaultLifecycleProcessor#onRefresh()
 *      @see DefaultLifecycleProcessor#startBeans(boolean)
 *
 * 		// Propagate refresh to lifecycle processor first.
 * 		getLifecycleProcessor().onRefresh();
 *
 * 		// Publish the final event.
 * 		publishEvent(new ContextRefreshedEvent(this));
 *
 *      // JMX ?
 * 		// Participate in LiveBeansView MBean, if active.
 * 		LiveBeansView.registerApplicationContext(this);
 * </pre>
 *
 *
 * 13.重置缓存
 *
 * // Reset common introspection caches in Spring's core, since we
 * // might not ever need metadata for singleton beans anymore...
 * resetCommonCaches();
 *
 * @see AbstractApplicationContext#resetCommonCaches()
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
