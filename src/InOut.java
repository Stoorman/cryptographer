import java.io.*;

public class InOut {

    public static void fileReadWrite(String fileInPath, String fileOutPath, int method, int key) {
        try (FileReader reader = new FileReader(fileInPath);
             FileWriter writer = new FileWriter(fileOutPath)) {
            char[] buffer = new char[2048];
            while (reader.ready()) {
                int real = reader.read(buffer);
                for (int i = 0; i < real; i++) {
                    buffer[i] = EncoderDecoder.encryption(buffer[i], key);
                }
                writer.write(buffer, 0, real);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

