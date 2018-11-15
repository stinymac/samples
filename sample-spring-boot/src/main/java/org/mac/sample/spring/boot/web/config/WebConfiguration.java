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

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/**
 * SpringBoot Web 自动装配
 *
 * @see DispatcherServletAutoConfiguration
 * @see WebMvcAutoConfiguration
 *
 * 如SpringBoot对静态资源的处理、欢迎页、favicon.ico的处理
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
 * 即所有的 xx/favicon.ico 都在静态资源文件下找
 *
 * <a>http://localhost:8080/favicon.ico</a>
 *
 * @auther mac
 * @date 2018-11-15
 */

public class WebConfiguration {
}
