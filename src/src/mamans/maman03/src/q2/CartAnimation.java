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


    /* ATC is acronym of ADD_TO_CART ; RFC is acronym of REMOVE_FROM_CART */
    private static final double ATC_START_X = 220;

    private static final double ATC_START_Y = 20;

    private static final double ATC_END_X = 140;

    private static final double ATC_END_Y = 162;

    private static final double RFC_START_X = 140;

    private static final double RFC_START_Y = 162;

    private static final double RFC_END_X = 220;

    private static final double RFC_END_Y = 20;

    private static final int ATC_SIZE_FREE_PARAM = 60;

    private static final int ATC_SIZE_COEF = 30;

    private static final int RFC_SIZE_FREE_PARAM = 30;

    private static final int RFC_SIZE_COEF = 30;
    private static final int CLEAR_RECT_X = 0;
    private static final int CLEAR_RECT_Y = 0;
    private static final double GLOBAL_ALPHA_FREE_PARAM = 1.0;
    private static final double GLOBAL_ALPHA_COEF = 0.4;
    private static final double DEFAULT_GLOBAL_ALPHA = 1.0;

    private final Canvas canvas;
    private final Image image;
    private final boolean addToCart;

    /** CartAnimation constructor
     *
     * @param canvas javafx application canvas object
     * @param addToCart if true, play add-to-cart animation, otherwise play remove-fro-cart animation
     * @param image product Image object
     *
     * */
    public CartAnimation(Canvas canvas, Image image, boolean addToCart) {
        this.canvas = canvas;
        this.image = image;
        this.addToCart = addToCart;
        this.setDaemon(true);
    }

    @Override
    public void run() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        double startX = addToCart ? ATC_START_X : RFC_START_X;
        double startY = addToCart ? ATC_START_Y : RFC_START_Y;
        double endX = addToCart ? ATC_END_X : RFC_END_X;
        double endY = addToCart ? ATC_END_Y : RFC_END_Y;

        /* during products animation image moves in 3 axis (x, y, alpha)
        * where alpha controls transparently (obviously...)
        * each of these axis have its on linear function and their values chane over frames
        * the variable `t` is the bounded variable of all these functions.
        * */

        for (int i = 0; i <= DEFAULT_FRAMES; i++) {
            double t = (double) i / DEFAULT_FRAMES;

            double x = startX + (endX - startX) * t;
            double y = startY + (endY - startY) * t;

            double atcAnimationFunction = ATC_SIZE_FREE_PARAM - ATC_SIZE_COEF * t;
            double rtcAnimationFunction = RFC_SIZE_FREE_PARAM + RFC_SIZE_COEF * t;

            double size = addToCart ? atcAnimationFunction : rtcAnimationFunction;

            double alphaAnimationFunction = GLOBAL_ALPHA_FREE_PARAM - GLOBAL_ALPHA_COEF * t;

            /* JavaFX is not thread-safe. All GUI components (Canvas, ImageView, TextField, etc.) must be updated only
             by the JavaFX Application Thread. Since CartAnimation runs in its own worker thread,
             it cannot directly draw on the canvas. Platform.runLater() is used to schedule the drawing operation on
             the JavaFX Application Thread, ensuring that the GUI is updated safely and preventing runtime
             exceptions or unpredictable behavior.

             So I used it despite we did not learn it... */

            Platform.runLater(() -> {
                gc.clearRect(CLEAR_RECT_X, CLEAR_RECT_Y, canvas.getWidth(), canvas.getHeight());
                gc.setGlobalAlpha(alphaAnimationFunction);
                gc.drawImage(image, x, y, size, size);
                gc.setGlobalAlpha(DEFAULT_GLOBAL_ALPHA);
            });

            try {
                Thread.sleep(DEFAULT_DELAY);
            } catch (InterruptedException e) {
                return;
            }
        }

        Platform.runLater(() -> gc.clearRect(CLEAR_RECT_X, CLEAR_RECT_Y, canvas.getWidth(), canvas.getHeight()));
    }
}