/**
 * <h3> SupermarketSelfServiceController </h3>
 *
 * <p>
 *      this is the controller class to run Supermarket-Self-Service JAVAFX application.
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
import java.util.Map;
import java.util.Objects;

public class SupermarketSelfServiceController extends SupermarketSelfServiceBackend {


    /* this is some extra features that I added to this application:
     1. Bamba Sale (try to add Bamba to the cart, offered only once)
     2. Cost displacement (because why not)

     hop that you will like it... */
    private boolean isBambaSaleAlreadyOffered = false;
    private boolean isBambaSaleAccepted = false;
    private static final String BAMBA_PRODUCT = "Bamba (3.50)";
    private static final int NUMBER_OF_BAMBA_UNITS_IN_SALE = 3;
    private static final double APPLY_BAMABA_SALE_ON_COST = 9.99;
    private static final String PRODUCT_PRICE_DISPLAY_LEFT_STRING = " (";
    private static final String PRODUCT_PRICE_DISPLAY_RIGHT_STRING = ")";

    private static final String LISTVIEW_STYLE = "-fx-font-family: 'Courier New';";
    private static final String APPLICATION_TITLE = "Supermarket Self Service Checkout";
    private static final String CART_MAGIC_NAME = "Cart";
    private static final String CUSTOMER_CART_LISTVIEW_STRING_FORMAT = "%-15s %3d";
    private static final String POPULAR_PRODUCT_TEXT_PREFIX = "Most Popular Product is ";
    private static final int CLEAR_RECT_X = 0;
    private static final int CLEAR_RECT_Y = 0;
    private static final int POPULAR_PRODUCT_FILL_TEXT_X = 50;
    private static final int POPULAR_PRODUCT_FILL_TEXT_Y = 150;



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


    /** handle addToCart button press */
    @FXML
    void onAddToCartButtonPress(ActionEvent event) {
        ObservableList<String> selected = this.productList.getSelectionModel().getSelectedItems();
        String selectedProduct = selected.get(0);

        Map<String, String> productsMap = parseSelectedProduct(selected.get(0));
        String productName = productsMap.get("productName");
        String productEntry = productsMap.get("productEntry");
        Double productPrice = Double.parseDouble(productsMap.get("productPrice"));

        /* private joke */
        if (this.offerBambaSale(productEntry)) { return; }

        this.addToCart(selectedProduct);
        this.refreshCustomerCartList();
        this.increaseCostTextField(productPrice);
        this.playCartAnimation(productName, true);
    }

    /** handle removeFromCart button press */
    @FXML
    void onRemoveFromCartButtonPress(ActionEvent event) {

        if (this.isCartEmpty()) {
            return;
        }

        ObservableList<String> selected = this.customerCartList.getSelectionModel().getSelectedItems();

        if (selected.isEmpty()) {
            System.out.println("product must be selected in order to be removed from cart");
            return;
        }

        Map<String, String> productsMap = parseSelectedProduct(selected.get(0));
        String productName = productsMap.get("productName");
        String productEntry = productsMap.get("productEntry");
        Double productPrice = Double.parseDouble(productsMap.get("productPrice"));

        this.removeFromCart(productEntry);
        this.decreaseCostTextField(productPrice);
        this.refreshCustomerCartList();
        this.checkBambaSaleRelevance();
        this.playCartAnimation(productName, false);
    }


    /** handle calling checkout alert box when checkout button is pressed */
    @FXML
    void onCheckoutButtonPress(ActionEvent event) {
        double cost = Double.parseDouble(this.costTextField.getText());
        this.callCheckoutInfoBox(this.getDcHashMap(), cost, this.isBambaSaleAccepted);
    }

    /** Display most popular product in current customer cart (or null if its empty) */
    @FXML
    void onGetMaxButtonPress(ActionEvent event) {

        gc.clearRect(CLEAR_RECT_X, CLEAR_RECT_Y, canvas.getWidth(), canvas.getHeight());
        gc.fillText(POPULAR_PRODUCT_TEXT_PREFIX + this.getMaxDup(),
                POPULAR_PRODUCT_FILL_TEXT_X,
                POPULAR_PRODUCT_FILL_TEXT_Y);
    }

    /** Offer bamba sale to customer
     * <p>
     *     activated only when this sale is not being already offered to the customer,
     *     and only when the customer added a single unit of Bamba into the cart.
     *     will offered only once.
     * </p>
     *
     * @param product some product name
     * @return true iff:
     * <ol>
     *     <li>product equals Bamba</li>
     *     <li>Bamba sale offer is not already been send to customer</li>
     *     <li>the customer accept the sale offer</li>
     * </ol>
     * */
    private boolean offerBambaSale(String product) {
        if (Objects.equals(product, BAMBA_PRODUCT) & !(this.isBambaSaleAlreadyOffered)) {
            this.isBambaSaleAlreadyOffered = true;
            if (this.alertBambaSale()) {
                this.applyBambaSale();
                this.playCartAnimation("Bamba", true);
                return true;
            }
        }
        return false;
    }

    /** Apply Bamba Sale to Customer Cart
     *
     * <p>3 Bamba units (only) in 9.99</p>
     * */
    private void applyBambaSale() {

        for (int i=0; i<NUMBER_OF_BAMBA_UNITS_IN_SALE; i++)
            this.addToCart(BAMBA_PRODUCT);

        this.isBambaSaleAccepted = true;
        this.refreshCustomerCartList();
        this.increaseCostTextField(APPLY_BAMABA_SALE_ON_COST);
    }

    /** Check Bamba sale relevance
     *
     * <p>cancel Bamba offer if remains less than 3 Bambas in customer cart</p>
     * */
    private void checkBambaSaleRelevance() {

        if (!this.isBambaSaleAccepted) {
            return;
        }

        if (countProductsInCart(BAMBA_PRODUCT) < NUMBER_OF_BAMBA_UNITS_IN_SALE) {
            this.isBambaSaleAccepted = false;
        }

    }

    /** Play cart product animation
     *
     * <p>implemented by a Thread class object</p>
     *
     * @param productName string name of product (there is an PNG image in that name)
     * @param addToCart depend on its boolean value, play adding or removal animation
     * */
    private void playCartAnimation(String productName, boolean addToCart) {
        new CartAnimation(
                this.canvas,
                SupermarketImages.getImage(productName),
                addToCart
        ).start();
    }

    /** Refresh customer cart list
     * <p>
     * clear customerCartList ListView and then re-fill it with the updated DupCount cart snapshot
     * (honestly, it is a bit ugly, and yet very simple and nice to understand, so I will keep it)
     * </p>
     * */
    private void refreshCustomerCartList() {
        this.customerCartList.getItems().clear();

        if (!this.isCartEmpty()) {
            this.getDcHashMap().forEach((productName,productCount) ->  {
                this.customerCartList.getItems().add(
                        String.format(CUSTOMER_CART_LISTVIEW_STRING_FORMAT, productName, productCount));
            });
        }
    }

    /** Product listView Setup
     *
     * <p>add all products with their prices into productList</p>
     *
     * */
    private void productsListSetup() {

        this.getProductDictData().forEach( (productName, productPrice) -> {
            this.productList.getItems().add(
                    productName + PRODUCT_PRICE_DISPLAY_LEFT_STRING + productPrice + PRODUCT_PRICE_DISPLAY_RIGHT_STRING
            );
        });
    }

    /** Initiate cost text field (set to zero)
     * <p>
     * not part of the requirements, but I told to myself that if we create a supermarket
     * self-service checkout application, it will be nice that we have product with prices
     * (unless this supermarket belongs to the communist party...)
     * </p>
     * */
    private void initCostTextField() {
        this.costTextField.setText("0");
    }

    /** Increase cost text field
     *
     * @param value a double represent the cost of product added to the cart
     * */
    private void increaseCostTextField(Double value) {

        double newValue = Double.parseDouble(costTextField.getText()) + value;
        costTextField.setText(String.valueOf(roundCost(newValue)));
    }

    /** Decrease cost text field
     *
     * @param value a double represent the cost of product removed from the cart
     * */
    private void decreaseCostTextField(Double value) {

        double newValue = Double.parseDouble(costTextField.getText()) - value;
        costTextField.setText(String.valueOf(roundCost(newValue)));

        /* enforce that the cost cannot be less than zero (by whatsoever reason) */
        if (Math.round(newValue) <= 0) {
            costTextField.setText("0");
        }

    }

    /** ListView style setup
     *
     * <p>override the default style format of these ListView to something nicer</p>
     * */
    private void listViewStyleSetup() {
        customerCartList.setStyle(LISTVIEW_STYLE);
        productList.setStyle(LISTVIEW_STYLE);

    }

    /** Apply product image display on select
     *
     * <p>this method enable showing this supermarket product images during selection</p>
     * */
    private void applyProductsImageDisplayOnSelect() {
        productList.getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> {

                    if (newValue == null) {
                        return;
                    }

                    String productName = newValue.substring(0, newValue.indexOf(PRODUCT_PRICE_DISPLAY_LEFT_STRING));
                    this.productImageView.setImage(SupermarketImages.getImage(productName));
                });
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

        gc = canvas.getGraphicsContext2D();

        /* set title */
        this.title.setText(APPLICATION_TITLE);

        /* load the PNG cart image */
        this.cartImageView.setImage(SupermarketImages.getImage(CART_MAGIC_NAME));

        this.initCostTextField();
        this.loadProductsFromFile(this.getProductsFilePath());
        this.productsListSetup();
        this.listViewStyleSetup();
        this.applyProductsImageDisplayOnSelect();

        System.out.println(canvas.getWidth() + " " +  canvas.getHeight());
    }
}
