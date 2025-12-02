package gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class QualityTest {

    @Test
    @DisplayName("Quality initialized with valid value")
    void qualityInitializedWithValidValue() {
        Quality quality = new Quality(25);
        assertEquals(25, quality.getValue());
    }

    @Test
    @DisplayName("Quality decrease reduces value by 1")
    void qualityDecreaseReducesValueBy1() {
        Quality quality = new Quality(10);
        quality.decrease();
        assertEquals(9, quality.getValue());
    }

    @Test
    @DisplayName("Quality decrease does not go below 0")
    void qualityDecreaseDoesNotGoBelowZero() {
        Quality quality = new Quality(0);
        quality.decrease();
        assertEquals(0, quality.getValue());
    }

    @Test
    @DisplayName("Quality decrease at 1 becomes 0")
    void qualityDecreaseAt1BecomesZero() {
        Quality quality = new Quality(1);
        quality.decrease();
        assertEquals(0, quality.getValue());
    }

    @Test
    @DisplayName("Quality increase raises value by 1")
    void qualityIncreaseRaisesValueBy1() {
        Quality quality = new Quality(25);
        quality.increase();
        assertEquals(26, quality.getValue());
    }

    @Test
    @DisplayName("Quality increase does not exceed 50")
    void qualityIncreaseDoesNotExceed50() {
        Quality quality = new Quality(50);
        quality.increase();
        assertEquals(50, quality.getValue());
    }

    @Test
    @DisplayName("Quality increase at 49 becomes 50")
    void qualityIncreaseAt49Becomes50() {
        Quality quality = new Quality(49);
        quality.increase();
        assertEquals(50, quality.getValue());
    }

    @Test
    @DisplayName("Quality reset sets value to 0")
    void qualityResetSetsValueToZero() {
        Quality quality = new Quality(30);
        quality.reset();
        assertEquals(0, quality.getValue());
    }

    @Test
    @DisplayName("Quality isPositive returns true when > 0")
    void qualityIsPositiveReturnsTrueWhenGreaterThanZero() {
        Quality quality = new Quality(1);
        assertTrue(quality.isPositive());
    }

    @Test
    @DisplayName("Quality isPositive returns false when = 0")
    void qualityIsPositiveReturnsFalseWhenZero() {
        Quality quality = new Quality(0);
        assertFalse(quality.isPositive());
    }

    @Test
    @DisplayName("Multiple decreases work correctly")
    void multipleDecreasesWorkCorrectly() {
        Quality quality = new Quality(5);
        for (int i = 0; i < 3; i++) {
            quality.decrease();
        }
        assertEquals(2, quality.getValue());
    }

    @Test
    @DisplayName("Multiple increases work correctly")
    void multipleIncreasesWorkCorrectly() {
        Quality quality = new Quality(45);
        for (int i = 0; i < 3; i++) {
            quality.increase();
        }
        assertEquals(48, quality.getValue());
    }

    @Test
    @DisplayName("Multiple increases cap at 50")
    void multipleIncreasesCapAt50() {
        Quality quality = new Quality(48);
        for (int i = 0; i < 5; i++) {
            quality.increase();
        }
        assertEquals(50, quality.getValue());
    }

    @Test
    @DisplayName("Multiple decreases floor at 0")
    void multipleDecreasesFloorAtZero() {
        Quality quality = new Quality(2);
        for (int i = 0; i < 5; i++) {
            quality.decrease();
        }
        assertEquals(0, quality.getValue());
    }

    @Test
    @DisplayName("Quality can be initialized at boundary 0")
    void qualityCanBeInitializedAtBoundaryZero() {
        Quality quality = new Quality(0);
        assertEquals(0, quality.getValue());
        assertFalse(quality.isPositive());
    }

    @Test
    @DisplayName("Quality can be initialized at boundary 50")
    void qualityCanBeInitializedAtBoundary50() {
        Quality quality = new Quality(50);
        assertEquals(50, quality.getValue());
        assertTrue(quality.isPositive());
    }

    @Test
    @DisplayName("Quality can be initialized above 50 for legendary items")
    void qualityCanBeInitializedAbove50ForLegendaryItems() {
        Quality quality = new Quality(80);
        assertEquals(80, quality.getValue());
        assertTrue(quality.isPositive());
    }
}
