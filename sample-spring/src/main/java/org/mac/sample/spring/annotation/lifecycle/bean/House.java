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

package org.mac.sample.spring.annotation.lifecycle.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * A bean
 *
 * @auther mac
 * @date 2018-10-08
 */

public class House implements InitializingBean,DisposableBean{
    /**
     * 1、@Autowired：自动注入：
     * 		1）、默认优先按照类型去容器中找对应的组件找到就赋值
     * 		2）、如果找到多个相同类型的组件，再将属性的名称作为组件的id去容器中查找
     * 		3）、@Qualifier("")：使用@Qualifier指定需要装配的组件的id，而不是使用属性名
     * 		4）、自动装配默认一定要将属性赋值好，没有就会报错；
     * 			可以使用@Autowired(required=false);
     * 		5）、@Primary：让Spring进行自动装配的时候，默认使用首选的bean；
     * 				也可以继续使用@Qualifier指定需要装配的bean的名字
     *
     * 2、Spring还支持使用@Resource(JSR250)和@Inject(JSR330)[java规范的注解]
     * 		@Resource:
     * 			可以和@Autowired一样实现自动装配功能；默认是按照组件名称进行装配的；
     * 			没有能支持@Primary功能没有支持@Autowired（required=false）;
     * 		@Inject:
     * 			需要导入javax.inject的包，和Autowired的功能一样。没有required=false的功能；
     *  @Autowired:Spring定义的； @Resource、@Inject都是java规范
     *
     * AutowiredAnnotationBeanPostProcessor:解析完成自动装配功能；
     *
     * 3、 @Autowired:构造器，参数，方法，属性；都是从容器中获取参数组件的值
     * 		1）、[标注在方法位置]：@Bean+方法参数；参数从容器中获取;默认不写@Autowired效果是一样的；都能自动装配
     * 		2）、[标在构造器上]：如果组件只有一个有参构造器，这个有参构造器的@Autowired可以省略，参数位置的组件还是可以自动从容器中获取
     * 		3）、放在参数位置：
     *
     * 4、自定义组件想要使用Spring容器底层的一些组件（ApplicationContext，BeanFactory，xxx）；
     * 		自定义组件实现xxxAware；在创建对象的时候，会调用接口规定的方法注入相关组件；Aware；
     * 		把Spring底层一些组件注入到自定义的Bean中；
     * 		xxxAware：功能使用xxxProcessor；
     * 			ApplicationContextAware <<==>> ApplicationContextAwareProcessor；
     */
    @Autowired
    private Dog dog;
    @Resource
    private Cat cat;
    @Inject
    private Car car;

    public House(Dog dog) {
        this.dog = dog;
    }

    public House() {

    }

    // bean destroy
    @Override
    public void destroy() throws Exception {
        System.out.println("->House destroy");
    }

    // bean init
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("->House initialize");
    }

    @Override
    public String toString() {
        return "House{" +
                "dog=" + dog +
                ", cat=" + cat +
                ", car=" + car +
                '}';
    }
}
