package org.mac.examples.concurrency.pattern.active_object;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-08 9:56
 **/
public interface ActiveObject {

    public Result<String> buildString (char fillChar,int count);
    public void displayString (String text);
}
