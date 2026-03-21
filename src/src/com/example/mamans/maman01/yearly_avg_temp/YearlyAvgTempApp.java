package com.example.mamans.maman01.yearly_avg_temp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class YearlyAvgTempApp extends Application {

    private final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    // for debug only
    public double[] values = {20,25,22,23,19,18,15,7,9,20,30,35};

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        double canvasWidth = 500;
        double canvasHeight = 400;

        Canvas canvas = new Canvas(canvasWidth, canvasHeight);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        drawBarChart(gc, canvasWidth, canvasHeight);

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, canvasWidth, canvasHeight);

        stage.setTitle("JavaFX 21 Canvas Bar Chart");
        stage.setScene(scene);
        stage.show();

    }

    private void drawBarChart(GraphicsContext gc, double width, double height) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, width, height);

        double padding = 50;
        double chartWidth = width - 2 * padding;
        double chartHeight = height - 2 * padding;

        // Find max value for scaling
        double maxValue = 0;
        for (double v : values) {
            if (v > maxValue) maxValue = v;
        }

        // Bar settings
        double barWidth = chartWidth / values.length * 0.6;
        double gap = chartWidth / values.length * 0.4;

        // Draw axes
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeLine(padding, height - padding, width - padding, height - padding); // X-axis
        gc.strokeLine(padding, padding, padding, height - padding); // Y-axis

        // Draw bars
        for (int i = 0; i < values.length; i++) {
            double barHeight = (values[i] / maxValue) * (chartHeight - 20);
            double x = padding + i * (barWidth + gap) + gap / 2;
            double y = height - padding - barHeight;

            gc.setFill(Color.GRAY);
            //gc.setFill(Color.hsb(i * 60, 0.7, 0.9));
            gc.fillRect(x, y, barWidth, barHeight);

            // Draw category labels
            gc.setFill(Color.BLACK);
            gc.fillText(months[i], x + barWidth / 4, height - padding + 15);

            // Draw value labels
            gc.fillText(String.valueOf(values[i]), x + barWidth / 4, y - 5);
        }
    }
}
