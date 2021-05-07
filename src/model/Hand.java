package model;

import java.util.List;

public class Hand {

    private String handDescription;
    private int handValue;
    private List<Card> cards;

    public Hand(String handDescription, int handValue, List<Card> cards) {
        this.handDescription = handDescription;
        this.handValue = handValue;
        this.cards = cards;
    }

    public String getHandDescription() {
        return handDescription;
    }

    public void setHandDescription(String handDescription) {
        this.handDescription = handDescription;
    }

    public int getHandValue() {
        return handValue;
    }

    public void setHandValue(int handValue) {
        this.handValue = handValue;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

}
