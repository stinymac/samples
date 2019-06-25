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

package org.mac.sample.spring.annotation.tx.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.config.AopConfigUtils;
import org.springframework.aop.framework.autoproxy.InfrastructureAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AutoProxyRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.AbstractTransactionManagementConfiguration;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;
import org.springframework.transaction.annotation.TransactionManagementConfigurationSelector;
import org.springframework.transaction.event.TransactionalEventListenerFactory;
import org.springframework.transaction.interceptor.BeanFactoryTransactionAttributeSourceAdvisor;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.sql.DataSource;
import java.lang.reflect.Method;

/**
 * autoconfig
 *
 *
 * Spring 事务分析
 *
 * @see {@link EnableTransactionManagement}
 *
 * EnableTransactionManagement注解使用注解@Import({TransactionManagementConfigurationSelector.class})
 * @see {@link TransactionManagementConfigurationSelector#selectImports(AdviceMode)}
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
 * 默认为{@link org.springframework.context.annotation.AdviceMode#PROXY} 即使用{@link AutoProxyRegistrar}和{@link ProxyTransactionManagementConfiguration}
 *
 * AutoProxyRegistrar 向容器注册自动代理创建器
 *
 * @see {@link AutoProxyRegistrar#registerBeanDefinitions(AnnotationMetadata, BeanDefinitionRegistry)}
 *     @see {@link AopConfigUtils#registerAutoProxyCreatorIfNecessary(BeanDefinitionRegistry)}
 *
 *     <pre>
 *         registerOrEscalateApcAsRequired(InfrastructureAdvisorAutoProxyCreator.class, registry, source);
 *     </pre>
 *
 *     即向容器中注册类型为InfrastructureAdvisorAutoProxyCreator的自动代理创建器
 *         @see {@link AopConfigUtils#registerOrEscalateApcAsRequired(Class, BeanDefinitionRegistry, Object)}
 *
 *     @see {@link InfrastructureAdvisorAutoProxyCreator} 即一个SmartInstantiationAwareBeanPostProcessor (@see {@link AnnotationAwareAspectJAutoProxyCreator})
*
 * ProxyTransactionManagementConfiguration 向容器中注册
 *
 * 事务增强器transactionAdvisor(@see {@link BeanFactoryTransactionAttributeSourceAdvisor})
 * 事务属性TransactionAttributeSource(@see {@link AnnotationTransactionAttributeSource})
 * 事务拦截器TransactionInterceptor(@see {@link TransactionInterceptor})
 * TransactionalEventListener (@see {@link TransactionalEventListenerFactory})
 *
 * @see {@link AbstractTransactionManagementConfiguration}
 *
 *
 * 容器完成初始化,事务方法被代理
 *
 * 调用时事务拦截器生效
 *
 * @see {@link TransactionAspectSupport#invokeWithinTransaction(Method, Class, TransactionAspectSupport.InvocationCallback)}
 *
 *
 * @auther mac
 * @date 2018-10-18
 */

@EnableTransactionManagement
@Configuration
@ComponentScan({"org.mac.sample.spring.annotation.tx.service","org.mac.sample.spring.annotation.tx.repository"})
public class TransactionApplicationConfiguration {

    @Bean("hikariDataSource")
    public DataSource dataSource() {

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/jpa?useSSL=false");
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
