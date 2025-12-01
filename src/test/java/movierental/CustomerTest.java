package movierental;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class CustomerTest {

    @Test
    @DisplayName("Statement with no rentals should show zero amount and zero points")
    public void testStatementWithNoRentals() {
        Customer customer = new Customer("Alice");
        
        String expected = "Rental Record for Alice\n" +
                "Amount owed is 0.0\n" +
                "You earned 0 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Regular movie for 1 day should cost $2.0 and earn 1 point")
    public void testStatementWithSingleRegularMovieOneDayRental() {
        Customer customer = new Customer("Bob");
        customer.addRental(new Rental(new Movie("The Matrix", Movie.REGULAR), 1));
        
        String expected = "Rental Record for Bob\n" +
                "\tThe Matrix\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Regular movie for 2 days should cost $2.0 (at boundary) and earn 1 point")
    public void testStatementWithSingleRegularMovieTwoDaysRental() {
        Customer customer = new Customer("Charlie");
        customer.addRental(new Rental(new Movie("Inception", Movie.REGULAR), 2));
        
        String expected = "Rental Record for Charlie\n" +
                "\tInception\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Regular movie for 3 days should cost $3.5 (charges apply after 2 days) and earn 1 point")
    public void testStatementWithSingleRegularMovieThreeDaysRental() {
        Customer customer = new Customer("Diana");
        customer.addRental(new Rental(new Movie("Interstellar", Movie.REGULAR), 3));
        
        String expected = "Rental Record for Diana\n" +
                "\tInterstellar\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Regular movie for 5 days should cost $6.5 and earn 1 point")
    public void testStatementWithSingleRegularMovieFiveDaysRental() {
        Customer customer = new Customer("Eve");
        customer.addRental(new Rental(new Movie("Avatar", Movie.REGULAR), 5));
        
        String expected = "Rental Record for Eve\n" +
                "\tAvatar\t6.5\n" +
                "Amount owed is 6.5\n" +
                "You earned 1 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("New release for 1 day should cost $3.0 and earn 1 point (no bonus)")
    public void testStatementWithSingleNewReleaseOneDayRental() {
        Customer customer = new Customer("Frank");
        customer.addRental(new Rental(new Movie("Dune", Movie.NEW_RELEASE), 1));
        
        String expected = "Rental Record for Frank\n" +
                "\tDune\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("New release for 2 days should cost $6.0 and earn 2 points (bonus point awarded)")
    public void testStatementWithSingleNewReleaseTwoDaysRental() {
        Customer customer = new Customer("Grace");
        customer.addRental(new Rental(new Movie("Oppenheimer", Movie.NEW_RELEASE), 2));
        
        String expected = "Rental Record for Grace\n" +
                "\tOppenheimer\t6.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 2 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("New release for 3 days should cost $9.0 and earn 2 points (bonus point awarded)")
    public void testStatementWithSingleNewReleaseThreeDaysRental() {
        Customer customer = new Customer("Henry");
        customer.addRental(new Rental(new Movie("Barbie", Movie.NEW_RELEASE), 3));
        
        String expected = "Rental Record for Henry\n" +
                "\tBarbie\t9.0\n" +
                "Amount owed is 9.0\n" +
                "You earned 2 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Children's movie for 1 day should cost $1.5 and earn 1 point")
    public void testStatementWithSingleChildrensMovieOneDayRental() {
        Customer customer = new Customer("Ivy");
        customer.addRental(new Rental(new Movie("Finding Nemo", Movie.CHILDRENS), 1));
        
        String expected = "Rental Record for Ivy\n" +
                "\tFinding Nemo\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Children's movie for 3 days should cost $1.5 (at boundary) and earn 1 point")
    public void testStatementWithSingleChildrensMovieThreeDaysRental() {
        Customer customer = new Customer("Jack");
        customer.addRental(new Rental(new Movie("Frozen", Movie.CHILDRENS), 3));
        
        String expected = "Rental Record for Jack\n" +
                "\tFrozen\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Children's movie for 4 days should cost $3.0 (charges apply after 3 days) and earn 1 point")
    public void testStatementWithSingleChildrensMovieFourDaysRental() {
        Customer customer = new Customer("Karen");
        customer.addRental(new Rental(new Movie("Moana", Movie.CHILDRENS), 4));
        
        String expected = "Rental Record for Karen\n" +
                "\tMoana\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Children's movie for 7 days should cost $7.5 and earn 1 point")
    public void testStatementWithSingleChildrensMovieSevenDaysRental() {
        Customer customer = new Customer("Leo");
        customer.addRental(new Rental(new Movie("The Lion King", Movie.CHILDRENS), 7));
        
        String expected = "Rental Record for Leo\n" +
                "\tThe Lion King\t7.5\n" +
                "Amount owed is 7.5\n" +
                "You earned 1 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Multiple regular movies should sum amounts correctly and award 1 point per rental")
    public void testStatementWithMultipleRegularMovies() {
        Customer customer = new Customer("Mia");
        customer.addRental(new Rental(new Movie("Jaws", Movie.REGULAR), 2));
        customer.addRental(new Rental(new Movie("Golden Eye", Movie.REGULAR), 3));
        customer.addRental(new Rental(new Movie("Titanic", Movie.REGULAR), 5));
        
        String expected = "Rental Record for Mia\n" +
                "\tJaws\t2.0\n" +
                "\tGolden Eye\t3.5\n" +
                "\tTitanic\t6.5\n" +
                "Amount owed is 12.0\n" +
                "You earned 3 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Multiple new releases should earn bonus points for rentals of 2+ days")
    public void testStatementWithMultipleNewReleases() {
        Customer customer = new Customer("Noah");
        customer.addRental(new Rental(new Movie("Short New", Movie.NEW_RELEASE), 1));
        customer.addRental(new Rental(new Movie("Long New", Movie.NEW_RELEASE), 2));
        customer.addRental(new Rental(new Movie("Extended New", Movie.NEW_RELEASE), 3));
        
        String expected = "Rental Record for Noah\n" +
                "\tShort New\t3.0\n" +
                "\tLong New\t6.0\n" +
                "\tExtended New\t9.0\n" +
                "Amount owed is 18.0\n" +
                "You earned 5 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Multiple children's movies should calculate amounts correctly across different rental periods")
    public void testStatementWithMultipleChildrensMovies() {
        Customer customer = new Customer("Olivia");
        customer.addRental(new Rental(new Movie("Bambi", Movie.CHILDRENS), 3));
        customer.addRental(new Rental(new Movie("Toy Story", Movie.CHILDRENS), 4));
        customer.addRental(new Rental(new Movie("Shrek", Movie.CHILDRENS), 6));
        
        String expected = "Rental Record for Olivia\n" +
                "\tBambi\t1.5\n" +
                "\tToy Story\t3.0\n" +
                "\tShrek\t6.0\n" +
                "Amount owed is 10.5\n" +
                "You earned 3 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Mixed movie types should calculate total amounts and points correctly")
    public void testStatementWithMixedMovieTypes() {
        Customer customer = new Customer("Bob");
        customer.addRental(new Rental(new Movie("Jaws", Movie.REGULAR), 2));
        customer.addRental(new Rental(new Movie("Golden Eye", Movie.REGULAR), 3));
        customer.addRental(new Rental(new Movie("Short New", Movie.NEW_RELEASE), 1));
        customer.addRental(new Rental(new Movie("Long New", Movie.NEW_RELEASE), 2));
        customer.addRental(new Rental(new Movie("Bambi", Movie.CHILDRENS), 3));
        customer.addRental(new Rental(new Movie("Toy Story", Movie.CHILDRENS), 4));

        String expected = "" +
                "Rental Record for Bob\n" +
                "\tJaws\t2.0\n" +
                "\tGolden Eye\t3.5\n" +
                "\tShort New\t3.0\n" +
                "\tLong New\t6.0\n" +
                "\tBambi\t1.5\n" +
                "\tToy Story\t3.0\n" +
                "Amount owed is 19.0\n" +
                "You earned 7 frequent renter points";

        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Statement should include customer name in the header")
    public void testStatementWithDifferentCustomerNames() {
        Customer customer = new Customer("John Smith");
        customer.addRental(new Rental(new Movie("The Godfather", Movie.REGULAR), 1));
        
        String expected = "Rental Record for John Smith\n" +
                "\tThe Godfather\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Regular movies should earn exactly 1 point per rental regardless of rental period")
    public void testStatementFrequentRenterPointsRegularMovies() {
        Customer customer = new Customer("Quinn");
        customer.addRental(new Rental(new Movie("Movie1", Movie.REGULAR), 1));
        customer.addRental(new Rental(new Movie("Movie2", Movie.REGULAR), 1));
        customer.addRental(new Rental(new Movie("Movie3", Movie.REGULAR), 1));
        
        String expected = "Rental Record for Quinn\n" +
                "\tMovie1\t2.0\n" +
                "\tMovie2\t2.0\n" +
                "\tMovie3\t2.0\n" +
                "Amount owed is 6.0\n" +
                "You earned 3 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("New releases rented 2+ days should earn bonus frequent renter point")
    public void testStatementFrequentRenterPointsWithBonusNewReleases() {
        Customer customer = new Customer("Rachel");
        customer.addRental(new Rental(new Movie("NewMovie1", Movie.NEW_RELEASE), 1));
        customer.addRental(new Rental(new Movie("NewMovie2", Movie.NEW_RELEASE), 2));
        customer.addRental(new Rental(new Movie("NewMovie3", Movie.NEW_RELEASE), 3));
        
        // 1 point for NewMovie1 (1 day, no bonus)
        // 2 points for NewMovie2 (2 days, gets bonus)
        // 2 points for NewMovie3 (3 days, gets bonus)
        String expected = "Rental Record for Rachel\n" +
                "\tNewMovie1\t3.0\n" +
                "\tNewMovie2\t6.0\n" +
                "\tNewMovie3\t9.0\n" +
                "Amount owed is 18.0\n" +
                "You earned 5 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Statement should handle large number of rentals (10+) correctly")
    public void testStatementWithLargeNumberOfRentals() {
        Customer customer = new Customer("Sam");
        for (int i = 1; i <= 10; i++) {
            customer.addRental(new Rental(new Movie("Movie" + i, Movie.REGULAR), 1));
        }
        
        String expected = "Rental Record for Sam\n" +
                "\tMovie1\t2.0\n" +
                "\tMovie2\t2.0\n" +
                "\tMovie3\t2.0\n" +
                "\tMovie4\t2.0\n" +
                "\tMovie5\t2.0\n" +
                "\tMovie6\t2.0\n" +
                "\tMovie7\t2.0\n" +
                "\tMovie8\t2.0\n" +
                "\tMovie9\t2.0\n" +
                "\tMovie10\t2.0\n" +
                "Amount owed is 20.0\n" +
                "You earned 10 frequent renter points";
        
        assertEquals(expected, customer.statement());
    }

    @Test
    @DisplayName("Regular movie pricing boundary: 2 days = $2.0, 3 days = $3.5")
    public void testStatementAmountCalculationBoundaryRegular() {
        // Testing boundary at 2 days for regular movies
        Customer customer1 = new Customer("TestUser1");
        customer1.addRental(new Rental(new Movie("Regular2Days", Movie.REGULAR), 2));
        
        String expected1 = "Rental Record for TestUser1\n" +
                "\tRegular2Days\t2.0\n" +
                "Amount owed is 2.0\n" +
                "You earned 1 frequent renter points";
        
        assertEquals(expected1, customer1.statement());
        
        Customer customer2 = new Customer("TestUser2");
        customer2.addRental(new Rental(new Movie("Regular3Days", Movie.REGULAR), 3));
        
        String expected2 = "Rental Record for TestUser2\n" +
                "\tRegular3Days\t3.5\n" +
                "Amount owed is 3.5\n" +
                "You earned 1 frequent renter points";
        
        assertEquals(expected2, customer2.statement());
    }

    @Test
    @DisplayName("Children's movie pricing boundary: 3 days = $1.5, 4 days = $3.0")
    public void testStatementAmountCalculationBoundaryChildrens() {
        // Testing boundary at 3 days for children's movies
        Customer customer1 = new Customer("TestUser3");
        customer1.addRental(new Rental(new Movie("Childrens3Days", Movie.CHILDRENS), 3));
        
        String expected1 = "Rental Record for TestUser3\n" +
                "\tChildrens3Days\t1.5\n" +
                "Amount owed is 1.5\n" +
                "You earned 1 frequent renter points";
        
        assertEquals(expected1, customer1.statement());
        
        Customer customer2 = new Customer("TestUser4");
        customer2.addRental(new Rental(new Movie("Childrens4Days", Movie.CHILDRENS), 4));
        
        String expected2 = "Rental Record for TestUser4\n" +
                "\tChildrens4Days\t3.0\n" +
                "Amount owed is 3.0\n" +
                "You earned 1 frequent renter points";
        
        assertEquals(expected2, customer2.statement());
    }
}