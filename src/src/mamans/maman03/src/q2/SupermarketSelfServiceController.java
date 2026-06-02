package mamans.maman03.src.q2;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class SupermarketSelfServiceController extends SupermarketSelfServiceBackend {


    private boolean is_bamba_sale_offered = false;

    private boolean is_bamba_sale_offer_accepted = false;

    private GraphicsContext gc;

    @FXML
    private TextField costTextField;

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
    private ImageView cartImageView;

    @FXML
    private ImageView productImageView;

    @FXML
    void onAddToCartButtonPress(ActionEvent event) {
        ObservableList<String> selected = this.productList.getSelectionModel().getSelectedItems();

        String selectedProduct = selected.get(0);
        String productName = selectedProduct.substring(0, selectedProduct.indexOf(" ("));
        Double productPrice = Double.parseDouble(
                selectedProduct.substring(
                        selectedProduct.indexOf("(") + 1,
                        selectedProduct.indexOf(")")));

        System.out.println(selectedProduct);
        this.addToCart(selectedProduct);
        //selected.forEach(this::addToCart);

        this.updateCustomerCartList();
        this.increaseCostTextField(productPrice);


        /* private joke */
        if ((this.getDcHashMap().containsKey("Bamba (3.50)")) & !(this.is_bamba_sale_offered)) {
            this.is_bamba_sale_offered = true;
            if (this.offerBambaSale()) {

                this.addToCart("Bamba (3.50)");
                this.addToCart("Bamba (3.50)");
                this.is_bamba_sale_offer_accepted = true;

                this.updateCustomerCartList();
                this.increaseCostTextField(productPrice*2 - 0.51);
            }
        }

        this.playCartAnimation(productName, true);
    }


    @FXML
    void onRemoveFromCartButtonPress(ActionEvent event) {

        if (this.isCartEmpty()) {
            return;
        }

        System.out.println("remove product from cart");
        ObservableList<String> selected = this.customerCartList.getSelectionModel().getSelectedItems();

        if (selected.isEmpty()) {
            System.out.println("product must be selected in order to be removed from cart");
            return;
        }

        String selectedProduct = selected.get(0);
        String productName = selectedProduct.substring(0, selectedProduct.indexOf(" ("));
        Double productPrice = Double.parseDouble(
                selectedProduct.substring(
                        selectedProduct.indexOf("(") + 1,
                        selectedProduct.indexOf(")")));


        String productEntry = selectedProduct.substring(0, selectedProduct.indexOf(")")+1);

        System.out.println(productEntry);

        System.out.println(this.getDcHashMap().toString());
        this.removeFromCart(productEntry);
        this.decreaseCostTextField(productPrice);
        //selected.forEach(this::removeFromCart);

        this.updateCustomerCartList();

        this.checkBambaSaleRelevance();

        this.playCartAnimation(productName, false);

    }


    @FXML
    void onCheckoutButtonPress(ActionEvent event) {
        this.callCheckoutInfoBox(this.getDcHashMap(), this.is_bamba_sale_offer_accepted);
    }

    @FXML
    void onGetMaxButtonPress(ActionEvent event) {

        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.fillText("Most Popular Item is " + this.getMaxDup(), 50, 150);
    }

    private void checkBambaSaleRelevance() {

        if (!this.is_bamba_sale_offer_accepted) {
            return;
        }

        if (this.getDcHashMap().get("Bamba (3.50)") < 3) {
            this.is_bamba_sale_offer_accepted = false;
        }

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
                this.customerCartList.getItems().add(
                        String.format("%-15s %3d",
                                productName,
                                productCount));
            });

        }


    }

    private void productsListSetup() {

        this.getProductDictData().forEach( (productName, productPrice) -> {
            this.productList.getItems().add(productName + " (" + productPrice + ")");
        });

    }

    private void initCostTextField() {
        this.costTextField.setText("");
    }

    private void increaseCostTextField(Double value) {
        if (Objects.equals(costTextField.getText(), "")) {
            costTextField.setText("0");
        }

        double newValue = Double.parseDouble(costTextField.getText()) + value;

        costTextField.setText(String.valueOf(Math.round(newValue * 100.0) / 100.0));

    }

    private void decreaseCostTextField(Double value) {
        if (Objects.equals(costTextField.getText(), "")) {
            costTextField.setText("0");
            return;
        }

        double newValue = Double.parseDouble(costTextField.getText()) - value;

        costTextField.setText(String.valueOf(Math.round(newValue * 100.0) / 100.0));

        if (Math.round(newValue) == 0) {
            costTextField.setText("");
        }

    }

    @FXML
    void initialize() throws IOException {
        assert animationPane != null : "fx:id=\"animationPane\" was not injected: check your FXML file 'supermarket_self_service_checkout.fxml'.";
        assert canvas != null : "fx:id=\"canvas\" was not injected: check your FXML file 'supermarket_self_service_checkout.fxml'.";
        assert cartImageView != null : "fx:id=\"cartImageView\" was not injected: check your FXML file 'supermarket_self_service_checkout.fxml'.";
        assert customerCartList != null : "fx:id=\"customerCartList\" was not injected: check your FXML file 'supermarket_self_service_checkout.fxml'.";
        assert productImageView != null : "fx:id=\"productImageView\" was not injected: check your FXML file 'supermarket_self_service_checkout.fxml'.";
        assert productList != null : "fx:id=\"productList\" was not injected: check your FXML file 'supermarket_self_service_checkout.fxml'.";
        assert title != null : "fx:id=\"title\" was not injected: check your FXML file 'supermarket_self_service_checkout.fxml'.";
        assert costTextField != null : "fx:id=\"costTextField\" was not injected: check your FXML file 'supermarket_self_service_checkout.fxml'.";

        this.cartImageView.setImage(SupermarketImages.getImage("Cart"));

        this.initCostTextField();
        this.loadProductsFromFile(this.getProductsFilePath());
        this.productsListSetup();

        gc = canvas.getGraphicsContext2D();
        this.title.setText("Supermarket Self Service Checkout");

        customerCartList.setStyle(
                "-fx-font-family: 'Courier New';"
        );

        productList.setStyle(
                "-fx-font-family: 'Courier New';"
        );



        productList.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> {

                    if (newValue == null) {
                        return;
                    }

                    String productName =
                            newValue.substring(
                                    0,
                                    newValue.indexOf(" (")
                            );

                    this.productImageView.setImage(
                            SupermarketImages.getImage(productName)
                    );
                });
    }

}
