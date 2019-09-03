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

class GroovyBook {
    String title //声明一个属性
}

def groovyBook = new GroovyBook()

//通过显示的方法调用来使用属性
groovyBook.setTitle('Groovy conquers the world')
assert groovyBook.getTitle() == 'Groovy conquers the world'
//通过groovy的快捷方式来使用属性
groovyBook.title = 'Groovy in Action'
assert groovyBook.title == 'Groovy in Action'