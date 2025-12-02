package movierental;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private final String name;
    private List<Rental> rentals = new ArrayList<>();

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

    private String statementHeader() {
        return String.format("Rental Record for %s\n", getName());
    }

    private String statementBody() {
        return rentals.stream()
                .map(Rental::getIndividualStatementLine)
                .reduce("", String::concat);
    }

    private String statementFooter() {
        return String.format("Amount owed is %s\n" +
                        "You earned %d frequent renter points",
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
