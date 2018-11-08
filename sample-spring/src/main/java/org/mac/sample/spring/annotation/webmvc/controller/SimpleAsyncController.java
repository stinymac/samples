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

package org.mac.sample.spring.annotation.webmvc.controller;

import org.mac.sample.spring.annotation.webmvc.component.MockMessageQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;

/**
 * Spring MVC 异步处理
 *
 * @auther mac
 * @date 2018-11-07
 */
@Controller
public class SimpleAsyncController {

    /***
     * 异步启动 response 保持
     *
     * 异步完成 SpringMVC 重新派发请求 恢复处理
     *
     * http-bio-8080-exec-2 process request at:1541600754778
     * http-bio-8080-exec-2 process request over at:1541600754778
     *
     * 2次拦截
     * --> SimpleInterceptor#preHandle()...
     * async:MvcAsync1 process start at:1541600754786
     * MvcAsync1 processing...
     * async:MvcAsync1 process end at:1541600759787
     *
     * --> SimpleInterceptor#preHandle()...
     * SimpleInterceptor#postHandle()...
     * SimpleInterceptor#afterCompletion()...
     *
     * 异步拦截器
     * Servlet API : AsyncListener
     * SpringMVC:AsyncHandlerInterceptor
     *
     * @return
     */
    @Autowired
    private MockMessageQueue mockMessageQueue;

    @RequestMapping("/async/callable")
    @ResponseBody
    public Callable<String> doAsync() {
        System.err.println(Thread.currentThread().getName() + " process request at:"+System.currentTimeMillis());
        Callable<String> callable = ()->{
            System.err.println("async:" + Thread.currentThread().getName() + " process start at:"+System.currentTimeMillis());
            mockLongTimeCostBusiness();
            System.err.println("async:" + Thread.currentThread().getName() + " process end at:"+System.currentTimeMillis());
            return "async over.";
        };
        System.err.println(Thread.currentThread().getName() + " process request over at:"+System.currentTimeMillis());
        return callable;
    }

    private void mockLongTimeCostBusiness() {
        try {
            System.err.println(Thread.currentThread().getName()+" processing...");
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/async/order/create")
    @ResponseBody
    public DeferredResult<Object> asyncCreateOrder() {
        System.err.println(Thread.currentThread().getName() + " process request at:"+System.currentTimeMillis());

        DeferredResult<Object> deferredResult = new DeferredResult<>(5000L,"Fail");

        //模拟发送消息 通知创建订单
        mockMessageQueue.put(deferredResult);

        System.err.println(Thread.currentThread().getName() + " process request over at:"+System.currentTimeMillis());
        return  deferredResult;
    }

    /**
     * 手动发起一个请求模拟异步创建订单
     */
    @RequestMapping("/async/order/create/mock")
    @ResponseBody
    public String MockCreateOrder(){
        System.err.println(Thread.currentThread().getName() + " process order create  at:"+System.currentTimeMillis());

        DeferredResult<Object> deferredResult = null;
        do {
            deferredResult = ( DeferredResult<Object>) mockMessageQueue.take();
        } while (deferredResult == null);

        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deferredResult.setResult("success");

        System.err.println(Thread.currentThread().getName() + " process order create over at:"+System.currentTimeMillis());
        return "create success";
    }


}
