/*
 *       ___        |
 *      /_\ `*      |.===.         ,,,,,
 *     (o o)        {}o o{}       /(o o)\
 * ooO--(_)--Ooo-ooO--(_)--Ooo-ooO--(_)--Ooo-
 *
 * 虽不能至,心向往之。(Although it is not possible, the heart is longing for it.)
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
