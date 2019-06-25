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

package org.mac.sample.spring.boot.web.config;

import org.mac.sample.spring.boot.web.interceptor.LoginControlInterceptor;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import java.util.List;

/**
 * SpringBoot Web 自动装配
 *
 * @see DispatcherServletAutoConfiguration
 * @see WebMvcAutoConfiguration
 *
 * SpringBoot对静态资源的处理、欢迎页、favicon.ico的处理
 *
 * 静态资源
 * @see WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#addResourceHandlers(ResourceHandlerRegistry)
 * <pre>
 *     if (!this.resourceProperties.isAddMappings()) {
 *         logger.debug("Default resource handling disabled");
 *         return;
 *     }
 *     Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
 *     CacheControl cacheControl = this.resourceProperties.getCache()
 *                                     .getCachecontrol().toHttpCacheControl();
 *     if (!registry.hasMappingForPattern("/webjars/**")) {
 *         customizeResourceHandlerRegistration(registry.addResourceHandler("/webjars/**")
 *             .addResourceLocations("classpath:/META-INF/resources/webjars/")
 *             .setCachePeriod(getSeconds(cachePeriod))
 *             .setCacheControl(cacheControl));
 *     }
 *     String staticPathPattern = this.mvcProperties.getStaticPathPattern();
 *     if (!registry.hasMappingForPattern(staticPathPattern)) {
 *         customizeResourceHandlerRegistration(registry.addResourceHandler(staticPathPattern)
 *                 .addResourceLocations(getResourceLocations(this.resourceProperties.getStaticLocations()))
 *                 .setCachePeriod(getSeconds(cachePeriod))
 *                 .setCacheControl(cacheControl));
 *     }
 * </pre>
 * webjars的方式:
 * 即添加/webjars/**的请求处理器 所有/webjars/**的请求从classpath:/META-INF/resources/webjars/下查找
 * <a>http://localhost:8080/webjars/jquery/3.3.1-1/jquery.js</a>
 *
 * 静态资源文件:
 * 添加静态资源处理器(private String staticPathPattern = "/**";) 所有/**请求
 * 从以下路径查找
 * @see org.springframework.boot.autoconfigure.web.ResourceProperties
 * <pre>
 *     private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
 *     "classpath:/META-INF/resources/", "classpath:/resources/",
 *     "classpath:/static/", "classpath:/public/" };
 * </pre>
 *
 * <a>http://localhost:8080/asserts/css/bootstrap.min.css</a>
 *
 * 欢迎首页
 *
 * @see WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#welcomePageHandlerMapping(ApplicationContext)
 * <pre>
 *     return new WelcomePageHandlerMapping(
 *         new TemplateAvailabilityProviders(applicationContext),applicationContext, getWelcomePage(),
 *                                                                 this.mvcProperties.getStaticPathPattern());
 * </pre>
 *
 * 即查找静态资源路径下的index.html文件
 * @see WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#getIndexHtml(String)
 * <pre>
 *     private Resource getIndexHtml(String location) {
 *         return this.resourceLoader.getResource(location + "index.html");
 *     }
 * </pre>
 *
 * <a>http://localhost:8080/</a>
 *
 * favicon.ico
 *
 * @see WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter.FaviconConfiguration#faviconHandlerMapping()
 * 即所有的 xx/favicon.ico 都在静态资源路径目录下找
 *
 * <a>http://localhost:8080/favicon.ico</a>
 *
 *
 *
 *
 * SpringBoot 对SpringMVC自动装配
 *
 * @see WebMvcAutoConfiguration
 *
 * @see <a>https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-spring-mvc-auto-configuration</a>
 *
 * Inclusion of ContentNegotiatingViewResolver and BeanNameViewResolver beans.
 *
 * ContentNegotiatingViewResolver : 获取容器中的所有视图解析器 选择最合适的一个
 * <pre>
 *     @Override
 * 	   protected void initServletContext(ServletContext servletContext) {
 * 		Collection<ViewResolver> matchingBeans =
 * 				BeanFactoryUtils.beansOfTypeIncludingAncestors(obtainApplicationContext(), ViewResolver.class).values();
 * 		if (this.viewResolvers == null) {
 * 			this.viewResolvers = new ArrayList<>(matchingBeans.size());
 * 			for (ViewResolver viewResolver : matchingBeans) {
 * 				if (this != viewResolver) {
 * 					this.viewResolvers.add(viewResolver);
 * 				}
 * 			}
 * 		}
 * 		else {
 * 			for (int i = 0; i < this.viewResolvers.size(); i++) {
 * 				ViewResolver vr = this.viewResolvers.get(i);
 * 				if (matchingBeans.contains(vr)) {
 * 					continue;
 * 				}
 * 				String name = vr.getClass().getName() + i;
 * 				obtainApplicationContext().getAutowireCapableBeanFactory().initializeBean(vr, name);
 * 			}
 *
 * 		}
 * 		AnnotationAwareOrderComparator.sort(this.viewResolvers);
 * 		this.cnmFactoryBean.setServletContext(servletContext);
 * 	   }
 * </pre>
 *
 * <pre>
 *     RequestAttributes attrs = RequestContextHolder.getRequestAttributes();
 * 		Assert.state(attrs instanceof ServletRequestAttributes, "No current ServletRequestAttributes");
 * 		List<MediaType> requestedMediaTypes = getMediaTypes(((ServletRequestAttributes) attrs).getRequest());
 * 		if (requestedMediaTypes != null) {
 * 			List<View> candidateViews = getCandidateViews(viewName, locale, requestedMediaTypes);
 * 			View bestView = getBestView(candidateViews, requestedMediaTypes, attrs);
 * 			if (bestView != null) {
 * 				return bestView;
 * 			}
 * 		}
 * </pre>
 * @see ContentNegotiatingViewResolver#getBestView(List, List, RequestAttributes)
 *
 * Support for serving static resources, including support for WebJars (covered later in this document)).
 *
 * Automatic registration of Converter, GenericConverter, and Formatter beans.
 * 类型转换和格式化
 *
 * Support for HttpMessageConverters (covered later in this document).
 * 转换Http请求和响应 如对象转换Json
 *
 * Automatic registration of MessageCodesResolver (covered later in this document).
 * 如错误代码
 *
 * Static index.html support.
 * Custom Favicon support (covered later in this document).
 *
 * Automatic use of a ConfigurableWebBindingInitializer bean (covered later in this document).
 * 如WebDataBinder
 *
 * --------------------------------------------------------------------------------------------------
 * tip:
 *
 * SpringBoot在自动配置时 一般先查看容器中是否有自定义的配置 使用自定义 并合并自定义和自动配置
 * 没有则使用自动配置
 *
 *
 * @auther mac
 * @date 2018-11-15
 */
@Configuration
//@EnableWebMvc 自动配置失效 需要完全自定义配置 SpringMVC
//原因: @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)标注在WebMvcAutoConfiguration类上
//即容器中不存在WebMvcConfigurationSupport时自动配置类生效
//而@EnableWebMvc会导入WebMvcConfigurationSupport
public class WebApplicationConfiguration implements WebMvcConfigurer {

    /***
     *  SpringBoot自动配置SpringMVC和自定义扩展SpringMVC配置
     *
     *  @see  WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter
     *
     *  @Configuration
     * 	@Import(EnableWebMvcConfiguration.class)
     * 	@EnableConfigurationProperties({ WebMvcProperties.class, ResourceProperties.class })
     * 	@Order(0)
     * 	public static class WebMvcAutoConfigurationAdapter
     * 			implements WebMvcConfigurer, ResourceLoaderAware {
     * 		......
     * 	}
     *
     *
     * @Import(EnableWebMvcConfiguration.class)
     *
     * @see DelegatingWebMvcConfiguration#setConfigurers(List)
     * <pre>
     * 	    @Autowired(required = false)
     * 	    public void setConfigurers(List<WebMvcConfigurer> configurers) {
     *       	if (!CollectionUtils.isEmpty(configurers)) {
     *     		    this.configurers.addWebMvcConfigurers(configurers);
     *          }
     * 	    }
     * </pre>
     * 容器向其中注入了全部的WebMvcConfigurer,自定义扩展的MVC配置被合并到自动配置中
     *
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginControlInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login","/asserts/**","/webjars/**","/index.html");
    }

    /**
     * @see WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#localeResolver()
     *
     * <pre>
     *      @Bean
     * 		@ConditionalOnMissingBean
     * 		@ConditionalOnProperty(prefix = "spring.mvc", name = "locale")
     * 		public LocaleResolver localeResolver() {
     * 			if (this.mvcProperties
     * 					.getLocaleResolver() == WebMvcProperties.LocaleResolver.FIXED) {
     * 				return new FixedLocaleResolver(this.mvcProperties.getLocale());
     * 			}
     * 			AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
     * 			localeResolver.setDefaultLocale(this.mvcProperties.getLocale());
     * 			return localeResolver;
     * 		}
     * </pre>
     *
     * @ConditionalOnMissingBean 按Bean搜索匹配,缺失则注册生效
     * Bean条件匹配注册
     * @see org.springframework.boot.autoconfigure.condition.OnBeanCondition#getMatchingBeans(ConditionContext, OnBeanCondition.BeanSearchSpec)
     *
     * 条件匹配日志:
     * Condition OnBeanCondition on org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration$WebMvcAutoConfigurationAdapter#localeResolver matched due to @ConditionalOnMissingBean (types: org.springframework.web.servlet.LocaleResolver; SearchStrategy: all) did not find any beans
     * 条件匹配搜索策略
     * <pre>
     *    public enum SearchStrategy {
     *
     * 	      //Search only the current context.
     *        CURRENT,
     *
     * 	      //Search all ancestors(祖先), but not the current context.
     *        ANCESTORS,
     *
     * 	      //Search the entire hierarchy.
     *        ALL
     *    }
     * </pre>
     *
     * 因此这里注册自定义LocaleResolver时不能写为
     * <pre>
     *     public SimpleCustomizeLocaleResolver simpleCustomizeLocaleResolver()
     *     或
     *     public LocaleResolver simpleCustomizeLocaleResolver()
     * </pre>
     *
     * @return
     */
    /*@Bean
    public LocaleResolver localResolver(){
        return new SimpleCustomizeLocaleResolver();
    }*/
}
