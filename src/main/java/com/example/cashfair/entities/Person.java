package com.example.cashfair.entities;

public class Person {
    public String name;
    public double porc;

    public Person(String name, double porc) {
        this.name = name;
        this.porc = porc;
    }

    public String getName() {
        return name;
    }

    public double getPorc() {
        return porc;
    }
}
