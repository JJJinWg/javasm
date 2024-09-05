package oop3;

public class Car {
    private String id;                // 차량 고유번호
    private String name;              // 차량 이름
    private String color;             // 차량 색상
    private int speed;                // 현재 속도 (KM/h)
    private int targetSpeed;          // 목표 속도 (평균 속도)
    private int boostSpeed;           // 부스터 사용 시 속도
    private boolean isRunning;        // 차량이 운행 중인지 여부
    private int gauge;                // 드리프트 게이지 (0~100)
    private int boosters;             // 부스터 개수 (최대 2개)
    private double distance;          // 주행 거리 (KM)
    private boolean isBoosting;       // 부스터 사용 중인지 여부
    private Engine engine;            // 차량의 엔진

    // 생성자: 차량의 초기 속성을 설정
    public Car(String id, String name, String color, int targetSpeed, int boostSpeed, Engine engine) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.speed = 0;               // 초기 속도는 0
        this.targetSpeed = targetSpeed; // 차량의 평균 속도
        this.boostSpeed = boostSpeed;   // 차량의 부스터 속도
        this.isRunning = false;       // 초기 상태는 정지
        this.gauge = 0;               // 초기 게이지는 0
        this.boosters = 0;            // 초기 부스터 개수는 0
        this.distance = 0;            // 초기 주행 거리는 0
        this.isBoosting = false;      // 초기 부스터 사용 상태는 false
        this.engine = engine;         // 차량의 엔진 설정
    }

    // 차량의 속도를 목표 속도까지 서서히 증가시키는 메서드
    public void accelerate() {
        int accelerationTime = 3000; // 3초 안에 목표 속도 도달
        int increment = targetSpeed / (accelerationTime / 100); // 속도 증가량 계산
        while (speed < targetSpeed) {
            speed += increment;
            if (speed > targetSpeed) {
                speed = targetSpeed; // 목표 속도를 넘지 않도록 제한
            }
            System.out.println("Accelerating... Speed: " + speed + " km/h");
            try {
                Thread.sleep(100); // 0.1초마다 속도 증가
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 차량을 출발시키는 메서드
    public void start() {
        if (!isRunning) {
            isRunning = true;
            engine.start(); // 엔진 켜기
            System.out.println(name + " started.");
            accelerate(); // 속도를 서서히 증가시킴
            drive(); // 차량 주행 시작
        } else {
            System.out.println(name + " is already running.");
        }
    }

    // 차량을 주행시키는 메서드, 주행 거리에 따라 게이지 증가
    public void drive() {
        new Thread(() -> {
            while (isRunning && distance < 5) { // 5km 주행 목표
                distance += speed / 3600.0; // 시간당 거리 증가 (속도(KM/h) / 3600 = 초당 거리)
                System.out.printf("Driving... Distance: %.2f km, Speed: %d km/h%n", distance, speed);
                gauge += 20; // 게이지가 더 빠르게 증가 (5초마다 100%로 채워짐)
                if (gauge >= 100) {
                    gainBooster(); // 게이지가 가득 차면 부스터 획득
                }
                try {
                    Thread.sleep(1000); // 1초 대기
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            stop(); // 목표 주행 거리 도달 시 정지
        }).start();
    }

    // 게이지가 100%가 되면 부스터를 얻고 게이지를 초기화
    private void gainBooster() {
        if (boosters < 2) { // 부스터 최대 2개까지 저장 가능
            boosters++;
            gauge = 0; // 게이지 초기화
            System.out.println("Booster gained! Total boosters: " + boosters);
        } else {
            gauge = 100; // 부스터가 가득 찬 상태에서도 게이지 유지
            System.out.println("Booster slots full! Total boosters: " + boosters);
        }
    }

    // 부스터를 사용하는 메서드
    public synchronized void useBooster() {
        if (boosters > 0 && !isBoosting) {
            boosters--;
            isBoosting = true; // 부스터 사용 상태 설정
            int originalSpeed = speed; // 현재 속도 저장
            speed = boostSpeed; // 부스터 사용 시 최고 속도 증가
            System.out.println("Booster used! Speed: " + speed + " km/h. Remaining boosters: " + boosters);

            // 부스터 효과를 3초간 유지하고 원래 속도로 복원
            new Thread(() -> {
                try {
                    Thread.sleep(3000); // 3초간 부스터 효과 지속
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                speed = originalSpeed; // 속도를 원래 상태로 복원
                isBoosting = false; // 부스터 사용 상태 해제
                System.out.println("Booster effect ended. Speed: " + speed + " km/h.");
            }).start();
        } else if (isBoosting) {
            System.out.println("Already boosting. Wait until boost effect ends.");
        } else {
            System.out.println("No boosters available.");
        }
    }

    // 차량을 정지시키는 메서드
    public void stop() {
        if (isRunning) {
            speed = 0;
            isRunning = false;
            engine.stop(); // 엔진 끄기
            System.out.println(name + " has stopped. Total distance: " + distance + " km.");
        } else {
            System.out.println(name + " is already stopped.");
        }
    }

    // 차량의 세부 정보를 반환하는 메서드
    public String getDetails() {
        return "Car [ID: " + id + ", Name: " + name + ", Color: " + color + ", Speed: " + speed + " km/h, "
                + "Target Speed: " + targetSpeed + " km/h, Boost Speed: " + boostSpeed + " km/h, "
                + "Boosters: " + boosters + ", Distance: " + distance + " km, Engine: " + engine.getDetails() + "]";
    }
}
