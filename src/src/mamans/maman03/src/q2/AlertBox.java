/**
 * <h3> AlertBox </h3>
 *
 * <p>
 *      this class provide all alertBoxes needed by our supermarket application
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

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;

import java.util.Optional;

public class AlertBox {

    private static final String CHECKOUT_TITLE = "Checkout";
    private static final String CHECKOUT_HEADER_TEXT = "Purchase Summary";
    private static final String BAMBA_SALE_TITLE = "Sale Offer";
    private static final String BAMBA_SALE_HEADER_TEXT = "We have a Bamba Sale offer for you!";
    private static final int CHECKOUT_TEXT_AREA_WIDTH = 420;
    private static final int CHECKOUT_TEXT_AREA_HEIGHT = 300;
    private static final int CHECKOUT_DIALOG_PANE_WIDTH = 740;
    private static final int CHECKOUT_DIALOG_PANE_HEIGHT = 380;




    /** Show Checkout Alert Box
     *
     * @param textArea textArea object box received from backend.BuildTextArea() (contains a nice checkout message)
     * */
    protected static void showCheckout(TextArea textArea) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(CHECKOUT_TITLE);
        alert.setHeaderText(CHECKOUT_HEADER_TEXT);

        textArea.setPrefWidth(CHECKOUT_TEXT_AREA_WIDTH);
        textArea.setPrefHeight(CHECKOUT_TEXT_AREA_HEIGHT);

        alert.getDialogPane().setPrefWidth(CHECKOUT_DIALOG_PANE_WIDTH);
        alert.getDialogPane().setPrefHeight(CHECKOUT_DIALOG_PANE_HEIGHT);
        alert.getDialogPane().setContent(textArea);

        alert.showAndWait();
    }

    /** Show Bamba Sale Alert Box
     *
     * @param message sale message
     * @return true if sale is accepted (user press ok), otherwise false
     *
     * */
    protected static boolean showBambaSale(String message) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle(BAMBA_SALE_TITLE);
        alert.setHeaderText(BAMBA_SALE_HEADER_TEXT);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            return true;
        }

        if (result.isPresent() && result.get() == ButtonType.CANCEL) {
            return false;
        }

        return false;
    }
}
