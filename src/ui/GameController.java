package ui;

import game.Board;
import game.Game;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class GameController {

  private Game game;
  private ControlPanel controlPanel;
  private boolean isVsAI;
  private boolean player1Turn = true;

  public GameController(boolean isVsAI, ControlPanel controlPanel) {
    this.isVsAI = isVsAI;
    this.game = new Game();
    this.controlPanel = controlPanel;
  }

   public void handleNumberClick(int row, int col, BoardView boardView) {
        Board board = boardView.getBoard();
      if (!board.isMarked(row, col)) {
            if (isVsAI) {
                 if (boardView.getIsUserBoard()) {
                      int number = board.getGrid()[row][col];
                      board.markNumber(number);
                    game.getOpponentBoard().markNumber(number);
                     int aiNumber = game.makeAIMove();
                      controlPanel.setCalledNumberLabel("AI called number: " + aiNumber);
                      game.getUserBoard().markNumber(aiNumber);
                    game.getOpponentBoard().markNumber(aiNumber);
                 }
               controlPanel.updateBoards();
                if (checkWinCondition()) {
                   handleGameOver();
                }
            } else {
             if (boardView.getIsUserBoard() && player1Turn){
                int number = board.getGrid()[row][col];
                  board.markNumber(number);
                   game.getOpponentBoard().markNumber(number);
                player1Turn = false;
               controlPanel.setCalledNumberLabel("Player 2 Turn");
            } else if (!boardView.getIsUserBoard() && !player1Turn){
                int number = board.getGrid()[row][col];
                  board.markNumber(number);
                   game.getUserBoard().markNumber(number);
                  player1Turn = true;
                 controlPanel.setCalledNumberLabel("Player 1 Turn");
            }
             controlPanel.updateBoards();
              if (checkWinCondition()) {
                 handleGameOver();
               }
         }
        }
    }
  private boolean checkWinCondition() {
    return game.checkWinCondition();
  }

  private void handleGameOver() {
    String message;
     int userLines = game.getUserBoard().countClearedLines();
    int opponentLines = game.getOpponentBoard().countClearedLines();
    if (userLines >= 5) {
      message = "\nCongratulations! Player 1 wins with " + userLines + " lines cleared!";
    } else {
      if (isVsAI) {
        message = "\nAI wins with " + opponentLines + " lines cleared! Better luck next time.";
      } else {
        message = "\nPlayer 2 wins with " + opponentLines + " lines cleared! Better luck next time.";
      }
    }

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Game Over");
    alert.setHeaderText("The game has finished!" + message);
    alert.setContentText("What would you like to do?");

    ButtonType restartButton = new ButtonType("Restart Game");
    ButtonType optionsButton = new ButtonType("Go to Options");
    ButtonType quitButton = new ButtonType("Quit Game");

    alert.getButtonTypes().setAll(restartButton, optionsButton, quitButton);

    alert.showAndWait().ifPresent(response -> {
      if (response == restartButton) {
        controlPanel.restartGame(isVsAI);
      } else if (response == optionsButton) {
        controlPanel.goToOptions();
      } else if (response == quitButton) {
        controlPanel.quitGame();
      }
    });
  }

  public Game getGame() {
    return game;
  }
    public boolean getIsVsAI(){
      return isVsAI;
  }
}