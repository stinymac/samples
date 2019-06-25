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

package org.mac.sample.spring.annotation.webmvc.config;

import org.mac.sample.spring.annotation.webmvc.interceptor.SimpleInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringMVC容器的配置
 *
 * @auther mac
 * @date 2018-11-06
 */
//只扫描Controller
@ComponentScan(
    value = {"org.mac.sample.spring.annotation.webmvc"},
    includeFilters = {
        @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
    },
    useDefaultFilters = false
)
@EnableWebMvc
public class SpringMVCApplicationConfiguration implements WebMvcConfigurer{
    // Ctrl + o
    /**
     * 配置视图
     *
     * @param registry
     */
    public void configureViewResolvers(ViewResolverRegistry registry) {
        //jsp("/WEB-INF/", ".jsp");
        registry.jsp();
    }

    /**
     * 配置静态资源处理
     *
     * @param configurer
     */
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    /**
     * 配置拦截器
     *
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SimpleInterceptor()).addPathPatterns("/**");
    }
}
