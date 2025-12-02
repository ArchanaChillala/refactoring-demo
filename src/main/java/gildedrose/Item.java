package gildedrose;

public class Item {

    private final String name;
    private final SellIn sellIn;
    private final Quality quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = new SellIn(sellIn);
        this.quality = new Quality(quality);
    }

    public static Item create(String name, int sellIn, int quality) {
        return ItemFactory.createItem(name, sellIn, quality);
    }

    public String getName() {
        return name;
    }

    public int getSellIn() {
        return sellIn.getValue();
    }

    public int getQuality() {
        return quality.getValue();
    }

    public void updateQuality() {
        decreaseQuality();
        decreaseSellIn();
        applyExpiredPenalty();
    }

    protected void decreaseQuality() {
        quality.decrease();
    }

    protected void decreaseSellIn() {
        sellIn.decrease();
    }

    protected void applyExpiredPenalty() {
        if (isExpired() && quality.isPositive()) {
            quality.decrease();
        }
    }

    protected void increaseQuality() {
        quality.increase();
    }

    protected void resetQuality() {
        quality.reset();
    }

    protected boolean isExpired() {
        return sellIn.isExpired();
    }

    protected boolean isSellInWithin(int days) {
        return sellIn.isWithin(days);
    }

    @Override
    public String toString() {
        return String.format("%s, %d, %d", this.name, this.sellIn.getValue(), this.quality.getValue());
    }
}