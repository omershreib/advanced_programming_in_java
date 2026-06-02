package mamans.maman03.src.q2;


import mamans.maman03.src.q1.DupCount;

import java.util.HashMap;
import java.util.Map;

public class SupermarketSelfServiceBackend extends DupCount<String> {

    //private static final String productsPath = "resources/products";
    private static final String productsFilePath =  "resources/products/products.csv";

    //private final String productsFilePath = "D:\\Users\\omers\\IdeaProjects\\advanced_programming_in_java\\src\\src\\mamans\\maman03\\src\\q2\\resources\\products\\products.csv";


    private Map<String, String> productsDict = new HashMap<>();

    //private static final InfoBox infoBox = new InfoBox();


    public String getProductsFilePath() {
        return this.productsFilePath;
    }

    public SupermarketSelfServiceBackend() {
        super();

    }

    public void loadProductsFromFile(String filePath) {
        System.out.println("load supermarket products from list: " + this.getProductsFilePath());
        CSVReader reader = new CSVReader(filePath);
        reader.read();

        productsDict = reader.getData();
    }

    public Map<String, String> getProductDictData() {
        return productsDict;
    }

    public void addToCart(String product) {
        System.out.println("add product into cart");
        this.add(product);
    }

    public int removeFromCart(String product) {

        String[] tokens = product.split("\t");

        String productName = tokens[0];
        int _productCount = Integer.valueOf(tokens[1]);

        return this.remove(productName);
    }

    public boolean isCartEmpty() {
        return this.isEmpty();
    }

    public void callCheckoutInfoBox(HashMap<String, Integer> customerCart, boolean is_bamba_sale_offer_accepted) {
        StringBuilder checkoutMessage = new StringBuilder();

        checkoutMessage.append(String.format("%-15s %3s", "product", "count")).append("\n");

        double cost = 0.0;
        double productPrice;

        for (Map.Entry<String, Integer> entry : customerCart.entrySet()) {
            String productName = entry.getKey();
            Integer productCount = entry.getValue();
            checkoutMessage.append(String.format("%-15s %3d",
                    productName,
                    productCount)).append("\n");
            productPrice = (Double.parseDouble(productName.substring(productName.indexOf("(")+1, productName.indexOf(")"))));
            cost = cost + productPrice*productCount;
        }

        if (is_bamba_sale_offer_accepted) {
            cost = cost - 0.51;
            checkoutMessage.append("\ntotal cost: ").append(Math.round(cost * 100.0) / 100.0);
            checkoutMessage.append("\nyou saved 0.51 new shekels because of the bamba sale!");
        }

        else {
            checkoutMessage.append("\ntotal cost: ").append(Math.round(cost * 100.0) / 100.0);
        }

        AlertBox.showCheckout(checkoutMessage.toString());


    }

    public boolean offerBambaSale() {

        String message = "Buy 3 Bamba packages in 9.99!";
        return AlertBox.showBambaSale(message);
    }

}
