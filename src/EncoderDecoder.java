public class EncoderDecoder {

    private static final char[] russianAlphabetArray =
            {'а', 'б', 'в', 'г', 'д', 'е', 'ё',
                    'ж', 'з', 'и', 'й', 'к', 'л', 'м',
                    'н', 'о', 'п', 'р', 'с', 'т', 'у',
                    'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ',
                    'ы', 'ь', 'э', 'ю', 'я'};
    public static char encryption(char inChar, int key) {
        return inChar;
    }


}


//    public static char[] encryption(char[] inDataArray, int key) {
//        char[] outDataArray = inDataArray;
//        for (int i = 0; i < inDataArray.length; i++) {                  //сравнение каждого элемента inDataArray с
//                                                                         // russianAlphabetArray, находим на какой позиции находится буква
//            for (int j = 0; j < russianAlphabetArray.length; j++) {
//                if (Character.toLowerCase(inDataArray[i]) == russianAlphabetArray[j]) {
//                    if (j + key < russianAlphabetArray.length && j + key>=0) {       //защита от выхода за границы массива russianAlphabetArray
//
//                        outDataArray[i] = russianAlphabetArray[j + key];          //шифрование символа
//                    } else if (j+key >= russianAlphabetArray.length) {
//                        outDataArray[i] = russianAlphabetArray[j + key - russianAlphabetArray.length];  //если есть выход за границы массива справа, то переходим на начало массива
//                    } else if (j+key<0) {
//                        outDataArray[i] = russianAlphabetArray[j + key + russianAlphabetArray.length];   //если есть выход за границы массива слева, то переходим на конец массива
//                    }
//                    break;                                                                                  //символ найден и зашифрован, дальше перебор не имеет смысла
//                }
//            }
//        }
//        return outDataArray;
//
//    }
//
//    public static char[] decryption(char[] inDataArray, int key) {
//        return encryption(inDataArray, key*(-1));
//    }