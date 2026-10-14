package week3.car;

public class Week3Car {
    public static void main(String[] args) {
        // Car nesnesi oluşturma (Plaka, Model, Başlangıç Yakıtı, Depo Kapasitesi)
        System.out.println("Creating a new car...");
        Car myCar = new Car("34 ABC 123", "Toyota Corolla", 25.0, 50.0);

        // İlk durumu kontrol et
        myCar.checkStatus();

        // Normal Sürüş
        System.out.println("Driving 120 km...");
        myCar.drive(120); // 12 Litre harcar (25 - 12 = 13 Litre kalır)
        myCar.checkStatus();

        // Edge Case 1: Yetersiz Yakıt Testi (13 L yakıt var, 200 km için 20 L lazım)
        System.out.println("Driving 200 km (Edge Case: Insufficient Fuel Test)...");
        myCar.drive(200); // "Not enough fuel for this trip!" yazmalı

        // Düşük yakıt uyarısı için ekstra sürüş yapalım (100 km -> 10 L harcar, 3 L kalır. 3 < 5 L yani %10 altı)
        System.out.println("\nDriving 100 km to trigger low fuel warning...");
        myCar.drive(100);
        myCar.checkStatus(); // "Low fuel warning!" basmalı

        // Normal Yakıt Alma
        System.out.println("Refueling 20 liters...");
        myCar.refuel(20); // 3 + 20 = 23 Litre olur
        myCar.checkStatus();

        // Edge Case 2: Depo Kapasitesini Aşma Testi (23 L var, 40 L daha eklersek 63 L olur. Depo 50 L)
        System.out.println("Refueling 40 liters (Edge Case: Tank Capacity Overflow Test)...");
        myCar.refuel(40); // "Tank is full, extra fuel discarded." yazmalı
        myCar.checkStatus();
    }
}
