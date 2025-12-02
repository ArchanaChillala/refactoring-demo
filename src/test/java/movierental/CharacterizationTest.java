package movierental;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.Assert.*;

/**
 * Characterization Tests for Movie Rental System
 * 
 * These tests document and verify the existing behavior of the system as a baseline.
 * They serve as regression tests to ensure refactoring doesn't change functionality.
 * 
 * Test Coverage Summary:
 * - Statement format and structure verification
 * - Cross-cutting behavior patterns
 * - System-wide edge cases
 */
public class CharacterizationTest {

    // ========== SYSTEM-WIDE BEHAVIOR CHARACTERIZATION ==========

    @Test
    @DisplayName("CHARACTERIZATION: Complete system behavior - pricing and points across all movie types")
    public void characterizeCompleteSystemBehavior() {
        Customer customer = new Customer("System Test");
        customer.addRental(new Rental(new RegularMovie("Regular 1 day"), 1));
        customer.addRental(new Rental(new RegularMovie("Regular 5 days"), 5));
        customer.addRental(new Rental(new NewReleaseMovie("New 1 day"), 1));
        customer.addRental(new Rental(new NewReleaseMovie("New 3 days"), 3));
        customer.addRental(new Rental(new ChildrensMovie("Children 1 day"), 1));
        customer.addRental(new Rental(new ChildrensMovie("Children 7 days"), 7));
        
        String statement = customer.statement();
        
        // Verify all charges
        assertTrue(statement.contains("2.0"));  // Regular 1 day
        assertTrue(statement.contains("6.5"));  // Regular 5 days
        assertTrue(statement.contains("3.0"));  // New 1 day OR Children 7 days base (needs disambiguation)
        assertTrue(statement.contains("9.0"));  // New 3 days
        assertTrue(statement.contains("1.5"));  // Children 1 day
        assertTrue(statement.contains("7.5"));  // Children 7 days
        
        // Total: 2.0 + 6.5 + 3.0 + 9.0 + 1.5 + 7.5 = 29.5
        assertTrue(statement.contains("Amount owed is 29.5"));
        
        // Points: 1 + 1 + 1 + 2 + 1 + 1 = 7
        assertTrue(statement.contains("You earned 7 frequent renter points"));
    }

    
    // ========== STATEMENT FORMAT CHARACTERIZATION ==========
    
    @Test
    @DisplayName("CHARACTERIZATION: Statement format structure and conventions")
    public void characterizeStatementFormat() {
        Customer customer = new Customer("Alice");
        customer.addRental(new Rental(new RegularMovie("Movie"), 3));
        
        String statement = customer.statement();
        
        // Verify header format
        assertTrue(statement.startsWith("Rental Record for Alice\n"));
        
        // Verify rental line format (tab, title, tab, amount, newline)
        assertTrue(statement.contains("\tMovie\t3.5\n"));
        
        // Verify footer format (no space before newline)
        assertTrue(statement.contains("Amount owed is 3.5\nYou earned"));
        assertTrue(statement.contains("You earned 1 frequent renter points"));
        
        // Verify no plural handling ("1 frequent renter points" not "1 frequent renter point")
        assertFalse(statement.contains("1 frequent renter point\n"));
    }

    @Test
    @DisplayName("CHARACTERIZATION: Decimal formatting always shows one decimal place")
    public void characterizeDecimalFormatting() {
        Customer customer = new Customer("Test");
        customer.addRental(new Rental(new RegularMovie("M1"), 3));  // 3.5
        customer.addRental(new Rental(new NewReleaseMovie("M2"), 2));  // 6.0
        
        String statement = customer.statement();
        
        // Individual charges formatted with one decimal place
        assertTrue(statement.contains("\tM1\t3.5\n"));
        assertTrue(statement.contains("\tM2\t6.0\n"));
        
        // Total formatted with one decimal place
        assertTrue(statement.contains("Amount owed is 9.5"));
    }
    
    @Test
    @DisplayName("CHARACTERIZATION: Statement preserves rental order")
    public void characterizeRentalOrdering() {
        Customer customer = new Customer("Test");
        customer.addRental(new Rental(new RegularMovie("First"), 1));
        customer.addRental(new Rental(new ChildrensMovie("Second"), 1));
        customer.addRental(new Rental(new NewReleaseMovie("Third"), 1));
        
        String statement = customer.statement();
        
        // Verify rentals appear in the order they were added
        int firstPos = statement.indexOf("First");
        int secondPos = statement.indexOf("Second");
        int thirdPos = statement.indexOf("Third");
        
        assertTrue(firstPos < secondPos);
        assertTrue(secondPos < thirdPos);
    }
    
    // ========== EDGE CASES CHARACTERIZATION ==========
    
    @Test
    @DisplayName("CHARACTERIZATION: Zero days rental charges base rate (except new releases)")
    public void characterizeZeroDaysRental() {
        Customer customer = new Customer("Test");
        customer.addRental(new Rental(new RegularMovie("R"), 0));
        customer.addRental(new Rental(new ChildrensMovie("C"), 0));
        customer.addRental(new Rental(new NewReleaseMovie("N"), 0));
        
        String statement = customer.statement();
        
        // Regular: $2.0 (base), Children's: $1.5 (base), New Release: $0.0 = $3.5 total
        assertTrue(statement.contains("Amount owed is 3.5"));
        
        // All earn 1 point (new release 0 days doesn't qualify for bonus)
        assertTrue(statement.contains("You earned 3 frequent renter points"));
    }

    
    @Test
    @DisplayName("CHARACTERIZATION: Special characters in customer and movie names are preserved")
    public void characterizeSpecialCharacterHandling() {
        Customer customer = new Customer("Mary Jane-Watson");
        customer.addRental(new Rental(new RegularMovie("Test: Movie & More!"), 1));
        customer.addRental(new Rental(new ChildrensMovie("Movie's Title"), 1));
        
        String statement = customer.statement();
        
        assertTrue(statement.contains("Rental Record for Mary Jane-Watson\n"));
        assertTrue(statement.contains("Test: Movie & More!"));
        assertTrue(statement.contains("Movie's Title"));
    }
    
    @Test
    @DisplayName("CHARACTERIZATION: Pricing model comparison across movie types")
    public void characterizePricingModelDifferences() {
        // Same rental period, different prices
        Movie regular = new RegularMovie("Regular");
        Movie newRelease = new NewReleaseMovie("New");
        Movie childrens = new ChildrensMovie("Children");
        
        // At 5 days:
        // Regular: $2.0 + (5-2)*$1.5 = $6.5
        // New Release: 5*$3.0 = $15.0
        // Children's: $1.5 + (5-3)*$1.5 = $4.5
        
        assertEquals(6.5, regular.getCharge(5), 0.01);
        assertEquals(15.0, newRelease.getCharge(5), 0.01);
        assertEquals(4.5, childrens.getCharge(5), 0.01);
        
        // New releases are always most expensive
        assertTrue(newRelease.getCharge(5) > regular.getCharge(5));
        assertTrue(newRelease.getCharge(5) > childrens.getCharge(5));
    }
    
    @Test
    @DisplayName("CHARACTERIZATION: Frequent renter points only vary by movie type and duration")
    public void characterizeFrequentRenterPointsRules() {
        // Points are independent of price
        // Regular and Children's always give 1 point regardless of duration
        // New releases give 2 points if rented more than 1 day, otherwise 1 point
        
        assertEquals(1, new Rental(new RegularMovie("R"), 1).getFrequentRenterPoints());
        assertEquals(1, new Rental(new RegularMovie("R"), 100).getFrequentRenterPoints());
        
        assertEquals(1, new Rental(new ChildrensMovie("C"), 1).getFrequentRenterPoints());
        assertEquals(1, new Rental(new ChildrensMovie("C"), 100).getFrequentRenterPoints());
        
        assertEquals(1, new Rental(new NewReleaseMovie("N"), 1).getFrequentRenterPoints());
        assertEquals(2, new Rental(new NewReleaseMovie("N"), 2).getFrequentRenterPoints());
        assertEquals(2, new Rental(new NewReleaseMovie("N"), 100).getFrequentRenterPoints());
    }
    
    @Test
    @DisplayName("CHARACTERIZATION: Customer can have zero rentals")
    public void characterizeEmptyCustomer() {
        Customer customer = new Customer("Empty Customer");
        
        String expected = "Rental Record for Empty Customer\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }
    
    @Test
    @DisplayName("CHARACTERIZATION: Customer name is immutable and always reflected in statement")
    public void characterizeCustomerNameBehavior() {
        Customer customer1 = new Customer("John Smith");
        Customer customer2 = new Customer("123");
        Customer customer3 = new Customer("");
        
        assertEquals("John Smith", customer1.getName());
        assertTrue(customer1.statement().contains("Rental Record for John Smith\n"));
        
        assertEquals("123", customer2.getName());
        assertTrue(customer2.statement().contains("Rental Record for 123\n"));
        
        assertEquals("", customer3.getName());
        assertTrue(customer3.statement().contains("Rental Record for \n"));
    }
    
    @Test
    @DisplayName("CHARACTERIZATION: Rental calculation delegates to movie type")
    public void characterizeRentalDelegationPattern() {
        // Rental doesn't calculate charges; it delegates to the Movie
        Rental regularRental = new Rental(new RegularMovie("R"), 5);
        Rental newReleaseRental = new Rental(new NewReleaseMovie("N"), 5);
        Rental childrensRental = new Rental(new ChildrensMovie("C"), 5);
        
        // Verify charge delegation works correctly
        assertEquals(6.5, regularRental.getCharge(), 0.01);
        assertEquals(15.0, newReleaseRental.getCharge(), 0.01);
        assertEquals(4.5, childrensRental.getCharge(), 0.01);
        
        // Verify statement line format
        assertEquals("\tR\t6.5\n", regularRental.getIndividualStatementLine());
        assertEquals("\tN\t15.0\n", newReleaseRental.getIndividualStatementLine());
        assertEquals("\tC\t4.5\n", childrensRental.getIndividualStatementLine());
    }
}
