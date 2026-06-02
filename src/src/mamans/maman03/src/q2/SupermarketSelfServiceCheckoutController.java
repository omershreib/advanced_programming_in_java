package mamans.maman03.src.q2;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.canvas.GraphicsContext;
import javafx.animation.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class SupermarketSelfServiceCheckoutController extends SupermarketSelfServiceCheckoutBackend {


    //private static final ADD_TO_CART_

    private GraphicsContext gc;

    @FXML
    private Pane animationPane;

    @FXML
    private Canvas canvas;

    @FXML
    private ListView<String> customerCartList;

    @FXML
    private ListView<String> productList;

    @FXML
    private Text title;

    @FXML
    private ImageView imageView;

    @FXML
    void onAddToCartButtonPress(ActionEvent event) {
        ObservableList<String> selected = this.productList.getSelectionModel().getSelectedItems();
        selected.forEach(this::addToCart);

        this.updateCustomerCartList();
        this.playCartAnimation(true);
    }


    @FXML
    void onRemoveFromCartButtonPress(ActionEvent event) {

        if (this.isCartEmpty()) {
            return;
        }

        System.out.println(this.getProductDictData().isEmpty());

        System.out.println("remove product from cart");
        ObservableList<String> selected = this.customerCartList.getSelectionModel().getSelectedItems();

        System.out.println(selected);
        selected.forEach(this::removeFromCart);

        this.updateCustomerCartList();
        this.playCartAnimation(false);

    }


    @FXML
    void onCheckoutButtonPress(ActionEvent event) {

    }

    @FXML
    void onGetMaxButtonPress(ActionEvent event) {

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.fillText("Most Popular Item is " + this.getMaxDup(), 50, 150);
    }

//    private void playCartAnimation(boolean addToCart) {
//        ImageView flyingImage = new ImageView(imageView.getImage());
//        flyingImage.setFitWidth(55);
//        flyingImage.setFitHeight(55);
//        flyingImage.setPreserveRatio(true);
//        flyingImage.setOpacity(0.9);
//
//        double productListX = addToCart ? animationPane.getWidth() - 40 : 40;
//        double cartX = imageView.getLayoutX() + imageView.getFitWidth() / 2 - 25;
//        double cartY = imageView.getLayoutY() + 20;
//
//        flyingImage.setLayoutX(addToCart ? productListX : cartX);
//        flyingImage.setLayoutY(addToCart ? 30 : cartY);
//
//        animationPane.getChildren().add(flyingImage);
//
//        TranslateTransition fly = new TranslateTransition(Duration.millis(550), flyingImage);
//        fly.setToX(addToCart ? cartX - productListX : productListX - cartX);
//        fly.setToY(addToCart ? cartY - 30 : 30 - cartY);
//
//        ScaleTransition scale = new ScaleTransition(Duration.millis(550), flyingImage);
//        scale.setFromX(1.0);
//        scale.setFromY(1.0);
//        scale.setToX(addToCart ? 0.35 : 1.4);
//        scale.setToY(addToCart ? 0.35 : 1.4);
//
//        FadeTransition fade = new FadeTransition(Duration.millis(550), flyingImage);
//        fade.setFromValue(0.95);
//        fade.setToValue(0.0);
//
//        ParallelTransition animation = new ParallelTransition(fly, scale, fade);
//        animation.setOnFinished(e -> animationPane.getChildren().remove(flyingImage));
//        animation.play();
//    }

    private void playCartAnimation(boolean addToCart) {
        new CartAnimation(
                this.canvas,
                this.imageView.getImage(),
                addToCart
        ).start();
    }


    private void updateCustomerCartList() {

        this.customerCartList.getItems().clear();

        if (!this.isCartEmpty()) {
            this.getDcHashMap().forEach((productName,productCount) ->  {
                this.customerCartList.getItems().add(productName + "\t" + productCount);
            });

        }


    }

    private void productsListSetup() {

        this.getProductDictData().forEach( (productName, productPrice) -> {
            this.productList.getItems().add(productName + " (" + productPrice + ")");
        });

    }

    @FXML
    void initialize() throws IOException {
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'supermarket_self_service_checkout.fxml'.";
        assert imageView != null : "fx:id=\"imageView\" was not injected: check your FXML file 'supermarket_self_service_checkout.fxml'.";
        assert customerCartList != null : "fx:id=\"customerCartList\" was not injected: check your FXML file 'supermarket_self_service_checkout.fxml'.";
        assert productList != null : "fx:id=\"productList\" was not injected: check your FXML file 'supermarket_self_service_checkout.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'supermarket_self_service_checkout.fxml'.";

        //System.out.println(new File(".").getAbsolutePath());
        File file = new File("D:\\Users\\omers\\IdeaProjects\\advanced_programming_in_java\\src\\src\\mamans\\maman03\\src\\q2\\cart.png");
        Image image = new Image(file.toURI().toString());
        this.imageView.setImage(image);

        this.loadProductsFromFile(this.getProductsFilePath());
        this.productsListSetup();

        gc = canvas.getGraphicsContext2D();
        this.title.setText("Supermarket Self Service Checkout");
    }

}
