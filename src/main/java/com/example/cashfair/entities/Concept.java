package com.example.cashfair.entities;

import java.util.ArrayList;

public class Concept {
    public ArrayList<Person> ListPerson;
    public double money;
    public String currency;
    public String date;

    public Concept(ArrayList<Person> ListPerson, double money, String currency, String date) {
        this.ListPerson = ListPerson;
        this.money = money;
        this.currency = currency;
        this.date = date;
    }

    public ArrayList<Person> getListPerson() {
        return ListPerson;
    }

    public String getDate() {
        return date;
    }

    public void removePerson(int i) {
        ListPerson.remove(i);
    }
}
