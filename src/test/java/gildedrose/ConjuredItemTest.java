package gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class ConjuredItemTest {

    @Test
    @DisplayName("Conjured item quality degrades by 2 before sell date")
    void conjuredItemQualityDegradesByTwoBeforeSellDate() {
        Item[] items = new Item[] { new Item("Conjured", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(9, items[0].getSellIn());
        assertEquals(18, items[0].getQuality());
    }

    @Test
    @DisplayName("Conjured item quality degrades by 4 after sell date")
    void conjuredItemQualityDegradesByFourAfterSellDate() {
        Item[] items = new Item[] { new Item("Conjured", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(-1, items[0].getSellIn());
        assertEquals(16, items[0].getQuality());
    }

    @Test
    @DisplayName("Conjured item quality never goes negative")
    void conjuredItemQualityNeverNegative() {
        Item[] items = new Item[] { new Item("Conjured", 5, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(4, items[0].getSellIn());
        assertEquals(0, items[0].getQuality());
    }

    @Test
    @DisplayName("Conjured item with quality 1 before sell date becomes 0")
    void conjuredItemWithQuality1BeforeSellDateBecomesZero() {
        Item[] items = new Item[] { new Item("Conjured", 5, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(4, items[0].getSellIn());
        assertEquals(0, items[0].getQuality());
    }

    @Test
    @DisplayName("Conjured item with quality 3 after sell date becomes 0")
    void conjuredItemWithQuality3AfterSellDateBecomesZero() {
        Item[] items = new Item[] { new Item("Conjured", -1, 3) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(-2, items[0].getSellIn());
        assertEquals(0, items[0].getQuality());
    }

    @Test
    @DisplayName("Conjured item degrades twice as fast as normal item")
    void conjuredItemDegradesTwiceAsFastAsNormalItem() {
        Item[] items = new Item[] {
            new Item("Conjured", 10, 20),
            new Item("Normal Item", 10, 20)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(9, items[0].getSellIn());
        assertEquals(18, items[0].getQuality()); // Conjured: -2
        assertEquals(9, items[1].getSellIn());
        assertEquals(19, items[1].getQuality()); // Normal: -1
    }

    @Test
    @DisplayName("Conjured item with multiple updates degrades correctly")
    void conjuredItemMultipleUpdates() {
        Item[] items = new Item[] { new Item("Conjured", 5, 20) };
        GildedRose app = new GildedRose(items);
        
        // Day 1: sellIn=5, quality=20 -> sellIn=4, quality=18
        app.updateQuality();
        assertEquals(4, items[0].getSellIn());
        assertEquals(18, items[0].getQuality());
        
        // Day 2: sellIn=4, quality=18 -> sellIn=3, quality=16
        app.updateQuality();
        assertEquals(3, items[0].getSellIn());
        assertEquals(16, items[0].getQuality());
        
        // Day 3: sellIn=3, quality=16 -> sellIn=2, quality=14
        app.updateQuality();
        assertEquals(2, items[0].getSellIn());
        assertEquals(14, items[0].getQuality());
    }
}
