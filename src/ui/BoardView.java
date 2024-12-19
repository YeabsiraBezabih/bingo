package ui;

import game.Board;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class BoardView {

    private GridPane gridPane;
    private Board board;
    private boolean isUserBoard;
    private GameController gameController;

    public BoardView(Board board, boolean isUserBoard, GameController gameController) {
        this.board = board;
        this.isUserBoard = isUserBoard;
        this.gameController = gameController;
        createBoardView();
    }

    private void createBoardView() {
        gridPane = new GridPane();
        gridPane.setHgap(8);
        gridPane.setVgap(8);
        gridPane.setAlignment(Pos.CENTER);
        BorderPane.setMargin(gridPane, new Insets(10));
         gridPane.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 10; -fx-border-color: #ccc; -fx-border-width: 2;");
        displayCard();
    }


  private void displayCard() {
    int[][] card = board.getGrid();
    for (int row = 0; row < 5; row++) {
      for (int col = 0; col < 5; col++) {
        Button button = new Button(Integer.toString(card[row][col]));
        button.setPrefSize(60, 60);
        button.setFont(Font.font(16));
        button.setStyle("-fx-background-radius: 5; -fx-border-radius: 5;");
        int finalRow = row;
        int finalCol = col;
        if (isUserBoard) {
          button.setOnAction(event -> markNumber(finalRow, finalCol));
        } else {
            if (gameController.getIsVsAI()){
               button.setDisable(true);
            } else {
               button.setOnAction(event -> markNumber(finalRow, finalCol));
           }
        }
          gridPane.add(button, col, row);
      }
    }
  }

    private void markNumber(int row, int col) {
        gameController.handleNumberClick(row, col, this);
    }


    public void updateBoardDisplay() {
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 5; col++) {
                Button button = (Button) gridPane.getChildren().get(row * 5 + col);
                if (board.isMarked(row, col)) {
                    button.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 5; -fx-border-radius: 5;");
                     button.setFont(Font.font(16));
                    if (board.getGrid()[row][col] == 0) {
                        button.setText("FREE");
                    }
                } else {
                   button.setStyle("-fx-background-radius: 5; -fx-border-radius: 5;");
                    button.setFont(Font.font(16));
                }
            }
        }
    }


    public GridPane getGridPane() {
        return gridPane;
    }

    public void setDisable(boolean disable) {
        gridPane.setDisable(disable);
    }

    public Board getBoard(){
        return board;
    }

    public boolean getIsUserBoard(){
        return isUserBoard;
    }
}