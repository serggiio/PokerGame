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
            // System.out.println("");
            // System.out.println("valor de auxiliar " + valueAux);
            flush = this.validateFlush(player, lowCard, valueAux);
            // System.out.println("Valor metodo flush> " + flush);
            // System.out.println("");
            if (!flush) {
                break;
            }
        }

        // Evaluar si tiene pares o repetidos
        System.out.println("\nPares o repetidos");
        List<Card> tempPairList = player.getCardList();
        Card tempCard = tempPairList.get(0);
        // tempPairList.remove(0);
        int repeatsNumber = 0;
        List<Hand> tempHands = new ArrayList<>();
        List<Card> tempCards = new ArrayList<>();
        List<Card> validatedCards = new ArrayList<>();

        /*
         * for (int i = 0; i < tempPairList.size(); i++) {
         * System.out.println("inicio FOR> " + tempCard.getValue() + " " +
         * tempCard.getType()); System.out.println("Evaluar> " + tempCard.getValue() +
         * " " + tempCard.getType()); System.out.println("Evaluar> " +
         * tempPairList.get(i).getValue() + " " + tempPairList.get(i).getType()); if
         * (tempCard.getValue() == tempPairList.get(i).getValue()) {
         * System.out.println("Repetido> " + tempPairList.get(i).getValue());
         * repeatsNumber += 1; }
         * 
         * System.out.println("lista> " + tempPairList.get(i).getValue() + " " +
         * tempPairList.get(i).getType()); tempCard = tempPairList.get(i);
         * System.out.println("reemplazado> " + tempCard.getValue() + " " +
         * tempCard.getType()); tempPairList.remove(i); i -= 1; }
         */

        for (int i = 0; i < tempPairList.size(); i++) {
            repeatsNumber = 1;
            tempCards.clear();
            // System.out.println("Contiene la carta validada> " +
            // validatedCards.contains(tempPairList.get(i)));
            for (int j = 0; j < tempPairList.size(); j++) {
                if (!tempPairList.get(i).equals(tempPairList.get(j))) {

                    /*
                     * System.out.println("\n");
                     * 
                     * System.out.println( "Evaluar> " + tempPairList.get(i).getValue() + " " +
                     * tempPairList.get(i).getType()); System.out.println( "Evaluar> " +
                     * tempPairList.get(j).getValue() + " " + tempPairList.get(j).getType());
                     */

                    if (tempPairList.get(i).getValue().equals(tempPairList.get(j).getValue())) {
                        repeatsNumber += 1;

                        // revisar, siempre va con 2
                        tempCards.add(tempPairList.get(i));
                        tempCards.add(tempPairList.get(j));

                    }
                }

            }
            // Si tiene alguna carta repetida agregar la mano
            if (repeatsNumber > 1) {
                System.out.println("Numero de veces repetidos> " + repeatsNumber);

                switch (repeatsNumber) {
                    case 2:
                        tempHands.add(new Hand("Par", 2, tempCards));
                        break;
                    case 3:
                        tempHands.add(new Hand("Trio", 4, tempCards));
                        // System.out.println("add " + tempCards.size());

                        break;
                    case 4:
                        tempHands.add(new Hand("Poker", 8, tempCards));
                        break;
                    default:
                        break;
                }

            }

        }

        /*
         * System.out.println("Revision de manos> "); for (var eachs : tempHands) {
         * System.out.println("\nMano> ");
         * System.out.println(eachs.getHandDescription()); System.out.println("Cards " +
         * eachs.getCards()); for (var cardas : eachs.getCards()) {
         * System.out.println(cardas.getValue() + " " + cardas.getType()); } }
         */

        System.out.println("\n");
        System.out.println("Revision de manos> ");
        for (var each : tempHands) {
            System.out.println("\nMano> ");
            System.out.println(each.getHandDescription() + " " + each.getCards().size());
            for (var card : each.getCards()) {
                System.out.println("Cartas> " + card.getValue() + ", " + card.getType());
            }
        }

        System.out.println("\nEvaluacion COLOR> " + color);
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
                // System.out.println("Valor a evaluar> " + valueAux + " carta> " +
                // playerCard.getValue());
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

        // ramdomCardList.add(new Card());
        // ramdomCardList.add(new Card());
        // ramdomCardList.add(new Card());
        // ramdomCardList.add(new Card());
        // ramdomCardList.add(new Card());

        ramdomCardList.add(new Card("trebol", "2"));
        ramdomCardList.add(new Card("diamante", "2"));
        ramdomCardList.add(new Card("diamante", "8"));
        ramdomCardList.add(new Card("diamante", "8"));
        ramdomCardList.add(new Card("espada", "2"));

        return ramdomCardList;

    }
}
