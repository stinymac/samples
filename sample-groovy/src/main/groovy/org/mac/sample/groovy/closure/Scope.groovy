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

def x = 0

//闭包在声明的时候，绑定了本地变量的引用。
10.times {
    x++
}
assert x == 10

class Mother {
    int field = 1

    int foo() {
        return 2
    }

    Closure birth (param) {
        def local = 3

        def closure = {caller ->
            [this,field,this.foo(),local,param,caller,owner]
        }

        return closure
    }
}

Mother julia = new Mother()
println julia

closure = julia.birth(4)
println closure

println this

context = closure.call(this)

println context


def fun(n) {
    return {n += it}
}

def accumulator = fun(1)

println accumulator (2)
