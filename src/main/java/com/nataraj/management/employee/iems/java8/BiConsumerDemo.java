package com.nataraj.management.employee.iems.java8;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.function.BiConsumer;

public class BiConsumerDemo<T,U> {

    private String name;
    private int age;

    public BiConsumerDemo(){

    }

    public BiConsumerDemo(MyBiConsumer<BiConsumerDemo,String> biConsumer){

    }

    public void printNameAndAge(String name, Integer age){
        System.out.println("name is " + name + " and age is " + age);
    }

    public void printName(Integer age){
        System.out.println("Age is " + age);
    }

    public static void main(String[] args) {

        MyBiConsumer<BiConsumerDemo,String> my = (BiConsumerDemo bd ,String x) -> bd.convert(x);
        MyBiConsumer<BiConsumerDemo, String> t = BiConsumerDemo::convert;
        MyBiConsumer<StringHelper,String> myHelper = StringHelper::upperCase;
        BiConsumerDemo demo2 = new BiConsumerDemo(myHelper);
        BiConsumerDemo demo3 = new BiConsumerDemo(t);

    }

    public void convert(String value){
        System.out.println(value.toUpperCase());
    }






}
