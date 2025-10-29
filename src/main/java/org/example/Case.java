package org.example;

import java.util.ArrayList;
import java.util.List;

public class Case {
    private String name;
    private List<PriceEntry> history;

    public Case(String name) {
        this.name = name;
        this.history = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<PriceEntry> getHistory() {
        return history;
    }

    public void addPrice(double price) {
        history.add(new PriceEntry(price));
    }

    public double getLatestPrice() {
        if (history.isEmpty()) return 0.0;
        return history.get(history.size() - 1).getPrice();
    }

    @Override
    public String toString() {
        return name + " | Latest: " + getLatestPrice() + "€ (" + history.size() + " records)";
    }
}
