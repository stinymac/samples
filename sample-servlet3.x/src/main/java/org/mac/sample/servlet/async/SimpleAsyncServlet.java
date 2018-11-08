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

package org.mac.sample.servlet.async;

import javax.servlet.AsyncContext;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Async Servlet
 *
 * @auther mac
 * @date 2018-11-04
 */
@WebServlet(value = "/async",asyncSupported = true)
public class SimpleAsyncServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp){
        System.err.println(Thread.currentThread().getName() + " process request at:"+System.currentTimeMillis());
        final AsyncContext asyncContext = req.startAsync();

        asyncContext.start(()->{
            System.err.println("async:" + Thread.currentThread().getName() + " process start at:"+System.currentTimeMillis());
            mockLongTimeCostBusiness();

            asyncContext.complete();

            ServletResponse servletResponse = req.getAsyncContext().getResponse();
            try {
                servletResponse.getWriter().write("Async response...");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.err.println("async:" + Thread.currentThread().getName() + " process end at:"+System.currentTimeMillis());
        });
        System.err.println(Thread.currentThread().getName() + " process request over at:"+System.currentTimeMillis());
    }

    private void mockLongTimeCostBusiness() {
        try {
            System.err.println(Thread.currentThread().getName()+" processing...");
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
