package org.mac.sample.corejava.pattern.specification;

public class NotQueryCriteria<T> extends CompositeQueryCriteria<T>{

    private QueryCriteria<T> not;

    public NotQueryCriteria(QueryCriteria<T> not) {
        this.not = not;
    }

    @Override
    public Boolean isMatched(T o) {
        return not.isMatched(o);
    }
}
