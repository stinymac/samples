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

package org.mac.sample.spring.annotation.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * aspect
 *
 * From apereo/cas
 * @date 2018-10-10
 */
@Aspect
public class LogAspect {
    @Around("(execution (public * org.mac.sample.spring.annotation.aop.service.SimpleService.*(..)))")
    public Object traceMethod(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object returnVal = null;
        final Logger log = getLog(proceedingJoinPoint);
        final String methodName = proceedingJoinPoint.getSignature().getName();

        try {
            if (log.isTraceEnabled()) {
                final Object[] args = proceedingJoinPoint.getArgs();
                final String arguments;
                if (args == null || args.length == 0) {
                    arguments = "";
                } else {
                    arguments = Arrays.deepToString(args);
                }
                log.trace("Entering method [" + methodName + " with arguments [" + arguments + "]");
            }
            returnVal = proceedingJoinPoint.proceed();
            return returnVal;
        } finally {
            if (log.isTraceEnabled()) {
                log.trace("Leaving method [" + methodName + "] with return value [" + (returnVal != null ? returnVal.toString() : "null") + "].");
            }
        }
    }

    protected Logger getLog(final JoinPoint joinPoint) {
        final Object target = joinPoint.getTarget();

        if (target != null) {
            return LoggerFactory.getLogger(target.getClass());
        }

        return LoggerFactory.getLogger(getClass());
    }
}
