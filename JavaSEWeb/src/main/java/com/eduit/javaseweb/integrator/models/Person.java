package com.eduit.javaseweb.integrator.models;

import com.eduit.javaseweb.utils.StringUtils;

public abstract class Person {
    private int age;
    private String name;
    private String lastName;
    private Phone phone;
    private Residence residence;

    public Person(int age, String name, String lastName, Phone phone, Residence residence) {
        this.age = age;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.residence = residence;
    }
    
    public String getFullName(){
        return StringUtils.wordToCamelCase(name).concat(", ").concat(StringUtils.wordToCamelCase(lastName));
    }

    @Override
    public String toString() {
        return String.format("%s, %s %d years old (%s) %s", name, lastName, age, phone, residence);
    }    
}