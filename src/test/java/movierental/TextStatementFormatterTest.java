package movierental;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class TextStatementFormatterTest {

    private final TextStatementFormatter formatter = new TextStatementFormatter();

    // ========== FORMAT METHOD TESTS ==========

    @Test
    @DisplayName("Format should generate correct statement for empty rental list")
    public void testFormatWithNoRentals() {
        List<Rental> rentals = new ArrayList<>();
        
        String result = formatter.format("John Doe", rentals);
        
        assertTrue(result.contains("Rental Record for John Doe"));
        assertTrue(result.contains("Amount owed is 0.0"));
        assertTrue(result.contains("You earned 0 frequent renter points"));
    }

    @Test
    @DisplayName("Format should generate correct statement for single rental")
    public void testFormatWithSingleRental() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new RegularMovie("The Matrix"), 3));
        
        String result = formatter.format("Alice", rentals);
        
        assertTrue(result.contains("Rental Record for Alice"));
        assertTrue(result.contains("\tThe Matrix\t3.5"));
        assertTrue(result.contains("Amount owed is 3.5"));
        assertTrue(result.contains("You earned 1 frequent renter points"));
    }

    @Test
    @DisplayName("Format should generate correct statement for multiple rentals")
    public void testFormatWithMultipleRentals() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new RegularMovie("Movie A"), 2));
        rentals.add(new Rental(new NewReleaseMovie("Movie B"), 3));
        rentals.add(new Rental(new ChildrensMovie("Movie C"), 4));
        
        String result = formatter.format("Bob", rentals);
        
        assertTrue(result.contains("Rental Record for Bob"));
        assertTrue(result.contains("\tMovie A\t2.0"));
        assertTrue(result.contains("\tMovie B\t9.0"));
        assertTrue(result.contains("\tMovie C\t3.0"));
        assertTrue(result.contains("Amount owed is 14.0"));
        assertTrue(result.contains("You earned 4 frequent renter points")); // 1 + 2 (bonus) + 1
    }

    @Test
    @DisplayName("Format should handle customer name with special characters")
    public void testFormatWithSpecialCharactersInName() {
        List<Rental> rentals = new ArrayList<>();
        
        String result = formatter.format("O'Brien", rentals);
        
        assertTrue(result.contains("Rental Record for O'Brien"));
    }

    @Test
    @DisplayName("Format should handle long customer names")
    public void testFormatWithLongCustomerName() {
        List<Rental> rentals = new ArrayList<>();
        String longName = "ThisIsAVeryLongCustomerNameForTesting";
        
        String result = formatter.format(longName, rentals);
        
        assertTrue(result.contains("Rental Record for " + longName));
    }

    // ========== FORMAT LINE METHOD TESTS ==========

    @Test
    @DisplayName("FormatLine should format rental with correct tab and newline")
    public void testFormatLineStructure() {
        Rental rental = new Rental(new RegularMovie("Test Movie"), 1);
        
        String result = formatter.formatLine(rental);
        
        assertTrue(result.startsWith("\t"));
        assertTrue(result.endsWith("\n"));
        assertTrue(result.contains("Test Movie"));
    }

    @Test
    @DisplayName("FormatLine should format charge with one decimal place")
    public void testFormatLineDecimalFormatting() {
        Rental rental = new Rental(new RegularMovie("Movie"), 3);
        
        String result = formatter.formatLine(rental);
        
        assertTrue(result.contains("3.5"));
        assertFalse(result.contains("3.50"));
    }

    @Test
    @DisplayName("FormatLine should handle zero charge")
    public void testFormatLineWithZeroCharge() {
        Rental rental = new Rental(new RegularMovie("Free Movie"), 0);
        
        String result = formatter.formatLine(rental);
        
        assertTrue(result.contains("0.0") || result.contains("2.0")); // Regular movie base charge
    }

    @Test
    @DisplayName("FormatLine should handle movie titles with special characters")
    public void testFormatLineWithSpecialCharactersInTitle() {
        Rental rental = new Rental(new RegularMovie("Movie: The Sequel"), 1);
        
        String result = formatter.formatLine(rental);
        
        assertTrue(result.contains("Movie: The Sequel"));
    }

    @Test
    @DisplayName("FormatLine should handle large charge amounts")
    public void testFormatLineWithLargeCharge() {
        Rental rental = new Rental(new NewReleaseMovie("Expensive"), 100);
        
        String result = formatter.formatLine(rental);
        
        assertTrue(result.contains("300.0"));
    }

    // ========== INTEGRATION TESTS ==========

    @Test
    @DisplayName("Format should correctly sum charges for multiple rentals")
    public void testFormatCalculatesTotalChargeCorrectly() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new RegularMovie("A"), 1)); // 2.0
        rentals.add(new Rental(new RegularMovie("B"), 5)); // 6.5
        rentals.add(new Rental(new NewReleaseMovie("C"), 2)); // 6.0
        
        String result = formatter.format("Test", rentals);
        
        assertTrue(result.contains("Amount owed is 14.5"));
    }

    @Test
    @DisplayName("Format should correctly sum frequent renter points")
    public void testFormatCalculatesTotalPointsCorrectly() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new RegularMovie("A"), 1)); // 1 point
        rentals.add(new Rental(new NewReleaseMovie("B"), 1)); // 1 point
        rentals.add(new Rental(new NewReleaseMovie("C"), 3)); // 2 points (bonus)
        
        String result = formatter.format("Test", rentals);
        
        assertTrue(result.contains("You earned 4 frequent renter points"));
    }

    @Test
    @DisplayName("Format should maintain correct structure with header, body, footer")
    public void testFormatStructure() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new RegularMovie("Movie"), 2));
        
        String result = formatter.format("Customer", rentals);
        
        String[] lines = result.split("\n");
        assertTrue(lines.length >= 3);
        assertTrue(lines[0].contains("Rental Record"));
        assertTrue(lines[1].startsWith("\t"));
        assertTrue(lines[lines.length - 1].contains("frequent renter points"));
    }

    // ========== EDGE CASE TESTS ==========

    @Test
    @DisplayName("Format should handle maximum integer days rented")
    public void testFormatWithMaxDaysRented() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new NewReleaseMovie("Long Rental"), Integer.MAX_VALUE));
        
        String result = formatter.format("Test", rentals);
        
        assertNotNull(result);
        assertTrue(result.contains("Rental Record"));
    }

    @Test
    @DisplayName("Format should handle empty string customer name")
    public void testFormatWithEmptyCustomerName() {
        List<Rental> rentals = new ArrayList<>();
        
        String result = formatter.format("", rentals);
        
        assertTrue(result.contains("Rental Record for "));
    }

    @Test
    @DisplayName("FormatLine should produce consistent output for same rental")
    public void testFormatLineConsistency() {
        Rental rental = new Rental(new RegularMovie("Consistent"), 3);
        
        String result1 = formatter.formatLine(rental);
        String result2 = formatter.formatLine(rental);
        
        assertEquals(result1, result2);
    }

    @Test
    @DisplayName("Format should handle very long rental list")
    public void testFormatWithManyRentals() {
        List<Rental> rentals = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            rentals.add(new Rental(new RegularMovie("Movie " + i), 1));
        }
        
        String result = formatter.format("Bulk Customer", rentals);
        
        assertTrue(result.contains("Rental Record"));
        assertTrue(result.contains("Amount owed"));
        assertTrue(result.split("\n").length >= 52); // header + 50 movies + footer
    }
}
