import java.util.Scanner;

/**
 * HumanPlayer class manages human player actions.
 */
public class HumanPlayer extends Player {

    /**
     * Gets the move from standard input.
     * @return A string representing the move in the format "x y", or "quit" to exit the game.
     */
    @Override
    public String getMove() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your move 'x y' (space between x and y coordinates), or type 'quit' to exit: ");
            String input = scanner.nextLine();
            if ("quit".equalsIgnoreCase(input)) {
                return input;
            }
            // Validate the input here if needed
            String[] parts = input.split(" ");
            if (parts.length == 2) {
                try {
                    Integer.parseInt(parts[0]);
                    Integer.parseInt(parts[1]);
                    return input;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter numbers for x and y coordinates.");
                }
            } else {
                System.out.println("Invalid input. Please enter the move in the format 'x y'.");
            }
        }
    }
}
