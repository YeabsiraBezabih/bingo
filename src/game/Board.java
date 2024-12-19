package game;

public class Board {
    private final int[][] board;
    private final boolean[][] marked;

    public Board() {
        this.board = NumberGenerator.generateUniqueBoard(); // Generate a unique Bingo board
        this.marked = new boolean[5][5]; // 5x5 board
    }

    // Method to display the board with formatting and colors
    public void display() {
     // Will be used to display the board on the GUI
    }

    // Mark a number on the board
    public void markNumber(int number) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == number) {
                    marked[i][j] = true; // Mark the number
                }
            }
        }
    }

    // Check if a number at a specific position is marked
    public boolean isMarked(int row, int col) {
        return marked[row][col]; // Return true if the cell is marked
    }

    // Get the grid of numbers on the Bingo board
    public int[][] getGrid() {
        return board; // Return the grid of numbers
    }

    // Count the number of cleared lines (rows, columns, or diagonals)
    public int countClearedLines() {
        int clearedLines = 0;

        // Check rows
        for (int i = 0; i < 5; i++) {
            if (isRowCleared(i)) clearedLines++;
        }

        // Check columns
        for (int i = 0; i < 5; i++) {
            if (isColumnCleared(i)) clearedLines++;
        }

        // Check diagonals
        if (isDiagonalCleared(true)) clearedLines++;  // Top-left to bottom-right
        if (isDiagonalCleared(false)) clearedLines++; // Top-right to bottom-left

        return clearedLines;
    }

    // Check if a specific row is completely marked
    private boolean isRowCleared(int row) {
        for (int j = 0; j < 5; j++) {
            if (!marked[row][j]) return false;
        }
        return true;
    }

    // Check if a specific column is completely marked
    private boolean isColumnCleared(int col) {
        for (int i = 0; i < 5; i++) {
            if (!marked[i][col]) return false;
        }
        return true;
    }

    // Check if any diagonal is completely marked
   private boolean isDiagonalCleared(boolean topLeftToBottomRight) {
    if (topLeftToBottomRight) {
        for (int i = 0; i < 5; i++) {
            if (!marked[i][i]) return false;
        }
    } else {
        for (int i = 0; i < 5; i++) {
            if (!marked[i][4 - i]) return false;
        }
    }
    return true;
}
}