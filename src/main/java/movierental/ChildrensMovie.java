package movierental;

public class ChildrensMovie extends Movie {
    
    private static final double BASE_CHARGE = 1.5;
    private static final double EXTRA_CHARGE_PER_DAY = 1.5;
    private static final int BASE_PERIOD = 3;

    public ChildrensMovie(String title) {
        super(title);
    }
    
    @Override
    public double getCharge(int daysRented) {
        if (exceedsBasePeriod(daysRented))
            return calculateExtendedRentalCharge(daysRented);
        return BASE_CHARGE;
    }

    private static double calculateExtendedRentalCharge(int daysRented) {
        return BASE_CHARGE + extraCharge(daysRented);
    }

    private static double extraCharge(int daysRented) {
        return countExtraDays(daysRented) * EXTRA_CHARGE_PER_DAY;
    }

    private static int countExtraDays(int daysRented) {
        return daysRented - BASE_PERIOD;
    }

    private static boolean exceedsBasePeriod(int daysRented) {
        return daysRented > BASE_PERIOD;
    }
}
