package Application.Objects.Cards;

/**
 *
 * @author jefemayoneso
 */
public enum CardType {
    CLUBS("♣"), // ♣
    DIAMONDS("♦"), // ♦
    HEARTS("♥"), // ♥
    SPADES("♠"); // ♠

    private final String icon;

    private CardType(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
