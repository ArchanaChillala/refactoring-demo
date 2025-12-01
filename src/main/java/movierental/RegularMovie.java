package movierental;

public class RegularMovie extends Movie {
    
    private static final double BASE_CHARGE = 2.0;
    private static final int FREE_DAYS = 2;
    private static final double ADDITIONAL_CHARGE_PER_DAY = 1.5;
    
    public RegularMovie(String title) {
        super(title);
    }
    
    @Override
    public int getPriceCode() {
        return Movie.REGULAR;
    }
    
    @Override
    public double getCharge(int daysRented) {
        double amount = BASE_CHARGE;
        if (daysRented > FREE_DAYS)
            amount += (daysRented - FREE_DAYS) * ADDITIONAL_CHARGE_PER_DAY;
        return amount;
    }
}
