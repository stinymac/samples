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

package org.mac.sample.corejava.concurrency.pattern.active_object;

/**
 *
 * @author Mac
 * @create 2018-06-08 10:00
 **/

public class Servant implements ActiveObject{
    @Override
    public Result<String> buildString(char fillChar, int count) {
        char[] buff = new char[count];
        for (int i = 0; i < count; i++) {
            buff[i] = fillChar;
        }
        simulateElapsedTime (10);
        return new RealResult<String>(new String(buff));
    }

    @Override
    public void displayString(String text) {
        simulateElapsedTime (10);
        System.out.println("-> "+text);
    }

    private void simulateElapsedTime (long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
