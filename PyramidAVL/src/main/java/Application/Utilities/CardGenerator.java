/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Application.Utilities;

import Application.Objects.Cards.CardType;
import Application.Objects.Cards.PokerCard;
import Application.Web.Exceptions.ApiRequestException;
import org.springframework.http.HttpStatus;

/**
 *
 * @author jefemayoneso
 */
public class CardGenerator {

    /**
     * Create a new Poker Card based on basic data
     *
     * @param value
     * @param cardType
     * @return
     */
    public PokerCard createCard(String value, CardType cardType) {
        PokerCard card;
        int val;
        // calc value of card
        switch (value.toLowerCase()) {
            case "k":
                val = 13;
                break;
            case "q":
                val = 12;
                break;
            case "j":
                val = 11;
                break;
            default: // integer
                val = Integer.valueOf(value.trim());
        }
        if (val < 14 && val > 0) {
            card = new PokerCard(value.toUpperCase() + cardType.getIcon(), val, cardType);
            return card;
        } else {
            throw new ApiRequestException("El valor de la carte debe estar en el rango [1,10] o {J,Q,K}", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get the card type from icon
     *
     * @param icon
     * @return
     */
    public CardType getTypeFromString(String icon) {
        try {
            if (icon.equals(CardType.CLUBS.getIcon())) {
                return CardType.CLUBS;
            } else if (icon.equals(CardType.DIAMONDS.getIcon())) {
                return CardType.DIAMONDS;
            } else if (icon.equals(CardType.HEARTS.getIcon())) {
                return CardType.HEARTS;
            } else if (icon.equals(CardType.SPADES.getIcon())) {
                return CardType.SPADES;
            } else {
                return CardType.INVALID;
            }
        } catch (Exception e) {
            throw new ApiRequestException("Tipo de carta invalido: " + icon, HttpStatus.BAD_REQUEST);
        }
    }

}
