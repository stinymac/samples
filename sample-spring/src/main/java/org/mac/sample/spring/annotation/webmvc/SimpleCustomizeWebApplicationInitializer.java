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

package org.mac.sample.spring.annotation.webmvc;


import org.mac.sample.spring.annotation.webmvc.config.ApplicationConfiguration;
import org.mac.sample.spring.annotation.webmvc.config.SpringMVCApplicationConfiguration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.AbstractContextLoaderInitializer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.ServletContext;


/**
 *
 * Spring MVC 注册到Servlet容器
 *
 * 根据 Servlet 3.0规范 Web容器在启动时会扫描每个Jar包下META-INF/services/javax.servlet.ServletContainerInitializer
 * 文件，若文件存在根据该文件指定的ServletContainerInitializer实现类，Web容器加载该类，执行自定义初始化逻辑。
 *
 * Spring的该文件在spring-web(spring-web-5.1.2.RELEASE.jar)包下
 * 其指定的实现类为
 * org.springframework.web.SpringServletContainerInitializer
 *
 * @see org.springframework.web.SpringServletContainerInitializer
 *
 * @HandlesTypes(WebApplicationInitializer.class)标注在SpringServletContainerInitializer
 *
 * Web容器启动传入所有的WebApplicationInitializer的实现类
 *
 * @see org.springframework.web.WebApplicationInitializer
 *
 * <pre>
 *     List<WebApplicationInitializer> initializers = new LinkedList<>();
 *
 *     if (webAppInitializerClasses != null) {
 *         for (Class<?> waiClass : webAppInitializerClasses) {
 *         // Be defensive: Some servlet containers provide us with invalid classes,
 *         // no matter what @HandlesTypes says...
 *             if (!waiClass.isInterface() && !Modifier.isAbstract(waiClass.getModifiers()) &&
 *                     WebApplicationInitializer.class.isAssignableFrom(waiClass)) {
 *                 try {
 *                     initializers.add((WebApplicationInitializer) ReflectionUtils.accessibleConstructor(waiClass).newInstance());
 *                 }
 *                 catch (Throwable ex) {
 *                     throw new ServletException("Failed to instantiate WebApplicationInitializer class", ex);
 *                 }
 *             }
 *         }
 *     }
 * </pre>
 *
 * SpringServletContainerInitializer在此依次执行执行每个WebApplicationInitializer实现类的
 * onStartup(servletContext)方法
 *
 *
 * Spring中{@link WebApplicationInitializer}的抽象实现
 *
 * @see AbstractContextLoaderInitializer
 * @see AbstractContextLoaderInitializer#registerContextLoaderListener(ServletContext)//Spring Root 容器
 *     @see AbstractDispatcherServletInitializer
 *     @see AbstractDispatcherServletInitializer#registerDispatcherServlet(ServletContext) //Spring 容器Web 注册DispatchServlet等
 *         @see AbstractAnnotationConfigDispatcherServletInitializer
 *         @see AbstractAnnotationConfigDispatcherServletInitializer#createRootApplicationContext()
 *         @see AbstractAnnotationConfigDispatcherServletInitializer#createServletApplicationContext()
 *
 * -----------------------------------------------------------------------------------------------------------------
 * tip:
 * Mechanism of Operation(运作机制)、opposed(对抗)、in combination with(与...结合) compliant(兼容)
 *
 *
 * @auther mac
 * @date 2018-11-04
 */
public class SimpleCustomizeWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 指定根容器的配置类
     *
     * @return
     */
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfiguration.class};
    }
    /**
     * 指定Spring MVC Web容器的配置类
     *
     * @return
     */
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringMVCApplicationConfiguration.class};
    }

    /**
     * 指定Servlet(即 org.springframework.web.servlet.DispatchServlet)的映射
     * @return
     */
    protected String[] getServletMappings() {
        // 拦截全部请求 如 *.js 、*.png ... 不包括 *.jsp
        return new String[]{"/"};
    }
}
