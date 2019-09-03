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

def adder = {x,y -> return x + y}

println adder(4,3)
println adder.call(4,3)


def benchmark(Integer repeat,Closure worker) {
    start = System.currentTimeMillis()
    repeat.times (worker)
    end = System.currentTimeMillis()

    return end - start
}

println benchmark(10000) {(int) it/2}
println benchmark(10000) {it.intdiv(2)}


def caller (Closure closure){
    closure.getParameterTypes().size()
}
println  caller { one -> }
println  caller { one, two -> }


def adderOne = adder.curry(1)

println adderOne(5)


def configurator = {format,filter,line ->
    filter(line) ? format (line) : null
}

def appender = { config,append,line ->
     def out = config(line)
     if (out) {
         append(out)
     }
}

def dateFormatter = {line -> "${new Date()}:$line"}
def debugFilter = {line -> line.contains('debug')}
def consoleAppender = {line -> println line}

def conf = configurator.curry(dateFormatter,debugFilter)
def logger = appender.curry(conf,consoleAppender)

logger('Here is some debug message')
logger('This will not be printed')