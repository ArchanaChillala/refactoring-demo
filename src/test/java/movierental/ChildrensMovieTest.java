package movierental;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.*;

public class ChildrensMovieTest {

    @Test
    @DisplayName("Children's movie should store title correctly")
    public void testChildrensMovieCreation() {
        Movie movie = new ChildrensMovie("Frozen");
        
        assertEquals("Frozen", movie.getTitle());
    }

    @Test
    @DisplayName("Children's movie for 1 day should charge base rate of $1.5")
    public void testChargeOneDay() {
        Movie movie = new ChildrensMovie("Test Movie");
        
        assertEquals(1.5, movie.getCharge(1), 0.01);
    }

    @Test
    @DisplayName("Children's movie for 2 days should charge base rate of $1.5")
    public void testChargeTwoDays() {
        Movie movie = new ChildrensMovie("Test Movie");
        
        assertEquals(1.5, movie.getCharge(2), 0.01);
    }

    @Test
    @DisplayName("Children's movie for 3 days should charge base rate of $1.5 (boundary)")
    public void testChargeThreeDays() {
        Movie movie = new ChildrensMovie("Test Movie");
        
        assertEquals(1.5, movie.getCharge(3), 0.01);
    }

    @Test
    @DisplayName("Children's movie for 4 days should charge $3.0 (base + 1 extra day)")
    public void testChargeFourDays() {
        Movie movie = new ChildrensMovie("Test Movie");
        
        assertEquals(3.0, movie.getCharge(4), 0.01);
    }

    @Test
    @DisplayName("Children's movie for 5 days should charge $4.5 (base + 2 extra days)")
    public void testChargeFiveDays() {
        Movie movie = new ChildrensMovie("Test Movie");
        
        assertEquals(4.5, movie.getCharge(5), 0.01);
    }

    @Test
    @DisplayName("Children's movie for 7 days should charge $7.5 (base + 4 extra days)")
    public void testChargeSevenDays() {
        Movie movie = new ChildrensMovie("Test Movie");
        
        // Base: $1.5 + (7-3) * $1.5 = $1.5 + $6.0 = $7.5
        assertEquals(7.5, movie.getCharge(7), 0.01);
    }

    @Test
    @DisplayName("Children's movie for 10 days should charge $12.0")
    public void testChargeTenDays() {
        Movie movie = new ChildrensMovie("Test Movie");
        
        // Base: $1.5 + (10-3) * $1.5 = $1.5 + $10.5 = $12.0
        assertEquals(12.0, movie.getCharge(10), 0.01);
    }

    @Test
    @DisplayName("Children's movie for 0 days should charge base rate")
    public void testChargeZeroDays() {
        Movie movie = new ChildrensMovie("Test Movie");
        
        assertEquals(1.5, movie.getCharge(0), 0.01);
    }

    @Test
    @DisplayName("Children's movie pricing formula: base + (days - 3) * extra_rate")
    public void testChargePricingFormula() {
        Movie movie = new ChildrensMovie("Test Movie");
        
        // Test multiple scenarios to verify formula
        assertEquals(1.5, movie.getCharge(1), 0.01);   // Base only
        assertEquals(1.5, movie.getCharge(2), 0.01);   // Base only
        assertEquals(1.5, movie.getCharge(3), 0.01);   // Base only (boundary)
        assertEquals(3.0, movie.getCharge(4), 0.01);   // Base + 1*1.5
        assertEquals(6.0, movie.getCharge(6), 0.01);   // Base + 3*1.5
        assertEquals(15.0, movie.getCharge(12), 0.01); // Base + 9*1.5
    }

    @Test
    @DisplayName("Children's movie boundary test: 3 vs 4 days")
    public void testBoundaryThreeFourDays() {
        Movie movie = new ChildrensMovie("Boundary Test");
        
        // At boundary (3 days), should be base charge only
        assertEquals(1.5, movie.getCharge(3), 0.01);
        
        // Just over boundary (4 days), should add extra charge
        assertEquals(3.0, movie.getCharge(4), 0.01);
    }

    @Test
    @DisplayName("Children's movie should be instance of Movie")
    public void testChildrensMovieInheritance() {
        Movie movie = new ChildrensMovie("Test Movie");
        
        assertTrue(movie instanceof Movie);
        assertTrue(movie instanceof ChildrensMovie);
    }

    @Test
    @DisplayName("Multiple children's movies with same title should be independent")
    public void testMultipleInstances() {
        Movie movie1 = new ChildrensMovie("Same Title");
        Movie movie2 = new ChildrensMovie("Same Title");
        
        assertEquals(movie1.getTitle(), movie2.getTitle());
        assertNotSame(movie1, movie2);
    }

    @Test
    @DisplayName("Children's movie with long title should work correctly")
    public void testLongTitle() {
        Movie movie = new ChildrensMovie("A Very Long Movie Title For Testing Purposes");
        
        assertEquals("A Very Long Movie Title For Testing Purposes", movie.getTitle());
        assertEquals(1.5, movie.getCharge(2), 0.01);
    }
}
