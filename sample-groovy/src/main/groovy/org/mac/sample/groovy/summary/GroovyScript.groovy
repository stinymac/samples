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

package org.mac.sample.groovy.summary
/**
 * 在没有明确编译Book类的时候使用Book类！唯一的前提是Book类
 * 的文件Book.groovy必须在类路径中，groovy运行时系统将自动找到这个文件，
 * 显式的将它编译成类
 */

Book gina = new Book('Groovy in Action')
assert gina.getTitle() == 'Groovy in Action'
assert getTitleBackwards(gina) == 'noitcA ni yvoorG'

//执行之前脚本被转换、编译和产生类

String getTitleBackwards(book) {
    title = book.getTitle()
    return title.reverse()
}