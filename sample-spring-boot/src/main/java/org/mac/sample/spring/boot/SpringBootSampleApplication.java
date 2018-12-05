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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.annotation.DeferredImportSelector;
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
	 * 然后应用运行
	 * @see SpringApplication#run(java.lang.String...)
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SpringBootSampleApplication.class, args);
	}
}
