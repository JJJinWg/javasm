package oop3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Engine jiuEngine = new Engine("E001", "JIU", 300, "Gasoline");
        Engine xEngine = new Engine("E002", "X", 350, "Diesel");
        Engine v1Engine = new Engine("E003", "V1", 400, "Hybrid");

        Car car1 = new Car("C001", "Speedster", "Blue", 180, 300, jiuEngine);
        Car car2 = new Car("C002", "Turbo", "Red", 200, 320, xEngine);
        Car car3 = new Car("C003", "Stealth", "Black", 190, 310, v1Engine);
        Car car4 = new Car("C004", "Roadster", "Yellow", 170, 290, jiuEngine);
        Car car5 = new Car("C005", "Bullet", "Silver", 210, 330, v1Engine);

        Driver driver = new Driver("RacerX", 5);
        System.out.println("Driver: " + driver.getNickname() + ", Level: " + driver.getLevel());

        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a car to drive (1-5): ");
        System.out.println("1. " + car1.getDetails());
        System.out.println("2. " + car2.getDetails());
        System.out.println("3. " + car3.getDetails());
        System.out.println("4. " + car4.getDetails());
        System.out.println("5. " + car5.getDetails());

        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1 -> driver.selectCar(car1);
            case 2 -> driver.selectCar(car2);
            case 3 -> driver.selectCar(car3);
            case 4 -> driver.selectCar(car4);
            case 5 -> driver.selectCar(car5);
            default -> {
                System.out.println("Invalid choice. No car selected.");
                return;
            }
        }

        System.out.println("\n--- Driving Scenario ---");
        driver.drive();

        while (true) {
            System.out.println("Press 'B' to use booster or 'Q' to quit:");
            String input = scanner.nextLine().trim().toUpperCase();

            if (input.equals("B")) {
                driver.useBooster();
            } else if (input.equals("Q")) {
                break;
            } else {
                System.out.println("Invalid input. Please press 'B' to use booster or 'Q' to quit.");
            }
        }

        System.out.println("\n--- Final Status ---");
        if (driver.getSelectedCar() != null) {
            System.out.println(driver.getSelectedCar().getDetails());
        }
    }
}