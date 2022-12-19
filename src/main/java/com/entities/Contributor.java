package com.entities;

import java.text.DecimalFormat;

public class Contributor {
    private String name;
    private double money;
    private double percentage;
    public Contributor(String name, double money, double percentage) {
        this.name = name;
        this.money = money;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "Contributor{" +
                "name='" + name + '\'' +
                ", money=" + money +
                ", percentage=" + percentage +
                '}';
    }
}
