package game;

import java.util.Set;

public class AIPlayer extends Player {

    public AIPlayer(String name) {
        super(name);
    }

    /**
     * AI makes an optimized move based on board analysis.
     * @param board AI's Bingo board
     * @param calledNumbers Set of already called numbers
     * @return The chosen number
     */
    public int makeMove(Board board, Set<Integer> calledNumbers) {
        // Analyze rows, columns, and diagonals
        int[][] grid = board.getGrid();
        int size = grid.length;
        int bestMove = -1;

        // Track line progress and best choice
        int maxMarks = -1;

        // Check rows
        for (int row = 0; row < size; row++) {
            int marks = 0;
            int candidate = -1;
            for (int col = 0; col < size; col++) {
                if (board.isMarked(row, col)) {
                    marks++;
                } else if (!calledNumbers.contains(grid[row][col])) {
                    candidate = grid[row][col];
                }
            }
            if (marks > maxMarks && candidate != -1) {
                maxMarks = marks;
                bestMove = candidate;
            }
        }

        // Check columns
        for (int col = 0; col < size; col++) {
            int marks = 0;
            int candidate = -1;
            for (int row = 0; row < size; row++) {
                if (board.isMarked(row, col)) {
                    marks++;
                } else if (!calledNumbers.contains(grid[row][col])) {
                    candidate = grid[row][col];
                }
            }
            if (marks > maxMarks && candidate != -1) {
                maxMarks = marks;
                bestMove = candidate;
            }
        }

        // Check diagonals
        int[] diagonals = {0, 0}; // [left-to-right, right-to-left]
        int[] candidates = {-1, -1};
        for (int i = 0; i < size; i++) {
            // Left-to-right diagonal
            if (board.isMarked(i, i)) {
                diagonals[0]++;
            } else if (!calledNumbers.contains(grid[i][i])) {
                candidates[0] = grid[i][i];
            }

            // Right-to-left diagonal
            if (board.isMarked(i, size - 1 - i)) {
                diagonals[1]++;
            } else if (!calledNumbers.contains(grid[i][size - 1 - i])) {
                candidates[1] = grid[i][size - 1 - i];
            }
        }
        for (int d = 0; d < 2; d++) {
            if (diagonals[d] > maxMarks && candidates[d] != -1) {
                maxMarks = diagonals[d];
                bestMove = candidates[d];
            }
        }

        // Fallback: Random valid number
        if (bestMove == -1) {
            bestMove = NumberGenerator.generateUniqueNumber(1, size * size, calledNumbers);
        }

        return bestMove;
    }
}