package gildedrose;

public class Conjured extends Item {

    public Conjured(int sellIn, int quality) {
        super("Conjured", sellIn, quality);
    }

    @Override
    public void updateQuality() {
        decreaseQuality();
        decreaseQuality();
        decreaseSellIn();
        applyExpiredPenalty();
    }

    @Override
    protected void applyExpiredPenalty() {
        if (isExpired()) {
            decreaseQuality();
            decreaseQuality();
        }
    }
}
