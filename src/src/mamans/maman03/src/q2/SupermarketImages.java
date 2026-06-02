package mamans.maman03.src.q2;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class SupermarketImages {

    private static final Map<String, String> IMAGES = new HashMap<>();

    static {
        IMAGES.put("Bread", "images/bread.png");
        IMAGES.put("Eggs Pack", "images/eggs.png");
        IMAGES.put("Milk", "images/milk.png");
        IMAGES.put("Cheese", "images/cheese.png");
        IMAGES.put("Cereal", "images/cereal.png");
        IMAGES.put("Red Wine", "images/wine.png");
        IMAGES.put("Carrots", "images/carrots.png");
        IMAGES.put("Cucumber", "images/cucumber.png");
        IMAGES.put("Onions", "images/onion.png");
        IMAGES.put("Tomatoes", "images/tomatoes.png");
        IMAGES.put("Sausage", "images/sausage.png");
        IMAGES.put("Bamba", "images/bamba.png");
        IMAGES.put("Cart", "images/cart.png");
    }

    public static Image getImage(String productName) {

        String path = IMAGES.get(productName);

        if (path == null) {
            path = "images/cart.png";
        }

        return new Image(
                SupermarketImages.class
                        .getResource(path)
                        .toExternalForm()
        );
    }
}