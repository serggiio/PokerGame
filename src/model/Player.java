package model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private String name;
    private Boolean winner;
    public static int playerId = 0;
    private List<Card> cardList;
    private List<Hand> handList;

    public Player(String name) {
        // System.out.println("Jugador: " + playerId + " ");
        playerId++;
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public List<Hand> getHandList() {
        return handList;
    }

    public void setHandList(List<Hand> handList) {
        this.handList = handList;
    }

    public static int getPlayerId() {
        return playerId;
    }

}
