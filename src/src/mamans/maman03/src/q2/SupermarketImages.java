/**
 * <h3> SupermarketImages </h3>
 *
 * <p>
 *      useful class to handle image loading and calling
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

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Map;

public class SupermarketImages {

    private static final String imagePath = "resources/images";

    private static final Map<String, String> IMAGES = new HashMap<>();

    static {
        IMAGES.put("Bread", imagePath + "/" + "bread.png");
        IMAGES.put("Eggs Pack", imagePath + "/" + "eggs.png");
        IMAGES.put("Milk", imagePath + "/" + "milk.png");
        IMAGES.put("Cheese", imagePath + "/" + "cheese.png");
        IMAGES.put("Cereal", imagePath + "/" + "cereal.png");
        IMAGES.put("Red Wine", imagePath + "/" + "wine.png");
        IMAGES.put("Carrots", imagePath + "/" + "carrots.png");
        IMAGES.put("Cucumber", imagePath + "/" + "cucumber.png");
        IMAGES.put("Onions", imagePath + "/" + "onion.png");
        IMAGES.put("Tomatoes", imagePath + "/" + "tomatoes.png");
        IMAGES.put("Sausage", imagePath + "/" + "sausage.png");
        IMAGES.put("Bamba", imagePath + "/" + "bamba.png");
        IMAGES.put("Cart", imagePath + "/" + "cart.png");
    }

    public static Image getImage(String productName) {

        String path = IMAGES.get(productName);

        return new Image(
                SupermarketImages.class
                        .getResource(path)
                        .toExternalForm()
        );
    }
}