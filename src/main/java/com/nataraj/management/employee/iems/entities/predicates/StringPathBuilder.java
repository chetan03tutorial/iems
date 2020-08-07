package com.nataraj.management.employee.iems.entities.predicates;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

import java.util.Optional;

public class StringPathBuilder {

    public static <T> Optional<BooleanExpression> buildExpression(Class<T> t, PredicateBuilder.SearchCritera searchCritera){
        PathBuilder<T> pathBuilder = new PathBuilder<T>(t, "e");
        StringPath path = pathBuilder.getString(searchCritera.getSearchKey());
        if (searchCritera.getOperation().equalsIgnoreCase(":")) {
            return Optional.of(path.containsIgnoreCase(searchCritera.getSearchValue()));
        }
        return Optional.empty();
    }

}
