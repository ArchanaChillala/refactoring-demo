package gildedrose;

public class Item {

    protected static final int MAX_QUALITY = 50;
    
    private final String name;
    private int sellIn;
    private int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public static Item create(String name, int sellIn, int quality) {
        return ItemFactory.createItem(name, sellIn, quality);
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn;
    }

    public int getQuality() {
        return quality;
    }

    public void updateQuality() {
        decreaseQuality();
        decreaseSellIn();
        applyExpiredPenalty();
    }

    protected void decreaseQuality() {
        if (quality > 0) {
            quality--;
        }
    }

    protected void decreaseSellIn() {
        sellIn--;
    }

    protected void applyExpiredPenalty() {
        if (isExpired() && quality > 0) {
            quality--;
        }
    }

    protected void increaseQuality() {
        if (quality < MAX_QUALITY) {
            quality++;
        }
    }

    protected void resetQuality() {
        quality = 0;
    }

    protected boolean isExpired() {
        return sellIn < 0;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}