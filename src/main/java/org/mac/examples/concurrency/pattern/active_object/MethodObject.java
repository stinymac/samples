package org.mac.examples.concurrency.pattern.active_object;

/**
 * Entry class description
 *
 * @author Mac
 * @create 2018-06-08 15:00
 **/
public abstract class MethodObject {
    protected final Servant servant;
    protected final FutureResult futureResult;

    public MethodObject(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();
}
