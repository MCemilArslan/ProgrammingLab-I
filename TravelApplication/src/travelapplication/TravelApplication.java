import java.util.ArrayList;

public class TravelApplication {
    public static void main(String[] args) {
        ArrayList<Ride> rides = new ArrayList<>();

        // Nesneleri oluşturup listeye ekliyoruz
        // 1. Standart Ride
        Ride ride1 = new Ride("Ahmet Yilmaz", "Kadikoy", "Taksim", 12.5, 30, 15.0);
        
        // 2. EconomyRide
        EconomyRide ride2 = new EconomyRide("Ayse Kaya", "Besiktas", "Uskudar", 5.0, 15, 4.0, 1.0); // Düşük tutar test -> 50 TL sınırı
        
        // 3. PremiumRide (Hatalı surgeMultiplier uyarısı testi)
        PremiumRide ride3 = new PremiumRide("Mehmet Demir", "Avis", "Airport", 25.0, 40, 20.0, 100.0, 4.5); 
        
        // 4. SharedRide
        SharedRide ride4 = new SharedRide("Zeynep Celik", "Levent", "Maslak", 8.0, 20, 10.0, 2.0, 3);

        rides.add(ride1);
        rides.add(ride2);
        rides.add(ride3);
        rides.add(ride4);

        // Döngü ile detayları ve hesaplanan ücretleri yazdırıyoruz
        System.out.println("\n==========================================================================================");
        System.out.println("                                    RIDE DETAILS                                          ");
        System.out.println("==========================================================================================");

        for (int i = 0; i < rides.size(); i++) {
            Ride currentRide = rides.get(i);
            System.out.println("Ride " + (i + 1) + " [" + currentRide.getClass().getSimpleName() + "]:");
            System.out.println("Details: " + currentRide.toString());
            System.out.printf("Calculated Fare: %.2f TL\n", currentRide.calculateFare());
            System.out.println("------------------------------------------------------------------------------------------");
        }
    }
}
