package com.nataraj.management.employee.iems.utils;

import com.nataraj.management.employee.iems.entities.predicates.*;

import java.math.BigDecimal;

public class DbPredicateBuilderUtil {



    public static DbPredicateBuilder getPredicateBuilder(String value){
        if(org.apache.commons.lang3.math.NumberUtils.isCreatable(value)){
            BigDecimal number = new BigDecimal(value);
            try {
                int intValue = number.intValueExact();
                return IntegerPredicateBuilder::buildExpression;
            } catch (ArithmeticException e) {
                try {
                    long longValue = number.longValueExact();
                    return DoublePredicateBuilder::buildExpression;
                } catch (ArithmeticException ex) {
                    double doubleValue = number.doubleValue();
                    return LongPredicateBuilder::buildExpression;
                }
            }
        }
        return StringPathBuilder::buildExpression;
    }

    public static Class<? extends Number> convertToNumber(String value){

        if(org.apache.commons.lang3.math.NumberUtils.isCreatable(value)){
            BigDecimal number = new BigDecimal(value);
            try {
                int intValue = number.intValueExact();
                return Integer.class;
            } catch (ArithmeticException e) {
                try {
                    long longValue = number.longValueExact();
                    return Long.class;
                } catch (ArithmeticException ex) {
                    double doubleValue = number.doubleValue();
                    return Double.class;
                }
            }
        }
        return null;
    }


}
