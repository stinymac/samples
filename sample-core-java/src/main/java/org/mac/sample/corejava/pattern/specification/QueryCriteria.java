package org.mac.sample.corejava.pattern.specification;

public interface QueryCriteria<T> {

    QueryCriteria<T> and(QueryCriteria<T> queryCriteria);
    QueryCriteria<T> or(QueryCriteria<T> queryCriteria);
    QueryCriteria<T> not(QueryCriteria<T> queryCriteria);

    Boolean isMatched(T o);
}
