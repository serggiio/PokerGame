import java.util.ArrayList;
import java.util.List;

import model.GameController;
import model.Player;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");
        Player player1 = new Player("Sergio");
        Player player2 = new Player("Juan");

        GameController game = new GameController(player1, player2);
        game.runGame();

    }
}
