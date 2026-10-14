package week3.car;

public class Car {
    // Attributes (Özellikler)
    private String plateNumber;
    private String model;
    private double mileage;
    private double fuelLevel;
    private double tankCapacity;

    // Constructor (Yapıcı Metot)
    public Car(String plateNumber, String model, double fuelLevel, double tankCapacity) {
        this.plateNumber = plateNumber;
        this.model = model;
        this.mileage = 0; // Başlangıçta kilometre 0
        this.fuelLevel = fuelLevel;
        this.tankCapacity = tankCapacity;
    }

    // 1. Drive Metodu (10 km -> 1 Litre yakıt harcar)
    public void drive(double km) {
        double neededFuel = km / 10.0;

        if (neededFuel > fuelLevel) {
            System.out.println("Not enough fuel for this trip!");
        } else {
            mileage += km;
            fuelLevel -= neededFuel;
            System.out.println("Successfully driven " + km + " km.");
        }
    }

    // 2. Refuel Metodu (Depoyu doldurma)
    public void refuel(double amount) {
        if (fuelLevel + amount > tankCapacity) {
            fuelLevel = tankCapacity;
            System.out.println("Tank is full, extra fuel discarded.");
        } else {
            fuelLevel += amount;
            System.out.println("Successfully refueled " + amount + " liters.");
        }
    }

    // 3. Check Status Metodu (Durum Kontrolü)
    public void checkStatus() {
        System.out.println("\n--- CAR STATUS ---");
        System.out.println("Plate Number: " + plateNumber);
        System.out.println("Model       : " + model);
        System.out.println("Mileage     : " + mileage + " km");
        System.out.println("Fuel Level  : " + fuelLevel + " / " + tankCapacity + " L");

        // Depo %10'un altındaysa uyarı ver
        if (fuelLevel < (tankCapacity * 0.10)) {
            System.out.println("Low fuel warning!");
        }
        System.out.println("-------------------\n");
    }
}
