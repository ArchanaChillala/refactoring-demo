package gildedrose;

public class GildedRose {
    private final Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
        replaceWithSpecializedItems();
    }

    private void replaceWithSpecializedItems() {
        for (int i = 0; i < items.length; i++) {
            items[i] = createSpecializedItem(items[i]);
        }
    }

    private Item createSpecializedItem(Item item) {
        return Item.create(item.getName(), item.getSellIn(), item.getQuality());
    }

    public void updateQuality() {
        for (Item item : items) {
            item.updateQuality();
        }
    }
}