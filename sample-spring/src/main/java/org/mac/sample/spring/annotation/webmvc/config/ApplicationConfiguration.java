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

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 * Spring 根容器配置
 *
 * @auther mac
 * @date 2018-11-06
 */
//只不扫描Controller
@ComponentScan(
        value = {"org.mac.sample.spring.annotation.webmvc"},
        excludeFilters = {
            @ComponentScan.Filter(type = FilterType.ANNOTATION,classes = {Controller.class})
        }
)
public class ApplicationConfiguration {
}
