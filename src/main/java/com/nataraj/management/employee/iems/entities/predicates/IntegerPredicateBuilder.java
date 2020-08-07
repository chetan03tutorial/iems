package com.nataraj.management.employee.iems.entities.predicates;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;

import java.util.Optional;

public class IntegerPredicateBuilder {

    public static <T> Optional<BooleanExpression> buildExpression(Class<T> t, PredicateBuilder.SearchCritera searchCritera){
        PathBuilder<T> pathBuilder = new PathBuilder<>(t, "e");
        NumberPath<Integer> path = pathBuilder.getNumber(searchCritera.getSearchKey(), Integer.class);
        int integerValue = Integer.parseInt(searchCritera.getSearchValue());
        switch(searchCritera.getOperation()){
            case ":":
                return Optional.of(path.eq(integerValue));
            case ">":
                return Optional.of(path.goe(integerValue));
            case "<":
                return Optional.of(path.loe(integerValue));
        }
        return Optional.of(Expressions.asBoolean(true).isTrue());
    }
}
