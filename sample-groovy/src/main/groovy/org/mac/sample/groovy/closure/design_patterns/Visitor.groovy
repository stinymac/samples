package org.mac.sample.groovy.closure.design_patterns

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

class Drawing {
    List shapes

    def accept(Closure yield) {
        shapes.each {it.accept(yield)}
    }
}
class Shape {
    def accept (Closure yield) {
        yield.call(this)
    }
}
class Square extends Shape {
    def width

    def area () {
        width**2
    }
}

class Circle extends Shape {
    def radius

    def area() {
        Math.PI * radius ** 2
    }
}

def picture = new Drawing(shapes: [new Square(width:1),new Circle(radius:1)])

def total = 0

picture.accept {total += it.area()}
println total

picture.accept {println it.class.name + ":" + it.area()}