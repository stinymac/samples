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

package org.mac.sample.spring.boot.data.mybatis.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis configuration
 *
 * Spring Boot中MyBatis自动配置
 * @see MybatisAutoConfiguration
 *
 *
 * @auther mac
 * @date 2018-12-04
 */
@Configuration
@MapperScan({"org.mac.sample.spring.boot.data.mybatis.annotation","org.mac.sample.spring.boot.data.mybatis.xml"})
public class MyBatisCustomizeConfiguration {
    @Bean
    public ConfigurationCustomizer configurationCustomizer() {

        return (org.apache.ibatis.session.Configuration configuration)->
                configuration.setMapUnderscoreToCamelCase(true);
    }
}
