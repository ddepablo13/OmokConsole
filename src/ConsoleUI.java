import java.util.Scanner;
import java.util.Random;

/**
 * ConsoleUI class manages the user interface in the console.
 */
public class ConsoleUI {
    private OmokGame game;
    private Player player1, player2;

    /**
     * Constructor that initializes the OmokGame object.
     */
    public ConsoleUI() {
        game = new OmokGame(15);
    }

    /**
     * Starts the game loop, allowing players to take turns and make moves.
     * Also handles the main menu for choosing between human and computer opponents.
     */
    public void startGame() {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (true) {  // Main menu loop
            System.out.println("Welcome to Omok!");
            System.out.println();
            System.out.println("How to Play:");
            System.out.println("Players (Player 1 and 2) take turns placing a stone on the board.");
            System.out.println("The first to get five in a row wins.");
            System.out.println("When you start a match, Player 1 or 2 is randomly selected to go first,");
            System.out.println("so scroll up when you first start a match to see who went/goes first.");
            System.out.println();
            System.out.println("1. Human vs Human");
            System.out.println("2. Human vs Computer");
            System.out.println("    - When playing against the Computer, Computer is automatically Player 2.");
            System.out.println("3. Quit");
            System.out.println();
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            if ("3".equals(choice) || "quit".equalsIgnoreCase(choice)) {
                System.out.println("Exiting game. Goodbye!");
                return;
            }

            // Reset the game board
            game = new OmokGame(15);

            if ("1".equals(choice)) {
                player1 = new HumanPlayer();
                player2 = new HumanPlayer();
            } else if ("2".equals(choice)) {
                player1 = new HumanPlayer();
                player2 = new ComputerPlayer(game);
            } else {
                System.out.println("Invalid choice. Type a number from 1-3.");
                continue;
            }

            // Randomly choose who goes first
            boolean player1GoesFirst = random.nextBoolean();
            if (player1GoesFirst) {
                System.out.println("Player 1 goes first!");
            } else {
                System.out.println("Player 2 goes first!");
            }

            while (true) {  // Game loop
                Player currentPlayer = player1GoesFirst ? player1 : player2;
                displayBoard();
                String moveStr = currentPlayer.getMove();
                if ("quit".equalsIgnoreCase(moveStr)) {
                    System.out.println("Returning to main menu.");
                    break;
                }
                int[] move = parseMove(moveStr);
                if (game.getBoardState()[move[0]][move[1]] == '-') {
                    game.placeStone(move[0], move[1]);
                    if (game.isWinner()) {
                        System.out.println((player1GoesFirst ? "Player 1" : "Player 2") + " wins!");
                        break;
                    }
                    game.nextTurn();
                } else {
                    System.out.println("Cell already occupied. Try again.");
                    continue;
                }

                // Switch turns
                player1GoesFirst = !player1GoesFirst;
            }
        }
    }

    /**
     * Parses the move string into an array of integers.
     * @param moveStr The move string.
     * @return An array of integers representing the move.
     */
    private int[] parseMove(String moveStr) {
        String[] parts = moveStr.split(" ");
        return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
    }

    /**
     * Displays the current state of the board.
     */
    public void displayBoard() {
        char[][] board = game.getBoardState();
        System.out.print("    ");
        for (int i = 0; i < board.length; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();

        System.out.print("    ");
        for (int i = 0; i < board.length; i++) {
            System.out.print("---");
        }
        System.out.println();

        for (int i = 0; i < board.length; i++) {
            System.out.printf("%2d |", i);
            for (int j = 0; j < board[i].length; j++) {
                System.out.print((board[i][j] == '-' ? ' ' : board[i][j]) + " |");
            }
            System.out.println();

            System.out.print("    ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print("---");
            }
            System.out.println();
        }
    }
}
