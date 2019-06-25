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

package org.mac.sample.spring.boot.log.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 使用SLF4J
 *
 * 应用程序面向SLF4J编程
 * logback 直接实现了SLF4J 导入jar可以直接使用
 *
 * 其他日志实现如log4j log4j2 JUL 则需引入桥接包适配
 *
 * @see <a>https://www.slf4j.org/images/concrete-bindings.png</a>
 *
 * 使用SLF4J统一日志(如SpringBoot2.1.0 默认统一使用SLF4J+logback)
 *
 * @see <a>https://www.slf4j.org/images/legacy.png</a>
 *
 * 1.移除其他日志的实现(如果需要只保留API)
 *
 * 2.导入SLF4J对其他日志的桥接包
 *    如在jul-to-slf4j-1.7.25.jar中
 *    org.slf4j.bridge.SLF4JBridgeHandler extends java.util.logging.Handler
 * 3.导入SLF4J+logback
 *
 * spring-boot-stater-logging-2.1.0 导入的日志依赖
 *     <dependency>
 *         <groupId>ch.qos.logback</groupId>
 *         <artifactId>logback-classic</artifactId>
 *         <version>1.2.3</version>
 *         <scope>compile</scope>
 *     </dependency>
 *     <dependency>
 *         <groupId>org.apache.logging.log4j</groupId>
 *         <artifactId>log4j-to-slf4j</artifactId>
 *         <version>2.11.1</version>
 *         <scope>compile</scope>
 *     </dependency>
 *     <dependency>
 *         <groupId>org.slf4j</groupId>
 *         <artifactId>jul-to-slf4j</artifactId>
 *         <version>1.7.25</version>
 *         <scope>compile</scope>
 *     </dependency>
 *
 * Spring Framwork自身使用的日志则是spring-jcl即Spring包装过的jcl
 * 程序中使用的是
 * private static final Log logger = LogFactory.getLog(*.class);
 * 但是Spring通过编程实现了日志框架的适配
 * @see org.apache.commons.logging.LogFactory#getLog(Class)
 * <pre>
 *     return LogAdapter.createLog(name);
 * </pre>
 *
 * @see org.apache.commons.logging.LogAdapter
 * <pre>
 *   static {
 *       ClassLoader cl = LogAdapter.class.getClassLoader();
 *       try {
 *           // Try Log4j 2.x API
 *           Class.forName("org.apache.logging.log4j.spi.ExtendedLogger", false, cl);
 *           logApi = LogApi.LOG4J;
 *       }
 *       catch (ClassNotFoundException ex1) {
 *           try {
 *               // Try SLF4J 1.7 SPI
 *               Class.forName("org.slf4j.spi.LocationAwareLogger", false, cl);
 *               logApi = LogApi.SLF4J_LAL;
 *           }
 *           catch (ClassNotFoundException ex2) {
 *               try {
 *                   // Try SLF4J 1.7 API
 *                   Class.forName("org.slf4j.Logger", false, cl);
 *                   logApi = LogApi.SLF4J;
 *               }
 *               catch (ClassNotFoundException ex3) {
 *                   // Keep java.util.logging as default
 *               }
 *           }
 *       }
 *   }
 * </pre>
 *
 * 即在当前环境中(存在Log4j 2.11.1 API)Spring框架自身调用日志的流程为
 * jcl->log4j2 API->log4j-to-slf4j->slf4j->logback
 *
 * 切换日志框架
 *
 * slf4j+log4j的方式
 * <dependency>
 *     <groupId>org.springframework.boot</groupId>
 *     <artifactId>spring‐boot‐starter‐web</artifactId>
 *     <exclusions>
 *         <exclusion>
 *             <artifactId>logback‐classic</artifactId>
 *             <groupId>ch.qos.logback</groupId>
 *         </exclusion>
 *     </exclusions>
 * </dependency>
 *
 * <dependency>
 *     <groupId>org.slf4j</groupId>
 *     <artifactId>slf4j‐log4j12</artifactId>
 * </dependency>
 *
 * 切换为log4j2
 * <dependency>
 *    <groupId>org.springframework.boot</groupId>
 *    <artifactId>spring‐boot‐starter‐web</artifactId>
 *    <exclusions>
 *        <exclusion>
 *           <artifactId>spring‐boot‐starter‐logging</artifactId>
 *           <groupId>org.springframework.boot</groupId>
 *        </exclusion>
 *    </exclusions>
 * </dependency>
 *
 * <dependency>
 *    <groupId>org.springframework.boot</groupId>
 *    <artifactId>spring‐boot‐starter‐log4j2</artifactId>
 * </dependency>
 *
 *
 * @auther mac
 * @date 2018-11-13
 */
@Service
public class BizService {

    private static final Logger logger = LoggerFactory.getLogger(BizService.class);

    public void doSomething(){
        logger.trace("trace...");
        logger.debug("debug...");
        logger.info("info...");
        logger.warn("warn...");
        logger.error("error...");
    }
}
