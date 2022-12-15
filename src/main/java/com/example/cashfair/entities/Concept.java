package com.example.cashfair.entities;

import java.util.ArrayList;

public class Concept {
    public ArrayList<Contributor> ListContributor;
    public String currency;
    public String date;

    public String conceptName;

    public Concept(ArrayList<Contributor> ListContributor,  String currency, String date, String conceptName) {
        this.ListContributor= ListContributor;
        this.currency = currency;
        this.date = date;
        this.conceptName = conceptName;
    }

    public ArrayList<Contributor> getListContributor() {
        return ListContributor;
    }

    public String getDate() {
        return date;
    }

    public void removePerson(int i) {
        ListContributor.remove(i);
    }

    public String getConceptName() {
        return conceptName;
    }

    @Override
    public String toString() {
        return "Concept{" +
                "ListContributor=" + ListContributor +
                ", currency='" + currency + '\'' +
                ", date='" + date + '\'' +
                ", conceptName='" + conceptName + '\'' +
                '}';
    }
}
