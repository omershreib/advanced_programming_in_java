package mamans.maman01.src.q1;

import javafx.application.Application;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * <h3> BullsAndCowsApp </h3>
 *
 * <p>
 *     this is the main class interface for running the Bulls-and-Cows game required to implement in maman01 (q1).
 *     handle the GUI displacement (implemented by alerts boxes) during this entire game's lifetime.
 * <p>
 *     uses the BullsAndCowsManager to interacts between the game logic (i.e., the backend class) and the
 *     player's input class handler (i.e., the parser class)
 * </p>
 *
 * @maman   01
 * @question    1
 * @author  Omer Shraibshtein (205984271)
 * @email   omershreib@gmail.com
 * @since   2026-04-02
 * */

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
    private static final String GAME_OVER_TITLE = GAME_TITLE + " (Game Over)";
    private static final String GAME_OVER_HEADER_TEXT = "Bullseye! your manage to correctly guess the number";
    private static final String GAME_OVER_PREFIX_CONTENT_TEXT = "player guesses history:\n";


    // restart or exit game constants variables
    private static final String GAME_RESTART_OR_EXIT_TITLE = GAME_TITLE + " (Restart or Exit Game)";
    private static final String GAME_RESTART_OR_EXIT_CONTENT_TEXT = "Press OK to restart the game, or Cancel of exit";


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
        info.setTitle(GAME_OVER_TITLE);
        info.setHeaderText(GAME_OVER_HEADER_TEXT);
        info.setContentText(GAME_OVER_PREFIX_CONTENT_TEXT + gameManager.getGuessesHistory());
        info.showAndWait();
    }

    /** Restart-or-Exit confirmation alert setup */
    private void setConfirmRestartOrExit() {
        confirmRestartOrExit.setTitle(GAME_RESTART_OR_EXIT_TITLE);
        confirmRestartOrExit.setHeaderText(GAME_RESTART_OR_EXIT_CONTENT_TEXT);
    }

    /** a runner method for starting a new BullsAndCows game */
    protected void runGame() {

        gameManager.initNewGame();

        boolean isValidInput;
        this.gameContentSetup();


        System.out.println("IsGameOver: " + Boolean.toString(gameManager.gameBackend.checkForGameOver()));
        System.out.println(gameManager.gameBackend.getCurrBulls());

        do {
            isValidInput = gameManager.playCurrGameTurn(textInputDialog.showAndWait().orElse("cancel"));

            System.out.println("isValidInput: " + Boolean.toString(isValidInput));
            System.out.println("IsGameOver: " + Boolean.toString(gameManager.gameBackend.checkForGameOver()));

            if (isValidInput)
                textInputDialog.setContentText(gameManager.getGuessesHistory());

            else {
                error.setContentText(gameManager.gameInputParser.getInputErrorMessage());
                error.showAndWait();
            }


        } while (!gameManager.gameBackend.checkForGameOver());

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
                this.runGame();
                this.setConfirmRestartOrExit();

                System.out.println("display restart-or-exit alert box");
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
