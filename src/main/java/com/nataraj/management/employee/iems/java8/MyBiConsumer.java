package com.nataraj.management.employee.iems.java8;

@FunctionalInterface
public interface MyBiConsumer<T,U> {
    public void consume(T t, U u);
}
