package org.mac.sample.corejava.pattern.specification;

public class OrQueryCriteria<T> extends CompositeQueryCriteria<T>{

    private QueryCriteria<T> left;
    private QueryCriteria<T> right;

    public OrQueryCriteria(QueryCriteria<T> left, QueryCriteria<T> right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Boolean isMatched(T o) {
        return left.isMatched(o) || right.isMatched(o);
    }
}
