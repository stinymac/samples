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

package org.mac.sample.spring.annotation.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.Arrays;

/**
 * Log aspect
 *
 * @auther mac
 * @date 2018-10-10
 */
@Aspect
public class ConsoleLogAspect {
    @Pointcut("execution(public int org.mac.sample.spring.annotation.aop.service.Calculator.*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void logMethodArguments(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName()+" ("+ Arrays.toString(joinPoint.getArgs())+")");
    }
    @After("pointCut()")
    public void logMethodAfter(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName()+" executed");
    }

    @AfterReturning(value = "pointCut()",returning = "result")
    public void logMethodReturn(JoinPoint joinPoint,Object result) {
        System.out.println(joinPoint.getSignature().getName()+" return:"+result);
    }

    @AfterThrowing(value = "pointCut()",throwing="e")
    public void logMethodThrowing(JoinPoint joinPoint,Exception e) {
        System.out.println(joinPoint.getSignature().getName()+" throw:"+e);
    }
}
