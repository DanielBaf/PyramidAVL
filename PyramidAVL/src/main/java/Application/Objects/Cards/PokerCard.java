package Application.Objects.Cards;

import lombok.Data;

/**
 * A poker card
 *
 * @author jefemayoneso
 */
@Data
public class PokerCard {

    private String text;
    private int value;
    private CardType type;

    public PokerCard(String text, int value, CardType type) {
        this.text = text;
        this.value = value;
        this.type = type;
    }

    @Override
    public String toString() {
        return this.text;
    }

}
