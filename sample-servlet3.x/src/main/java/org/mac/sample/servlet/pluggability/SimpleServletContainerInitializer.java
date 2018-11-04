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

package org.mac.sample.servlet.pluggability;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

/**
 * Servlet ServletContainerInitializer
 *
 * Servlet容器启动时扫描应用中的Jar查找ServletContainerInitializer的实现
 *
 * ServletContainerInitializer的实现类在Jar的
 * META-INF/services/javax.org.mac.sample.servlet.ServletContainerInitializer文件中指定
 *
 *
 * ServletContainerInitializer 类通过 jar services API 查找。对于每一个应用，应用启动时，由容器创建一个
 * ServletContainerInitializer 实例。 框架提供的 ServletContainerInitializer 实现必须绑定在 jar 包 的
 * META-INF/services 目录中的一个叫做 javax.org.mac.sample.servlet.ServletContainerInitializer 的文件，根据 jar services API，
 * 指定 ServletContainerInitializer 的实现。
 *
 * ServletContainerInitializer 实现上的
 * HandlesTypes 注解用于表示感兴趣的一些类，它们可能指定了 HandlesTypes 的 value 中的注解（类型、方
 * 法或自动级别的注解），或者是其类型的超类继承/实现了这些类之一。无论是否设置了 metadata-complete，
 * HandlesTypes 注解将应用
 *
 * 检测一个应用的类看是否它们匹配 ServletContainerInitializer 的 HandlesTypes 指定的条件时，如果应用的
 * 一个或多个可选的 JAR 包缺失，容器可能遇到类装载问题。由于容器不能决定是否这些类型的类装载失败
 * 将阻止应用正常工作，它必须忽略它们，同时也提供一个将记录它们的配置选项。
 *
 *
 * 如果 ServletContainerInitializer 实现没有@HandlesTypes 注解，或如果没有匹配任何指定的 HandlesType，那
 * 么它会为每个应用使用 null 值的集合调用一次。这将允许 initializer 基于应用中可用的资源决定是否需要初
 * 始化 Servlet/Filter。
 *
 * 在任何 Servlet Listener 的事件被触发之前，当应用正在启动时，ServletContainerInitializer 的 onStartup 方法
 * 将被调用。
 *
 * 如果 ServletContainerInitializer 被绑定到
 * 应用的 WEB-INF/lib 目录内的一个 JAR 包中，它的onStartup 方法在绑定到的应用启动期间将被仅调用一次。
 * 如果，相反，ServletContainerInitializer 被绑定到 WEB-INF/lib 目录外的一个 JAR 包中，但仍能被运行时的
 * 服务提供商查找机制发现时，每次启动应用时，它的 onStartup 方法将被调用
 *
 * @auther mac
 * @date 2018-11-04
 */
@HandlesTypes(value = {CustomizeServlet.class})
public class SimpleServletContainerInitializer implements ServletContainerInitializer {
    /**
     *
     * @param set 容器启动 传入@HandlesTypes指定的类型的全部实现类到Set<Class<?>> set
     * @param servletContext
     * @throws ServletException
     */
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        for(Class<?> c:set){
            System.out.println("-->"+c.getName());
        }
    }
}
