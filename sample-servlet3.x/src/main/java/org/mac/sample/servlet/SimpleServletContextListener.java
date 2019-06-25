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

package org.mac.sample.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Servlet context listener
 *
 * @auther mac
 * @date 2018-11-04
 */
public class SimpleServletContextListener implements ServletContextListener {

     public void contextInitialized(ServletContextEvent sce) {
         System.out.println("Simple servlet contextListener context initialized run...");
     }


    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Simple servlet contextListener context destroyed run...");
    }

}
