package movierental;

public class ChildrensMovie extends Movie {
    
    private static final double BASE_CHARGE = 1.5;
    private static final int THRESHOLD_DAYS = 3;
    private static final double ADDITIONAL_CHARGE_PER_DAY = 1.5;
    
    public ChildrensMovie(String title) {
        super(title);
    }
    
    @Override
    public double getCharge(int daysRented) {
        if (exceedsThreshold(daysRented))
            return chargeWithExtraDays(daysRented);
        return BASE_CHARGE;
    }

    private static double chargeWithExtraDays(int daysRented) {
        return BASE_CHARGE + extraCharge(daysRented);
    }

    private static double extraCharge(int daysRented) {
        return (daysRented - THRESHOLD_DAYS) * ADDITIONAL_CHARGE_PER_DAY;
    }

    private static boolean exceedsThreshold(int daysRented) {
        return daysRented > THRESHOLD_DAYS;
    }
}
