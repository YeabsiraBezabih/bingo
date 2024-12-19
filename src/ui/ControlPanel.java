package ui;

import game.Board;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ControlPanel {

    private BoardView userBoardView;
    private BoardView aiBoardView;
    private Label calledNumberLabel;
    private BorderPane root;
    private GameController gameController;
    private boolean isVsAI;
    private BingoGUI bingoGUI;
    private static final int BOARD_WIDTH = 450;
    private static final int BOARD_HEIGHT = 450;


    public ControlPanel(boolean isVsAI, BingoGUI bingoGUI) {
        this.bingoGUI = bingoGUI;
        this.isVsAI = isVsAI;
        this.gameController = new GameController(isVsAI, this);
        createUI();
    }


    private void createUI() {
        root = new BorderPane();
        HBox boardsContainer = new HBox();
        boardsContainer.setAlignment(Pos.CENTER);
          boardsContainer.setSpacing(30);

        Board userBoard = gameController.getGame().getUserBoard();
        Board aiBoard = gameController.getGame().getOpponentBoard();

         StackPane userBoardStackPane = new StackPane();
        VBox player1BoardContainer = new VBox(10);
        Label player1Label = new Label("Player 1's Board");
        player1Label.setStyle("-fx-font-size: 20px;");
       userBoardView = new BoardView(userBoard, true, gameController);
        player1BoardContainer.getChildren().addAll(player1Label, userBoardView.getGridPane());
        userBoardStackPane.getChildren().add(player1BoardContainer);

        StackPane aiBoardStackPane = new StackPane();
        VBox player2BoardContainer = new VBox(10);
        Label player2Label = new Label(isVsAI ? "AI's Board" : "Player 2's Board");
        player2Label.setStyle("-fx-font-size: 20px;");
        aiBoardView = new BoardView(aiBoard, false, gameController);
        player2BoardContainer.getChildren().addAll(player2Label, aiBoardView.getGridPane());
         aiBoardStackPane.getChildren().add(player2BoardContainer);

        boardsContainer.getChildren().addAll(userBoardStackPane, aiBoardStackPane);


        BorderPane.setMargin(boardsContainer, new Insets(20));


        // Bottom Controls
        HBox controlsContainer = new HBox(10);
        controlsContainer.setAlignment(Pos.CENTER);
        calledNumberLabel = new Label("Click a number to continue!");
         Button restartButton = new Button("Restart");
         restartButton.setFont(javafx.scene.text.Font.font(16));
        restartButton.setOnAction(event -> restartGame(isVsAI));
          Button quitButton = new Button("Quit");
          quitButton.setFont(javafx.scene.text.Font.font(16));
        quitButton.setOnAction(event -> quitGame());
        controlsContainer.getChildren().addAll(calledNumberLabel, restartButton, quitButton);
      VBox bottomControls = new VBox(10, controlsContainer);
        bottomControls.setAlignment(Pos.CENTER);
        BorderPane.setMargin(bottomControls, new Insets(20));


        root.setCenter(boardsContainer);
        root.setBottom(bottomControls);
    }

    public void updateBoards() {
        userBoardView.updateBoardDisplay();
        aiBoardView.updateBoardDisplay();
    }

    public void setCalledNumberLabel(String text) {
        calledNumberLabel.setText(text);
    }

    public String getCalledNumberLabel() {
        return calledNumberLabel.getText();
    }

    public void disableOtherBoard(boolean isPlayer1Turn) {
        if (isPlayer1Turn) {
            userBoardView.setDisable(false);
            aiBoardView.setDisable(true);
        } else {
            userBoardView.setDisable(true);
            aiBoardView.setDisable(false);
        }
    }


    public void restartGame(boolean isVsAI) {
        bingoGUI.showGame(isVsAI);
    }

    public void goToOptions() {
        bingoGUI.showChoicePage();
    }

    public void quitGame() {
        bingoGUI.getPrimaryStage().close();
    }


    public BorderPane getRoot() {
        return root;
    }
}