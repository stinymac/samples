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

package org.mac.sample.groovy.basic_grammar
/**-----------------same--------------------*/

//相同的包处理机制（包括包的声明和 import 语句）
//类和方法的定义（嵌套类除外）
//控制结构语句【for（init;test;inc）循环除外】
//操作符、表达式和赋值
//异常处理
//变量声明（也有一些不同）
//对象实例化，引用和取消引用对象，方法调用

/**-----------------different--------------------*/

//通过新的表达式和操作符访问 Java 对象
//多种途径声明对象
//提供新的控制结构来进行流程控制
//引入新的数据类型和相应的操作符与表达式
//把所有事物都看成对象来处理

/*
 * Groovy 中方法调用的圆括号是可选的，这是建立在消除模棱两可的情况和 groovy 语
 * 言规范中概述优先处理规则上的。
 */
URLEncoder.encode 'a b','UTF-8'

/*
 * Groovy 自动导入以下包和类：
 *  groovy.lang.*
 *  groovy.util.*
 *  java.lang.*
 *  java.util.*
 *  java.net.*
 *  java.io.*
 *  java.math.BigInteger
 *  java.math.BigDecimal
 */

