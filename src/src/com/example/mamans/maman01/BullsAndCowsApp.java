package com.example.mamans.maman01;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ListIterator;
import java.util.Optional;

public class BullsAndCowsApp extends Application {
    BullsAndCowsErrorManager gameErrorManager = new BullsAndCowsErrorManager();

    TextInputDialog textInputDialog = new TextInputDialog();
    Alert error = new Alert(Alert.AlertType.ERROR);

    Alert info = new Alert(Alert.AlertType.INFORMATION);

    Alert gameStartOrExitWindow = new Alert(Alert.AlertType.CONFIRMATION);

    private final String GAME_OVER_INFO_MASSAGE = "Bullseye! your manage to correctly guess the number";

    private final String GAME_TITLE = "BullsAndCows!";


    private void setGameStartOrExitWindow() {
        gameStartOrExitWindow.setTitle("new game or exit");
        gameStartOrExitWindow.setHeaderText("Press OK to restart the game, or CANCEL of exit");
    }

    public void newGame() {
        BullsAndCowsManager game = new BullsAndCowsManager();
        StringBuilder guessesHistory = new StringBuilder();
        game.initNewGame();

        while (!game.checkForGameOver()) {
            textInputDialog.setTitle("bulls and cows");
            textInputDialog.setHeaderText("Lets' Play Bulls and Cows!\nTry To Guess The 4-Digit Number:");

            Optional<String> playerInput = textInputDialog.showAndWait();



            playerInput.ifPresent(System.out::println);

            if (game.gamePlayer.parsePlayerInput(playerInput.orElse("cancel"))) {
                game.archive.addPlayerGuessToArchive(game.gamePlayer.getCurrPlayerNumberAsString());

                game.compareNumbers(game.gameBackend.getGameNumberAsString(),
                        game.gamePlayer.getCurrPlayerNumberAsString());

                String result = game.summarizeGuessResult();

                guessesHistory.append("\n").append(result);

//                for (ListIterator<String> guesses = game.archive.getGuessArchive(); guesses.hasNext(); ) {
//                    guessesHistory.append("\n").append(guesses.next());
//                }
                textInputDialog.setContentText(guessesHistory.toString());

            }

            else {
                this.error.setContentText(game.gamePlayer.getInputErrorMessage());
                this.error.showAndWait();
            }

        }

        info.setTitle("Game Over");
        info.setHeaderText(this.GAME_OVER_INFO_MASSAGE);
        info.setContentText("player guesses history:\n" + guessesHistory.toString());
        info.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        this.setGameStartOrExitWindow();
        //boolean isClose = false;

        while (true) {
            try {
                this.newGame();

                System.out.println("restart or exit");
                Optional<ButtonType> result = gameStartOrExitWindow.showAndWait();

//                if (result.isPresent() && result.get() == ButtonType.OK) {
//                    this.newGame();
//                }

                if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                    System.out.println("Close Program");
                    primaryStage.close();
                    //System.exit(0);
                }
            }

            catch (Exception e) {
                System.out.println(e.toString());
                primaryStage.close();
                System.exit(1);
            }

        }



    }
}
