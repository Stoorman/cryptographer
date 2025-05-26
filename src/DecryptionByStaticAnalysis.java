import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class DecryptionByStaticAnalysis {


    public static HashMap<Character, Integer> frequencyAnalysis(String fileInPath) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        try (FileReader reader = new FileReader(fileInPath)) {
            char[] buffer = new char[2048];
            while (reader.ready()) {
                int real = reader.read(buffer);
                for (int i = 0; i < real; i++) {
                    boolean seach = frequencyMap.containsKey(buffer[i]);
                    if (seach == true) {
                        int frequency = frequencyMap.get(buffer[i]);
                        frequency++;
                        frequencyMap.put(buffer[i], frequency);
                    } else {
                        frequencyMap.put(buffer[i], 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return frequencyMap;
    }
}
