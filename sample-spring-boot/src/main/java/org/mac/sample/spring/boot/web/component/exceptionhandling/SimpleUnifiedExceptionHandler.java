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

package org.mac.sample.spring.boot.web.component.exceptionhandling;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.DefaultErrorViewResolver;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * SpringBoot 异常处理方式
 *
 *
 * 默认情况下
 *
 * 浏览器访问返回Whitelabel Error Page 页面
 * 其他客户端访问返回json格式的错误信息
 *
 * @see ErrorMvcAutoConfiguration
 *
 * 其注册了如下4个组件到容器
 * <pre>
 *  @Bean
 * 	@ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
 * 	public DefaultErrorAttributes errorAttributes() {
 * 		return new DefaultErrorAttributes(
 * 				this.serverProperties.getError().isIncludeException());
 * 	}
 *
 * 	@Bean
 * 	@ConditionalOnMissingBean(value = ErrorController.class, search = SearchStrategy.CURRENT)
 * 	public BasicErrorController basicErrorController(ErrorAttributes errorAttributes) {
 * 		return new BasicErrorController(errorAttributes, this.serverProperties.getError(),
 * 				this.errorViewResolvers);
 * 	}
 *
 * 	@Bean
 * 	public ErrorPageCustomizer errorPageCustomizer() {
 * 		return new ErrorPageCustomizer(this.serverProperties, this.dispatcherServletPath);
 * 	}
 *
 * 	@Bean
 * 	@ConditionalOnBean(DispatcherServlet.class)
 * 	@ConditionalOnMissingBean
 * 	public DefaultErrorViewResolver conventionErrorViewResolver() {
 * 			return new DefaultErrorViewResolver(this.applicationContext,
 * 					this.resourceProperties);
 * 	}
 * </pre>
 *
 * @see DefaultErrorAttributes
 * 错误相关信息
 * @see DefaultErrorAttributes#getErrorAttributes(WebRequest, boolean)
 * <pre>
 *     errorAttributes.put("timestamp", new Date());
 *
 *     if (status == null) {
 * 			errorAttributes.put("status", 999);
 * 			errorAttributes.put("error", "None");
 * 			return;
 * 		}
 * 		errorAttributes.put("status", status);
 * 		try {
 * 			errorAttributes.put("error", HttpStatus.valueOf(status).getReasonPhrase());
 * 		}
 * 		catch (Exception ex) {
 * 			// Unable to obtain a reason
 * 			errorAttributes.put("error", "Http Status " + status);
 * 		}
 *
 * 	    errorAttributes.put("exception", error.getClass().getName());
 *
 * 	    errorAttributes.put("message",
 * 					StringUtils.isEmpty(message) ? "No message available" : message);
 * 		//JSR303
 * 		errorAttributes.put("errors", result.getAllErrors());
 * 			errorAttributes.put("message",
 * 					"Validation failed for object='" + result.getObjectName()
 * 							+ "'. Error count: " + result.getErrorCount());
 * </pre>
 *
 * @see BasicErrorController
 * <pre>
 *     @Controller
 *     @RequestMapping("${server.error.path:${error.path:/error}}")
 *     public class BasicErrorController extends AbstractErrorController {
 *         ......
 *     }
 * </pre>
 * 即默认/error的处理器
 *
 * 处理响应浏览器错误
 * @see BasicErrorController#errorHtml(HttpServletRequest, HttpServletResponse)
 * <pre>
 *  @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
 * 	public ModelAndView errorHtml(HttpServletRequest request,
 * 			HttpServletResponse response) {
 * 		HttpStatus status = getStatus(request);
 * 		Map<String, Object> model = Collections.unmodifiableMap(getErrorAttributes(
 * 				request, isIncludeStackTrace(request, MediaType.TEXT_HTML)));
 * 		response.setStatus(status.value());
 * 		ModelAndView modelAndView = resolveErrorView(request, response, status, model);
 * 		return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
 * 	}
 * </pre>
 * @see AbstractErrorController#resolveErrorView(HttpServletRequest, HttpServletResponse, HttpStatus, Map)
 * <pre>
 *     for (ErrorViewResolver resolver : this.errorViewResolvers) {
 * 			ModelAndView modelAndView = resolver.resolveErrorView(request, status, model);
 * 			if (modelAndView != null) {
 * 				return modelAndView;
 * 			}
 * 		}
 * 		return null;
 * </pre>
 * 即遍历全部的errorViewResolver 解析返回 自动配置的errorViewResolver为DefaultErrorViewResolver
 * @see ErrorMvcAutoConfiguration.DefaultErrorViewResolverConfiguration
 * <pre>
 *     @Bean
 * 		@ConditionalOnBean(DispatcherServlet.class)
 * 		@ConditionalOnMissingBean
 * 		public DefaultErrorViewResolver conventionErrorViewResolver() {
 * 			return new DefaultErrorViewResolver(this.applicationContext,
 * 					this.resourceProperties);
 * 		}
 * </pre>
 * @see DefaultErrorViewResolver#resolveErrorView(HttpServletRequest, HttpStatus, Map)
 *
 * <pre>
 *  static {
 * 		Map<Series, String> views = new EnumMap<>(Series.class);
 * 		views.put(Series.CLIENT_ERROR, "4xx");
 * 		views.put(Series.SERVER_ERROR, "5xx");
 * 		SERIES_VIEWS = Collections.unmodifiableMap(views);
 * 	}
 *
 *
 * 	ModelAndView modelAndView = resolve(String.valueOf(status.value()), model);
 * 	if (modelAndView == null && SERIES_VIEWS.containsKey(status.series())) {
 * 		modelAndView = resolve(SERIES_VIEWS.get(status.series()), model);
 * 	}
 * 	return modelAndView;
 * </pre>
 * @see DefaultErrorViewResolver#resolve(String, Map)
 * @see DefaultErrorViewResolver#resolveResource(String, Map)
 *
 * 即模板引擎可用取模板路径下error路径下的错误页面模板
 * 否则错静态资源下查找对应的 errorViewName(即SERIES_VIEWS.get(status.series()状态码).html文件
 *
 * 若以上两处都没有返回null
 * <pre>
 *     return (modelAndView != null) ? modelAndView : new ModelAndView("error", model);
 * </pre>
 *
 * @see ErrorMvcAutoConfiguration.WhitelabelErrorViewConfiguration
 * <pre>
 *     @Bean(name = "error")
 * 	   @ConditionalOnMissingBean(name = "error")
 * 	   public View defaultErrorView() {
 * 			return this.defaultErrorView;//StaticView
 * 	   }
 * </pre>
 * @see ErrorMvcAutoConfiguration.StaticView
 *
 * 处理响应JSON格式错误
 * @see BasicErrorController#error(HttpServletRequest)
 * <pre>
 *  @RequestMapping
 * 	public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
 * 		Map<String, Object> body = getErrorAttributes(request,
 * 				isIncludeStackTrace(request, MediaType.ALL));
 * 		HttpStatus status = getStatus(request);
 * 		return new ResponseEntity<>(body, status);
 * 	}
 * </pre>
 *
 * @see ErrorMvcAutoConfiguration.ErrorPageCustomizer
 *   其向容器中注册错误页面
 *   @see ErrorMvcAutoConfiguration.ErrorPageCustomizer#registerErrorPages(ErrorPageRegistry)
 *   <pre>
 *       ErrorPage errorPage = new ErrorPage(this.dispatcherServletPath
 * 					.getRelativePath(this.properties.getError().getPath()));
 * 			errorPageRegistry.addErrorPages(errorPage);
 *
 *       @see ErrorProperties
 *
 *       <pre>
 *           @Value("${error.path:/error}")
 * 	         private String path = "/error";
 * 	     </pre>
 *   </pre>
 *
 *
 * @see DefaultErrorViewResolver
 *
 * @auther mac
 * @date 2018-11-24
 */
@ControllerAdvice
public class SimpleUnifiedExceptionHandler {

    /**
     * 浏览器即其他客户端都返回JSON数据
     *
     * @param e
     * @return
     */
   /* @ResponseBody
    @ExceptionHandler(Exception.class)
    public Map<String,Object> handleException(Exception e) {

        Map<String,Object> errors = new HashMap<>();
        errors.put("code","");
        errors.put("message",e.getMessage());

        return  errors;
    }*/


    @ExceptionHandler(Exception.class)
    public String handleException(HttpServletRequest request,Exception e) {

        Map<String,Object> ext = new LinkedHashMap<>();
        ext.put("code","0000");
        ext.put("message",e.getMessage());

        /** @see SimpleCustomizeErrorAttributes#getErrorAttributes(WebRequest, boolean)  */
        request.setAttribute(SimpleCustomizeErrorAttributes.EXT_ERROR_ATTRIBUTES_NAME,ext);

        /**
         * 设置状态码
         * @see DefaultErrorAttributes#addStatus(Map, RequestAttributes)
         *
         * SpringBoot 才能以状态码解析到对应错误的视图模板名或静态错误错误页
         */
        request.setAttribute("javax.servlet.error.status_code",HttpStatus.INTERNAL_SERVER_ERROR.value());
        // 转发给SpringBoot的错误处理流程处理,错误响应自适应
        return  "forward:/error";
    }
}