import java.io.*;

public class InOut {

    public static void fileReadWrite(String fileInPath, String fileOutPath, int method, int key) {
        try (FileReader reader = new FileReader(fileInPath);
             FileWriter writer = new FileWriter(fileOutPath)) {
            char[] buffer = new char[2048];
            while (reader.ready()) {
                int real = reader.read(buffer);
                for (int i = 0; i < real; i++) {
                    switch (method) {
                        case 1:
                            buffer[i] = EncoderDecoder.encryption(buffer[i], key);
                            break;
                        case 2 :
                            buffer[i] = EncoderDecoder.decryption(buffer[i], key);
                            break;
                            // должен кидать исключение, что такого метода нет(если не 1 и 2), а ещё можно switch вынести в отдельный метод
                    }
                }
                writer.write(buffer, 0, real);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

