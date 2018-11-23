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

package org.mac.sample.spring.boot.web.component;

import org.springframework.boot.autoconfigure.context.MessageSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 国际化 Local Resolver
 *
 * SpringBoot 国际化自动配置
 * @see MessageSourceAutoConfiguration
 *
 * <pre>
 *     @Configuration
 *     @ConditionalOnMissingBean(value = MessageSource.class, search = SearchStrategy.CURRENT)
 *     @AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
 *     @Conditional(ResourceBundleCondition.class)
 *     @EnableConfigurationProperties
 *     public class MessageSourceAutoConfiguration {
 *         ......
 *     }
 * </pre>
 *
 * @Conditional(ResourceBundleCondition.class)
 * @see MessageSourceAutoConfiguration.ResourceBundleCondition#getMatchOutcome(ConditionContext, AnnotatedTypeMetadata)
 * @see MessageSourceAutoConfiguration.ResourceBundleCondition#getMatchOutcomeForBasename(ConditionContext, String)
 *
 * 即默认的资源文件为类路径下的message,若有通过spring.messages.basename指定文件
 * 则加载指定文件，默认或指定文件都为加载成功,返回条件结果不匹配。
 *
 * 自动配置成功，向容器中注册ResourceBundleMessageSource作为国际化资源解析的Bean
 * @see ResourceBundleMessageSource
 *
 * SpringBoot 对SpringMVC 国际化LocaleResolver的配置
 * @see WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter#localeResolver()
 * <pre>
 *     if (this.mvcProperties
 *					.getLocaleResolver() == WebMvcProperties.LocaleResolver.FIXED) {
 * 			return new FixedLocaleResolver(this.mvcProperties.getLocale());
 *	   }
 *	   AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
 *	   localeResolver.setDefaultLocale(this.mvcProperties.getLocale());
 *	   return localeResolver;
 * </pre>
 * 即默认使用AcceptHeaderLocaleResolver
 * @see  AcceptHeaderLocaleResolver#resolveLocale(HttpServletRequest)
 * 从请求头中 [Accept-Language: zh-CN,zh;q=0.9,en;q=0.8] 取值
 *
 * @auther mac
 * @date 2018-11-20
 */
@Component("localeResolver")
public class SimpleCustomizeLocaleResolver implements LocaleResolver {

    public static final String LOCALE_PARAMETER_NAME = "locale";
    public static final String LANGUAGE_COUNTRY_CODE_SPLITTER = "_";

    @Override
    public Locale resolveLocale(HttpServletRequest request) {

        String localeParameter = request.getParameter(LOCALE_PARAMETER_NAME);

        if (StringUtils.hasText(localeParameter)){

            String[] codes = localeParameter.split(LANGUAGE_COUNTRY_CODE_SPLITTER);

            if(codes != null && codes.length > 1 ) {

                String language = codes[0];
                String country = codes[1];

                return new Locale(language, country);
            }
        }
        return request.getLocale();
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        /**
         * @see org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver#setLocale(HttpServletRequest, HttpServletResponse, Locale)
         */
        throw new UnsupportedOperationException(
                "Cannot change HTTP accept header - use a different locale resolution strategy");
    }
}
