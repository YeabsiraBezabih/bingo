package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class ChoicePage {

    private VBox root;
    private BingoGUI bingoGUI;

    public ChoicePage(BingoGUI bingoGUI) {
        this.bingoGUI = bingoGUI;
        createUI();
    }

  private void createUI() {
      root = new VBox(20);
      root.setAlignment(Pos.CENTER);
      root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

      Text title = new Text("Choose your game mode:");
      title.setFont(Font.font("Verdana", FontWeight.BOLD, 32));
     title.setFill(Color.WHITE);

      Button aiButton = new Button("Play against AI");
      aiButton.setOnAction(event -> bingoGUI.showGame(true));
      aiButton.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
      aiButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10 20; -fx-background-radius: 5;");
      Button playerButton = new Button("Play against Player");
     playerButton.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
     playerButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10 20; -fx-background-radius: 5;");
      playerButton.setOnAction(event -> bingoGUI.showGame(false));


      root.getChildren().addAll(title, aiButton, playerButton);
  }
    public VBox getRoot() {
        return root;
    }
}