package org.mac.sample.corejava.pattern.specification;

public abstract class CompositeQueryCriteria<T> implements QueryCriteria<T> {

    public QueryCriteria<T> and(QueryCriteria<T> queryCriteria)
    {
        return new AndQueryCriteria<T>(this, queryCriteria);
    }

    public QueryCriteria<T> or(QueryCriteria<T> queryCriteria)
    {
        return new OrQueryCriteria<T>(this, queryCriteria);
    }

    public QueryCriteria<T> not(QueryCriteria<T> queryCriteria)
    {
        return new NotQueryCriteria<T>(queryCriteria);
    }

    public abstract Boolean isMatched(T o);
}
