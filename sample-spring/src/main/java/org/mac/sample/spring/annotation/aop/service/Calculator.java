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

package org.mac.sample.spring.annotation.aop.service;

import org.springframework.stereotype.Service;

/**
 * Calculator service
 *
 * @auther mac
 * @date 2018-10-10
 */
@Service
public class Calculator {

    public int add(int i,int j){
        return i+j;
    }

    public int div(int i,int j){
        return i/j;
    }
}
