public class EconomyRide extends Ride {
    private double pricePerMinute;

    public EconomyRide(String passengerName, String pickup, String destination, double distanceKm, int durationMin, double pricePerKm, double pricePerMinute) {
        super(passengerName, pickup, destination, distanceKm, durationMin, pricePerKm);
        this.pricePerMinute = pricePerMinute;
    }

    public double getPricePerMinute() {
        return pricePerMinute;
    }

    @Override
    public double calculateFare() {
        // base fare + (durationMin * pricePerMinute)
        double totalFare = super.calculateFare() + (getDurationMin() * pricePerMinute);
        
        // Minimum ücret 50 TL kuralı
        if (totalFare < 50) {
            return 50.0;
        }
        return totalFare;
    }

    @Override
    public String toString() {
        return super.toString() + " | Price/Min: " + pricePerMinute + " TL";
    }
}
