package mamans.maman03.src.q2;


import mamans.maman03.src.q1.DupCount;

import java.util.HashMap;
import java.util.Map;

public class SupermarketSelfServiceBackend extends DupCount<String> {


    private final String productsFilePath = "D:\\Users\\omers\\IdeaProjects\\advanced_programming_in_java\\src\\src\\mamans\\maman03\\src\\q2\\products.csv";

    private Map<String, String> productsDict = new HashMap<>();


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

}
