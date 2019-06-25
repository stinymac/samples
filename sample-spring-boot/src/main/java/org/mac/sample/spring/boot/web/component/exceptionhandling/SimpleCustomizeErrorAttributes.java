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

package org.mac.sample.spring.boot.web.component.exceptionhandling;

import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * SpringBoot 的WEB错误处理
 *
 * @see ErrorMvcAutoConfiguration
 * @see BasicErrorController
 *
 * 操作的数据都取自ErrorAttributes,默认为DefaultErrorAttributes
 * 类的实例,因此重写DefaultErrorAttributes可以实现自定义的错误信息
 *
 * @auther mac
 * @date 2018-11-24
 */
@Component
public class SimpleCustomizeErrorAttributes extends DefaultErrorAttributes {

    public static final String EXT_ERROR_ATTRIBUTES_NAME = "ext";

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {

        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);

        Map<String,Object> ext = (Map<String, Object>) webRequest.getAttribute(EXT_ERROR_ATTRIBUTES_NAME, RequestAttributes.SCOPE_REQUEST);
        errorAttributes.put(EXT_ERROR_ATTRIBUTES_NAME,ext);

        return errorAttributes;
    }
}
