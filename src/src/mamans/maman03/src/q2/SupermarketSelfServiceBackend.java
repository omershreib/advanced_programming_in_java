/**
 * <h3> SupermarketSelfServiceBackend </h3>
 *
 * <p>
 *      this is the backend class to run Supermarket-Self-Service JAVAFX application.
 *      the backend handle this application logic and mechanics which includes:
 *      <ol>
 *          <li> interaction with DupCount (that represents the customer cart) </li>
 *          <li> products file loading </li>
 *          <li> alert box setup and calling (the cart checkout required it) </li>
 *          <li> product entry parsing </li>
 *      </ol>
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

import javafx.scene.control.TextArea;
import mamans.maman03.src.q1.DupCount;
import java.util.HashMap;
import java.util.Map;

public class SupermarketSelfServiceBackend extends DupCount<String> {

    private static final String productsFilePath =  "resources/products/products.csv";
    private static final double DEFAULT_ROUND = 100.0;
    private static final String TEXT_AREA_STYLE = "-fx-font-family: 'Consolas';" + "-fx-font-size: 12px;";
    private static final String BAMBA_SALE_OFFER_REMINDER_IN_CHECKOUT = "\"You saved ₪0.51 thanks to the Bamba sale!\"";

    private static final String BAMBA_SALE_OFFER_MASSAGE = "Only today, buy 3 Bamba units in 9.99! (offer will be presented only once)";
    
    
    /* productsDict contains that products data loaded from file
    (please do not confuse with the controller productList) */
    private Map<String, String> productsDict = new HashMap<>();


    protected String getProductsFilePath() {
        return productsFilePath;
    }

    /** SupermarketSelfServiceBackend constructor */
    protected SupermarketSelfServiceBackend() {
        super();
    }

    /** Load products file */
    protected void loadProductsFromFile(String filePath) {
        System.out.println("load supermarket products from list: " + this.getProductsFilePath());
        CSVReader reader = new CSVReader(filePath);
        reader.read();

        productsDict = reader.getData();
    }

    protected Map<String, String> getProductDictData() {
        return productsDict;
    }

    /** Add to Cart
     * <p>
     *  a wrapper method for DupCount.add()
     * <br> addToCart() sound more informative than just add()
     * <br> in addition, I wanted to add an access modifier layer to adding/removing items from cart
     * but did not want to do this on DupCount method that I prefer to be public
     * </p>
     * */
    protected void addToCart(String product) {
        System.out.println("add " + product + " into cart");
        this.add(product);
    }


    /** Remove from Cart
     * <p>
     * a wrapper method for DupCount.remove()
     * <br>
     * removeFromCart() sound more informative than just remove()
     * <br>
     * in addition, I wanted to add an access modifier layer to adding/removing items from cart
     * but did not want to do this on DupCount method that I prefer to be public
     * </p>
     *
     * @param product string name of product
     * @return an integer represent the current number of product remain in the cart after this removal
     * */
    protected int removeFromCart(String product) {
        System.out.println("remove " + product + " from cart");
        return this.remove(product);
    }

    protected int countProductsInCart(String product) {
        return this.getDcHashMap().get(product);
    }

    /** check if the cart is currently empty
     * <p>
     *  a wrapper method to DupCount.isEmpty()
     * </p>
     *
     * @return true if the cart is empty, otherwise false
     * */
    protected boolean isCartEmpty() {
        return this.isEmpty();
    }

    /** Parse selected product added or removed from cart
     * <p>
     * for example, the raw-string-product during adding:
     *  <br>"Cereal (14.90)" ← (*)
     *  <br> will be parsed and split into "Cereal" and "14.90"
     *  <br><br> following raw-string-product during removal:
     *  <br> "Cereal (14.90)  5"
     *  <br> need to also parse the productEntry (equals to (*)) recognized by DupCount from removal
     *
     *
     * @param selectedProduct a selected string product from customerCartList or productList
     * @return HashMap with 3 keys: productName, productPrice and productEntry (values are String)
     * </p>
     * */
    protected static Map<String, String> parseSelectedProduct(String selectedProduct) {

        Map<String, String> resultMap = new HashMap<>();

        /* no magic numbers! everything have meaning */
        int fistIndexOfProductName = 0;
        int lastIndexOfProductName = selectedProduct.indexOf(" (");

        int fistIndexOfProductPrice = selectedProduct.indexOf("(") + 1;
        int lastIndexOfProductPrice = selectedProduct.indexOf(")");

        int fistIndexOfProductEntry = 0;
        int lastIndexOfProductEntry = selectedProduct.indexOf(")") + 1;
        
        String productName = selectedProduct.substring(fistIndexOfProductName, lastIndexOfProductName);
        Double productPrice = Double.parseDouble(
                selectedProduct.substring(fistIndexOfProductPrice,lastIndexOfProductPrice)
        );
        
        String productEntry = selectedProduct.substring(fistIndexOfProductEntry, lastIndexOfProductEntry);

        resultMap.put("productName", productName);
        resultMap.put("productPrice", String.valueOf(productPrice));
        resultMap.put("productEntry", productEntry);

        return resultMap;
    }
    
    /** TextArea Builder
     * 
     * @param sb the checkout message stringBuilder
     * @return styled textArea
     * */
    private static TextArea BuildTextArea(StringBuilder sb) {
        TextArea textArea = new TextArea(sb.toString());
        textArea.setEditable(false);
        textArea.setWrapText(false);
        textArea.setStyle(TEXT_AREA_STYLE);
        
        return textArea;
    }

    /** Call checkout info box
     *
     * @param customerCart customer cart provided from DupCount.getDcHashMap()
     * @param bambaSaleStatus boolean status off bamba sale offer
     * */
    public void callCheckoutInfoBox(HashMap<String, Integer> customerCart, double cost, boolean bambaSaleStatus) {

        StringBuilder checkoutMessage = new StringBuilder();

        /* insert text header to checkout massage (hope this will not count as magic variables) */
        checkoutMessage.append(String.format("%-20s %5s%n", "Product", "Count"));
        checkoutMessage.append("------------------------------\n");
        

        customerCart.forEach((productName, productCount) -> {
            checkoutMessage.append(String.format("%-20s %5d%n", productName, productCount)).append("\n");
        });
        
        checkoutMessage.append("\n");
        checkoutMessage.append(String.format("Total Cost: %.2f%n", cost));

        if (bambaSaleStatus) {
            checkoutMessage.append(BAMBA_SALE_OFFER_REMINDER_IN_CHECKOUT);
        }
        
        AlertBox.showCheckout(BuildTextArea(checkoutMessage));
    }

    /** Alert Bamba sale
     *
     * @return true if sale is accepted (user press ok), otherwise false
     * */
    protected boolean alertBambaSale() {
        return AlertBox.showBambaSale(BAMBA_SALE_OFFER_MASSAGE);
    }
    
    /** Round Cost 
     * 
     * @param value the total cost of some product(s)
     * @return rounded value of this cost
     * */
    public static double roundCost(double value) {
        return Math.round(value * DEFAULT_ROUND) / DEFAULT_ROUND;
    }

}
