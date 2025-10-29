package org.example;

public enum SteamCase {
    FRACTURE("Fracture Case"),
    REVOLUTION("Revolution Case"),
    RECOIL("Recoil Case"),
    KILOWATT("Kilowatt Case");

    private final String marketName;

    SteamCase(String marketName) {
        this.marketName = marketName;
    }

    public String getMarketName() {
        return marketName;
    }

    @Override
    public String toString() {
        return marketName;
    }
}
