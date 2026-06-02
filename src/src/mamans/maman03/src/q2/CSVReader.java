package mamans.maman03.src.q2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {

    private final String filePath;
    private final Map<String, String> dataMap = new HashMap<>();

    public CSVReader(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }



    public void read() {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(getClass().getResource(filePath).getPath())))) {

            // Skip header if exists
            String header = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length >= 2) {
                    String key = values[0].trim();
                    String value = values[1].trim();
                    dataMap.put(key, value);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getData() {
        return dataMap;
    }

    public static void main(String[] args) {
        // System.out.println(new File(".").getAbsolutePath());

        String path = "D:\\Users\\omers\\IdeaProjects\\advanced_programming_in_java\\src\\src\\mamans\\maman03\\src\\q2\\products.csv";
        CSVReader reader = new CSVReader(path);
        //CSVReader reader = new CSVReader("products.csv");
        reader.read();

        // Example usage
        for (Map.Entry<String, String> entry : reader.getData().entrySet()) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }
}

