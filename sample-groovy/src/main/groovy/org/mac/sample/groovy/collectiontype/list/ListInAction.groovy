package org.mac.sample.groovy.collectiontype.list

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

def quickSort(List list) {
    if (list.size() < 2) return list
    def pivot = list[list.size().intdiv(2)]
    println 'pivot:'+pivot
    def left = list.findAll {item-> item < pivot}
    def middle = list.findAll {item-> item == pivot}
    def right = list.findAll {item-> item > pivot}

    return (quickSort(left) + middle + quickSort(right))
}

println quickSort([2,4,5,1,3,5,6,1112,0])