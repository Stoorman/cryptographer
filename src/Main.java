import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        String inputFile = "D:\\!JavaRushTest\\Input.txt";
        String outputFile = "D:\\!JavaRushTest\\Output.txt";
        String doubleConversion = "D:\\!JavaRushTest\\back and forth.txt";
        int keyCaesar = 1;
        InOut.fileReadWrite(inputFile,outputFile,1,keyCaesar);
        InOut.fileReadWrite(outputFile, doubleConversion, 2, keyCaesar);

        System.out.println(DecryptionByStaticAnalysis.frequencyAnalysis(outputFile));

        }
    }
