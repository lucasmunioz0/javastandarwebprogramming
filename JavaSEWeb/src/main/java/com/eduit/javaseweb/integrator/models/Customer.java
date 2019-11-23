package com.eduit.javaseweb.integrator.models;

public class Customer extends Person{
    private int idCustomer;

    public Customer(int idCustomer, int age, String name, String lastName, Phone phone, Residence residence) {
        super(age, name, lastName, phone, residence);
        this.idCustomer = idCustomer;
    }

    @Override
    public String toString() {
        return String.format("Customer: %d | %s", idCustomer, super.toString());
    }
}