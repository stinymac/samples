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

package org.mac.sample.spring.annotation.extension.config;

import org.mac.sample.spring.annotation.extension.bean.Cat;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration bean
 *
 * @auther mac
 * @date 2018-10-22
 */
@Configuration
@ComponentScan({"org.mac.sample.spring.annotation.extension.processor",
                "org.mac.sample.spring.annotation.extension.listener",
                "org.mac.sample.spring.annotation.extension.service"})
public class ExtensionApplicationConfiguration {

    @Bean(initMethod = "init",destroyMethod = "destroy")
    public Cat cat() {
        return new Cat(1,"Tom");
    }
}
