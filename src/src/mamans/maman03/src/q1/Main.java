package mamans.maman03.src.q1;

import java.util.HashMap;

public class Main {
    public static void main (String[] args) {

        HashMap<String, Integer> hashMap = new HashMap<>();

        hashMap.put("a", 1);

        hashMap.put("a", hashMap.get("a")+1);

        System.out.println(hashMap.toString());


    }
}
