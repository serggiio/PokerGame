package model;

import java.util.ArrayList;

public class Card {
    private String type;
    private String value;
    public String[] cardValues = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };
    public String[] cardTypes = { "corazon", "trebol", "diamante", "espada" };

    public Card() {
        getRamdomCard();
    }

    public Card(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public void getRamdomCard() {
        double randomPositionValue = Math.floor(Math.random() * (cardValues.length));
        double randomPositionType = Math.floor(Math.random() * (cardTypes.length));

        this.value = cardValues[(int) randomPositionValue];
        this.type = cardTypes[(int) randomPositionType];
    }

    public String getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
