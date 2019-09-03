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

/**========================List=================================*/

def roman = ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII']
//访问列表
assert roman[4] == 'IV'

//扩展
roman[8] = 'VIII'

assert roman.size() == 9

/**========================Ranges=================================*/
def x = 1..10

assert x.contains(5)
assert x.contains(15) == false
assert x.size() == 10
assert x.from == 1
assert x.to == 10
assert x.reverse() == 10..1

/**========================Map=================================*/

def http = [
        100 : 'CONTINUE',
        200 : 'OK',
        400 : 'BAD REQUEST'
    ]

assert http[200] == 'OK'

http[500] = 'INTERNAL SERVER ERROR'

assert http.size() == 4