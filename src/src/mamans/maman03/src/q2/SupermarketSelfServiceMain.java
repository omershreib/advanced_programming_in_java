/**
 * <h3> SupermarketSelfServiceMain </h3>
 *
 * <p>
 *      this is the main class to run Supermarket-Self-Service JAVAFX application
 * </p>
 * <br>
 * <p> Note: HTML tags helps to improve comments readability in editors like IntelliJ that support it </p>
 *
 * @maman   03
 * @question    2
 * @author  Omer Shraibshtein (205984271)
 * @email   omershreib@gmail.com
 * @since   2026-06-02
 * */

package mamans.maman03.src.q2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class SupermarketSelfServiceMain extends Application {


    // application stage setup
    private static final int DEFAULT_STAGE_WIDTH = 800;
    private static final int DEFAULT_STAGE_HEIGHT = 550;
    private static final String STAGE_TITLE = "Supermarket Self Service Checkout";
    private static final boolean SET_STAGE_RESIZABLE = false;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("supermarket_self_service_checkout.fxml")));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.setWidth(DEFAULT_STAGE_WIDTH);
        primaryStage.setHeight(DEFAULT_STAGE_HEIGHT);
        primaryStage.setResizable(SET_STAGE_RESIZABLE);
        primaryStage.setTitle(STAGE_TITLE);
        primaryStage.show();
    }

}
