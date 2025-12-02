package gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class SellInTest {

    @Test
    @DisplayName("SellIn initialized with valid positive value")
    void sellInInitializedWithValidPositiveValue() {
        SellIn sellIn = new SellIn(10);
        assertEquals(10, sellIn.getValue());
    }

    @Test
    @DisplayName("SellIn initialized with zero")
    void sellInInitializedWithZero() {
        SellIn sellIn = new SellIn(0);
        assertEquals(0, sellIn.getValue());
    }

    @Test
    @DisplayName("SellIn initialized with negative value")
    void sellInInitializedWithNegativeValue() {
        SellIn sellIn = new SellIn(-5);
        assertEquals(-5, sellIn.getValue());
    }

    @Test
    @DisplayName("SellIn decrease reduces value by 1")
    void sellInDecreaseReducesValueBy1() {
        SellIn sellIn = new SellIn(10);
        sellIn.decrease();
        assertEquals(9, sellIn.getValue());
    }

    @Test
    @DisplayName("SellIn decrease can go negative")
    void sellInDecreaseCanGoNegative() {
        SellIn sellIn = new SellIn(0);
        sellIn.decrease();
        assertEquals(-1, sellIn.getValue());
    }

    @Test
    @DisplayName("SellIn isExpired returns false when positive")
    void sellInIsExpiredReturnsFalseWhenPositive() {
        SellIn sellIn = new SellIn(5);
        assertFalse(sellIn.isExpired());
    }

    @Test
    @DisplayName("SellIn isExpired returns false when zero")
    void sellInIsExpiredReturnsFalseWhenZero() {
        SellIn sellIn = new SellIn(0);
        assertFalse(sellIn.isExpired());
    }

    @Test
    @DisplayName("SellIn isExpired returns true when negative")
    void sellInIsExpiredReturnsTrueWhenNegative() {
        SellIn sellIn = new SellIn(-1);
        assertTrue(sellIn.isExpired());
    }

    @Test
    @DisplayName("SellIn isWithin returns true when value <= threshold")
    void sellInIsWithinReturnsTrueWhenValueLessThanOrEqualThreshold() {
        SellIn sellIn = new SellIn(10);
        assertTrue(sellIn.isWithin(10));
        assertTrue(sellIn.isWithin(15));
    }

    @Test
    @DisplayName("SellIn isWithin returns false when value > threshold")
    void sellInIsWithinReturnsFalseWhenValueGreaterThanThreshold() {
        SellIn sellIn = new SellIn(11);
        assertFalse(sellIn.isWithin(10));
    }

    @Test
    @DisplayName("SellIn isWithin edge case at exact threshold")
    void sellInIsWithinEdgeCaseAtExactThreshold() {
        SellIn sellIn = new SellIn(5);
        assertTrue(sellIn.isWithin(5));
    }

    @Test
    @DisplayName("SellIn isWithin with negative values")
    void sellInIsWithinWithNegativeValues() {
        SellIn sellIn = new SellIn(-5);
        assertTrue(sellIn.isWithin(0));
        assertTrue(sellIn.isWithin(-5));
        assertFalse(sellIn.isWithin(-10));
    }

    @Test
    @DisplayName("Multiple decreases work correctly")
    void multipleDecreasesWorkCorrectly() {
        SellIn sellIn = new SellIn(5);
        for (int i = 0; i < 3; i++) {
            sellIn.decrease();
        }
        assertEquals(2, sellIn.getValue());
        assertFalse(sellIn.isExpired());
    }

    @Test
    @DisplayName("SellIn transitions from positive to negative through zero")
    void sellInTransitionsFromPositiveToNegativeThroughZero() {
        SellIn sellIn = new SellIn(2);
        
        sellIn.decrease(); // 1
        assertFalse(sellIn.isExpired());
        assertEquals(1, sellIn.getValue());
        
        sellIn.decrease(); // 0
        assertFalse(sellIn.isExpired());
        assertEquals(0, sellIn.getValue());
        
        sellIn.decrease(); // -1
        assertTrue(sellIn.isExpired());
        assertEquals(-1, sellIn.getValue());
    }

    @Test
    @DisplayName("SellIn boundary test at threshold 10")
    void sellInBoundaryTestAtThreshold10() {
        SellIn sellIn11 = new SellIn(11);
        SellIn sellIn10 = new SellIn(10);
        SellIn sellIn9 = new SellIn(9);
        
        assertFalse(sellIn11.isWithin(10));
        assertTrue(sellIn10.isWithin(10));
        assertTrue(sellIn9.isWithin(10));
    }

    @Test
    @DisplayName("SellIn boundary test at threshold 5")
    void sellInBoundaryTestAtThreshold5() {
        SellIn sellIn6 = new SellIn(6);
        SellIn sellIn5 = new SellIn(5);
        SellIn sellIn4 = new SellIn(4);
        
        assertFalse(sellIn6.isWithin(5));
        assertTrue(sellIn5.isWithin(5));
        assertTrue(sellIn4.isWithin(5));
    }
}
