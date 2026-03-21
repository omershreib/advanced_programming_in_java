package com.example.mamans.maman01;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class BullsAndCowsApp extends Application {
    private final TextInputDialog textInputDialog = new TextInputDialog();
    private final Alert error = new Alert(Alert.AlertType.ERROR);

    private final Alert info = new Alert(Alert.AlertType.INFORMATION);

    private final Alert gameStartOrExitWindow = new Alert(Alert.AlertType.CONFIRMATION);

    private static final String GAME_OVER_INFO_MASSAGE = "Bullseye! your manage to correctly guess the number";

    private static final String GAME_TITLE = "BullsAndCows!";


    private void setGameStartOrExitWindow() {
        gameStartOrExitWindow.setTitle("new game or exit");
        gameStartOrExitWindow.setHeaderText("Press OK to restart the game, or Cancel of exit");
    }

    public void newGame() {
        BullsAndCowsManager game = new BullsAndCowsManager();
        StringBuilder guessesHistory = new StringBuilder();
        game.initNewGame();

        textInputDialog.setContentText("");
        textInputDialog.getEditor().clear();

        while (!game.checkForGameOver()) {
            textInputDialog.setTitle(GAME_TITLE);
            textInputDialog.setHeaderText("Lets' Play Bulls and Cows!\nTry To Guess The 4-Digit Number:");

            Optional<String> playerInput = textInputDialog.showAndWait();
            
            if (game.gameInputParser.parsePlayerInput(playerInput.orElse("cancel"))) {
                game.archive.addPlayerGuessToArchive(game.gameInputParser.getCurrPlayerNumberAsString());

                game.compareNumbers(game.gameBackend.getGameNumberAsString(),
                        game.gameInputParser.getCurrPlayerNumberAsString());

                String result = game.summarizeGuessResult();

                guessesHistory.append("\n").append(result);
                textInputDialog.setContentText(guessesHistory.toString());
            }

            else {
                this.error.setContentText(game.gameInputParser.getInputErrorMessage());
                this.error.showAndWait();
            }

        }

        info.setTitle("Game Over");
        info.setHeaderText(GAME_OVER_INFO_MASSAGE);
        info.setContentText("player guesses history:\n" + guessesHistory);
        info.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        this.setGameStartOrExitWindow();
        boolean isClosed = false;

        while (!isClosed) {
            try {
                this.newGame();

                System.out.println("restart or exit");
                Optional<ButtonType> result = gameStartOrExitWindow.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    System.out.println("restart game");
                }

                if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                    System.out.println("close program");
                    primaryStage.close();
                    isClosed = true;
                }
            }

            catch (Exception e) {
                System.out.println(e);
                primaryStage.close();
                System.exit(1);
            }

        }

    }
}
