package movierental;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String _name;
    private List<Rental> _rentals = new ArrayList<Rental>();

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
        return "Rental Record for " + getName() + "\n";
    }

    private String statementBody() {
        String result = "";
        for (Rental each : _rentals) {
            result += each.getStatementLine();
        }
        return result;
    }

    private String statementFooter() {
        String result = "";
        result += "Amount owed is " + getTotalAmount() + "\n";
        result += "You earned " + getTotalFrequentRenterPoints() + " frequent renter points";
        return result;
    }

    private double getTotalAmount() {
        double totalAmount = 0;
        for (Rental each : _rentals) {
            totalAmount += each.getCharge();
        }
        return totalAmount;
    }

    private int getTotalFrequentRenterPoints() {
        int totalPoints = 0;
        for (Rental each : _rentals) {
            totalPoints += each.getFrequentRenterPoints();
        }
        return totalPoints;
    }
}
