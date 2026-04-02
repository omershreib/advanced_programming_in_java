package mamans.maman01.src.q1;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

public class BullsAndCowsApp extends Application {

    // player input interface
    private static final TextInputDialog textInputDialog = new TextInputDialog();


    // alerts interfaces
    private static final Alert error = new Alert(Alert.AlertType.ERROR);
    private static final Alert info = new Alert(Alert.AlertType.INFORMATION);
    private static final Alert confirmRestartOrExit = new Alert(Alert.AlertType.CONFIRMATION);


    // ongoing game constants variables
    private static final String GAME_TITLE = "BullsAndCows!";
    private static final String ONGOING_GAME_HEADER_TEXT = "Lets' Play Bulls and Cows!\nTry To Guess The 4-Digit Number:";


    // game over constants variables
    private static final String GAME_OVER_HEADER_TEXT = "Bullseye! your manage to correctly guess the number";
    private static final String GAME_OVER_PREFIX_CONTENT_TEXT = "player guesses history:\n";


    // game manager constant class object
    private static final BullsAndCowsManager gameManager = new BullsAndCowsManager();


    /** ongoing game info alert box content setup and display */
    private void gameContentSetup() {
        textInputDialog.setContentText("");
        textInputDialog.getEditor().clear();

        textInputDialog.setTitle(GAME_TITLE);
        textInputDialog.setHeaderText(ONGOING_GAME_HEADER_TEXT);
    }

    /** game over info alert box content setup and display */
    private void showGameOverInfoAlert() {
        info.setTitle("Game Over");
        info.setHeaderText(GAME_OVER_HEADER_TEXT);
        info.setContentText(GAME_OVER_PREFIX_CONTENT_TEXT + gameManager.getGuessesHistory());
        info.showAndWait();
    }

    /** Restart-or-Exit confirmation alert setup */
    private void setConfirmRestartOrExit() {
        confirmRestartOrExit.setTitle("new game or exit");
        confirmRestartOrExit.setHeaderText("Press OK to restart the game, or Cancel of exit");
    }

    /** main method for starting a new BullsAndCows game */
    public void newGame() {

        gameManager.initNewGame();

        this.gameContentSetup();

        while (!gameManager.checkForGameOver()) {
            Optional<String> playerInput = textInputDialog.showAndWait();

            // accept player guess input if valid (or exit upon cancel button press)
            if (gameManager.gameInputParser.parsePlayerInput(playerInput.orElse("cancel"))) {

                // compare between the current player's guess with the true game's number
                gameManager.compareNumbers(gameManager.gameBackend.getGameNumberAsString(),
                        gameManager.gameInputParser.getCurrPlayerNumberAsString());

                // update guesses-history and display it to the player
                gameManager.updateGuessesHistory(gameManager.summarizeGuessResult());
                textInputDialog.setContentText(gameManager.getGuessesHistory());
            }

            // handle input parsing error by alerting with a proper error message
            else {
                error.setContentText(gameManager.gameInputParser.getInputErrorMessage());
                error.showAndWait();
            }

        }

        this.showGameOverInfoAlert();
    }

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {

        boolean isClosed = false;

        while (!isClosed) {
            try {
                this.newGame();
                this.setConfirmRestartOrExit();

                // uncomment during debug:
                //System.out.println("display restart-or-exit alert box");
                Optional<ButtonType> result = confirmRestartOrExit.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    // uncomment during debug:
                    //System.out.println("restart game");
                }

                if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                    // uncomment during debug:
                    //System.out.println("close program");
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
