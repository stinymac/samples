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

package org.mac.sample.spring.boot.autoconfig.config;

import org.mac.sample.spring.boot.autoconfig.component.SimpleComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * configuration
 *
 * @auther mac
 * @date 2018-11-11
 */
@Configuration
@ImportResource(locations = {"classpath:beans.xml"})
public class SampleApplicationConfiguration {
    @Bean
    public SimpleComponent simpleComponent(){
        return new SimpleComponent();
    }
}
