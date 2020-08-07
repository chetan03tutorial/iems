package com.nataraj.management.employee.iems.entities.predicates;

import com.nataraj.management.employee.iems.utils.DbPredicateBuilderUtil;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PredicateBuilder<T> {

    private final List<SearchCritera> criterias;
    private final Class<T> t;

    private PredicateBuilder(Class<T> t){
        criterias = new ArrayList<>();
        this.t = t;
    }

    public static <T> PredicateBuilder getPredicateBuilder(Class<T> t){
        return new PredicateBuilder(t);
    }

    public  void predicate(String key, String value,
                                String operation){
        criterias.add(new SearchCritera(key,value,operation));
    }

    private Predicate getPredicate(SearchCritera searchCritera)
    {
        String value = searchCritera.getSearchValue();
        DbPredicateBuilder builder = DbPredicateBuilderUtil.getPredicateBuilder(value);
        Optional<BooleanExpression> optional = builder.apply(t,searchCritera);
        return optional.get();
    }

    public BooleanExpression buildPredicate(){
        List<Predicate> predicates = criterias.stream().map(this::getPredicate).collect(Collectors.toList());
        BooleanExpression result = Expressions.asBoolean(true).isTrue();
        for(Predicate predicate : predicates){
            result = result.and(predicate);
        }
        return result;
    }





    public static class SearchCritera {
        private String searchKey;
        private String searchValue;
        private String operation;

        public SearchCritera(String key, String value, String operation){
            this.searchKey = key;
            this.searchValue = value;
            this.operation = operation;
        }
        public String getSearchKey() {
            return searchKey;
        }

        public void setSearchKey(String searchKey) {
            this.searchKey = searchKey;
        }

        public String getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(String searchValue) {
            this.searchValue = searchValue;
        }

        public String getOperation() {
            return operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }
    }

}
