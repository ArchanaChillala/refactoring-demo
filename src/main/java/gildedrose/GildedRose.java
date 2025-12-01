package gildedrose;

public class GildedRose {
    private final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
        replaceWithSpecializedItems();
    }

    private void replaceWithSpecializedItems() {
        for (int i = 0; i < items.length; i++) {
            items[i] = Item.create(items[i].getName(), items[i].getSellIn(), items[i].getQuality());
        }
    }

    public void updateQuality() {
        for (Item item : items) {
            item.updateQuality();
        }
    }
}