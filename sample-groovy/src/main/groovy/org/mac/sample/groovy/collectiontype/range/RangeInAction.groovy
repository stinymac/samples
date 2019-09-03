package org.mac.sample.groovy.collectiontype.range

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

class WeekDay implements Comparable{
    
    static final DAYS = ['Sun','Mon','Tue','Wed','Thu','Fri','Sat']

    private int index = 0

    WeekDay (String day) {
        index = DAYS.indexOf(day)
    }

    WeekDay next() {
        return new WeekDay(DAYS[(index+1) % DAYS.size()])
    }

    WeekDay previous() {
        return new WeekDay(DAYS[index - 1])
    }

    @Override
    int compareTo(Object o) {
        return this.index <=> o.index
    }
    String toString(){
       return DAYS[index]
    }
}

def mon = new WeekDay('Mon')
def fri = new WeekDay('Fri')

def workLog = ''

for (day in mon..fri) {
    workLog += day.toString() + ' '
}

println workLog