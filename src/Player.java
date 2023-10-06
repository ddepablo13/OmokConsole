/**
 * Player is an abstract class for players.
 */
public abstract class Player {

    /**
     * Abstract method to get the player's move.
     * @return A string representing the move in the format "x y", or "quit" to exit the game.
     */
    public abstract String getMove();
}
