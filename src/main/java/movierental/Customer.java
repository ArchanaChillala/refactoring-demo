package movierental;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Customer {

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
        return String.format("Rental Record for %s\n", getName());
    }

    private String statementBody() {
        return rentals.stream()
                .map(Rental::getIndividualStatementLine)
                .collect(Collectors.joining());
    }

    private String statementFooter() {
        return String.format("Amount owed is %s\n" +
                        "You earned %d frequent renter points",
                getTotalAmount(), getTotalFrequentRenterPoints());
    }

    private String htmlStatementHeader() {
        return String.format("<h1>Rental Record for <em>%s</em></h1>\n", getName());
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
        return String.format("<table>\n%s\n</table>\n", tableRows);
    }

    private String htmlStatementFooter() {
        return String.format("<p>Amount owed is <em>%.1f</em></p>\n" +
                        "<p>You earned <em>%d</em> frequent renter points</p>",
                getTotalAmount(), getTotalFrequentRenterPoints());
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
