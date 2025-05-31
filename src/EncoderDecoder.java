public class EncoderDecoder {

    private static final char[] russianSmallLetters =
            {'а', 'б', 'в', 'г', 'д', 'е', 'ё',
                    'ж', 'з', 'и', 'й', 'к', 'л', 'м',
                    'н', 'о', 'п', 'р', 'с', 'т', 'у',
                    'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ',
                    'ы', 'ь', 'э', 'ю', 'я', '.', ',',
                    '«', '»', '"', '\'', ':', '!', '?', ' '};
    private static final char[] russianCapitalLetter =
            {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё',
                    'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М',
                    'Н', 'О', 'П', 'Р', 'С', 'Т', 'У',
                    'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ',
                    'Ы', 'Ь', 'Э', 'Ю', 'Я'};
    // добавить поддержку английского алфавита, а так же заглавных букв

    public static char encryption(char inChar, int key) {
        char outChar = inChar;
        int lengthArray = russianSmallLetters.length;
        for (int i = 0; i < lengthArray; i++) {
            if (inChar == russianSmallLetters[i]) {
                int newIndex = (i + key) % lengthArray;
                if (newIndex < 0) {
                    newIndex += lengthArray;
                }
                outChar = russianSmallLetters[newIndex];
                break;
            }
        }
        return outChar;
    }
    public static char decryption(char inChar, int key) {
        return encryption(inChar, -key);
    }
}