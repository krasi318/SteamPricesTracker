package org.example;

import java.util.Map;

public class Main {
    static void main() {
        Map<String, Case> cases = CaseStorage.loadCases();

        cases.putIfAbsent("Kilowatt Case", new Case("Kilowatt Case"));
        cases.putIfAbsent("Recoil case", new Case("Recoil case"));
        cases.putIfAbsent("Paris 2023 Challengers Sticker Capsule", new Case("Paris 2023 Challengers Sticker Capsule"));
        cases.putIfAbsent("Revolution Case", new Case("Revolution Case"));
        cases.putIfAbsent("Fracture Case", new Case("Fracture Case"));
        cases.get("Kilowatt Case").addPrice(0.43);
        cases.get("Recoil case").addPrice(0.43);
        cases.get("Fracture Case").addPrice(0.57);
        cases.get("Revolution Case").addPrice(0.50);
        cases.get("Paris 2023 Challengers Sticker Capsule").addPrice(0.13);

        CaseStorage.saveCases(cases);

        cases.values().forEach(System.out::println);

    }
}
