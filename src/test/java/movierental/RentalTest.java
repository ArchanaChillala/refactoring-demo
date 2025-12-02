package movierental;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.*;

public class RentalTest {

    @Test
    @DisplayName("Rental should store movie and days rented correctly")
    public void testRentalCreation() {
        Movie movie = new RegularMovie("Test Movie");
        Rental rental = new Rental(movie, 3);
        
        assertNotNull(rental);
    }

    @Test
    @DisplayName("Regular movie rental should return correct charge")
    public void testGetChargeRegularMovie() {
        Movie movie = new RegularMovie("The Matrix");
        Rental rental = new Rental(movie, 3);
        
        assertEquals(3.5, rental.getCharge(), 0.01);
    }

    @Test
    @DisplayName("New release movie rental should return correct charge")
    public void testGetChargeNewReleaseMovie() {
        Movie movie = new NewReleaseMovie("Dune");
        Rental rental = new Rental(movie, 3);
        
        assertEquals(9.0, rental.getCharge(), 0.01);
    }

    @Test
    @DisplayName("Children's movie rental should return correct charge")
    public void testGetChargeChildrensMovie() {
        Movie movie = new ChildrensMovie("Frozen");
        Rental rental = new Rental(movie, 4);
        
        assertEquals(3.0, rental.getCharge(), 0.01);
    }

    @Test
    @DisplayName("Regular movie rental should earn 1 frequent renter point")
    public void testFrequentRenterPointsRegularMovie() {
        Movie movie = new RegularMovie("The Matrix");
        Rental rental = new Rental(movie, 5);
        
        assertEquals(1, rental.getFrequentRenterPoints());
    }

    @Test
    @DisplayName("New release movie rented for 1 day should earn 1 point (no bonus)")
    public void testFrequentRenterPointsNewReleaseOneDay() {
        Movie movie = new NewReleaseMovie("Dune");
        Rental rental = new Rental(movie, 1);
        
        assertEquals(1, rental.getFrequentRenterPoints());
    }

    @Test
    @DisplayName("New release movie rented for 2 days should earn 2 points (bonus)")
    public void testFrequentRenterPointsNewReleaseTwoDays() {
        Movie movie = new NewReleaseMovie("Dune");
        Rental rental = new Rental(movie, 2);
        
        assertEquals(2, rental.getFrequentRenterPoints());
    }

    @Test
    @DisplayName("New release movie rented for 3+ days should earn 2 points (bonus)")
    public void testFrequentRenterPointsNewReleaseMultipleDays() {
        Movie movie = new NewReleaseMovie("Dune");
        Rental rental = new Rental(movie, 5);
        
        assertEquals(2, rental.getFrequentRenterPoints());
    }

    @Test
    @DisplayName("Children's movie rental should earn 1 frequent renter point")
    public void testFrequentRenterPointsChildrensMovie() {
        Movie movie = new ChildrensMovie("Frozen");
        Rental rental = new Rental(movie, 7);
        
        assertEquals(1, rental.getFrequentRenterPoints());
    }

    @Test
    @DisplayName("Statement line should be formatted correctly with movie title and charge")
    public void testGetIndividualStatementLine() {
        Movie movie = new RegularMovie("The Matrix");
        Rental rental = new Rental(movie, 3);
        
        String expected = "\tThe Matrix\t3.5\n";
        assertEquals(expected, rental.getIndividualStatementLine());
    }

    @Test
    @DisplayName("Statement line should format charge with one decimal place")
    public void testStatementLineFormatting() {
        Movie movie = new NewReleaseMovie("Dune");
        Rental rental = new Rental(movie, 2);
        
        String statementLine = rental.getIndividualStatementLine();
        assertTrue(statementLine.contains("6.0"));
        assertTrue(statementLine.contains("Dune"));
    }

    @Test
    @DisplayName("Rental with zero days should calculate correct charge")
    public void testRentalWithZeroDays() {
        Movie movie = new RegularMovie("Test Movie");
        Rental rental = new Rental(movie, 0);
        
        assertEquals(2.0, rental.getCharge(), 0.01);
    }

    @Test
    @DisplayName("Boundary test: New release at 1 day threshold for bonus points")
    public void testNewReleaseBonusPointBoundary() {
        Movie movie = new NewReleaseMovie("Boundary Test");
        
        Rental rentalOneDay = new Rental(movie, 1);
        assertEquals(1, rentalOneDay.getFrequentRenterPoints());
        
        Rental rentalTwoDays = new Rental(movie, 2);
        assertEquals(2, rentalTwoDays.getFrequentRenterPoints());
    }
}
