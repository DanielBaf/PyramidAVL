package Application.Objects.Cards;

/**
 *
 * @author jefemayoneso
 */
public enum CardType {
    CLUBS("♣", 0), // ♣
    DIAMONDS("♦", 20), // ♦
    HEARTS("♥", 40), // ♥
    SPADES("♠", 60), // ♠
    INVALID("", -1);

    private final String icon;
    private final int displacement;

    private CardType(String icon, int displacement) {
        this.icon = icon;
        this.displacement = displacement;
    }

    public String getIcon() {
        return icon;
    }

    public int getDisplacement() {
        return displacement;
    }

}
