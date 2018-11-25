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
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

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
}
