package org.example;

import java.time.LocalDate;

public class PriceEntry {
    private LocalDate date;
    private double price;

    public PriceEntry(double price) {
        this.date = LocalDate.now();
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return date + ": " + price + "€";
    }
}