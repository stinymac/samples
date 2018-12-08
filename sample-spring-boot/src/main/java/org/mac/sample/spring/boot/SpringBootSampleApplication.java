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
package org.mac.sample.spring.boot;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationImportSelector;
import org.springframework.boot.autoconfigure.AutoConfigurationMetadata;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.BackgroundPreinitializer;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.logging.ConditionEvaluationReportLoggingListener;
import org.springframework.boot.builder.ParentContextCloserApplicationListener;
import org.springframework.boot.cloud.CloudFoundryVcapEnvironmentPostProcessor;
import org.springframework.boot.context.ConfigurationWarningsApplicationContextInitializer;
import org.springframework.boot.context.ContextIdApplicationContextInitializer;
import org.springframework.boot.context.FileEncodingApplicationListener;
import org.springframework.boot.context.config.AnsiOutputApplicationListener;
import org.springframework.boot.context.config.ConfigFileApplicationListener;
import org.springframework.boot.context.config.DelegatingApplicationContextInitializer;
import org.springframework.boot.context.config.DelegatingApplicationListener;
import org.springframework.boot.context.logging.ClasspathLoggingApplicationListener;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.boot.env.SpringApplicationJsonEnvironmentPostProcessor;
import org.springframework.boot.env.SystemEnvironmentPropertySourceEnvironmentPostProcessor;
import org.springframework.boot.liquibase.LiquibaseServiceLocatorApplicationListener;
import org.springframework.boot.web.context.ServerPortInfoApplicationContextInitializer;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.context.event.AbstractApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Collection;

/**
 * Spring Boot 应用主程序入口(自动装配)
 *
 * @see SpringBootApplication
 *
 * <pre>
 *     @SpringBootConfiguration
 *     @EnableAutoConfiguration
 *     @ComponentScan(excludeFilters = {
 * 		@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
 * 		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })
 * </pre>
 *
 * @see SpringBootConfiguration
 * 其被@Configuration标注(即Spring的配置组件) 因此此类(@SpringBootApplication标注的类)
 * 是Spring容器启动的配置类
 *
 * @see EnableAutoConfiguration 自动配置启用
 *
 * <pre>
 *     @AutoConfigurationPackage
 *
 *     @Import(AutoConfigurationImportSelector.class)
 * </pre>
 *
 * @AutoConfigurationPackage 被@Import(AutoConfigurationPackages.Registrar.class)标注
 *
 * 即向Spring容器中注册AutoConfigurationPackages.Registrar指定的BeanDefinition
 *
 * @see AutoConfigurationPackages.Registrar#registerBeanDefinitions(AnnotationMetadata, BeanDefinitionRegistry)
 * <pre>
 *     register(registry, new PackageImport(metadata).getPackageName());
 * </pre>
 *
 * <pre>
 *     PackageImport(AnnotationMetadata metadata) {
 * 			this.packageName = ClassUtils.getPackageName(metadata.getClassName());
 * 	   }
 * </pre>
 *
 * @see AutoConfigurationPackages#register(BeanDefinitionRegistry, String...)
 *
 * 向容器中注册了ID为org.springframework.boot.autoconfigure.AutoConfigurationPackages 定义为
 * <pre>
 *     GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
 * 	   beanDefinition.setBeanClass(BasePackages.class);//org.springframework.boot.autoconfigure.AutoConfigurationPackages#BasePackages
 * 	   beanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0,
 * 					packageNames);//org.mac.sample.spring.boot
 * </pre>
 * 的Bean定义
 *
 * 所以@SpringBootApplication标注的启动配置入口类(SpringBootSampleApplication.class)所在的包及其子包中
 * 所有的组件将注册到Spring容器中
 *
 * @Import(AutoConfigurationImportSelector.class)
 * 向容器中注册(由ConfigurationClassPostProcessor处理 @see org.springframework.context.annotation.ConfigurationClassPostProcessor)
 * AutoConfigurationImportSelector指定的Bean
 *
 * 在后置处理器ConfigurationClassPostProcessor执行ConfigurationClassParser时
 * @see ConfigurationClassPostProcessor#postProcessBeanDefinitionRegistry(BeanDefinitionRegistry)
 * 处理SpringBootConfiguration标注的类 当前为SpringBootSampleApplication.class
 *
 *
 * @see org.springframework.context.annotation.ConfigurationClassParser#processImports(
 *     org.springframework.context.annotation.ConfigurationClass,
 *     org.springframework.context.annotation.ConfigurationClassParser.SourceClass, Collection, boolean)
 *
 * 配置类解析器收集到@SpringBootApplication上的@Import(AutoConfigurationPackages.Registrar.class)和@Import(AutoConfigurationImportSelector.class)
 *
 * @see org.springframework.context.annotation.ConfigurationClassParser.DeferredImportSelectorHandler#process()
 *
 * @see AutoConfigurationImportSelector.AutoConfigurationGroup#process(AnnotationMetadata, DeferredImportSelector)
 *
 * 其调用AutoConfigurationImportSelector#getAutoConfigurationEntry(AutoConfigurationMetadata, AnnotationMetadata)
 * 返回自动装配的类全名称数组
 *
 *
 * @see AutoConfigurationImportSelector
 *     @see AutoConfigurationImportSelector#getAutoConfigurationEntry(AutoConfigurationMetadata, AnnotationMetadata)
 *     <pre>
 *      if (!isEnabled(annotationMetadata)) { // spring.boot.enableautoconfiguration == true?
 * 		    return EMPTY_ENTRY;
 * 		}
 * 		AnnotationAttributes attributes = getAttributes(annotationMetadata);
 * 		List<String> configurations = getCandidateConfigurations(annotationMetadata,
 * 				attributes);
 * 		configurations = removeDuplicates(configurations);
 * 		Set<String> exclusions = getExclusions(annotationMetadata, attributes);
 * 		checkExcludedClasses(configurations, exclusions);
 * 		configurations.removeAll(exclusions);
 * 		configurations = filter(configurations, autoConfigurationMetadata);
 * 		fireAutoConfigurationImportEvents(configurations, exclusions);
 * 		return new AutoConfigurationEntry(configurations, exclusions);
 *     </pre>
 *
 * List<String> configurations = getCandidateConfigurations(annotationMetadata,attributes);返回全部的自动装配类候选集合
 * @see AutoConfigurationImportSelector#getCandidateConfigurations(AnnotationMetadata, AnnotationAttributes)
 * <pre>
 *     List<String> configurations = SpringFactoriesLoader.loadFactoryNames(
 *         getSpringFactoriesLoaderFactoryClass(), getBeanClassLoader());
 * </pre>
 *
 * @see SpringFactoriesLoader#loadSpringFactories(ClassLoader)
 * <pre>
 *     //FACTORIES_RESOURCE_LOCATION = META-INF/spring.factories
 *     //即类路径下的spring.factories文件中指定的自动装配类集合
 *     Enumeration<URL> urls = (classLoader != null ?
 * 					classLoader.getResources(FACTORIES_RESOURCE_LOCATION) :
 * 					ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));
 * 	   result = new LinkedMultiValueMap<>();
 * 	   while (urls.hasMoreElements()) {
 * 	       URL url = urls.nextElement();
 * 		   UrlResource resource = new UrlResource(url);
 * 		   Properties properties = PropertiesLoaderUtils.loadProperties(resource);
 * 		   for (Map.Entry<?, ?> entry : properties.entrySet()) {
 * 			   String factoryClassName = ((String) entry.getKey()).trim();
 * 			   for (String factoryName : StringUtils.commaDelimitedListToStringArray((String) entry.getValue())) {
 * 						result.add(factoryClassName, factoryName.trim());
 * 			   }
 * 		   }
 *     }
 * </pre>
 * @see <a>spring-boot-autoconfigure-2.1.0.RELEASE-sources.jar!/META-INF/spring.factories</a>
 *
 * 所以SpringBoot默认支持的自动装配组件全部将被注册到Spring容器中
 *
 * 例如:
 * @see org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
 * @see org.springframework.boot.autoconfigure.http.HttpProperties
 * <pre>
 *     // 指定为配置类
 *     @Configuration
 *     // 启动HttpProperties的ConfigurationProperties
 *     // @ConfigurationProperties将HttpProperties对象的属性和配置文件中的对应配置绑定
 *     @EnableConfigurationProperties(HttpProperties.class)
 *     // 条件注入 判断当前应用是否为Web应用
 *     @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
 *     // 条件注入 判断CharacterEncodingFilter类是否存在
 *     @ConditionalOnClass(CharacterEncodingFilter.class)
 *     // 条件注入 判断配置文件中spring.http.encoding.enabled是否存在
 *     // matchIfMissing = true表示配置文件中不存在spring.http.encoding.enabled
 *     // spring.http.encoding.enabled = true 为默认
 *
 *     @ConditionalOnProperty(prefix = "spring.http.encoding", value = "enabled", matchIfMissing = true)
 *     public class HttpEncodingAutoConfiguration {
 *         @Bean
 *         // Conditional that only matches when no beans of the specified classes and/or
 *         // with the specified names are already contained in the BeanFactory.
 *         // 即CharacterEncodingFilter的实例不存在在容器中
 * 	       @ConditionalOnMissingBean
 * 	       public CharacterEncodingFilter characterEncodingFilter() {
 * 		       CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
 * 		       filter.setEncoding(this.properties.getCharset().name());
 * 		       filter.setForceRequestEncoding(this.properties.shouldForce(Type.REQUEST));
 * 		       filter.setForceResponseEncoding(this.properties.shouldForce(Type.RESPONSE));
 * 		       return filter;
 * 	       }
 *         ......
 *     }
 * </pre>
 * ------------------------------------------------------------------------------------------------------------------
 * tips:
 *
 * 自动配置类一般都要在一定条件下才能生效
 * 可以在配置文件中设置debug = true 控制台将输出自动配置报告 可以查看启用和未启用的自动配置组件
 *
 * @auther mac
 * @date 2018-10-10
 */
@SpringBootApplication
public class SpringBootSampleApplication {
	/**
	 * Spring Boot 启动、运行以及自动装配流程分析
	 *
	 * SpringApplication.run(SpringBootSampleApplication.class, args);
	 * 开始执行应用启动
	 *
	 * @see SpringApplication#run(java.lang.Class[], java.lang.String[])
	 * <pre>
	 *     return new SpringApplication(primarySources).run(args);
	 * </pre>
	 *
	 * 即首先创建Spring应用(new SpringApplication(primarySources))
	 * @see SpringApplication#SpringApplication(org.springframework.core.io.ResourceLoader, java.lang.Class[])
	 *
	 * <pre>
	 *      // 保存ResourceLoader 当前应用ResourceLoader为空
	 *      this.resourceLoader = resourceLoader;
	 * 		Assert.notNull(primarySources, "PrimarySources must not be null");
	 * 	    // 资源类Class对象 当前应用为	SpringBootSampleApplication.class
	 * 		this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
	 * 	    // 推断应用类型 即通过加载对应的类 判断是否存在 推断应用类型
	 * 	    @see org.springframework.boot.WebApplicationType#deduceFromClasspath()
	 *
	 * 		this.webApplicationType = WebApplicationType.deduceFromClasspath();
	 *
	 * 	    // 保存加载的	ApplicationContextInitializer类型的 initializers(即对Spring上下文初始化 @see org.springframework.context.ApplicationContextInitializer)
	 *      @see SpringApplication#getSpringFactoriesInstances(java.lang.Class, java.lang.Class[], java.lang.Object...)
	 *      @see SpringFactoriesLoader#loadSpringFactories(java.lang.ClassLoader)
	 *      // 即从META-INF/spring.factories文件中加载配置的spring factories,筛选出ApplicationContextInitializer
	 *      // 然后实例化这些ApplicationContextInitializer并排序 (一般按类上标注的排序注解或对Order的实现)
	 *      //当前的ApplicationContextInitializer为
	 *      @see DelegatingApplicationContextInitializer
	 *      @see org.springframework.boot.autoconfigure.SharedMetadataReaderFactoryContextInitializer
	 *      @see ContextIdApplicationContextInitializer
	 *      @see ConfigurationWarningsApplicationContextInitializer
	 *      @see ServerPortInfoApplicationContextInitializer // 同时实现了ApplicationListener
	 *      @see ConditionEvaluationReportLoggingListener
	 *      // @link {spring-boot-2.1.0.RELEASE.jar!/META-INF/spring.factories#org.springframework.context.ApplicationContextInitializer}
	 *      // @link {spring-boot-autoconfigure-2.1.0.RELEASE.jar!/META-INF/spring.factories#org.springframework.context.ApplicationContextInitializer}
	 *
	 * 		setInitializers((Collection) getSpringFactoriesInstances(
	 * 				ApplicationContextInitializer.class));
	 *
	 * 	    // 保存加载的	ApplicationListener类型的 listeners (监听器 @see org.springframework.context.ApplicationListener)
	 * 	    // 当前的ApplicationListener为
	 *      @see BackgroundPreinitializer //@see org.springframework.boot.autoconfigure.BackgroundPreinitializer#performPreinitialization()
	 *      // Liquibase是一个用于数据库重构和迁移的开源工具，通过日志文件的形式记录数据库的变更，
	 *      // 然后执行日志文件中的修改，将数据库更新或回滚到一致的状态
	 *      @see LiquibaseServiceLocatorApplicationListener
	 *      @see LoggingApplicationListener
	 *      @see ClasspathLoggingApplicationListener
	 *      @see DelegatingApplicationListener
	 *      @see ConfigFileApplicationListener
	 *      @see AnsiOutputApplicationListener
	 *      @see ParentContextCloserApplicationListener
	 *      @see org.springframework.boot.ClearCachesApplicationListener
	 *      @see FileEncodingApplicationListener
	 *      // @link {spring-boot-autoconfigure-2.1.0.RELEASE.jar!/META-INF/spring.factories}
	 *      // @link {spring-boot-2.1.0.RELEASE.jar!/META-INF/spring.factories}
	 *
	 * 		setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
	 *      // 推断MainClass
	 * 		this.mainApplicationClass = deduceMainApplicationClass();
	 * </pre>
	 *
	 * 然后应用运行
	 * @see SpringApplication#run(java.lang.String...)
	 * <pre>
	 *      StopWatch stopWatch = new StopWatch();
	 * 		stopWatch.start();//秒表启动
	 * 		ConfigurableApplicationContext context = null;
	 * 		Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
	 * 	    //java.awt.headless Headless模式是在缺少显示屏、键盘或者鼠标的系统配置。
	 * 		configureHeadlessProperty();
	 *
	 * 	    // 取RunListenners 即同样从META-INF/spring.factories加载 当前为
	 * 	    @see org.springframework.boot.context.event.EventPublishingRunListener
	 * 	    // @link {spring-boot-2.1.0.RELEASE.jar!/META-INF/spring.factories}
	 * 		SpringApplicationRunListeners listeners = getRunListeners(args);
	 *
	 * 	    // 当前即广播 ApplicationStartingEvent事件
	 * 	    @see SimpleApplicationEventMulticaster#multicastEvent(org.springframework.context.ApplicationEvent, org.springframework.core.ResolvableType)
	 *      @see AbstractApplicationEventMulticaster#getApplicationListeners(org.springframework.context.ApplicationEvent, org.springframework.core.ResolvableType)
	 *      @see AbstractApplicationEventMulticaster#retrieveApplicationListeners(org.springframework.core.ResolvableType, java.lang.Class, org.springframework.context.event.AbstractApplicationEventMulticaster.ListenerRetriever)
	 *      // 即从new SpringApplication()时加载的10个ApplicationListener中按支持的事件类型(ApplicationStartingEvent事件)过滤出4个
	 *      @see LoggingApplicationListener
	 *      @see BackgroundPreinitializer
	 *      @see DelegatingApplicationListener
	 *      @see LiquibaseServiceLocatorApplicationListener
	 *
	 *      LoggingApplicationListener#onApplicationStartingEvent获取系统日志类型
	 *  	@see org.springframework.boot.logging.LoggingSystem#get(java.lang.ClassLoader)
	 * 	 	// beforeInitialize初始化日志
	 * 	 	@see org.springframework.boot.logging.logback.LogbackLoggingSystem#beforeInitialize()
	 *      BackgroundPreinitializer#onApplicationEvent(org.springframework.boot.context.event.SpringApplicationEvent)
	 * 	    @see BackgroundPreinitializer#performPreinitialization() // 异步执行了一些初始化
	 *      DelegatingApplicationListener#onApplicationEvent(ApplicationEvent event) // 此时DelegatingApplicationListener什么都不做
	 * 	    LiquibaseServiceLocatorApplicationListener // 当前不应用
	 *
	 * 		listeners.starting();
	 * 		try {
	 * 	        // 封装main方法的参数
	 * 			ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
	 *
	 * 			// 准备环境 当前为WEB环境
	 *          @see SpringApplication#getOrCreateEnvironment()
	 * 			@see org.springframework.web.context.support.StandardServletEnvironment
	 *          @see SpringApplication#configureEnvironment(org.springframework.core.env.ConfigurableEnvironment, java.lang.String[])
	 *          @see SpringApplication#configurePropertySources(org.springframework.core.env.ConfigurableEnvironment, java.lang.String[])
	 *          @see SpringApplication#configureProfiles(org.springframework.core.env.ConfigurableEnvironment, java.lang.String[])
	 *          // 同时广播ApplicationEnvironmentPreparedEvent  同listeners.starting()一样
	 *          // 从new SpringApplication()时加载的10个ApplicationListener中按支持的事件类型(ApplicationEnvironmentPreparedEvent)过滤出7个
	 *          @see ConfigFileApplicationListener
	 *          @see AnsiOutputApplicationListener
	 *          @see LoggingApplicationListener
	 *          @see ClasspathLoggingApplicationListener
	 *          @see BackgroundPreinitializer
	 *          @see DelegatingApplicationListener
	 *          @see FileEncodingApplicationListener
	 *
	 *          //回调ConfigFileApplicationListener
	 *          @see ConfigFileApplicationListener#onApplicationEnvironmentPreparedEvent(org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent)
	 * 	        // 即从/META-INF/spring.factories加载EnvironmentPostProcessor 如下
	 * 	        @see SystemEnvironmentPropertySourceEnvironmentPostProcessor
	 * 	        @see SpringApplicationJsonEnvironmentPostProcessor
	 * 	        @see CloudFoundryVcapEnvironmentPostProcessor
	 * 	        // 并将ConfigFileApplicationListener(实现了EnvironmentPostProcessor)本身加入然后排序 最后依次执行
	 * 	        @see org.springframework.boot.env.SystemEnvironmentPropertySourceEnvironmentPostProcessor#postProcessEnvironment(org.springframework.core.env.ConfigurableEnvironment, org.springframework.boot.SpringApplication)
	 * 	        @see org.springframework.boot.env.SpringApplicationJsonEnvironmentPostProcessor#postProcessEnvironment(org.springframework.core.env.ConfigurableEnvironment, org.springframework.boot.SpringApplication)
	 * 	        // VCAP-VMware's Cloud Application Platform //Cloud Foundry is an open platform-as-a-service (PaaS).
	 * 	        @see org.springframework.boot.cloud.CloudFoundryVcapEnvironmentPostProcessor#postProcessEnvironment(org.springframework.core.env.ConfigurableEnvironment, org.springframework.boot.SpringApplication)
	 * 	        @see org.springframework.boot.context.config.ConfigFileApplicationListener#addPropertySources(org.springframework.core.env.ConfigurableEnvironment, org.springframework.core.io.ResourceLoader)
	 *
	 *          //回调AnsiOutputApplicationListener 即配置spring.output.ansi.enabled
	 *          @see AnsiOutputApplicationListener#onApplicationEvent(org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent)
	 * 	        //回调LoggingApplicationListener
	 * 	        @see LoggingApplicationListener#onApplicationEnvironmentPreparedEvent(org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent)
	 * 	        @see LoggingApplicationListener#initialize(org.springframework.core.env.ConfigurableEnvironment, java.lang.ClassLoader)
	 * 			//回调ClasspathLoggingApplicationListener即日志输出类路径下的jar
	 *          @see ClasspathLoggingApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 *          //回调BackgroundPreinitializer //do nothing
	 *          @see BackgroundPreinitializer#onApplicationEvent(org.springframework.boot.context.event.SpringApplicationEvent)
	 *          //回调DelegatingApplicationListener //getListeners 为空 do nothing
	 *          @see DelegatingApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
	 *          //回调FileEncodingApplicationListener 检查spring.mandatory-file-encoding 若没有直接返回
	 *          @see FileEncodingApplicationListener#onApplicationEvent(org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent)
	 *
	 * 			ConfigurableEnvironment environment = prepareEnvironment(listeners,
	 * 					applicationArguments);
	 * 			// 配置ignore bean info //spring.beaninfo.ignore
	 * 			configureIgnoreBeanInfo(environment);
	 *
	 * 			Banner printedBanner = printBanner(environment);
	 * 		    // 当前为WEB环境，创建AnnotationConfigServletWebServerApplicationContext
	 *          @see AnnotationConfigServletWebServerApplicationContext#AnnotationConfigServletWebServerApplicationContext()
	 *          <pre>
	 *              //this->AnnotationConfigServletWebServerApplicationContext implements AnnotationConfigRegistry
	 *              this.reader = new AnnotatedBeanDefinitionReader(this);
	 * 		        this.scanner = new ClassPathBeanDefinitionScanner(this);
	 *          </pre>
	 * 			context = createApplicationContext();// 创建context 创建BeanFactory 并注册了内置的注解配置PostProcess
	 *
	 * 	        // 从/META-INF/spring.factories加载错误报表分析器
	 * 			exceptionReporters = getSpringFactoriesInstances(SpringBootExceptionReporter.class,new Class[] { ConfigurableApplicationContext.class }, context);
	 *
	 * 			// 上下文准备
	 *
	 *          @see SpringApplication#postProcessApplicationContext(org.springframework.context.ConfigurableApplicationContext)
	 *
	 *          //执行初始化 即依次执行new SpingApplication时从/META-INF/spring.factories加载的6个initializer
	 *          // DelegatingApplicationContextInitializer 从环境中获取context.initializer.classes的值配置的
	 *          // 并依次执行 当前环境无context.initializer.classes配置
	 *          // SharedMetadataReaderFactoryContextInitializer 向上下文注册CachingMetadataReaderFactoryPostProcessor
	 *          // ContextIdApplicationContextInitializer 向BeanFactory中注册ContextId
	 *          // ConfigurationWarningsApplicationContextInitializer 向上下文注册ConfigurationWarningsPostProcessor
	 *          // ServerPortInfoApplicationContextInitializer 向上下文注册其自身 (implements ApplicationListener<WebServerInitializedEvent>)
	 *          // ConditionEvaluationReportLoggingListener 向上下文注册ConditionEvaluationReportListener
	 *          @see SpringApplication#applyInitializers(org.springframework.context.ConfigurableApplicationContext)
	 *
	 *          // 上下文准备事件发布 即广播ApplicationContextInitializedEvent
	 *          // 从加载的10个ApplicationListener中按支持的事件类型(ApplicationContextInitializedEvent)匹配到2个
	 *          @see BackgroundPreinitializer //do nothing
	 *          @see DelegatingApplicationListener //do nothing
	 *
	 *          // 加载资源
	 *          <pre>
	 * 	  		    load(context, sources.toArray(new Object[0]));// 将@SpringBootApplication标注的类(即当前类)作为配置source注册到容器
	 * 	  		</pre>
	 *
	 *          // 广播上下文加载完成事件 即广播ApplicationPreparedEvent
	 *          // 首先将已加载的10个listener放入上下文中
	 *          <pre>
	 *              for (ApplicationListener<?> listener : this.application.getListeners()) {
	 * 			        if (listener instanceof ApplicationContextAware) {
	 * 				        ((ApplicationContextAware) listener).setApplicationContext(context);
	 * 			        }
	 * 			        context.addApplicationListener(listener);
	 * 		        }
	 *          </pre>
	 *          匹配到4个listener 向其广播ApplicationPreparedEvent
	 *          @see ConfigFileApplicationListener // 上下文加入 @see PropertySourceOrderingPostProcessor
	 *          @see LoggingApplicationListener //beanFactory.registerSingleton(LOGGING_SYSTEM_BEAN_NAME, this.loggingSystem);
	 *          @see BackgroundPreinitializer // do nothing
	 *          @see DelegatingApplicationListener // do nothing
	 *
	 * 			prepareContext(context, environment, listeners, applicationArguments,printedBanner);
	 *
	 *          //解析配置 注册Bean 实例化并注册单例Bean ......
	 *          @see org.springframework.context.annotation.ConfigurationClassParser#parse(java.util.Set<org.springframework.beans.factory.config.BeanDefinitionHolder>)
	 *          @see org.springframework.context.annotation.ConfigurationClassParser#doProcessConfigurationClass
	 *          @see org.springframework.context.annotation.ComponentScanAnnotationParser#parse
	 *          @see org.springframework.context.annotation.ClassPathBeanDefinitionScanner#doScan
	 *          //classpath*:org/mac/sample/spring/boot/**\\/*.class
	 *          @see org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider#scanCandidateComponents
	 *          @see org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider#isCandidateComponent(org.springframework.core.type.classreading.MetadataReader)
	 *          @see org.springframework.context.annotation.AnnotationConfigUtils#applyScopedProxyMode
	 *          @see org.springframework.context.annotation.ConfigurationClassParser#processConfigurationClass
	 *          @see org.springframework.context.annotation.ConfigurationClassParser#doProcessConfigurationClass
	 *          @see org.springframework.context.annotation.ConfigurationClassParser#processImports
	 *          @see org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader#loadBeanDefinitionsForConfigurationClass
	 *          @see org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader#loadBeanDefinitionsFromImportedResources
	 *          @see org.springframework.context.annotation.ConfigurationClassBeanDefinitionReader#loadBeanDefinitionsFromRegistrars
	 *          @see @link {samples/sample-spring/src/main/java/org/mac/sample/spring/annotation/initialization/SpringAnnotationDriveApplication.java}
	 * 			***refreshContext(context);
	 *
	 * 			afterRefresh(context, applicationArguments);
	 * 			stopWatch.stop();
	 * 			if (this.logStartupInfo) {
	 * 				new StartupInfoLogger(this.mainApplicationClass)
	 * 						.logStarted(getApplicationLog(), stopWatch);
	 * 			}
	 * 			listeners.started(context);
	 * 			callRunners(context, applicationArguments);
	 * 		}
	 * 		catch (Throwable ex) {
	 * 			handleRunFailure(context, ex, exceptionReporters, listeners);
	 * 			throw new IllegalStateException(ex);
	 * 		}
	 *
	 * 		try {
	 * 			listeners.running(context);
	 * 		}
	 * 		catch (Throwable ex) {
	 * 			handleRunFailure(context, ex, exceptionReporters, null);
	 * 			throw new IllegalStateException(ex);
	 * 		}
	 * 		return context;
	 * </pre>
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSampleApplication.class, args);
	}
}
/**
 * tips:
 *
 * Spring字符串分割工具
 *
 * @see org.springframework.util.StringUtils#delimitedListToStringArray(java.lang.String, java.lang.String)
 *
 * 返回指定键映射到的值，如果不包含该键的映射返回给定的默认值。
 * @see java.util.Map#getOrDefault(java.lang.Object, java.lang.Object)
 *
 * properties 文件加载工具
 * @see org.springframework.core.io.support.PropertiesLoaderUtils#loadProperties(org.springframework.core.io.Resource)
 *
 * private static final String XML_FILE_EXTENSION = ".xml";
 *
 */
