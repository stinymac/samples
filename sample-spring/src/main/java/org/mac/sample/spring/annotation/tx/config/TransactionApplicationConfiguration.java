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

package org.mac.sample.spring.annotation.tx.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurationSelector;

import javax.sql.DataSource;

/**
 * config
 *
 *
 * Spring 事务分析
 *
 * @see {@link EnableTransactionManagement}
 *
 * EnableTransactionManagement注解使用注解@Import({TransactionManagementConfigurationSelector.class})
 * @see {@link TransactionManagementConfigurationSelector}
 *
 * <pre class="code">
 *     switch (adviceMode) {
 *         case PROXY:
 *             return new String[] {AutoProxyRegistrar.class.getName(),
 *                                  ProxyTransactionManagementConfiguration.class.getName()};
 *         case ASPECTJ:
 *             return new String[] {TransactionManagementConfigUtils.TRANSACTION_ASPECT_CONFIGURATION_CLASS_NAME};
 *         default:
 *             return null;
 *     }
 * </pre>
 *
 * @auther mac
 * @date 2018-10-18
 */

@EnableTransactionManagement
@Configuration
@ComponentScan({"org.mac.sample.spring.annotation.tx.service","org.mac.sample.spring.annotation.tx.service"})
public class TransactionApplicationConfiguration {

    @Bean("hikariDataSource")
    public DataSource dataSource() {

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("891028@root");
        HikariDataSource dataSource =  new HikariDataSource(hikariConfig);

        return dataSource;
    }

    @Bean
    public JdbcTemplate springJdbcTemplate(@Qualifier("hikariDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("hikariDataSource") DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
