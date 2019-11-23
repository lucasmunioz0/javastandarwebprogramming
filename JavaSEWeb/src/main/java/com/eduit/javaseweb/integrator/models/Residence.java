package com.eduit.javaseweb.integrator.models;

public class Residence {
    private int id;
    private String street;
    private String locality;
    private int number;

    public Residence(int id, String street, String locality, int number) {
        this.id = id;
        this.street = street;
        this.locality = locality;
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("%s - %s %s", locality, street, number);
    }
}
