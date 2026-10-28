public class PremiumRide extends Ride {
    private double comfortFee;
    private double surgeMultiplier;

    public PremiumRide(String passengerName, String pickup, String destination, double distanceKm, int durationMin, double pricePerKm, double comfortFee, double surgeMultiplier) {
        super(passengerName, pickup, destination, distanceKm, durationMin, pricePerKm);
        this.comfortFee = comfortFee;

        // surgeMultiplier 1.0 ile 3.0 arasında olmalıdır
        if (surgeMultiplier < 1.0 || surgeMultiplier > 3.0) {
            System.out.println("Warning: surgeMultiplier must be between 1.0 and 3.0! Setting to 1.0.");
            this.surgeMultiplier = 1.0;
        } else {
            this.surgeMultiplier = surgeMultiplier;
        }
    }

    public double getComfortFee() { return comfortFee; }
    public double getSurgeMultiplier() { return surgeMultiplier; }

    @Override
    public double calculateFare() {
        // (base fare + comfortFee) * surgeMultiplier
        return (super.calculateFare() + comfortFee) * surgeMultiplier;
    }

    @Override
    public String toString() {
        return super.toString() + " | Comfort Fee: " + comfortFee + " TL | Surge Multiplier: " + surgeMultiplier;
    }
}
