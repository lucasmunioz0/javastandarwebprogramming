package com.eduit.javaseweb.integrator.models;

public class Phone {
    private int id;
    private String type;
    private String number;

    public Phone(int id, String type, String number) {
        this.id = id;
        this.type = type;
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("%s: %s", type, number);
    }
}
