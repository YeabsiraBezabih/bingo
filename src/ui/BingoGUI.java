package ui;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


public class BingoGUI extends Application {

    private Stage primaryStage;
     private static final int MIN_WIDTH = 1300;
    private static final int MIN_HEIGHT = 730;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.initStyle(StageStyle.DECORATED); // Keep navigation bar
        showStartPage();
    }

     private void transitionScene(Scene scene, Pane root) {
    primaryStage.setScene(scene);
        primaryStage.show();
     primaryStage.setMaximized(true);
        FadeTransition ft = new FadeTransition(Duration.millis(300), root);
      ft.setFromValue(0);
      ft.setToValue(1);
      ft.play();
  }


    private void setInitialWindowPosition(Scene scene) {
         primaryStage.setScene(scene);
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double centerX = screenBounds.getMinX() + (screenBounds.getWidth() - MIN_WIDTH) / 2;
        double centerY = screenBounds.getMinY() + (screenBounds.getHeight() - MIN_HEIGHT) / 2;
        primaryStage.setX(centerX);
        primaryStage.setY(centerY);
        primaryStage.setMinWidth(MIN_WIDTH);
        primaryStage.setMinHeight(MIN_HEIGHT);
       if (!primaryStage.isMaximized()){
          primaryStage.show();
        }
    }


  private void setFullScreen(Scene scene){
     primaryStage.setScene(scene);
     primaryStage.show();
    primaryStage.setMaximized(true);
  }
    public void showStartPage() {
        StartPage startPage = new StartPage(this);
        Scene scene = new Scene(startPage.getRoot());
         primaryStage.setTitle("Bingo App");
        setFullScreen(scene);
        transitionScene(scene, startPage.getRoot());

    }

    public void showChoicePage() {
        ChoicePage choicePage = new ChoicePage(this);
        Scene scene = new Scene(choicePage.getRoot());
        setInitialWindowPosition(scene);
        transitionScene(scene, choicePage.getRoot());
        
      }

    public void showGame(boolean isVsAI) {
        ControlPanel controlPanel = new ControlPanel(isVsAI, this);
        Scene scene = new Scene(controlPanel.getRoot());
        setInitialWindowPosition(scene);
        transitionScene(scene, controlPanel.getRoot());

      }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}