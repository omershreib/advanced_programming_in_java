package mamans.maman03.src.q2;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class SupermarketSelfServiceController extends SupermarketSelfServiceBackend {


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

        String selectedItem = this.productList.getSelectionModel().getSelectedItem();
        String productName = selectedItem.substring(0, selectedItem.indexOf(" ("));

        this.playCartAnimation(productName, true);
    }


    @FXML
    void onRemoveFromCartButtonPress(ActionEvent event) {

        if (this.isCartEmpty()) {
            return;
        }

        // System.out.println(this.getProductDictData().isEmpty());

        System.out.println("remove product from cart");
        ObservableList<String> selected = this.customerCartList.getSelectionModel().getSelectedItems();

        if (selected.isEmpty()) {
            System.out.println("product must be selected in order to be removed from cart");
            return;
        }

        System.out.println(selected);
        selected.forEach(this::removeFromCart);

        this.updateCustomerCartList();

        String selectedItem = this.productList.getSelectionModel().getSelectedItem();
        String productName = selectedItem.substring(0, selectedItem.indexOf(" ("));

        this.playCartAnimation(productName, false);

    }


    @FXML
    void onCheckoutButtonPress(ActionEvent event) {

    }

    @FXML
    void onGetMaxButtonPress(ActionEvent event) {

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.fillText("Most Popular Item is " + this.getMaxDup(), 50, 150);
    }

    private void playCartAnimation(String productName, boolean addToCart) {
        new CartAnimation(
                this.canvas,
                SupermarketImages.getImage(productName),
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
//        File cartPNGFile = new File("D:\\Users\\omers\\IdeaProjects\\advanced_programming_in_java\\src\\src\\mamans\\maman03\\src\\q2\\images\\cart.png");
//        //File file = new File("D:\\Users\\omers\\IdeaProjects\\advanced_programming_in_java\\src\\src\\mamans\\maman03\\src\\q2\\cart.png");
//        Image image = new Image(cartPNGFile.toURI().toString());
//        this.imageView.setImage(image);
        this.imageView.setImage(SupermarketImages.getImage("Cart"));

        this.loadProductsFromFile(this.getProductsFilePath());
        this.productsListSetup();

        gc = canvas.getGraphicsContext2D();
        this.title.setText("Supermarket Self Service Checkout");
    }

}
