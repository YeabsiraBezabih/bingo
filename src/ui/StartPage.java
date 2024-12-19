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

public class StartPage {

    private VBox root;
    private BingoGUI bingoGUI;

    public StartPage(BingoGUI bingoGUI) {
        this.bingoGUI = bingoGUI;
        createUI();
    }

    private void createUI() {
      root = new VBox(20);
      root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Text title = new Text("Bingo Bash");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 48));
        title.setFill(Color.WHITE);

        Button startButton = new Button("Start Game");
         startButton.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
         startButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-padding: 10 20; -fx-background-radius: 5;");
        startButton.setOnAction(event -> bingoGUI.showChoicePage());


        root.getChildren().addAll(title, startButton);
    }

    public VBox getRoot() {
        return root;
    }
}