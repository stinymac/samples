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

package org.mac.sample.spring.annotation.ioc.config;

import org.mac.sample.spring.annotation.ioc.bean.AnimalFactoryBean;
import org.mac.sample.spring.annotation.ioc.bean.SimpleBean;
import org.mac.sample.spring.annotation.ioc.component.SimpleRepository;
import org.mac.sample.spring.annotation.ioc.condition.CustomImportBeanDefinitionRegistrar;
import org.mac.sample.spring.annotation.ioc.condition.CustomImportSelector;
import org.mac.sample.spring.annotation.ioc.condition.OneCondition;
import org.mac.sample.spring.annotation.ioc.condition.OtherCondition;
import org.mac.sample.spring.annotation.ioc.config.filter.CustomComponentScanFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Spring annotation context configuration
 *
 * 给容器中注册组件；
 * 1、包扫描+组件标注注解（@Controller/@Service/@Repository/@Component）[自定义的类]
 * 2、@Bean[导入第三方包的组件]
 * 3、@Import[快速给容器中导入一个组件]
 * 		1)、@Import(要导入到容器中的组件)；容器中就会自动注册这个组件，id默认是全类名
 * 		2)、ImportSelector:返回需要导入的组件的全类名数组；
 * 		3)、ImportBeanDefinitionRegistrar:手动注册bean到容器中
 * 4、使用Spring提供的 FactoryBean（工厂Bean）;
 * 		1)、默认获取到的是工厂bean调用getObject创建的对象
 * 		2)、要获取工厂Bean本身，需要给id前面加一个&
 * 			&colorFactoryBean
 * @auther mac
 * @date 2018-10-06
 */
@Configuration//@AliasFor(annotation = Component.class)
/*@ComponentScan(value = "org.mac.sample.spring.annotation.ioc.component",
        excludeFilters = {
        @Filter(type = FilterType.ANNOTATION,classes = Controller.class)
})*/
@ComponentScan(value = "org.mac.sample.spring.annotation.ioc.component",
        includeFilters = {
                @Filter(type = FilterType.ANNOTATION,classes = Service.class),
                @Filter(type = FilterType.ASSIGNABLE_TYPE,classes = SimpleRepository.class),
                @Filter(type = FilterType.CUSTOM,classes = CustomComponentScanFilter.class)
},useDefaultFilters = false)
//快速导入一个Bean到容器中 id:org.mac.sample.spring.annotation.ioc.bean.SimpleBean
//指定要导入的类或类选择器或自定义导入的类
@Import({SimpleBean.class, CustomImportSelector.class,CustomImportBeanDefinitionRegistrar.class})
public class ApplicationConfiguration {
    /**
     * SCOPE_PROTOTYPE
     * SCOPE_SINGLETON
     * SCOPE_REQUEST
     * SCOPE_SESSION
     */
    //@Scope("prototype")
    @Lazy
    @Bean
    public SimpleBean simpleBean(){
        return new SimpleBean();
    }

    @Bean("one")
    @Conditional(OneCondition.class)
    public SimpleBean oneSimpleBean(){
        return new SimpleBean(1,"Jerry");
    }

    @Bean("other")
    @Conditional(OtherCondition.class)
    public SimpleBean otherSimpleBean(){
        return new SimpleBean(1,"Tom");
    }
    @Bean
    public AnimalFactoryBean animalFactoryBean(){
        return new AnimalFactoryBean();
    }
}
