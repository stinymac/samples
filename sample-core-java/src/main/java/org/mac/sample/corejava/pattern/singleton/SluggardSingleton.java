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

package org.mac.sample.corejava.pattern.singleton;

/**
 *
 * @author Mac
 * @create 2018-05-21 15:11
 **/

public class SluggardSingleton {
    private static  SluggardSingleton INSTANCE ;

    private SluggardSingleton(){

    }

    public synchronized static SluggardSingleton getInstance(){
        if (null == INSTANCE) {
            INSTANCE = new SluggardSingleton();
        }
        return INSTANCE;
    }
}
