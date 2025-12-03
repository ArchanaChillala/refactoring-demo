package movierental;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class HtmlStatementFormatterTest {

    private final HtmlStatementFormatter formatter = new HtmlStatementFormatter();

    // ========== FORMAT METHOD TESTS ==========

    @Test
    @DisplayName("Format should generate valid HTML structure for empty rental list")
    public void testFormatWithNoRentals() {
        List<Rental> rentals = new ArrayList<>();
        
        String result = formatter.format("John Doe", rentals);
        
        assertTrue(result.contains("<h1>Rental Record for <em>John Doe</em></h1>"));
        assertTrue(result.contains("<p>Amount owed is <em>0.0</em></p>"));
        assertTrue(result.contains("<p>You earned <em>0</em> frequent renter points</p>"));
        assertFalse(result.contains("<table>")); // No table when no rentals
    }

    @Test
    @DisplayName("Format should generate correct HTML for single rental")
    public void testFormatWithSingleRental() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new RegularMovie("The Matrix"), 3));
        
        String result = formatter.format("Alice", rentals);
        
        assertTrue(result.contains("<h1>Rental Record for <em>Alice</em></h1>"));
        assertTrue(result.contains("<table>"));
        assertTrue(result.contains("<tr><td>The Matrix</td><td>3.5</td></tr>"));
        assertTrue(result.contains("</table>"));
        assertTrue(result.contains("<p>Amount owed is <em>3.5</em></p>"));
        assertTrue(result.contains("<p>You earned <em>1</em> frequent renter points</p>"));
    }

    @Test
    @DisplayName("Format should generate correct HTML for multiple rentals")
    public void testFormatWithMultipleRentals() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new RegularMovie("Movie A"), 2));
        rentals.add(new Rental(new NewReleaseMovie("Movie B"), 3));
        rentals.add(new Rental(new ChildrensMovie("Movie C"), 4));
        
        String result = formatter.format("Bob", rentals);
        
        assertTrue(result.contains("<h1>Rental Record for <em>Bob</em></h1>"));
        assertTrue(result.contains("<table>"));
        assertTrue(result.contains("<tr><td>Movie A</td><td>2.0</td></tr>"));
        assertTrue(result.contains("<tr><td>Movie B</td><td>9.0</td></tr>"));
        assertTrue(result.contains("<tr><td>Movie C</td><td>3.0</td></tr>"));
        assertTrue(result.contains("</table>"));
        assertTrue(result.contains("<p>Amount owed is <em>14.0</em></p>"));
        assertTrue(result.contains("<p>You earned <em>4</em> frequent renter points</p>")); // 1 + 2 (bonus) + 1
    }

    @Test
    @DisplayName("Format should handle customer name with HTML special characters")
    public void testFormatWithHtmlSpecialCharactersInName() {
        List<Rental> rentals = new ArrayList<>();
        
        String result = formatter.format("O'Brien & Co", rentals);
        
        assertTrue(result.contains("<em>O'Brien & Co</em>"));
    }

    @Test
    @DisplayName("Format should produce well-formed HTML structure")
    public void testFormatHtmlStructure() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new RegularMovie("Test"), 1));
        
        String result = formatter.format("Test", rentals);
        
        // Check HTML structure order
        int headerIndex = result.indexOf("<h1>");
        int tableIndex = result.indexOf("<table>");
        int footerIndex = result.indexOf("<p>Amount owed");
        
        assertTrue(headerIndex < tableIndex);
        assertTrue(tableIndex < footerIndex);
    }

    // ========== FORMAT LINE METHOD TESTS ==========

    @Test
    @DisplayName("FormatLine should generate correct HTML table row")
    public void testFormatLineStructure() {
        Rental rental = new Rental(new RegularMovie("Test Movie"), 1);
        
        String result = formatter.formatLine(rental);
        
        assertTrue(result.startsWith("  <tr>"));
        assertTrue(result.endsWith("</tr>"));
        assertTrue(result.contains("<td>Test Movie</td>"));
        assertTrue(result.contains("<td>2.0</td>"));
    }

    @Test
    @DisplayName("FormatLine should format charge with one decimal place in HTML")
    public void testFormatLineDecimalFormatting() {
        Rental rental = new Rental(new RegularMovie("Movie"), 3);
        
        String result = formatter.formatLine(rental);
        
        assertTrue(result.contains("<td>3.5</td>"));
        assertFalse(result.contains("<td>3.50</td>"));
    }

    @Test
    @DisplayName("FormatLine should handle movie titles with HTML special characters")
    public void testFormatLineWithSpecialCharactersInTitle() {
        Rental rental = new Rental(new RegularMovie("Movie: The <Sequel> & More"), 1);
        
        String result = formatter.formatLine(rental);
        
        assertTrue(result.contains("Movie: The <Sequel> & More")); // Raw HTML in test
    }

    @Test
    @DisplayName("FormatLine should maintain consistent indentation")
    public void testFormatLineIndentation() {
        Rental rental = new Rental(new RegularMovie("Test"), 1);
        
        String result = formatter.formatLine(rental);
        
        assertTrue(result.startsWith("  ")); // Two spaces for indentation
    }

    @Test
    @DisplayName("FormatLine should handle large charge amounts in HTML")
    public void testFormatLineWithLargeCharge() {
        Rental rental = new Rental(new NewReleaseMovie("Expensive"), 100);
        
        String result = formatter.formatLine(rental);
        
        assertTrue(result.contains("<td>300.0</td>"));
    }

    // ========== INTEGRATION TESTS ==========

    @Test
    @DisplayName("Format should correctly sum charges for multiple rentals in HTML")
    public void testFormatCalculatesTotalChargeCorrectly() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new RegularMovie("A"), 1)); // 2.0
        rentals.add(new Rental(new RegularMovie("B"), 5)); // 6.5
        rentals.add(new Rental(new NewReleaseMovie("C"), 2)); // 6.0
        
        String result = formatter.format("Test", rentals);
        
        assertTrue(result.contains("<em>14.5</em>"));
    }

    @Test
    @DisplayName("Format should correctly sum frequent renter points in HTML")
    public void testFormatCalculatesTotalPointsCorrectly() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new RegularMovie("A"), 1)); // 1 point
        rentals.add(new Rental(new NewReleaseMovie("B"), 1)); // 1 point
        rentals.add(new Rental(new NewReleaseMovie("C"), 3)); // 2 points (bonus)
        
        String result = formatter.format("Test", rentals);
        
        assertTrue(result.contains("<em>4</em> frequent renter points"));
    }

    @Test
    @DisplayName("Format should generate properly nested HTML table")
    public void testFormatTableNesting() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new RegularMovie("Movie1"), 2));
        rentals.add(new Rental(new RegularMovie("Movie2"), 3));
        
        String result = formatter.format("Customer", rentals);
        
        int tableOpenCount = countOccurrences(result, "<table>");
        int tableCloseCount = countOccurrences(result, "</table>");
        int trOpenCount = countOccurrences(result, "<tr>");
        int trCloseCount = countOccurrences(result, "</tr>");
        
        assertEquals(1, tableOpenCount);
        assertEquals(1, tableCloseCount);
        assertEquals(2, trOpenCount);
        assertEquals(2, trCloseCount);
    }

    @Test
    @DisplayName("Format should separate table rows with newlines")
    public void testFormatTableRowSeparation() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new RegularMovie("Movie1"), 1));
        rentals.add(new Rental(new RegularMovie("Movie2"), 1));
        
        String result = formatter.format("Test", rentals);
        
        assertTrue(result.contains("</tr>\n  <tr>"));
    }

    // ========== EDGE CASE TESTS ==========

    @Test
    @DisplayName("Format should handle empty string customer name in HTML")
    public void testFormatWithEmptyCustomerName() {
        List<Rental> rentals = new ArrayList<>();
        
        String result = formatter.format("", rentals);
        
        assertTrue(result.contains("<h1>Rental Record for <em></em></h1>"));
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
    @DisplayName("Format should handle very long rental list in HTML")
    public void testFormatWithManyRentals() {
        List<Rental> rentals = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            rentals.add(new Rental(new RegularMovie("Movie " + i), 1));
        }
        
        String result = formatter.format("Bulk Customer", rentals);
        
        assertTrue(result.contains("<h1>Rental Record"));
        assertTrue(result.contains("<table>"));
        int trCount = countOccurrences(result, "<tr>");
        assertEquals(50, trCount);
    }

    @Test
    @DisplayName("Format should handle maximum integer days rented")
    public void testFormatWithMaxDaysRented() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new NewReleaseMovie("Long Rental"), Integer.MAX_VALUE));
        
        String result = formatter.format("Test", rentals);
        
        assertNotNull(result);
        assertTrue(result.contains("<h1>Rental Record"));
    }

    @Test
    @DisplayName("Format should not include table when rentals are empty")
    public void testFormatNoTableWhenEmpty() {
        List<Rental> rentals = new ArrayList<>();
        
        String result = formatter.format("NoRentals", rentals);
        
        assertFalse(result.contains("<table>"));
        assertFalse(result.contains("</table>"));
        assertFalse(result.contains("<tr>"));
    }

    @Test
    @DisplayName("Format footer should use proper HTML emphasis tags")
    public void testFormatFooterEmphasisTags() {
        List<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(new RegularMovie("Test"), 1));
        
        String result = formatter.format("Test", rentals);
        
        assertTrue(result.contains("<em>2.0</em>"));
        assertTrue(result.contains("<em>1</em>"));
    }

    @Test
    @DisplayName("Format should handle long customer names in HTML")
    public void testFormatWithLongCustomerName() {
        List<Rental> rentals = new ArrayList<>();
        String longName = "ThisIsAVeryLongCustomerNameForTestingHTMLFormatting";
        
        String result = formatter.format(longName, rentals);
        
        assertTrue(result.contains("<em>" + longName + "</em>"));
    }

    // ========== HELPER METHODS ==========

    private int countOccurrences(String text, String pattern) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(pattern, index)) != -1) {
            count++;
            index += pattern.length();
        }
        return count;
    }
}
