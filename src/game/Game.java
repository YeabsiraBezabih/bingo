package game;



import java.util.HashSet;
import java.util.Set;

public class Game {
    private final Board userBoard;
    private final Board opponentBoard;
    private final Set<Integer> calledNumbers;
    private boolean gameEnded;
    private boolean isVsAI; // Determines if the game is against AI
    private final AIPlayer aiPlayer; // Instance of AIPlayer

    public Game() {
        this.userBoard = new Board();
        this.opponentBoard = new Board();
        this.calledNumbers = new HashSet<>();
        this.gameEnded = false;
        this.aiPlayer = new AIPlayer("AI Player"); // Initialize AIPlayer instance
        this.isVsAI = true;
    }

      public int drawNumber() {
            if (gameEnded) {
                return -1; // Game is over, don't draw a new number
            }
            int number = NumberGenerator.generateUniqueNumber(1, 25, calledNumbers);
            calledNumbers.add(number);
            userBoard.markNumber(number);
           opponentBoard.markNumber(number);
          return number;
      }
     public int makeAIMove(){
        int aiNumber = aiPlayer.makeMove(opponentBoard, calledNumbers);
        calledNumbers.add(aiNumber);
        userBoard.markNumber(aiNumber);
        opponentBoard.markNumber(aiNumber);
        return aiNumber;
    }
    public void setGameEnded(boolean gameEnded){
      this.gameEnded = gameEnded;
    }
    public boolean checkWinCondition() {
        int userLines = userBoard.countClearedLines();
        int opponentLines = opponentBoard.countClearedLines();

        if (userLines >= 5) {
            System.out.println("\n\033[1m\033[32mCongratulations!\033[0m Player 1 wins with \033[32m" + userLines + "\033[0m lines cleared!");
            gameEnded = true;
            return true;
        } else if (opponentLines >= 5) {
                System.out.println("\n\033[1m\033[31mAI wins\033[0m with \033[31m" + opponentLines + "\033[0m lines cleared! Better luck next time.");
            gameEnded = true;
            return true;
        }
        return false;
    }
    public Board getUserBoard(){
      return userBoard;
    }

    public Board getOpponentBoard(){
      return opponentBoard;
    }

    public Set<Integer> getCalledNumbers(){
      return calledNumbers;
    }
}