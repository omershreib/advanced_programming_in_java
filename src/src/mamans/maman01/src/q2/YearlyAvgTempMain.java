package mamans.maman01.src.q2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * <h3>YearlyAvgTempMain</h3>
 *
 * <p>
 *     this is the main starter class for running the javafx yearly-average-temperature application required to implement
 *     in maman 01 (q2).
 * </p>
 *
 * <p> the data presented in my bar charts depicts an <b>ACTUAL DATA</b> of the average temperature,
 * in Celsius degrees, that had been measured by the Israel's meteorological unit station located in the
 * coast of Tel-Aviv between 2021 and 2025 </p>
 * <br>
 * <p> attached <a href="https://ims.gov.il/en/data_gov">here</a> the source of my data </p>
 * <br>
 * <p> Note: HTML tags helps to improve comments readability in editors like IntelliJ that support it </p>
 *
 * @maman   01
 * @question    2
 * @author  Omer Shraibshtein (205984271)
 * @email   omershreib@gmail.com
 * @since   2026-04-* */

public class YearlyAvgTempMain extends Application {


    private final int DEFAULT_SCENE_WIDTH = 800;
    private final int DEFAULT_SCENE_HEIGHT = 600;

    private final int DEFAULT_STAGE_WIDTH = 850;
    private final int DEFAULT_STAGE_HEIGHT = 650;

    private final boolean SET_STAGE_RESIZABLE = false;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("yearly_avg_temp.fxml")));
        Scene scene = new Scene(root, DEFAULT_SCENE_WIDTH, DEFAULT_SCENE_HEIGHT);
        primaryStage.setScene(scene);

        primaryStage.setWidth(DEFAULT_STAGE_WIDTH);
        primaryStage.setHeight(DEFAULT_STAGE_HEIGHT);
        primaryStage.setResizable(SET_STAGE_RESIZABLE);
        primaryStage.setTitle("Yearly Average Temperature in The Cost of Tel-Aviv (2021 - 2025)");
        primaryStage.show();
    }
}
