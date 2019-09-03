package org.mac.sample.groovy.closure

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

/**当只有一个参数传递给闭包的时候，这个参数的声明是可选的，魔术变量 it 代替了声明*/
def closure = {counter -> counter++}
def c = {-> it++}


class MethodClosure {
    int limit

    MethodClosure(int limit) {
        this.limit = limit
    }

    boolean validate (String value) {
        return value.length() <= limit
    }
}

MethodClosure first = new MethodClosure(6)
MethodClosure second = new MethodClosure(5)

Closure firstClosure = first.&validate

def words = ['long string','medium','short','tiny']

println words.find (firstClosure)
println words.find (second.&validate)

class MultiMethod {

    int mysteryMethod(String value) {
        return value.length()
    }

    int mysteryMethod(List list) {
        return list.size()
    }

    int mysteryMethod(int x,int y) {
        return x + y
    }
}

Closure multi = new MultiMethod().&mysteryMethod

println multi ('String value')

println multi (['one','two'])

println multi (2,4)