/**
 * <h3> CSVReader </h3>
 *
 * <p>
 *      useful class to read CSV file
 *      (I know that there is a csv reader library in JAVA but was not sure if I am allowed to use it)
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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CSVReader {

    private static final int EXPECTED_CSV_LENGTH = 2;
    private final String filePath;
    private final Map<String, String> dataMap = new HashMap<>();

    public CSVReader(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return this.filePath;
    }


    /** Read CSV product file
     * <p>
     *     expect this type of CSV format (without a header line):
     *     <br> productName,productPrice
     * </p>
     * */
    public void read() {
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(String.valueOf(getClass().getResource(filePath).getPath())))) {

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length >= EXPECTED_CSV_LENGTH) {
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
}

