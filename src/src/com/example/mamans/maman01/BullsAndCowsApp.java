package com.example.mamans.maman01;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.ListIterator;
import java.util.Optional;

public class BullsAndCowsApp extends Application {
    private final String errorInputMassage = "InputError: Player input must contain 4 digits";
    TextInputDialog textInputDialog = new TextInputDialog();
    Alert error = new Alert(Alert.AlertType.ERROR);

    public static void main(String[] args) {
        launch(args);
    }


    public TextInputDialog getTextInputDialog() {
        return textInputDialog;
    }

    public void setTextInputDialog(TextInputDialog textInputDialog) { this.textInputDialog = textInputDialog; }


    @Override
    public void start(Stage primaryStage) {
        BullsAndCowsManager game = new BullsAndCowsManager();
        game.initNewGame();

        while (!game.checkForGameOver()) {
            textInputDialog.setHeaderText("Bulls and Cows Game Launch");
            Optional<String> playerInput = textInputDialog.showAndWait();

            playerInput.ifPresent(System.out::println);

            if (game.gamePlayer.parsePlayerInput(playerInput.orElse("None"))) {
                game.archive.addPlayerGuessToArchive(game.gamePlayer.getCurrPlayerNumberAsString());

                game.compareNumbers(game.gameBackend.getGameNumberAsString(),
                        game.gamePlayer.getCurrPlayerNumberAsString());

                game.displayGuessResult();

                StringBuilder guessesHistory = new StringBuilder();

                for (ListIterator<String> guesses = game.archive.getGuessArchive(); guesses.hasNext(); ) {
                    guessesHistory.append("\n").append(guesses.next());
                }
                textInputDialog.setContentText(guessesHistory.toString());

            }

            else {
                this.error.setContentText(this.errorInputMassage);
                this.error.showAndWait();
            }

        }



    }
}
