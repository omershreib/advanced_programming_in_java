/**
 * <h3> CartAnimation </h3>
 *
 * <p>
 *      Thread based class that implement product images added or removed from cart
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

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class CartAnimation extends Thread {

    private static final int DEFAULT_FRAMES = 30;
    private static final int DEFAULT_DELAY = 15;

    private static final double ADD_TO_CART_START_X = 220;

    private static final double ADD_TO_CART_START_Y = 20;

    private static final double ADD_TO_CART_END_X = 140;

    private static final double ADD_TO_CART_END_Y = 162;

    private static final double REMOVE_FROM_CART_START_X = 140;

    private static final double REMOVE_FROM_CART_START_Y = 162;

    private static final double REMOVE_FROM_CART_END_X = 220;

    private static final double REMOVE_FROM_CART_END_Y = 20;

    private static final int ADD_TO_CART_SIZE_FREE_VARIABLE = 60;
    
    private static final int ADD_TO_CART_SIZE_BOUNDED_VARIABLE = 30;

    private static final int ADD_TO_CART_SIZE_FREE_VARIABLE = 60;

    private static final int ADD_TO_CART_SIZE_BOUNDED_VARIABLE = 30;

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

        // ? 220 : 140
//        double startX = addToCart ? canvas.getWidth() - 60 : canvas.getWidth() / 2;
//
//        // ? 20 : 162
//        double startY = addToCart ? 20 : canvas.getHeight() / 2;
//
//        // ? 140 : 220
//        double endX = addToCart ? canvas.getWidth() / 2 : canvas.getWidth() - 60;
//
//        // ? 162 : 20
//        double endY = addToCart ? canvas.getHeight() / 2 : 20;

        double startX = addToCart ? ADD_TO_CART_START_X : REMOVE_FROM_CART_START_X;
        double startY = addToCart ? ADD_TO_CART_START_Y : REMOVE_FROM_CART_START_Y;
        double endX = addToCart ? ADD_TO_CART_END_X : REMOVE_FROM_CART_END_X;
        double endY = addToCart ? ADD_TO_CART_END_Y : REMOVE_FROM_CART_END_Y;


        for (int i = 0; i <= DEFAULT_FRAMES; i++) {
            double t = (double) i / DEFAULT_FRAMES;

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
                Thread.sleep(DEFAULT_DELAY);
            } catch (InterruptedException e) {
                return;
            }
        }

        Platform.runLater(() -> gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()));
    }
}