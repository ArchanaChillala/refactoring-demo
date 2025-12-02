package movierental;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private final String _name;
    private List<Rental> _rentals = new ArrayList<>();

    public Customer(String name) {
        _name = name;
    }

    public void addRental(Rental arg) {
        _rentals.add(arg);
    }

    public String getName() {
        return _name;
    }

    public String statement() {
        return statementHeader() + statementBody() + statementFooter();
    }

    private String statementHeader() {
        return String.format("Rental Record for %s\n", getName());
    }

    private String statementBody() {
        return _rentals.stream()
                .map(Rental::getStatementLine)
                .reduce("", String::concat);
    }

    private String statementFooter() {
        return String.format("Amount owed is %s\n" +
                        "You earned %d frequent renter points",
                getTotalAmount(), getTotalFrequentRenterPoints());
    }

    private double getTotalAmount() {
        return _rentals.stream()
                .mapToDouble(Rental::getCharge)
                .sum();
    }

    private int getTotalFrequentRenterPoints() {
        return _rentals.stream()
                .mapToInt(Rental::getFrequentRenterPoints)
                .sum();
    }
}
