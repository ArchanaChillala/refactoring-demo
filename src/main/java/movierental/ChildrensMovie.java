package movierental;

public class ChildrensMovie extends Movie {
    
    private static final double BASE_CHARGE = 1.5;
    private static final int FREE_DAYS = 3;
    private static final double ADDITIONAL_CHARGE_PER_DAY = 1.5;
    
    public ChildrensMovie(String title) {
        super(title);
    }
    
    @Override
    public int getPriceCode() {
        return Movie.CHILDRENS;
    }
    
    @Override
    public double getCharge(int daysRented) {
        double amount = BASE_CHARGE;
        if (daysRented > FREE_DAYS)
            amount += (daysRented - FREE_DAYS) * ADDITIONAL_CHARGE_PER_DAY;
        return amount;
    }
}
