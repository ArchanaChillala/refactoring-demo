package movierental;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.*;

public class RegularMovieTest {

    @Test
    @DisplayName("Regular movie should store title correctly")
    public void testRegularMovieCreation() {
        Movie movie = new RegularMovie("The Matrix");
        
        assertEquals("The Matrix", movie.getTitle());
    }

    @Test
    @DisplayName("Regular movie for 1 day should charge base rate of $2.0")
    public void testChargeOneDay() {
        Movie movie = new RegularMovie("Test Movie");
        
        assertEquals(2.0, movie.getCharge(1), 0.01);
    }

    @Test
    @DisplayName("Regular movie for 2 days should charge base rate of $2.0 (boundary)")
    public void testChargeTwoDays() {
        Movie movie = new RegularMovie("Test Movie");
        
        assertEquals(2.0, movie.getCharge(2), 0.01);
    }

    @Test
    @DisplayName("Regular movie for 3 days should charge $3.5 (base + 1 extra day)")
    public void testChargeThreeDays() {
        Movie movie = new RegularMovie("Test Movie");
        
        assertEquals(3.5, movie.getCharge(3), 0.01);
    }

    @Test
    @DisplayName("Regular movie for 4 days should charge $5.0 (base + 2 extra days)")
    public void testChargeFourDays() {
        Movie movie = new RegularMovie("Test Movie");
        
        assertEquals(5.0, movie.getCharge(4), 0.01);
    }

    @Test
    @DisplayName("Regular movie for 5 days should charge $6.5 (base + 3 extra days)")
    public void testChargeFiveDays() {
        Movie movie = new RegularMovie("Test Movie");
        
        assertEquals(6.5, movie.getCharge(5), 0.01);
    }

    @Test
    @DisplayName("Regular movie for 10 days should charge $14.0")
    public void testChargeTenDays() {
        Movie movie = new RegularMovie("Test Movie");
        
        // Base: $2.0 + (10-2) * $1.5 = $2.0 + $12.0 = $14.0
        assertEquals(14.0, movie.getCharge(10), 0.01);
    }

    @Test
    @DisplayName("Regular movie for 0 days should charge base rate")
    public void testChargeZeroDays() {
        Movie movie = new RegularMovie("Test Movie");
        
        assertEquals(2.0, movie.getCharge(0), 0.01);
    }

    @Test
    @DisplayName("Regular movie pricing formula: base + (days - 2) * extra_rate")
    public void testChargePricingFormula() {
        Movie movie = new RegularMovie("Test Movie");
        
        // Test multiple scenarios to verify formula
        assertEquals(2.0, movie.getCharge(1), 0.01);  // Base only
        assertEquals(2.0, movie.getCharge(2), 0.01);  // Base only
        assertEquals(3.5, movie.getCharge(3), 0.01);  // Base + 1*1.5
        assertEquals(8.0, movie.getCharge(6), 0.01);  // Base + 4*1.5
        assertEquals(17.0, movie.getCharge(12), 0.01); // Base + 10*1.5
    }

    @Test
    @DisplayName("Regular movie should be instance of Movie")
    public void testRegularMovieInheritance() {
        Movie movie = new RegularMovie("Test Movie");
        
        assertTrue(movie instanceof Movie);
        assertTrue(movie instanceof RegularMovie);
    }

    @Test
    @DisplayName("Multiple regular movies with same title should be independent")
    public void testMultipleInstances() {
        Movie movie1 = new RegularMovie("Same Title");
        Movie movie2 = new RegularMovie("Same Title");
        
        assertEquals(movie1.getTitle(), movie2.getTitle());
        assertNotSame(movie1, movie2);
    }
}
