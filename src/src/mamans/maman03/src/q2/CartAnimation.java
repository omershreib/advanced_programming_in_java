package mamans.maman03.src.q2;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class CartAnimation extends Thread {

    private final Canvas canvas;
    private final Image image;
    private final boolean addToCart;

    public CartAnimation(Canvas canvas, Image image, boolean addToCart) {
        this.canvas = canvas;
        this.image = image;
        this.addToCart = addToCart;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        int frames = 30;
        long delay = 15;


        double startX = addToCart ? canvas.getWidth() - 60 : canvas.getWidth() / 2;
        double startY = addToCart ? 20 : canvas.getHeight() / 2;

        double endX = addToCart ? canvas.getWidth() / 2 : canvas.getWidth() - 60;
        double endY = addToCart ? canvas.getHeight() / 2 : 20;


        for (int i = 0; i <= frames; i++) {
            double t = (double) i / frames;

            double x = startX + (endX - startX) * t;
            double y = startY + (endY - startY) * t;

            double size = addToCart
                    ? 60 - 30 * t
                    : 30 + 30 * t;

            Platform.runLater(() -> {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                gc.setGlobalAlpha(1.0 - t * 0.4);
                gc.drawImage(image, x, y, size, size);
                gc.setGlobalAlpha(1.0);
            });

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                return;
            }
        }

        Platform.runLater(() -> gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()));
    }
}