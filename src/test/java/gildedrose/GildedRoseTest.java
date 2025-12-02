package gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class GildedRoseTest {

    @Test
    @DisplayName("Normal item quality degrades by 1 before sell date")
    void normalItemQualityDegradesByOneBeforeSellDate() {
        Item[] items = new Item[] { new Item("Normal Item", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(9, items[0].getSellIn());
        assertEquals(19, items[0].getQuality());
    }

    @Test
    @DisplayName("Normal item quality degrades by 2 after sell date")
    void normalItemQualityDegradesByTwoAfterSellDate() {
        Item[] items = new Item[] { new Item("Normal Item", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(-1, items[0].getSellIn());
        assertEquals(18, items[0].getQuality());
    }

    @Test
    @DisplayName("Normal item quality never goes negative")
    void normalItemQualityNeverNegative() {
        Item[] items = new Item[] { new Item("Normal Item", 5, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(4, items[0].getSellIn());
        assertEquals(0, items[0].getQuality());
    }

    @Test
    @DisplayName("Normal item with quality 1 after sell date becomes 0")
    void normalItemWithQuality1AfterSellDateBecomesZero() {
        Item[] items = new Item[] { new Item("Normal Item", -1, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(-2, items[0].getSellIn());
        assertEquals(0, items[0].getQuality());
    }

    @Test
    @DisplayName("Aged Brie increases in quality before sell date")
    void agedBrieIncreasesInQualityBeforeSellDate() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(9, items[0].getSellIn());
        assertEquals(21, items[0].getQuality());
    }

    @Test
    @DisplayName("Aged Brie increases in quality by 2 after sell date")
    void agedBrieIncreasesInQualityByTwoAfterSellDate() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(-1, items[0].getSellIn());
        assertEquals(22, items[0].getQuality());
    }

    @Test
    @DisplayName("Aged Brie quality never exceeds 50")
    void agedBrieQualityNeverExceeds50() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(9, items[0].getSellIn());
        assertEquals(50, items[0].getQuality());
    }

    @Test
    @DisplayName("Aged Brie quality caps at 50 after sell date")
    void agedBrieQualityCapsAt50AfterSellDate() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(-2, items[0].getSellIn());
        assertEquals(50, items[0].getQuality());
    }

    @Test
    @DisplayName("Sulfuras never decreases in quality or sellIn")
    void sulfurasNeverChanges() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(10, items[0].getSellIn());
        assertEquals(80, items[0].getQuality());
    }

    @Test
    @DisplayName("Sulfuras with negative sellIn never changes")
    void sulfurasWithNegativeSellInNeverChanges() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", -1, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(-1, items[0].getSellIn());
        assertEquals(80, items[0].getQuality());
    }

    @Test
    @DisplayName("Backstage passes increase by 1 when sellIn > 10")
    void backstagePassesIncreaseBy1WhenSellInGreaterThan10() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(14, items[0].getSellIn());
        assertEquals(21, items[0].getQuality());
    }

    @Test
    @DisplayName("Backstage passes increase by 2 when sellIn = 10")
    void backstagePassesIncreaseBy2WhenSellInEquals10() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(9, items[0].getSellIn());
        assertEquals(22, items[0].getQuality());
    }

    @Test
    @DisplayName("Backstage passes increase by 2 when sellIn between 6 and 10")
    void backstagePassesIncreaseBy2WhenSellInBetween6And10() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 8, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(7, items[0].getSellIn());
        assertEquals(22, items[0].getQuality());
    }

    @Test
    @DisplayName("Backstage passes increase by 3 when sellIn = 5")
    void backstagePassesIncreaseBy3WhenSellInEquals5() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(4, items[0].getSellIn());
        assertEquals(23, items[0].getQuality());
    }

    @Test
    @DisplayName("Backstage passes increase by 3 when sellIn between 1 and 5")
    void backstagePassesIncreaseBy3WhenSellInBetween1And5() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 3, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(2, items[0].getSellIn());
        assertEquals(23, items[0].getQuality());
    }

    @Test
    @DisplayName("Backstage passes quality drops to 0 after concert")
    void backstagePassesQualityDropsToZeroAfterConcert() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(-1, items[0].getSellIn());
        assertEquals(0, items[0].getQuality());
    }

    @Test
    @DisplayName("Backstage passes quality never exceeds 50 with 15 days left")
    void backstagePassesQualityNeverExceeds50With15DaysLeft() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(14, items[0].getSellIn());
        assertEquals(50, items[0].getQuality());
    }

    @Test
    @DisplayName("Backstage passes quality caps at 50 when increasing by 2")
    void backstagePassesQualityCapsAt50WhenIncreasingBy2() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(9, items[0].getSellIn());
        assertEquals(50, items[0].getQuality());
    }

    @Test
    @DisplayName("Backstage passes quality caps at 50 when increasing by 3")
    void backstagePassesQualityCapsAt50WhenIncreasingBy3() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(4, items[0].getSellIn());
        assertEquals(50, items[0].getQuality());
    }

    @Test
    @DisplayName("Multiple items update correctly in single update")
    void multipleItemsUpdateCorrectly() {
        Item[] items = new Item[] {
            new Item("Normal Item", 10, 20),
            new Item("Aged Brie", 5, 10),
            new Item("Sulfuras, Hand of Ragnaros", 0, 80),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 30)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(9, items[0].getSellIn());
        assertEquals(19, items[0].getQuality());
        
        assertEquals(4, items[1].getSellIn());
        assertEquals(11, items[1].getQuality());
        
        assertEquals(0, items[2].getSellIn());
        assertEquals(80, items[2].getQuality());
        
        assertEquals(14, items[3].getSellIn());
        assertEquals(31, items[3].getQuality());
    }

    @Test
    @DisplayName("Multiple updates simulate multiple days")
    void multipleUpdatesSimulateMultipleDays() {
        Item[] items = new Item[] { new Item("Normal Item", 5, 10) };
        GildedRose app = new GildedRose(items);
        
        for (int i = 0; i < 7; i++) {
            app.updateQuality();
        }
        
        assertEquals(-2, items[0].getSellIn());
        assertEquals(1, items[0].getQuality()); // 5 days at -1, 2 days at -2
    }

    @Test
    @DisplayName("Backstage passes full lifecycle from 11 days to after concert")
    void backstagePassesFullLifecycle() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10) };
        GildedRose app = new GildedRose(items);
        
        // Day 1: 11 -> 10 days, quality +1
        app.updateQuality();
        assertEquals(10, items[0].getSellIn());
        assertEquals(11, items[0].getQuality());
        
        // Days 2-6: 10 -> 5 days, quality +2 each day
        for (int i = 0; i < 5; i++) {
            app.updateQuality();
        }
        assertEquals(5, items[0].getSellIn());
        assertEquals(21, items[0].getQuality());
        
        // Days 7-11: 5 -> 0 days, quality +3 each day
        for (int i = 0; i < 5; i++) {
            app.updateQuality();
        }
        assertEquals(0, items[0].getSellIn());
        assertEquals(36, items[0].getQuality());
        
        // Day 12: after concert, quality drops to 0
        app.updateQuality();
        assertEquals(-1, items[0].getSellIn());
        assertEquals(0, items[0].getQuality());
    }

    @Test
    @DisplayName("Aged Brie full lifecycle over 30 days")
    void agedBrieFullLifecycle() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 0) };
        GildedRose app = new GildedRose(items);
        
        // 10 days before sell date: +1 per day
        for (int i = 0; i < 10; i++) {
            app.updateQuality();
        }
        assertEquals(0, items[0].getSellIn());
        assertEquals(10, items[0].getQuality());
        
        // 20 days after sell date: +2 per day, but capped at 50
        for (int i = 0; i < 20; i++) {
            app.updateQuality();
        }
        assertEquals(-20, items[0].getSellIn());
        assertEquals(50, items[0].getQuality());
    }

    @Test
    @DisplayName("Normal item with 0 quality after sell date stays at 0")
    void normalItemWithZeroQualityAfterSellDateStaysAtZero() {
        Item[] items = new Item[] { new Item("Normal Item", -5, 0) };
        GildedRose app = new GildedRose(items);
        
        for (int i = 0; i < 5; i++) {
            app.updateQuality();
        }
        
        assertEquals(-10, items[0].getSellIn());
        assertEquals(0, items[0].getQuality());
    }

    @Test
    @DisplayName("Edge case: Backstage passes with quality 49 and sellIn 10")
    void backstagePassesEdgeCaseQuality49SellIn10() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(9, items[0].getSellIn());
        assertEquals(50, items[0].getQuality());
    }

    @Test
    @DisplayName("Edge case: Backstage passes with quality 48 and sellIn 5")
    void backstagePassesEdgeCaseQuality48SellIn5() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(4, items[0].getSellIn());
        assertEquals(50, items[0].getQuality());
    }

    @Test
    @DisplayName("Edge case: Normal item quality 1 before sell date")
    void normalItemQuality1BeforeSellDate() {
        Item[] items = new Item[] { new Item("Normal Item", 5, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(4, items[0].getSellIn());
        assertEquals(0, items[0].getQuality());
    }

    @Test
    @DisplayName("Edge case: Aged Brie with quality 49 after sell date")
    void agedBrieQuality49AfterSellDate() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(-2, items[0].getSellIn());
        assertEquals(50, items[0].getQuality());
    }

    @Test
    @DisplayName("Item.create uses factory pattern")
    void itemCreateUsesFactoryPattern() {
        Item agedBrie = Item.create("Aged Brie", 10, 20);
        assertInstanceOf(AgedBrie.class, agedBrie);
        
        Item backstagePass = Item.create("Backstage passes to a TAFKAL80ETC concert", 15, 30);
        assertInstanceOf(BackstagePass.class, backstagePass);
        
        Item sulfuras = Item.create("Sulfuras, Hand of Ragnaros", 0, 80);
        assertInstanceOf(Sulfuras.class, sulfuras);
        
        Item normalItem = Item.create("Normal Item", 10, 20);
        assertEquals(Item.class, normalItem.getClass());
    }

    @Test
    @DisplayName("GildedRose constructor converts items to polymorphic types")
    void gildedRoseConstructorConvertsItemsToPolymorphicTypes() {
        Item[] items = new Item[] {
            new Item("Aged Brie", 10, 20),
            new Item("Backstage passes to a TAFKAL80ETC concert", 15, 30),
            new Item("Normal Item", 5, 10)
        };
        
        new GildedRose(items);
        
        assertInstanceOf(AgedBrie.class, items[0]);
        assertInstanceOf(BackstagePass.class, items[1]);
        assertEquals(Item.class, items[2].getClass());
    }

    @Test
    @DisplayName("Empty item array handled correctly")
    void emptyItemArrayHandledCorrectly() {
        Item[] items = new Item[] {};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items.length);
    }

    @Test
    @DisplayName("Single item array works correctly")
    void singleItemArrayWorksCorrectly() {
        Item[] items = new Item[] { new Item("Normal Item", 5, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].getSellIn());
        assertEquals(9, items[0].getQuality());
    }

    @Test
    @DisplayName("Backstage passes at sellIn 1 increases by 3")
    void backstagePassesAtSellIn1IncreasesBy3() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].getSellIn());
        assertEquals(23, items[0].getQuality());
    }

    @Test
    @DisplayName("Backstage passes at sellIn 6 increases by 2")
    void backstagePassesAtSellIn6IncreasesBy2() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 6, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(5, items[0].getSellIn());
        assertEquals(22, items[0].getQuality());
    }

    @Test
    @DisplayName("Backstage passes at sellIn 11 increases by 1")
    void backstagePassesAtSellIn11IncreasesBy1() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(10, items[0].getSellIn());
        assertEquals(21, items[0].getQuality());
    }

    @Test
    @DisplayName("Normal item at sellIn 1 degrades by 1")
    void normalItemAtSellIn1DegradesBy1() {
        Item[] items = new Item[] { new Item("Normal Item", 1, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].getSellIn());
        assertEquals(9, items[0].getQuality());
    }

    @Test
    @DisplayName("Aged Brie at sellIn 1 increases by 1")
    void agedBrieAtSellIn1IncreasesBy1() {
        Item[] items = new Item[] { new Item("Aged Brie", 1, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, items[0].getSellIn());
        assertEquals(21, items[0].getQuality());
    }

    @Test
    @DisplayName("Item toString returns correct format")
    void itemToStringReturnsCorrectFormat() {
        Item[] items = new Item[] { new Item("Normal Item", 10, 20) };
        GildedRose app = new GildedRose(items);
        String result = items[0].toString();
        assertEquals("Normal Item, 10, 20", result);
    }

    @Test
    @DisplayName("Backstage passes with quality 47 at sellIn 5 caps at 50")
    void backstagePassesWithQuality47AtSellIn5CapsAt50() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 47) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, items[0].getSellIn());
        assertEquals(50, items[0].getQuality());
    }

    @Test
    @DisplayName("Multiple identical items update independently")
    void multipleIdenticalItemsUpdateIndependently() {
        Item[] items = new Item[] {
            new Item("Normal Item", 5, 10),
            new Item("Normal Item", 5, 10)
        };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        
        assertEquals(4, items[0].getSellIn());
        assertEquals(9, items[0].getQuality());
        assertEquals(4, items[1].getSellIn());
        assertEquals(9, items[1].getQuality());
    }
}
