package movierental;

public abstract class Movie {

    private String _title;

    public Movie(String title) {
        _title = title;
    }
    
    public String getTitle() {
        return _title;
    }

    public abstract double getCharge(int daysRented);

    public int getFrequentRenterPoints(int daysRented) {
        return 1;
    }
}
