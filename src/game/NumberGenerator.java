package game;


import java.util.Random;
import java.util.Set;
import java.util.HashSet;


public class NumberGenerator {

    private static final Random random = new Random();

    // Generate a unique random number within a range that hasn't been called yet
    public static int generateUniqueNumber(int min, int max, Set<Integer> calledNumbers) {
        int number;
        do {
            number = random.nextInt(max - min + 1) + min; // Generate a random number between min and max
        } while (calledNumbers.contains(number)); // Ensure the number hasn't been called
        return number;
    }

    // Method to generate the Bingo board with unique numbers
    public static int[][] generateUniqueBoard() {
        Set<Integer> calledNumbers = new HashSet<>();
        int[][] board = new int[5][5];
        int number;

        // Fill the board with unique numbers from 1 to 25
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                number = generateUniqueNumber(1, 25, calledNumbers);
                board[i][j] = number;
                calledNumbers.add(number);
            }
        }

        return board;
    }
}