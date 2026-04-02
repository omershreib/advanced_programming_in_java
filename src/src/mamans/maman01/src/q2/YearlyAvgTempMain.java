package mamans.maman01.src.q2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

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
