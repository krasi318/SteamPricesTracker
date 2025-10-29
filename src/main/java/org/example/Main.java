package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Steam Case Price Tracker ===\n");

        // Load saved cases from cases.json (if exists)
        Map<String, Case> cases = CaseStorage.loadCases();

        // Make sure all selected enums exist in the map
        for (SteamCase sc : SteamCase.values()) {
            cases.putIfAbsent(sc.getMarketName(), new Case(sc.getMarketName()));
        }

        // Fetch prices for each case
        for (SteamCase sc : SteamCase.values()) {
            Double price = SteamAPI.fetchCasePrice(sc.getMarketName());
            if (price != null) {
                Case c = cases.get(sc.getMarketName());
                c.addPrice(price);
                System.out.println("✅ " + sc.getMarketName() + ": " + price + "€");
            } else {
                System.out.println("❌ Failed to fetch " + sc.getMarketName());
            }
        }

        // Save updated cases back to file
        CaseStorage.saveCases(cases);

        System.out.println("\n✅ All prices updated and saved to cases.json!");
        List<String> caseNames = new ArrayList<>(cases.keySet());
        System.out.println("\nWhich case do you want to view?");
        for (int i = 0; i < caseNames.size(); i++) {
            System.out.println((i + 1) + ". " + caseNames.get(i));
        }

        System.out.print("\nEnter number: ");
        Scanner scanner = new Scanner(System.in);
        int choice;

        try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > caseNames.size()) {
                System.out.println("Invalid number!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please type a number.");
            return;
        }

        String selectedName = caseNames.get(choice - 1);
        Case selectedCase = cases.get(selectedName);

        System.out.println("\nShowing chart for: " + selectedName + " 📈");
        ChartGenerator.showCaseChart(selectedCase);

    }
}
