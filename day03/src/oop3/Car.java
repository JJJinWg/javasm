package oop3;

public class Car {
    private String id;
    private String name;
    private String color;
    private int speed;
    private int targetSpeed;
    private int boostSpeed;
    private boolean isRunning;
    private int gauge;
    private int boosters;
    private double distance;
    private boolean isBoosting;
    private Engine engine;

    public Car(String id, String name, String color, int targetSpeed, int boostSpeed, Engine engine) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.speed = 0;
        this.targetSpeed = targetSpeed;
        this.boostSpeed = boostSpeed;
        this.isRunning = false;
        this.gauge = 0;
        this.boosters = 0;
        this.distance = 0;
        this.isBoosting = false;
        this.engine = engine;
    }

    public void accelerate() {
        int accelerationTime = 3000;
        int increment = targetSpeed / (accelerationTime / 100);
        while (speed < targetSpeed) {
            speed += increment;
            if (speed > targetSpeed) {
                speed = targetSpeed;
            }
            System.out.println("Accelerating... Speed: " + speed + " km/h");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        if (!isRunning) {
            isRunning = true;
            engine.start();
            System.out.println(name + " started.");
            accelerate();
            drive();
        } else {
            System.out.println(name + " is already running.");
        }
    }

    public void drive() {
        new Thread(() -> {
            while (isRunning && distance < 5) {
                distance += speed / 3600.0;
                System.out.printf("Driving... Distance: %.2f km, Speed: %d km/h%n", distance, speed);
                gauge += 20;
                if (gauge >= 100) {
                    gainBooster();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            stop();
        }).start();
    }

    private void gainBooster() {
        if (boosters < 2) {
            boosters++;
            gauge = 0;
            System.out.println("Booster gained! Total boosters: " + boosters);
        } else {
            gauge = 100;
            System.out.println("Booster slots full! Total boosters: " + boosters);
        }
    }

    public synchronized void useBooster() {
        if (boosters > 0 && !isBoosting) {
            boosters--;
            isBoosting = true;
            int originalSpeed = speed;
            speed = boostSpeed;
            System.out.println("Booster used! Speed: " + speed + " km/h. Remaining boosters: " + boosters);

            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                speed = originalSpeed;
                isBoosting = false;
                System.out.println("Booster effect ended. Speed: " + speed + " km/h.");
            }).start();
        } else if (isBoosting) {
            System.out.println("Already boosting. Wait until boost effect ends.");
        } else {
            System.out.println("No boosters available.");
        }
    }

    public void stop() {
        if (isRunning) {
            speed = 0;
            isRunning = false;
            engine.stop();
            System.out.println(name + " has stopped. Total distance: " + distance + " km.");
        } else {
            System.out.println(name + " is already stopped.");
        }
    }

    public String getDetails() {
        return "Car [ID: " + id + ", Name: " + name + ", Color: " + color + ", Speed: " + speed + " km/h, "
                + "Target Speed: " + targetSpeed + " km/h, Boost Speed: " + boostSpeed + " km/h, "
                + "Boosters: " + boosters + ", Distance: " + distance + " km, Engine: " + engine.getDetails() + "]";
    }
}
