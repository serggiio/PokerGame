package model;

import java.util.ArrayList;
import java.util.List;

public abstract class GameActions {

    private Player playerObject;
    // List<Card> playerCards;

    public List<Card> getCards() {

        return this.sortCards();
    }

    public List<Hand> checkHand(Player player) {
        // TODO: revisar ambas manos y establecer un atributo en player ej.
        // player1.hands -> escalera, par de 4
        List<Hand> playerHand = new ArrayList<>();
        boolean color = true;
        boolean flush = true;
        String auxCardType, auxCardValue;

        // caso color, mismo palo
        for (int i = 0; i < player.getCardList().size(); i++) {

            auxCardType = player.getCardList().get(i).getType();
            // auxCardValue = player.getCardList().get(i).getValue();

            if (i + 1 <= player.getCardList().size() - 1) {
                if (!auxCardType.equals(player.getCardList().get(i + 1).getType())) {
                    // caso no hay escalera real
                    System.out.println("Entro al cambio de variable");
                    color = false;
                }
            }
        }

        // Obtener la menor carta para evaluar si tiene escalera
        Card lowCard = this.getLowestCardValue(player);
        System.out.println("carta menor> " + lowCard.getValue());
        int valueAux = Integer.parseInt(lowCard.getValue());

        // Evaluar si tiene escalera
        for (int i = 0; i < 4; i++) {
            valueAux += 1;
            System.out.println("");
            System.out.println("valor de auxiliar " + valueAux);
            flush = this.validateFlush(player, lowCard, valueAux);
            System.out.println("Valor metodo flush> " + flush);
            System.out.println("");
            if (!flush) {
                break;
            }
        }

        System.out.println("Evaluacion COLOR> " + color);
        System.out.println("Evaluacion ESCALERA > " + flush);

        // anadir si es que tiene un criterio de mano
        playerHand.add(new Hand("Par", 2, player.getCardList()));

        return playerHand;

    }

    private boolean validateFlush(Player player, Card lowCard, int valueAux) {
        boolean status = false;
        for (var playerCard : player.getCardList()) {
            // System.out.println(lowCard.getValue() + " 1 " + lowCard.getType());
            // System.out.println(playerCard.getValue() + " 2 " + playerCard.getType());
            if (!lowCard.equals(playerCard)) {
                // !lowCard.getValue().equals(playerCard.getValue()) &&
                // !lowCard.getType().equals(playerCard.getType())
                System.out.println("Valor a evaluar> " + valueAux + " carta> " + playerCard.getValue());
                if (playerCard.getValue().equals(String.valueOf(valueAux))) {
                    status = true;
                    break;

                }
                // System.out.println("Estado flush> " + flush);
            }
        }
        return status;
    }

    public int setWinner(Player player1, Player player2) {

        player1.setHandList(checkHand(player1));
        // player2.setHandList(checkHand(player2));
        System.out.println("*****Inicio envaluar ganador*****");
        System.out.println("Manos jugador 1 => " + player1.getHandList());
        if (player1.getHandList().size() > 0) {
            // caso ambos jugadores tienen almenos una mano
            return player2.getPlayerId();
        } /*
           * else { // caso no tienen manos, se evaluara el numero mas alto de carta Card
           * cardPlayer1 = this.getHighestCard(player1); Card cardPlayer2 =
           * this.getHighestCard(player2); if (Integer.parseInt(cardPlayer1.getValue()) >
           * Integer.parseInt(cardPlayer2.getValue())) {
           * System.out.println("Ganador jugador 1: " + player1.getName()); return
           * player1.getPlayerId(); } else if (Integer.parseInt(cardPlayer2.getValue()) >
           * Integer.parseInt(cardPlayer1.getValue())) {
           * System.out.println("Ganador jugador 2: " + player2.getName()); return
           * player2.getPlayerId(); } else { // TODO: player 1
           * System.out.println("Empate "); return 3; }
           * 
           * }
           */

        return player1.getPlayerId();

    }

    private Card getHighestCard(Player player) {
        Card tempCard = player.getCardList().get(0);
        // int maxNum = Integer.parseInt(player.getCardList().get(0).getValue());
        for (var card : player.getCardList()) {
            if (Integer.parseInt(card.getValue()) > Integer.parseInt(tempCard.getValue())) {
                tempCard = card;
            }
        }

        return tempCard;
    }

    private Card getLowestCardValue(Player player) {
        Card tempCard = player.getCardList().get(0);
        // int maxNum = Integer.parseInt(player.getCardList().get(0).getValue());
        for (var card : player.getCardList()) {

            if (tempCard.getValue().equals("J")) {
                tempCard.setValue("11");
            } else if (card.getValue().equals("J")) {
                card.setValue("11");
            } else if (tempCard.getValue().equals("Q")) {
                tempCard.setValue("12");
            } else if (card.getValue().equals("Q")) {
                card.setValue("12");
            } else if (tempCard.getValue().equals("K")) {
                tempCard.setValue("13");
            } else if (card.getValue().equals("K")) {
                card.setValue("13");
            }

            if (Integer.parseInt(card.getValue()) < Integer.parseInt(tempCard.getValue())) {
                tempCard = card;
            }
        }

        return tempCard;
    }

    private List<Card> sortCards() {

        List<Card> ramdomCardList = new ArrayList<>();

        ramdomCardList.add(new Card());
        ramdomCardList.add(new Card());
        ramdomCardList.add(new Card());
        ramdomCardList.add(new Card());
        ramdomCardList.add(new Card());

        // ramdomCardList.add(new Card("trebol", "8"));
        // ramdomCardList.add(new Card("diamante", "10"));
        // ramdomCardList.add(new Card("diamante", "J"));
        // ramdomCardList.add(new Card("diamante", "Q"));
        // ramdomCardList.add(new Card("diamante", "6"));

        return ramdomCardList;

    }
}
