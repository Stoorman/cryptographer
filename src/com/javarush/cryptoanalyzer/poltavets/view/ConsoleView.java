package com.javarush.cryptoanalyzer.poltavets.view;

import com.javarush.cryptoanalyzer.poltavets.entity.Result;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static com.javarush.cryptoanalyzer.poltavets.constants.Alphabet.RUSSIAN_SMALL_LETTERS;
import static com.javarush.cryptoanalyzer.poltavets.constants.ApplicationComplietionConstants.EXCEPTION;
import static com.javarush.cryptoanalyzer.poltavets.constants.ApplicationComplietionConstants.SUCCESS;
import static com.javarush.cryptoanalyzer.poltavets.constants.ConsoleViewConstants.*;

public class ConsoleView implements View {

    @Override
    public String[] getParameters() {
        Scanner scanner = new Scanner(System.in);
        String[] parameters = new String[5];
        /* parameters[0] - mode 1 - Шифрование файла, 2 - Расшифровка файла, 3 - Bruteforce, 4 - Статистический анализ, 0 - выход из программы;
           parameters[1] - key
           parameters[2] - путь к входному файлу
           parameters[3] - путь к выходной папке
           parameters[4] - путь к примеру текста для статистического анализа
         */

        System.out.print(GREETINGS);

        parameters[0] = getMode(scanner);
        int mode = Integer.parseInt(parameters[0]);
        if (mode == 1 || mode == 2) {
            parameters[1] = getKey(scanner);
        }

        parameters[2] = getFilePathFirst(scanner, mode);

        parameters[3] = getFilePathSecond(scanner, mode);
        if (mode == 4) {
            parameters[4] = getFilePathFirst(scanner, 5);
        }
        // TODO сделать пути по умолчанию
        // TODO Написать комментарии

        return parameters;
    }


    @Override
    public void printResult(Result result) {
        switch (result.getResultCode()) {
            case OK -> System.out.println(SUCCESS);
            case ERROR -> System.out.println(EXCEPTION + result.getApplicationException().getMessage());
        }

    }


    private String getMode(Scanner scanner) {
        while (true) {
            System.out.print(ACTION_SELECTION);
            boolean hasNextInt = scanner.hasNextInt();

            if (hasNextInt) {
                int number = scanner.nextInt();
                scanner.nextLine();
                if (number > 0 && number < 5) {
                    return Integer.toString(number);
                } else if (number == 0) {
                    exitApp();
                } else {
                    System.out.print(NO_THIS_OPTION);
                }

            } else {
                System.out.print(NOT_AN_INTEGER);
                scanner.next();
            }
        }
    }


    private String getKey(Scanner scanner) {
        while (true) {
            System.out.printf(KEY_SELECTION, RUSSIAN_SMALL_LETTERS.length);
            boolean hasNextInt = scanner.hasNextInt();
            if (hasNextInt) {
                int alphabetLength = RUSSIAN_SMALL_LETTERS.length;
                int number = scanner.nextInt();
                scanner.nextLine();

                if (number == 0) {
                    exitApp();
                }

                if (number != 0 && number % alphabetLength != 0) {
                    if (number < alphabetLength && number > -alphabetLength) {
                        return Integer.toString(number);

                    } else {
                        number = number % alphabetLength;
                        System.out.printf(KEY_FORM, number);
                        return Integer.toString(number);
                    }
                } else {
                    System.out.printf(KEY_IS_USELESS, RUSSIAN_SMALL_LETTERS.length);
                }

            } else {
                System.out.print(NOT_AN_INTEGER);
                scanner.next();
            }
        }
    }


    private String getFilePathFirst(Scanner scanner, int mode) {

        while (true) {
            switch (mode) {
                case 1 -> System.out.println(REQUEST_PATH_OPEN_FILE);
                case 2, 3, 4 -> System.out.println(REQUEST_PATH_ENCRYPTED_FILE);
                case 5 -> System.out.println(REQUEST_PATH_EXAMPLE_FILE);
            }

            String path = scanner.nextLine();

            if (path.equalsIgnoreCase(EXIT_PATH)) {
                exitApp();
            }

            boolean validate = validatePathIn(path);

            if (validate == true) {
                return path;
            }
            System.out.println(UNKNOWN_PATH_OPEN_FILE);
        }
    }


    private String getFilePathSecond(Scanner scanner, int mode) {
        while (true) {
            switch (mode) {
                case 1 -> System.out.println(REQUEST_PATH_ENCRYPTED_FOLDER);
                case 2, 3, 4 -> System.out.println(REQUEST_PATH_DECRYPTED_FOLDER);
            }

            String path = scanner.nextLine();

            if (path.equalsIgnoreCase(EXIT_PATH)) {
                exitApp();
            }

            boolean validate = validatePathOut(path, scanner);

            if (validate == true) {
                return path;
            }
        }
    }


    private boolean validatePathIn(String path) {
        return Files.exists(Paths.get(path));
    }


    private boolean validatePathOut(String path, Scanner scanner) {
        try {
            Path p = Paths.get(path);
            Path f = p.getParent();

            if (Files.exists(p) && Files.isRegularFile(p)) {
                return true;
            }

            if (!Files.exists(p)) {
                while (true) {
                    System.out.println(UNKNOWN_PATH_FOLDER_AND_CREATE);

                    if (scanner.hasNextInt()) {
                        int number = scanner.nextInt();
                        scanner.nextLine();

                        if (number == 1) {
                            if (f != null && !Files.exists(f)) {
                                Files.createDirectories(f);
                            }
                            Files.createFile(p);
                            System.out.println(FOLDER_AND_FILE_CREATE);
                            return true;
                        } else if (number == 2) {
                            return false;
                        } else {
                            System.out.print(NO_THIS_OPTION_Y_N);
                        }
                    } else {
                        System.out.print(NOT_AN_INTEGER);
                        scanner.next();
                    }
                }
            }
            if (!Files.isRegularFile(p)) {
                while (true) {
                    System.out.println(UNKNOWN_PATH_FILE_AND_CREATE);

                    if (scanner.hasNextInt()) {
                        int number = scanner.nextInt();
                        scanner.nextLine();

                        if (number == 1) {
                            Files.createFile(p);
                            System.out.println(FILE_CREATE);
                            return true;
                        } else if (number == 2) {
                            return false;
                        } else {
                            System.out.print(NO_THIS_OPTION_Y_N);
                        }
                    } else {
                        System.out.print(NOT_AN_INTEGER);
                        scanner.next();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void exitApp() {
        System.out.println(EXIT_APP);
        System.exit(0);
    }

}
