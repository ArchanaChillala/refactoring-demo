package movierental;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.*;

public class MovieTest {

    @Test
    @DisplayName("Movie title should be retrievable through getTitle()")
    public void testGetTitle() {
        Movie movie = new RegularMovie("The Godfather");
        assertEquals("The Godfather", movie.getTitle());
    }

    @Test
    @DisplayName("Movie title should be immutable after creation")
    public void testTitleImmutability() {
        Movie movie = new RegularMovie("Original Title");
        String title = movie.getTitle();
        
        assertEquals("Original Title", title);
        assertEquals("Original Title", movie.getTitle());
    }

    @Test
    @DisplayName("Different movie types should maintain their titles correctly")
    public void testDifferentMovieTypes() {
        Movie regular = new RegularMovie("Regular Title");
        Movie newRelease = new NewReleaseMovie("New Release Title");
        Movie childrens = new ChildrensMovie("Children's Title");
        
        assertEquals("Regular Title", regular.getTitle());
        assertEquals("New Release Title", newRelease.getTitle());
        assertEquals("Children's Title", childrens.getTitle());
    }

    @Test
    @DisplayName("Movie with empty string title should work")
    public void testEmptyTitle() {
        Movie movie = new RegularMovie("");
        assertEquals("", movie.getTitle());
    }

    @Test
    @DisplayName("Movie with very long title should work")
    public void testLongTitle() {
        String longTitle = "This Is A Very Long Movie Title That Contains Many Words And Should Still Work Correctly";
        Movie movie = new RegularMovie(longTitle);
        
        assertEquals(longTitle, movie.getTitle());
    }

    @Test
    @DisplayName("Movie titles with special characters should be preserved")
    public void testTitleWithSpecialCharacters() {
        Movie movie1 = new RegularMovie("Movie: The Sequel!");
        Movie movie2 = new RegularMovie("Movie & Show");
        Movie movie3 = new RegularMovie("Movie's Title");
        
        assertEquals("Movie: The Sequel!", movie1.getTitle());
        assertEquals("Movie & Show", movie2.getTitle());
        assertEquals("Movie's Title", movie3.getTitle());
    }

    @Test
    @DisplayName("Movie titles with numbers should work")
    public void testTitleWithNumbers() {
        Movie movie = new RegularMovie("2001: A Space Odyssey");
        assertEquals("2001: A Space Odyssey", movie.getTitle());
    }

    @Test
    @DisplayName("All movie types should implement abstract getCharge method")
    public void testGetChargeImplementation() {
        Movie regular = new RegularMovie("Regular");
        Movie newRelease = new NewReleaseMovie("New");
        Movie childrens = new ChildrensMovie("Children");
        
        // All should return a charge without throwing exception
        assertTrue(regular.getCharge(1) >= 0);
        assertTrue(newRelease.getCharge(1) >= 0);
        assertTrue(childrens.getCharge(1) >= 0);
    }

    @Test
    @DisplayName("Different movie types should have different charge calculations")
    public void testDifferentChargeCalculations() {
        Movie regular = new RegularMovie("Test");
        Movie newRelease = new NewReleaseMovie("Test");
        Movie childrens = new ChildrensMovie("Test");
        
        // For 5 days, charges should be different
        double regularCharge = regular.getCharge(5);
        double newReleaseCharge = newRelease.getCharge(5);
        double childrensCharge = childrens.getCharge(5);
        
        assertNotEquals(regularCharge, newReleaseCharge, 0.01);
        assertNotEquals(regularCharge, childrensCharge, 0.01);
        assertNotEquals(newReleaseCharge, childrensCharge, 0.01);
    }
}
