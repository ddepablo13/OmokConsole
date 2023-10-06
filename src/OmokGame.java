/**
 * OmokGame class manages the game board and game logic.
 */
public class OmokGame {
    private char[][] board;
    private char currentPlayer;

    /**
     * Constructor that initializes the board and sets the current player.
     * @param size The size of the board.
     */
    public OmokGame(int size) {
        board = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }
        currentPlayer = 'X';
    }

    /**
     * Places a stone on the board if the position is empty.
     * @param x X-coordinate.
     * @param y Y-coordinate.
     */
    public void placeStone(int x, int y) {
        if (board[x][y] == '-') {
            board[x][y] = currentPlayer;
        }
    }

    /**
     * Returns the current state of the board.
     * @return 2D char array representing the board.
     */
    public char[][] getBoardState() {
        return board;
    }

    /**
     * Switches the current player.
     */
    public void nextTurn() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    /**
     * Helper function to check for a win in a specific direction.
     * @param x Starting x-coordinate.
     * @param y Starting y-coordinate.
     * @param dx Change in x-coordinate.
     * @param dy Change in y-coordinate.
     * @param player The player to check for.
     * @return True if there is a win in the specified direction, false otherwise.
     */
    private boolean checkDirection(int x, int y, int dx, int dy, char player) {
        for (int i = 0; i < 5; i++) {
            int newX = x + i * dx;
            int newY = y + i * dy;
            if (newX < 0 || newX >= board.length || newY < 0 || newY >= board.length) {
                return false;
            }
            if (board[newX][newY] != player) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks for a winning condition.
     * @return True if there is a winner, false otherwise.
     */
    public boolean isWinner() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '-') continue;
                char player = board[i][j];
                if (checkDirection(i, j, 1, 0, player) ||  // Horizontal
                        checkDirection(i, j, 0, 1, player) ||  // Vertical
                        checkDirection(i, j, 1, 1, player) ||  // Diagonal \
                        checkDirection(i, j, 1, -1, player)) { // Diagonal /
                    return true;
                }
            }
        }
        return false;
    }
}
