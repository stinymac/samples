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

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * A bean
 *
 * @auther mac
 * @date 2018-10-08
 */
public class Cat implements ApplicationContextAware,BeanNameAware,EmbeddedValueResolverAware {
    @Value("${cat.name}")
    private String name;
    @Value("${cat.age}")
    private Integer age;

    private ApplicationContext applicationContext;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @PostConstruct
    public void init(){
        System.out.println("->Cat initialize");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("->Cat destroy");
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("当前bean的名字："+name);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ioc 容器："+applicationContext);
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        String resolveStringValue = resolver.resolveStringValue("${os.name}, #{10*18}");
        System.out.println("解析的字符串："+resolveStringValue);
    }
}
