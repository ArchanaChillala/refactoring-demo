package movierental;

public class Rental {

    private static final int BASE_THRESHOLD_DAYS = 1;
    private static final int DEFAULT_FREQUENT_RENTER_POINTS = 1;
    private static final int BONUS_FREQUENT_RENTER_POINTS = 2;

    private Movie _movie;
    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public double getCharge() {
        return _movie.getCharge(_daysRented);
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
        return _movie instanceof NewReleaseMovie;
    }

    private boolean isExtendedRental() {
        return _daysRented > BASE_THRESHOLD_DAYS;
    }

    public String getIndividualStatementLine() {
        return String.format("\t%s\t%.1f\n", _movie.getTitle(), getCharge());
    }
}
