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

package org.mac.sample.spring.boot.web.config;

import org.mac.sample.spring.boot.web.servlet.SimpleServlet;
import org.mac.sample.spring.boot.web.servlet.filter.SimpleFilter;
import org.mac.sample.spring.boot.web.servlet.listener.SimpleListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.embedded.TomcatWebServerFactoryCustomizer;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.ConfigurableTomcatWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.server.WebServerFactoryCustomizerBeanPostProcessor;
import org.springframework.boot.web.servlet.DynamicRegistrationBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletContextInitializerBeans;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import java.util.Arrays;

/**
 * Servlet filter listener registration configuration
 *
 * @auther mac
 * @date 2018-11-25
 */
@Configuration
public class ServletConfiguration {
    /**
     * Servlet 注册
     *
     * @see DispatcherServletAutoConfiguration.DispatcherServletRegistrationConfiguration#dispatcherServletRegistration(DispatcherServlet)
     *
     * <pre>
     *      @Bean(name = DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME)
     * 		@ConditionalOnBean(value = DispatcherServlet.class, name = DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
     * 		public DispatcherServletRegistrationBean dispatcherServletRegistration(
     * 				DispatcherServlet dispatcherServlet) {
     * 			DispatcherServletRegistrationBean registration = new DispatcherServletRegistrationBean(
     * 					dispatcherServlet, this.webMvcProperties.getServlet().getPath());// "/"
     * 			registration.setName(DEFAULT_DISPATCHER_SERVLET_BEAN_NAME);
     * 			registration.setLoadOnStartup(
     * 					this.webMvcProperties.getServlet().getLoadOnStartup());
     * 			if (this.multipartConfig != null) {
     * 				registration.setMultipartConfig(this.multipartConfig);
     * 			}
     * 			return registration;
     * 		}
     * </pre>
     *
     * Servlet组件注册Bean
     * @see ServletRegistrationBean
     * @see FilterRegistrationBean
     * @see ServletListenerRegistrationBean
     * 都是ServletContextInitializer的实现类
     *
     * @see ServletContextInitializer#onStartup(ServletContext)
     * @see RegistrationBean#onStartup(ServletContext)
     * @see RegistrationBean#register(String, ServletContext)
     * @see DynamicRegistrationBean#register(String, ServletContext)
     * @see ServletRegistrationBean#addRegistration(String, ServletContext)
     * <pre>
     *      String name = getServletName();
     * 		logger.info("Servlet " + name + " mapped to " + this.urlMappings);
     * 	    注册Servlet
     * 		return servletContext.addServlet(name, this.servlet);
     * </pre>
     * 嵌入式Servlet容器初始化时会调用全部的ServletContextInitializer
     * @see ServletWebServerApplicationContext#selfInitialize(ServletContext)
     * <pre>
     *     for (ServletContextInitializer beans : getServletContextInitializerBeans()) {
     * 			beans.onStartup(servletContext);
     * 		}
     * </pre>
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean simpleServlet() {

        ServletRegistrationBean registrationBean = new ServletRegistrationBean();

        registrationBean.setServlet(new SimpleServlet());
        registrationBean.addUrlMappings("/servlet");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean simpleFilter() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        registrationBean.setFilter(new SimpleFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/servlet"));

        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean simpleListener() {

        ServletListenerRegistrationBean registrationBean = new ServletListenerRegistrationBean();

        registrationBean.setListener(new SimpleListener());

        return registrationBean;
    }

    /**
     * Spring Boot 嵌入式WEB容器的自动配置和嵌入式WEB容器的启动
     *
     * @see EmbeddedWebServerFactoryCustomizerAutoConfiguration
     *
     * @see ServletWebServerFactoryAutoConfiguration
     *
     * 以默认的Tomcat为例，自动注册TomcatWebServerFactoryCustomizer
     * @see EmbeddedWebServerFactoryCustomizerAutoConfiguration.TomcatWebServerFactoryCustomizerConfiguration
     * <pre>
     *  @Configuration
     * 	@ConditionalOnClass({ Tomcat.class, UpgradeProtocol.class })
     * 	public static class TomcatWebServerFactoryCustomizerConfiguration {
     *
     * 		@Bean
     * 		public TomcatWebServerFactoryCustomizer tomcatWebServerFactoryCustomizer(
     * 				Environment environment, ServerProperties serverProperties) {
     * 			return new TomcatWebServerFactoryCustomizer(environment, serverProperties);
     * 		}
     *
     * 	}
     * </pre>
     * @see TomcatWebServerFactoryCustomizer
     * @see TomcatWebServerFactoryCustomizer#customize(ConfigurableTomcatWebServerFactory)
     *
     * 即和ServerProperties绑定的WEB容器定制配置被自动配置
     *
     * -----------------------------------------------------------------------------------------------------
     *
     * Spring Boot 自动配置、初始化和启动嵌入式容器(默认Tomcat)
     *
     * @see org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryConfiguration.EmbeddedTomcat;
     * <pre>
     *  @Configuration
     * 	@ConditionalOnClass({ Servlet.class, Tomcat.class, UpgradeProtocol.class })
     * 	@ConditionalOnMissingBean(value = ServletWebServerFactory.class, search = SearchStrategy.CURRENT)
     * 	public static class EmbeddedTomcat {
     *
     * 		@Bean
     * 		public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
     * 			return new TomcatServletWebServerFactory();
     * 		}
     *
     * 	}
     * </pre>
     *
     * @see TomcatServletWebServerFactory
     * @see TomcatServletWebServerFactory#getWebServer(ServletContextInitializer...)
     * <pre>
     *      Tomcat tomcat = new Tomcat();
     * 		File baseDir = (this.baseDirectory != null) ? this.baseDirectory
     * 				: createTempDir("tomcat");
     * 		tomcat.setBaseDir(baseDir.getAbsolutePath());
     * 		Connector connector = new Connector(this.protocol);
     * 		tomcat.getService().addConnector(connector);
     * 		customizeConnector(connector);
     * 		tomcat.setConnector(connector);
     * 		tomcat.getHost().setAutoDeploy(false);
     * 		configureEngine(tomcat.getEngine());
     * 		for (Connector additionalConnector : this.additionalTomcatConnectors) {
     * 			tomcat.getService().addConnector(additionalConnector);
     * 		}
     * 		prepareContext(tomcat.getHost(), initializers);
     * 		return getTomcatWebServer(tomcat);
     * </pre>
     *
     * @see TomcatWebServer
     * @see TomcatWebServer#initialize()
     * <pre>
     *      synchronized (this.monitor) {
     * 			try {
     * 				addInstanceIdToEngineName();
     *
     * 				Context context = findContext();
     * 				context.addLifecycleListener((event) -> {
     * 					if (context.equals(event.getSource())
     * 							&& Lifecycle.START_EVENT.equals(event.getType())) {
     * 						// Remove service connectors so that protocol binding doesn't
     * 						// happen when the service is started.
     * 						removeServiceConnectors();
     * 					}
     * 				});
     *
     * 				// Start the server to trigger initialization listeners
     * 				this.tomcat.start();
     *
     * 				// We can re-throw failure exception directly in the main thread
     * 				rethrowDeferredStartupExceptions();
     *
     * 				try {
     * 					ContextBindings.bindClassLoader(context, context.getNamingToken(),
     * 							getClass().getClassLoader());
     * 				}
     * 				catch (NamingException ex) {
     * 					// Naming is not enabled. Continue
     * 				}
     *
     * 				// Unlike Jetty, all Tomcat threads are daemon threads. We create a
     * 				// blocking non-daemon to stop immediate shutdown
     * 				startDaemonAwaitThread();
     * 			}
     * 			catch (Exception ex) {
     * 				stopSilently();
     * 				throw new WebServerException("Unable to start embedded Tomcat", ex);
     * 			}
     * 		}
     * </pre>
     *
     * Spring Boot 驱动嵌入式容器启动
     *
     * @see SpringApplication#run(String...)
     *
     * 1. 创建上下文 WEB应用创建的上下文为
     * @see org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext
     *
     * 2. refresh 上下文 进入Spring容器上下文的初始化过程
     * @see AbstractApplicationContext#refresh()
     *
     * [详细过程 @see @link org.mac.sample.spring.annotation.initialization.SpringAnnotationDriveApplication]
     *
     * <pre>
     *     synchronized (this.startupShutdownMonitor) {
     * 			......
     *          @see ServletWebServerApplicationContext 实现onRefresh创建并启动WEB容器
     * 			// Initialize other special beans in specific context subclasses.
     * 			onRefresh();
     *          ......
     * 		}
     * </pre>
     *
     * 3.通过AnnotationConfigServletWebServerApplicationContext实现的onRefresh方法
     *   实现WEB容器的启动
     *
     * @see ServletWebServerApplicationContext#onRefresh()
     * @see ServletWebServerApplicationContext#createWebServer()
     * <pre>
     *     WebServer webServer = this.webServer;
     * 		ServletContext servletContext = getServletContext();
     * 		if (webServer == null && servletContext == null) {
     * 			ServletWebServerFactory factory = getWebServerFactory();
     * 			this.webServer = factory.getWebServer(getSelfInitializer());
     * 		}
     * 		else if (servletContext != null) {
     * 			try {
     * 				getSelfInitializer().onStartup(servletContext);
     * 			}
     * 			catch (ServletException ex) {
     * 				throw new ApplicationContextException("Cannot initialize servlet context",
     * 						ex);
     * 			}
     * 		}
     * 		initPropertySources();
     * </pre>
     *
     * @see ServletWebServerApplicationContext#selfInitialize(ServletContext)
     * <pre>
     *      prepareWebApplicationContext(servletContext);
     * 		ConfigurableListableBeanFactory beanFactory = getBeanFactory();
     * 		ExistingWebApplicationScopes existingScopes = new ExistingWebApplicationScopes(
     * 				beanFactory);
     * 		WebApplicationContextUtils.registerWebApplicationScopes(beanFactory,
     * 				getServletContext());
     * 		existingScopes.restore();
     * 		WebApplicationContextUtils.registerEnvironmentBeans(beanFactory,
     * 				getServletContext());
     * 		for (ServletContextInitializer beans : getServletContextInitializerBeans()) {
     * 			beans.onStartup(servletContext);
     * 		}
     * </pre>
     * @see ServletContextInitializerBeans 从其中获取ServletContextInitializer的实现Bean
     *
     * ServletContextInitializer的实现Bean传入 开始初始化并启动WEB容器
     * [@see org.mac.sample.spring.annotation.webmvc.SimpleCustomizeWebApplicationInitializer]
     *
     * @see ServletWebServerApplicationContext#getWebServerFactory()
     *
     * 从BeanFactory中获取ServletWebServerFactory,默认情况下即可获得
     * @see org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryConfiguration.EmbeddedTomcat;
     * 自动配置的
     * @see org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
     *
     * @see TomcatServletWebServerFactory#getWebServer(ServletContextInitializer...)
     * 初始化并启动WEB容器
     *
     * @see TomcatWebServer#initialize()
     *
     * WEB容器启动后继续Spring IoC容器的refresh流程
     * 即Spring IoC容器中Bean的初始化
     *
     * 如需要注册自定义的 SimpleServlet
     * <pre>
     *     @Bean
     *     public ServletRegistrationBean simpleServlet() {
     *
     *         ServletRegistrationBean registrationBean = new ServletRegistrationBean();
     *
     *         registrationBean.setServlet(new SimpleServlet());
     *         registrationBean.addUrlMappings("/servlet");
     *
     *         return registrationBean;
     *     }
     * </pre>
     * 以及自定义的WebServerFactoryCustomizer等
     *
     *
     *
     * @return
     */
    @Bean
    public WebServerFactoryCustomizer webServerFactoryCustomizer(){
        /**
         * @see WebServerFactoryCustomizerBeanPostProcessor
         * @see WebServerFactoryCustomizerBeanPostProcessor#postProcessBeforeInitialization(org.springframework.boot.web.server.WebServerFactory)
         */
        return (WebServerFactory factory)-> {

            ConfigurableServletWebServerFactory configurableServletWebServerFactory = (ConfigurableServletWebServerFactory)factory;
            // WEB容器的自定义配置
            configurableServletWebServerFactory.setPort(8888);
        };
    }
}
