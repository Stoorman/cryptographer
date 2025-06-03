import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DecryptionByStaticAnalysis {


    public static HashMap<Character, Integer> frequencyAnalysis(String fileInPath) {
        //создаём HashMap и заносим туда новые символы. Если символ уже есть, то увеличиваем значение на 1.
        //На выходе получаем карту с частотой символов в файле
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        try (FileReader reader = new FileReader(fileInPath)) {
            char[] buffer = new char[2048];
            while (reader.ready()) {
                int real = reader.read(buffer);
                for (int i = 0; i < real; i++) {
                    boolean search = frequencyMap.containsKey(buffer[i]);
                    if (search == true) {
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

    public static char maxFrequency (HashMap<Character, Integer> frequencyMap) {
        int maxFrequency = 0;
        char maxChar = '@';
        for(Character key: frequencyMap.keySet()) {
            maxChar = maxFrequency>frequencyMap.get(key)?maxChar:key;
            maxFrequency = Math.max(maxFrequency, frequencyMap.get(key));
        }
        return maxChar;
    }
}
