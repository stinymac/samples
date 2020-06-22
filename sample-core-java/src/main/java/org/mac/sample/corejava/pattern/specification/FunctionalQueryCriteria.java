package org.mac.sample.corejava.pattern.specification;

import java.util.function.Function;

public class FunctionalQueryCriteria<T,R> extends CompositeQueryCriteria<T> {

    private Function <T,R> expression;

    public FunctionalQueryCriteria(Function<T, R> expression) {
        this.expression = expression;
    }

    @Override
    public Boolean isMatched(T o) {
        return (Boolean) expression.apply(o);
    }
}
