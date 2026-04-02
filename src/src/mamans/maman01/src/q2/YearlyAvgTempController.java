package mamans.maman01.src.q2;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;

public class YearlyAvgTempController {

    private final int BAR_CHART_SCALE;
    //private final int BAR_CHART_UPPER_X_FIXER = 10;
    //private final int BAR_CHART_UPPER_Y_FIXER = 100;
    private final int BAR_CHART_GAP;
    private final int BAR_CHART_WIDTH;
    private final int BAR_CHART_HEIGHT;

    private final Color BAR_CHART_GENERAL_COLOR = Color.LIGHTGRAY;

    private final Color BAR_CHART_MAXIMUM_COLOR = Color.ORANGERED;

    private final Color BAR_CHART_MINIMUM_COLOR = Color.DODGERBLUE;

    private int year = 2021;

    private final YearlyAvgTempDataProvider dataProvider = new YearlyAvgTempDataProvider();

    private final YearlyAvgTempBackend backend = new YearlyAvgTempBackend();

    private GraphicsContext gc;


    @FXML
    private Canvas canvas;

    @FXML
    private Pane pane;

    @FXML
    private ChoiceBox<Integer> yearChoiceBox;


    @FXML
    private Text yearTitle;

    public YearlyAvgTempController() {
        this.BAR_CHART_SCALE = 15;
        this.BAR_CHART_GAP = 10;
        this.BAR_CHART_WIDTH = 40;
        this.BAR_CHART_HEIGHT = 100;
    }


    private void setSelectYear(int year) { yearChoiceBox.setValue(year); }

    public void updateYearTitle(String str) {
        if (yearTitle != null) {
            yearTitle.setText(str);
        }
    }


    public void setYearToDisplay(int year) {
        if (year > this.dataProvider.getMaxYear())
            this.year = this.dataProvider.getMinYear();
        else
            this.year = year;
    }

    public int getYearToDisplay() { return this.year; }

    private void clearCanvas() { gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); }

    @FXML
    private void onSelectYearChoose() {

    }

    @FXML
    private void OnBtnNextPress(ActionEvent event) {

        int currentYear;
        this.clearCanvas();
        Integer currentYearChoiceBox = this.yearChoiceBox.valueProperty().isNull().get() ? null : this.yearChoiceBox.getValue();


//        try {
//            currYearChoiceBox = this.yearChoiceBox.valueProperty().get();
//        }
//
//        catch (Exception ignored) {
//            currYearChoiceBox = null;
//        }


        if (currentYearChoiceBox != null && this.dataProvider.isYearInData(currentYearChoiceBox)) {
            currentYear = currentYearChoiceBox;
            this.yearChoiceBox.setValue(null);
        }

        else
            currentYear = this.getYearToDisplay();

        this.updateYearTitle("Year: " + Integer.toString(currentYear));
        //yearTitle.setText("Year: " + Integer.toString(currentYear));

        List<Double> tempsValues = this.dataProvider.getYearlyData(currentYear);

        int hottestIndex = this.backend.getArgOfHottestTemperature(tempsValues);
        int coldestIndex = this.backend.getArgOfColdestTemperature(tempsValues);

        // update next-year-to-display
        this.setYearToDisplay(currentYear + 1);

        // --- old variables
        //double scale = 15;          // pixels per unit
        // double barWidth = 30;
        //double gap = 15;
        //double barWidth = 40;
        //double gap = 20;
        double offset = 100;
        double x_offset = 35;   // good value
        //double offset = 10;

        //double baselineY = canvasHeight - 20;  // bottom of chart
        //double baselineY = canvasHeight + 100;
        double baselineY = gc.getCanvas().getHeight() - offset;

        for (int i = 0; i < 12; i++) {
            double barHeight = tempsValues.get(i) * BAR_CHART_SCALE;
            double x = x_offset + i * (BAR_CHART_WIDTH + BAR_CHART_GAP);

            if (i == hottestIndex)
                gc.setFill(BAR_CHART_MAXIMUM_COLOR);


            else if (i == coldestIndex)
                gc.setFill(BAR_CHART_MINIMUM_COLOR);

            else
                gc.setFill(BAR_CHART_GENERAL_COLOR);

            //gc.setFill(BAR_CHART_GENERAL_COLOR);
            gc.fillRect(x, baselineY - barHeight, this.BAR_CHART_WIDTH, barHeight);

            gc.setFill(Color.BLACK);
            gc.fillText("  " + Double.toString(tempsValues.get(i)), x,baselineY - barHeight - 5);

            String monthTextStyle;

            if (i < 10)
                monthTextStyle = "     " + Integer.toString(i+1);

            else
                monthTextStyle = "    " + Integer.toString(i+1);

            gc.setFill(Color.BLACK);
            gc.fillText(monthTextStyle, x,baselineY + 15);

        }

    }

    @FXML
    void initialize() {
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'yearly_avg_temp.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'yearly_avg_temp.fxml'.";
        assert yearChoiceBox != null : "fx:id=\"selectYear\" was not injected: check your FXML file 'yearly_avg_temp.fxml'.";
        assert yearTitle != null : "fx:id=\"yearTitle\" was not injected: check your FXML file 'yearly_avg_temp.fxml'.";

        gc = canvas.getGraphicsContext2D();
        gc.getCanvas().setHeight(600);
        gc.getCanvas().setWidth(800);

        this.updateYearTitle("");

        yearTitle.setX(this.canvas.getLayoutX()/2 + 100);

        yearChoiceBox.getItems().addAll(2021, 2022, 2023, 2024, 2025);
        yearChoiceBox.setLayoutX(600);

        this.dataProvider.init();

    }

}
