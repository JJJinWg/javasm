package oop3;

public class Driver {
    private String nickname;
    private int level;
    private Car selectedCar;

    public Driver(String nickname, int level) {
        this.nickname = nickname;
        this.level = level;
    }

    public void selectCar(Car car) {
        this.selectedCar = car;
        System.out.println(nickname + " has selected " + car.getDetails());
    }

    public void drive() {
        if (selectedCar != null) {
            System.out.println(nickname + " is driving the car " + selectedCar.getDetails());
            selectedCar.start();
        } else {
            System.out.println("No car selected. Please select a car first.");
        }
    }

    public void useBooster() {
        if (selectedCar != null) {
            selectedCar.useBooster();
        } else {
            System.out.println("No car selected. Cannot use booster.");
        }
    }

    public String getNickname() {
        return nickname;
    }

    public int getLevel() {
        return level;
    }

    public Car getSelectedCar() {
        return selectedCar;
    }
}
