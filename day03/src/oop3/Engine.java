package oop3;

public class Engine {
    private String serial;
    private String name;
    private int power;
    private String type;

    public Engine(String serial, String name, int power, String type) {
        this.serial = serial;
        this.name = name;
        this.power = power;
        this.type = type;
    }

    public void start() {
        System.out.println(name + " engine started.");
    }

    public void stop() {
        System.out.println(name + " engine stopped.");
    }

    public String getStatus() {
        return name + " engine is running.";
    }

    public String getDetails() {
        return "Engine [Serial: " + serial + ", Name: " + name + ", Power: " + power + "HP, Type: " + type + "]";
    }
}
