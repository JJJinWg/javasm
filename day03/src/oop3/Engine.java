package oop3;

public class Engine {
    private String serial;  // 엔진의 고유 일련 번호
    private String name;    // 엔진 이름 (JIU, X, V1 중 하나)
    private int power;      // 엔진 출력 (마력)
    private String type;    // 엔진 종류 (예: 가솔린, 디젤)

    // 생성자: 엔진의 초기 속성을 설정
    public Engine(String serial, String name, int power, String type) {
        this.serial = serial;
        this.name = name;
        this.power = power;
        this.type = type;
    }

    // 엔진을 켜는 메서드
    public void start() {
        System.out.println(name + " engine started.");
    }

    // 엔진을 끄는 메서드
    public void stop() {
        System.out.println(name + " engine stopped.");
    }

    // 엔진의 현재 상태를 반환하는 메서드
    public String getStatus() {
        return name + " engine is running.";
    }

    // 엔진의 세부 정보를 반환하는 메서드
    public String getDetails() {
        return "Engine [Serial: " + serial + ", Name: " + name + ", Power: " + power + "HP, Type: " + type + "]";
    }
}
