import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        char[] inDataArray = {'Т','е','к','с','т', ',' ,' ','к','о','т','о','р','ы','й',' ','н','у','ж','н','о',
                ' ','з','а','ш','и','ф','р','о','в','а','т','ь'};

        String inputFile = "D:\\!JavaRushTest\\Input.txt";
        String outputFile = "D:\\!JavaRushTest\\Output.txt";
        int keyCaesar = 3;

        InOut.fileReadWrite(inputFile,outputFile,1,keyCaesar);



       // char[] test = EncoderDecoder.encryption(inDataArray,-3);
       // System.out.println(Arrays.toString(test));
       // test = EncoderDecoder.decryption(test,-3);
       // System.out.println(Arrays.toString(test));
        }
    }
