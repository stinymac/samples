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

package org.mac.sample.spring.annotation.extension.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.AbstractApplicationEventMulticaster;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

/**
 * Event listener
 *
 * @see {@link ApplicationEvent}
 *     @see {@link ApplicationContextEvent}
 *         @see {@link ContextRefreshedEvent}
 *         @see {@link ContextClosedEvent}
 *         @see {@link ContextStartedEvent}
 *         @see {@link ContextStoppedEvent}
 *    @see {@link PayloadApplicationEvent}
 *
 * 事件的发布过程
 *
 * 1.容器创建 @see {@link AbstractApplicationContext#refresh()}
 * 2.发布事件
 * @see {@link AbstractApplicationContext#finishRefresh()}
 *     @see {@link AbstractApplicationContext#publishEvent(Object, ResolvableType)}
 *
 *     // 拿到事件广播器广播事件
 *     getApplicationEventMulticaster().multicastEvent(applicationEvent, eventType);
 *     [
 *     事件广播器初始化
 *         @see {@link AbstractApplicationContext#refresh()}
 *             // 其逻辑为容器中有注册事件广播器直接使用，没有则new SimpleApplicationEventMulticaster(beanFactory)
*              // @see {@link SimpleApplicationEventMulticaster} @see {@link AbstractApplicationEventMulticaster}
 *             @see {@link AbstractApplicationContext#initApplicationEventMulticaster}
 *
 *             //将容器中所有的listener注册到事件广播器中
 *             @see {@link AbstractApplicationContext#registerListeners()}
 *     ]
 *
 *         @see {@link SimpleApplicationEventMulticaster#multicastEvent(ApplicationEvent, ResolvableType)}
 *         <pre>
 *             ResolvableType type = (eventType != null ? eventType : resolveDefaultEventType(event));
 *             for (final ApplicationListener<?> listener : getApplicationListeners(event, type)) {
 *                 // 同步或异步的执行器
 *                 Executor executor = getTaskExecutor();
 *                 if (executor != null) {
 *                     executor.execute(() -> invokeListener(listener, event));
 *                 }
 *                 else {
 *                     invokeListener(listener, event);
 *                 }
 *             }
 *         </pre>
 *
 *     执行监听方法
 *     @see {@link SimpleApplicationEventMulticaster#doInvokeListener(ApplicationListener, ApplicationEvent)}
 *
 *
 * @auther mac
 * @date 2018-10-28
 */
@Component
public class SimpleApplicationListener implements ApplicationListener<ApplicationEvent>{

    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("事件:"+event);
    }
}
