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
            double thisAmount = amountFor(each);
            result += individualRentalLine(each, thisAmount);
        }
        return result;
    }

    private static String individualRentalLine(Rental each, double thisAmount) {
        return "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(thisAmount) + "\n";
    }

    private String statementFooter() {
        String result = "";
        result += "Amount owed is " + String.valueOf(getTotalAmount()) + "\n";
        result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";
        return result;
    }

    private double amountFor(Rental rental) {
        double amount = 0;
        
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                amount += 2;
                if (rental.getDaysRented() > 2)
                    amount += (rental.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                amount += rental.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                amount += 1.5;
                if (rental.getDaysRented() > 3)
                    amount += (rental.getDaysRented() - 3) * 1.5;
                break;
        }
        
        return amount;
    }

    private int frequentRenterPointsFor(Rental rental) {
        // add frequent renter points
        int points = 1;
        // add bonus for a two day new release rental
        if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && rental.getDaysRented() > 1)
            points++;
        
        return points;
    }

    private double getTotalAmount() {
        double totalAmount = 0;
        for (Rental each : _rentals) {
            totalAmount += amountFor(each);
        }
        return totalAmount;
    }

    private int getTotalFrequentRenterPoints() {
        int totalPoints = 0;
        for (Rental each : _rentals) {
            totalPoints += frequentRenterPointsFor(each);
        }
        return totalPoints;
    }
}
