package oop3;

import java.util.Scanner;

public class Driver {
    private String nickname;  // 운전자의 닉네임
    private int level;        // 운전자의 레벨
    private Car selectedCar;  // 선택된 차량

    // 생성자: 운전자의 닉네임과 레벨을 설정
    public Driver(String nickname, int level) {
        this.nickname = nickname;
        this.level = level;
    }

    // 운전자가 차량을 선택하는 메서드
    public void selectCar(Car car) {
        this.selectedCar = car;
        System.out.println(nickname + " has selected " + car.getDetails());
    }

    // 선택된 차량을 운전하는 메서드
    public void drive() {
        if (selectedCar != null) {
            System.out.println(nickname + " is driving the car " + selectedCar.getDetails());
            selectedCar.start(); // 차량의 시동을 걸어 운전 시작
        } else {
            System.out.println("No car selected. Please select a car first.");
        }
    }

    // 운전자가 부스터를 사용하는 메서드
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
