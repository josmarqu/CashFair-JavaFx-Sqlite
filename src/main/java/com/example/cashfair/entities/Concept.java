package com.example.cashfair.entities;

import java.util.ArrayList;

public class Concept {
    public ArrayList<Contributor> ListContributors;
    public String currency;
    public String date;

    public String conceptName;

    public Concept(ArrayList<Contributor> ListContributors,  String currency, String date, String conceptName) {
        this.ListContributors= ListContributors;
        this.currency = currency;
        this.date = date;
        this.conceptName = conceptName;
    }

    public ArrayList<Contributor> getListContributor() {
        return ListContributors;
    }
    public String getDate() {
        return date;
    }
    public String getConceptName() {
        return conceptName;
    }

    public ArrayList<Contributor> getListContributors() {
        return ListContributors;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Concept{" +
                "ListContributor=" + ListContributors +
                ", currency='" + currency + '\'' +
                ", date='" + date + '\'' +
                ", conceptName='" + conceptName + '\'' +
                '}';
    }

    public void removePerson(int i) {
        ListContributors.remove(i);
    }
}
