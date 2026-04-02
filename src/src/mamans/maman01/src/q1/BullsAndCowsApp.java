package mamans.maman01.src.q1;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class BullsAndCowsApp extends Application {
    private static final TextInputDialog textInputDialog = new TextInputDialog();

    private static final Alert error = new Alert(Alert.AlertType.ERROR);
    
    private static final Alert info = new Alert(Alert.AlertType.INFORMATION);

    private static final Alert confirmRestartOrExit = new Alert(Alert.AlertType.CONFIRMATION);

    private static final String GAME_OVER_INFO_MASSAGE = "Bullseye! your manage to correctly guess the number";

    private static final String GAME_TITLE = "BullsAndCows!";

    private BullsAndCowsManager gameManager = new BullsAndCowsManager();


    /** Restart-or-Exit confirmation alert setup */
    private void setConfirmRestartOrExit() {
        confirmRestartOrExit.setTitle("new game or exit");
        confirmRestartOrExit.setHeaderText("Press OK to restart the game, or Cancel of exit");
    }

    /** main method for starting a new BullsAndCows game */
    public void newGame() {
        //BullsAndCowsManager game = new BullsAndCowsManager();
        //StringBuilder guessesHistory = new StringBuilder();
        //game.initNewGame();
        gameManager.initNewGame();

        textInputDialog.setContentText("");
        textInputDialog.getEditor().clear();

        //gameManager.setGameOver(false);

        System.out.println("is game over: " + gameManager.checkForGameOver());
        while (!gameManager.checkForGameOver()) {
            textInputDialog.setTitle(GAME_TITLE);
            textInputDialog.setHeaderText("Lets' Play Bulls and Cows!\nTry To Guess The 4-Digit Number:");

            Optional<String> playerInput = textInputDialog.showAndWait();
            
            if (gameManager.gameInputParser.parsePlayerInput(playerInput.orElse("cancel"))) {

                gameManager.compareNumbers(gameManager.gameBackend.getGameNumberAsString(),
                        gameManager.gameInputParser.getCurrPlayerNumberAsString());

                //String result = gameManager.summarizeGuessResult();

                gameManager.updateGuessesHistory(gameManager.summarizeGuessResult());
                //guessesHistory.append("\n").append(result);
                //textInputDialog.setContentText(guessesHistory.toString());
                textInputDialog.setContentText(gameManager.getGuessesHistory());
            }

            else {
                error.setContentText(gameManager.gameInputParser.getInputErrorMessage());
                error.showAndWait();
            }

        }

        info.setTitle("Game Over");
        info.setHeaderText(GAME_OVER_INFO_MASSAGE);
        info.setContentText("player guesses history:\n" + gameManager.getGuessesHistory());
        info.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        this.setConfirmRestartOrExit();
        boolean isClosed = false;

        while (!isClosed) {
            try {
                this.newGame();

                System.out.println("restart or exit");
                Optional<ButtonType> result = confirmRestartOrExit.showAndWait();

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
                System.out.println(e.toString());
                primaryStage.close();
                System.exit(1);
            }

        }

    }
}
