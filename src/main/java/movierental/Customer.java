package movierental;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Customer {

    private static final String RENTAL_RECORD_TEXT = "Rental Record for";
    private static final String AMOUNT_OWED_TEXT = "Amount owed is";
    private static final String YOU_EARNED_TEXT = "You earned";
    private static final String FREQUENT_RENTER_POINTS_TEXT = "frequent renter points";
    
    private static final String TEXT_HEADER_FORMAT = RENTAL_RECORD_TEXT + " %s\n";
    private static final String TEXT_FOOTER_FORMAT = AMOUNT_OWED_TEXT + " %s\n" + YOU_EARNED_TEXT + " %d " + FREQUENT_RENTER_POINTS_TEXT;
    
    private static final String HTML_HEADER_FORMAT = "<h1>" + RENTAL_RECORD_TEXT + " <em>%s</em></h1>\n";
    private static final String HTML_TABLE_FORMAT = "<table>\n%s\n</table>\n";
    private static final String HTML_FOOTER_FORMAT = "<p>" + AMOUNT_OWED_TEXT + " <em>%.1f</em></p>\n<p>" + YOU_EARNED_TEXT + " <em>%d</em> " + FREQUENT_RENTER_POINTS_TEXT + "</p>";

    private final String name;
    private final List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        return statementHeader() + statementBody() + statementFooter();
    }

    public String htmlStatement() {
        return htmlStatementHeader() + htmlStatementBody() + htmlStatementFooter();
    }

    private String statementHeader() {
        return String.format(TEXT_HEADER_FORMAT, getName());
    }

    private String statementBody() {
        return rentals.stream()
                .map(Rental::getIndividualStatementLine)
                .collect(Collectors.joining());
    }

    private String statementFooter() {
        return String.format(TEXT_FOOTER_FORMAT, getTotalAmount(), getTotalFrequentRenterPoints());
    }

    private String htmlStatementHeader() {
        return String.format(HTML_HEADER_FORMAT, getName());
    }

    private String htmlStatementBody() {
        if (hasNoRentals()) {
            return "";
        }
        return wrapInHtmlTable(generateHtmlTableRows());
    }

    private boolean hasNoRentals() {
        return rentals.isEmpty();
    }

    private String generateHtmlTableRows() {
        return rentals.stream()
                .map(Rental::getHtmlTableRow)
                .collect(Collectors.joining("\n"));
    }

    private String wrapInHtmlTable(String tableRows) {
        return String.format(HTML_TABLE_FORMAT, tableRows);
    }

    private String htmlStatementFooter() {
        return String.format(HTML_FOOTER_FORMAT, getTotalAmount(), getTotalFrequentRenterPoints());
    }

    private double getTotalAmount() {
        return rentals.stream()
                .mapToDouble(Rental::getCharge)
                .sum();
    }

    private int getTotalFrequentRenterPoints() {
        return rentals.stream()
                .mapToInt(Rental::getFrequentRenterPoints)
                .sum();
    }
}
