package com.javarush.cryptoanalyzer.poltavets.constants;


public class ConcoleViewConstants {
    public static final String GREETINGS = "Здравствуй! Данное приложение может зашифровать и расшифровать \n" +
                                            "твой текстовый файл. Файл должен иметь расширение \".txt\". на данный \n" +
                                            "момент приложение работает с заглавными и строчными символами кириллицы. \n" +
                                            "Так же приложение поддерживает взлом зашифрованного файла при помощи \n" +
                                            "атаки полным перебором(Bruteforce) и методом статистического анализа, но \n" +
                                            "следует понимать, что расшифровка такими методами не гарантирована. Удачи!\n";

    public static final String ACTION_SELECTION = "Что делаем:\n" +
                                                    "1 - Шифрование файла;\n" +
                                                    "2 - Расшифровка файла;\n" +
                                                    "3 - Bruteforce;\n" +
                                                    "4 - Статистический анализ;\n" +
                                                    "5 - Криптография - не моё, я ухожу\n";

    public static final String NOT_AN_INTEGER = "\nВведено не число. Попробуем ещё раз.\n\n";

    public static final String NO_THIS_OPTION = "\nНет такого варианта. Введи число от 1 до 5.\n\n";

    public static final String KEY_SELECTION = "Введи ключ шифрования.Ключ не должен быть равен 0 и кратен %d.\n";

    public static final String KEY_FORM = "Ключ больше длины алфавита. Применена операция вычисления остатка по модулю.\n" +
                                            "Новый ключ  %d. При дальнейшей работе можно использовать любой из двух ключей.\n";

    public static final String KEY_IS_USELESS = "Ключ равен нулю или кратен %d. Шифрование с таким ключом бессмысленно.\n";

    //public static final String
}
