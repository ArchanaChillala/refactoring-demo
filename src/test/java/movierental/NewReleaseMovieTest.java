package movierental;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.*;

public class NewReleaseMovieTest {

    @Test
    @DisplayName("New release movie should store title correctly")
    public void testNewReleaseMovieCreation() {
        Movie movie = new NewReleaseMovie("Dune");
        
        assertEquals("Dune", movie.getTitle());
    }

    @Test
    @DisplayName("New release movie for 1 day should charge $3.0")
    public void testChargeOneDay() {
        Movie movie = new NewReleaseMovie("Test Movie");
        
        assertEquals(3.0, movie.getCharge(1), 0.01);
    }

    @Test
    @DisplayName("New release movie for 2 days should charge $6.0")
    public void testChargeTwoDays() {
        Movie movie = new NewReleaseMovie("Test Movie");
        
        assertEquals(6.0, movie.getCharge(2), 0.01);
    }

    @Test
    @DisplayName("New release movie for 3 days should charge $9.0")
    public void testChargeThreeDays() {
        Movie movie = new NewReleaseMovie("Test Movie");
        
        assertEquals(9.0, movie.getCharge(3), 0.01);
    }

    @Test
    @DisplayName("New release movie for 5 days should charge $15.0")
    public void testChargeFiveDays() {
        Movie movie = new NewReleaseMovie("Test Movie");
        
        assertEquals(15.0, movie.getCharge(5), 0.01);
    }

    @Test
    @DisplayName("New release movie for 10 days should charge $30.0")
    public void testChargeTenDays() {
        Movie movie = new NewReleaseMovie("Test Movie");
        
        assertEquals(30.0, movie.getCharge(10), 0.01);
    }

    @Test
    @DisplayName("New release movie for 0 days should charge $0.0")
    public void testChargeZeroDays() {
        Movie movie = new NewReleaseMovie("Test Movie");
        
        assertEquals(0.0, movie.getCharge(0), 0.01);
    }

    @Test
    @DisplayName("New release movie pricing formula: days * $3.0")
    public void testChargePricingFormula() {
        Movie movie = new NewReleaseMovie("Test Movie");
        
        // Test multiple scenarios to verify formula
        assertEquals(0.0, movie.getCharge(0), 0.01);
        assertEquals(3.0, movie.getCharge(1), 0.01);
        assertEquals(6.0, movie.getCharge(2), 0.01);
        assertEquals(12.0, movie.getCharge(4), 0.01);
        assertEquals(21.0, movie.getCharge(7), 0.01);
        assertEquals(45.0, movie.getCharge(15), 0.01);
    }

    @Test
    @DisplayName("New release movie charge increases linearly with days")
    public void testLinearPricing() {
        Movie movie = new NewReleaseMovie("Test Movie");
        
        for (int days = 1; days <= 10; days++) {
            assertEquals(days * 3.0, movie.getCharge(days), 0.01);
        }
    }

    @Test
    @DisplayName("New release movie should be instance of Movie")
    public void testNewReleaseMovieInheritance() {
        Movie movie = new NewReleaseMovie("Test Movie");
        
        assertTrue(movie instanceof Movie);
        assertTrue(movie instanceof NewReleaseMovie);
    }

    @Test
    @DisplayName("Multiple new release movies with same title should be independent")
    public void testMultipleInstances() {
        Movie movie1 = new NewReleaseMovie("Same Title");
        Movie movie2 = new NewReleaseMovie("Same Title");
        
        assertEquals(movie1.getTitle(), movie2.getTitle());
        assertNotSame(movie1, movie2);
    }

    @Test
    @DisplayName("New release movie with special characters in title should work")
    public void testSpecialCharactersInTitle() {
        Movie movie = new NewReleaseMovie("Test: Movie & More!");
        
        assertEquals("Test: Movie & More!", movie.getTitle());
        assertEquals(9.0, movie.getCharge(3), 0.01);
    }

    @Test
    @DisplayName("New release movie should have most expensive rate per day")
    public void testMostExpensiveRate() {
        Movie newRelease = new NewReleaseMovie("New");
        Movie regular = new RegularMovie("Regular");
        Movie childrens = new ChildrensMovie("Children");
        
        // For 1 day rental
        assertTrue(newRelease.getCharge(1) > regular.getCharge(1));
        assertTrue(newRelease.getCharge(1) > childrens.getCharge(1));
    }

    @Test
    @DisplayName("New release movie for extended period should be expensive")
    public void testExtendedRentalCost() {
        Movie movie = new NewReleaseMovie("Expensive Rental");
        
        // 30 days would cost $90
        assertEquals(90.0, movie.getCharge(30), 0.01);
    }
}
