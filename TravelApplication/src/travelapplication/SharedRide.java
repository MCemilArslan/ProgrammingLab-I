public class SharedRide extends EconomyRide {
    private int passengerCount;

    public SharedRide(String passengerName, String pickup, String destination, double distanceKm, int durationMin, double pricePerKm, double pricePerMinute, int passengerCount) {
        super(passengerName, pickup, destination, distanceKm, durationMin, pricePerKm, pricePerMinute);

        // passengerCount 1 ile 4 arasında olmalıdır
        if (passengerCount < 1 || passengerCount > 4) {
            System.out.println("Warning: passengerCount must be between 1 and 4! Setting to 1.");
            this.passengerCount = 1;
        } else {
            this.passengerCount = passengerCount;
        }
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    @Override
    public double calculateFare() {
        // EconomyRide fare (minimum 50 TL kuralı dahil) / passengerCount + %10 servis ücreti
        double economyFare = super.calculateFare();
        double farePerPassenger = economyFare / passengerCount;
        return farePerPassenger * 1.10; // %10 servis ücreti eklendi
    }

    @Override
    public String toString() {
        return super.toString() + " | Passenger Count: " + passengerCount;
    }
}
