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

package org.mac.sample.spring.annotation.extension.service;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.EventListenerMethodProcessor;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;

/**
 * service
 *
 * @auther mac
 * @date 2018-10-28
 */
@Service
public class SimpleApplicationService {
    /**
     * @see EventListenerMethodProcessor
     * @see SmartInitializingSingleton
     *
     * @see {@link EventListenerMethodProcessor#afterSingletonsInstantiated()}
     * <pre>
     *     Method methodToUse = AopUtils.selectInvocableMethod(method, context.getType(beanName));
     *
     *     ApplicationListener<?> applicationListener =
     *     factory.createApplicationListener(beanName, targetType, methodToUse);
     *
     *     if (applicationListener instanceof ApplicationListenerMethodAdapter) {
     *         ((ApplicationListenerMethodAdapter) applicationListener).init(context, this.evaluator);
     *     }
     *     context.addApplicationListener(applicationListener);
     * </pre>
     *
     * SmartInitializingSingleton 实现流程
     *
     * 1.容器初始化后初始化所有的单实例Bean对象
     *
     * @see AbstractApplicationContext#refresh()
     * // Instantiate all remaining (non-lazy-init) singletons.
     * finishBeanFactoryInitialization(beanFactory);
     *
     * 2.初始化Bean对象 执行afterSingletonsInstantiated()
     * @see DefaultListableBeanFactory#preInstantiateSingletons()
     *
     *
     * @param event
     */
    @EventListener(classes = {ApplicationEvent.class})
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("在普通的业务组件中通过 @EventListener 注解实现事件监听:"+event);
    }
}
