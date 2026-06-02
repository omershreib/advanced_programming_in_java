package mamans.maman03.src.q2;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class AlertBox {

    private static final TextInputDialog textInputDialog = new TextInputDialog();
    private static final Alert info = new Alert(Alert.AlertType.INFORMATION);

    private static final Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);


//    public static void showCheckout(String message) {
//        info.setTitle("Checkout");
//        info.setHeaderText("Customer Cart Summary");
//        info.setContentText(message);
//        info.showAndWait();
//    }

    public static void showCheckout(TextArea textArea) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Checkout");
        alert.setHeaderText("Purchase Summary");

        alert.getDialogPane().setContent(textArea);

        alert.showAndWait();
    }

    public static boolean showBambaSale(String message) {
        confirm.setTitle("Sale Offer");
        confirm.setHeaderText("We have a Bamba Sale offer for you!");
        confirm.setContentText(message);
        Optional<ButtonType> result = confirm.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            return true;
        }

        if (result.isPresent() && result.get() == ButtonType.CANCEL) {
            return false;
        }

        return false;
    }
}
