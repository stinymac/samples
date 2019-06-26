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

public class DoubleCheckLockSingleton {
    // 使用volatile 阻止重排序优化而造成的返回的实例对象使用时的NullPointException
    private static  volatile DoubleCheckLockSingleton INSTANCE ;

    private DoubleCheckLockSingleton(){

    }

    public  static DoubleCheckLockSingleton getInstance(){
        if (null == INSTANCE) {

            synchronized (DoubleCheckLockSingleton.class) {

                if (null == INSTANCE) {
                    INSTANCE = new DoubleCheckLockSingleton();
                }
            }
        }
        return INSTANCE;
    }
}
