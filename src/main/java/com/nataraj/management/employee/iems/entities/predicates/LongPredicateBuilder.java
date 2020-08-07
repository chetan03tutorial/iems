package com.nataraj.management.employee.iems.entities.predicates;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;

import java.util.Optional;

public class LongPredicateBuilder {

    public static <T> Optional<BooleanExpression> buildExpression(Class<T> t, PredicateBuilder.SearchCritera searchCritera){
        PathBuilder<T> pathBuilder = new PathBuilder<T>(t, "e");
        NumberPath<Long> path = pathBuilder.getNumber(searchCritera.getSearchKey(), Long.class);
        Long value = Long.parseLong(searchCritera.getSearchValue());
        switch(searchCritera.getOperation()){
            case ":":
                return Optional.of(path.eq(value));
            case ">":
                return Optional.of(path.goe(value));
            case "<":
                return Optional.of(path.loe(value));
        }
        return Optional.empty();
    }
}
