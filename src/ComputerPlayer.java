/**
 * ComputerPlayer class manages computer player actions.
 */
public class ComputerPlayer extends Player {
    private OmokGame game;

    /**
     * Constructor that initializes the OmokGame object.
     * @param game An instance of the OmokGame class.
     */
    public ComputerPlayer(OmokGame game) {
        this.game = game;
    }

    /**
     * Gets the move based on a simple AI strategy.
     * @return A string representing the move in the format "x y".
     */
    @Override
    public String getMove() {
        // Simple AI: place a stone in the first available spot
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (game.getBoardState()[i][j] == '-') {
                    return i + " " + j;  // Return the move as a string
                }
            }
        }
        return "0 0";  // Fallback; should never happen
    }
}
