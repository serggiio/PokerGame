package model;

import java.util.ArrayList;
import java.util.List;

public class GameController extends GameActions {

    private Player player1, player2;

    public GameController(Player player1, Player player2) {
        super();
        this.player1 = player1;
        this.player2 = player2;

    }

    public void runGame() {
        this.player1.setCardList(this.getCards());
        this.player2.setCardList(this.getCards());

        System.out.println("Inicio del juego, jugador 1: " + this.player1.getName());

        for (Card cardObject : this.player1.getCardList()) {
            System.out.print(cardObject.getType() + " " + cardObject.getValue() + ", ");
        }

        System.out.println("\n Inicio del juego, jugador 2: " + this.player2.getName());

        for (Card cardObject : this.player2.getCardList()) {
            System.out.print(cardObject.getType() + " " + cardObject.getValue() + ", ");
        }
        System.out.println("\n");
        // System.out.println("Objeto jugador: " + this.player2.getCardList());

        int winnerId = this.setWinner(player1, player2);
        System.out.println("Id del individuo ganador: " + winnerId);

    }

}
