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
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-08 15:05
 **/

public class BuildStringMethod extends MethodObject{

    private final char fillChar;
    private final int count;

    public BuildStringMethod(Servant servant, FutureResult futureResult, char fillChar, int count) {
        super(servant, futureResult);
        this.fillChar = fillChar;
        this.count = count;
    }

    @Override
    public void execute() {
        Result<String> result = servant.buildString(fillChar,count);
        futureResult.setResultValue(result.getResultValue());
    }
}
