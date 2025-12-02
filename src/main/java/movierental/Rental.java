package movierental;

public class Rental {

    private static final int BASE_THRESHOLD_DAYS = 1;
    private static final int DEFAULT_FREQUENT_RENTER_POINTS = 1;
    private static final int BONUS_FREQUENT_RENTER_POINTS = 2;

    private Movie movie;
    private int daysRented;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public double getCharge() {
        return movie.getCharge(daysRented);
    }

    public int getFrequentRenterPoints() {
        if (qualifiesForBonusPoints()) {
            return BONUS_FREQUENT_RENTER_POINTS;
        }
        return DEFAULT_FREQUENT_RENTER_POINTS;
    }

    private boolean qualifiesForBonusPoints() {
        return isNewReleaseMovie() && isExtendedRental();
    }

    private boolean isNewReleaseMovie() {
        return movie instanceof NewReleaseMovie;
    }

    private boolean isExtendedRental() {
        return daysRented > BASE_THRESHOLD_DAYS;
    }

    public String getIndividualStatementLine() {
        return String.format("\t%s\t%.1f\n", movie.getTitle(), getCharge());
    }
}
