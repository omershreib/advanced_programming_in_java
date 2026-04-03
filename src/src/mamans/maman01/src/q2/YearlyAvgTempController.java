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
import java.util.Objects;
// import java.util.stream.Collectors;

public class YearlyAvgTempController {

    // bar chart default style setup
    private static final int BAR_CHART_SCALE = 15;
    private static final int BAR_CHART_GAP = 10;
    private static final int BAR_CHART_WIDTH = 40;
    private static final int BAR_CHART_BASELINE_Y_OFFSET = -100;
    private static final int BAR_CHART_BASELINE_X_OFFSET = 35;
    private static final Color BAR_CHART_GENERAL_COLOR = Color.LIGHTGRAY;
    private static final Color BAR_CHART_MAXIMUM_COLOR = Color.ORANGERED;
    private static final Color BAR_CHART_MINIMUM_COLOR = Color.DODGERBLUE;
    private static final Color BAR_CHART_TEXT_COLOR = Color.BLACK;


    // canvas default style setup
    private static final int CANVAS_WIDTH = 800;
    private static final int CANVAS_HEIGHT = 600;


    // choice box style setup
    private static final int CHOICE_BOX_X_LAYOUT = 600;
    private static final String CHOICE_BOX_DEFAULT_TEXT = "select a year";


    // year title default style setup
    private static final int YEAR_TITLE_X_OFFSET = 107;
    private static final String YEAR_TITLE_DEFAULT_TEXT = "";

    private static final String YEAR_TITLE_PREFIX = "Year: ";



    private static final YearlyAvgTempDataProvider dataProvider = new YearlyAvgTempDataProvider();
    private static final YearlyAvgTempBackend backend = new YearlyAvgTempBackend();

    /* if the next button pressed without a year selection on the
    choiceBox, then display data of the oldest year (2021) */
    private int year;

    private GraphicsContext gc;


    @FXML
    private Canvas canvas;

    @FXML
    private Pane pane;

    @FXML
    private ChoiceBox<String> yearChoiceBox;

    @FXML
    private Text yearTitle;


    //private void setSelectYear(int year) { yearChoiceBox.setValue(Integer.toString(year)); }

    public void setYearTitle(String str) { if (yearTitle != null)  yearTitle.setText(str); }


    public void setYearToDisplay(int year) {
        if (year > dataProvider.getMaxYear())
            this.year = dataProvider.getMinYear();
        else
            this.year = year;
    }

    public int getYearToDisplay() { return this.year; }

    private void clearCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0, 675, 600);

    }

    private void yearTitleSetup() {
        this.setYearTitle(YEAR_TITLE_DEFAULT_TEXT);
        yearTitle.setX(YEAR_TITLE_X_OFFSET);
    }

    private void canvasSetup() {
        gc = canvas.getGraphicsContext2D();
        gc.getCanvas().setHeight(CANVAS_HEIGHT);
        gc.getCanvas().setWidth(CANVAS_WIDTH);
    }

    private void boxChoiceSetup() {
        this.yearChoiceBox.getItems().add(CHOICE_BOX_DEFAULT_TEXT);
        this.yearChoiceBox.getSelectionModel().selectFirst();

        dataProvider.getAllYearsKeys().forEach(year -> this.yearChoiceBox.getItems().add(Integer.toString(year)));

        yearChoiceBox.setLayoutX(CHOICE_BOX_X_LAYOUT);
    }


    private void displayBarsChart(int year) {
        this.clearCanvas();

        this.setYearTitle(YEAR_TITLE_PREFIX + Integer.toString(year));

        List<Double> tempsValues = dataProvider.getYearlyData(year);

        int hottestIndex = backend.getArgOfHottestTemperature(tempsValues);
        int coldestIndex = backend.getArgOfColdestTemperature(tempsValues);

        // update next-year-to-display
        this.setYearToDisplay(year + 1);

        double offset = 100;
        double x_offset = 35;   // good value

        double baselineY = gc.getCanvas().getHeight() + BAR_CHART_BASELINE_Y_OFFSET;

        for (int i = 0; i < 12; i++) {
            double barHeight = tempsValues.get(i) * BAR_CHART_SCALE;
            double x = BAR_CHART_BASELINE_X_OFFSET + i * (BAR_CHART_WIDTH + BAR_CHART_GAP);


            /* set bar chart color following maximum/minimum/default case scenarios */
            if (i == hottestIndex)
                gc.setFill(BAR_CHART_MAXIMUM_COLOR);

            else if (i == coldestIndex)
                gc.setFill(BAR_CHART_MINIMUM_COLOR);

            else
                gc.setFill(BAR_CHART_GENERAL_COLOR);

            gc.fillRect(x, baselineY - barHeight, BAR_CHART_WIDTH, barHeight);
            gc.setFill(BAR_CHART_TEXT_COLOR);
            gc.fillText("  " + Double.toString(tempsValues.get(i)), x,baselineY - barHeight - 5);

            //String monthTextStyle;

            /* month text style needed to be right shifted a little if month has double digits */

            String monthTextStyle = i < 10 ? monthTextStyle = "     " + Integer.toString(i+1) : "    " + Integer.toString(i+1);

//            if (i < 10)
//                monthTextStyle = "     " + Integer.toString(i+1);
//
//            else
//                monthTextStyle = "    " + Integer.toString(i+1);

            //gc.setFill(Color.BLACK);
            gc.fillText(monthTextStyle, x,baselineY + 15);
        }
    }


    @FXML
    private void onSelectYearChoose() {
        int currentYear;

        String currentYearChoiceBox = this.yearChoiceBox.valueProperty().isNull().get() ? CHOICE_BOX_DEFAULT_TEXT : this.yearChoiceBox.getValue();

        if (!Objects.equals(currentYearChoiceBox, CHOICE_BOX_DEFAULT_TEXT) && dataProvider.isYearInData(Integer.parseInt(currentYearChoiceBox))) {
            currentYear = Integer.parseInt(currentYearChoiceBox);
            this.yearChoiceBox.setValue(CHOICE_BOX_DEFAULT_TEXT);
            this.displayBarsChart(currentYear);
        }

    }

    @FXML
    private void OnBtnNextPress(ActionEvent event) {

        int currentYear;

        String currentYearChoiceBox = this.yearChoiceBox.valueProperty().isNull().get() ? CHOICE_BOX_DEFAULT_TEXT : this.yearChoiceBox.getValue();

        if (!Objects.equals(currentYearChoiceBox, CHOICE_BOX_DEFAULT_TEXT) && dataProvider.isYearInData(Integer.parseInt(currentYearChoiceBox))) {
            currentYear = Integer.parseInt(currentYearChoiceBox);
            this.yearChoiceBox.setValue(CHOICE_BOX_DEFAULT_TEXT);
        }

        else
            currentYear = this.getYearToDisplay();

        this.displayBarsChart(currentYear);
    }

    @FXML
    void initialize() {
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'yearly_avg_temp.fxml'.";
        assert pane != null : "fx:id=\"pane\" was not injected: check your FXML file 'yearly_avg_temp.fxml'.";
        assert yearChoiceBox != null : "fx:id=\"selectYear\" was not injected: check your FXML file 'yearly_avg_temp.fxml'.";
        assert yearTitle != null : "fx:id=\"yearTitle\" was not injected: check your FXML file 'yearly_avg_temp.fxml'.";

        dataProvider.init();

        this.setYearToDisplay(dataProvider.getMinYear());

        this.canvasSetup();
        this.boxChoiceSetup();
        this.yearTitleSetup();
    }

}
