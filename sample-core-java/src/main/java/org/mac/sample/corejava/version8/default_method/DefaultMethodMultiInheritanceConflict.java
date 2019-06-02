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

package org.mac.sample.corejava.version8.default_method;

/**
 * 多继承 default 方法冲突
 *
 * @auther mac
 * @date 2019-06-02
 */

public class DefaultMethodMultiInheritanceConflict {
    //class 的优先级 > sub-interface > super-interface
    public static void main(String[] args) {
        C c = new C();
        c.m();
    }

    private interface A {

        default void m (){
            System.out.print("==A==");
        }
    }

    private interface B extends A {

        default void m (){
            System.out.print("==B==");
        }
    }

    private interface D {

        default void m (){
            System.out.print("==B==");
        }
    }

    private static class C implements B,A {

        public void m (){
            System.out.print("==C==");
        }
    }

    private static class E implements D,A {
        //此时必须重写
        public void m (){
            System.out.print("==C==");
        }
    }
}
