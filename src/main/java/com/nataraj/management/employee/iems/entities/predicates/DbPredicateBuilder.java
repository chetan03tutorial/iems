package com.nataraj.management.employee.iems.entities.predicates;

import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.Optional;

@FunctionalInterface
public interface DbPredicateBuilder {
    public <T> Optional<BooleanExpression> apply(Class<T> t, PredicateBuilder.SearchCritera searchCritera);
}
