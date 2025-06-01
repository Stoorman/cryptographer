package com.javarush.cryptoanalyzer.poltavets.view;

import com.javarush.cryptoanalyzer.poltavets.entity.Result;

import java.util.Scanner;

import static com.javarush.cryptoanalyzer.poltavets.constants.Alphabet.RUSSIAN_SMALL_LETTERS;
import static com.javarush.cryptoanalyzer.poltavets.constants.ApplicationComplietionConstants.EXCEPTION;
import static com.javarush.cryptoanalyzer.poltavets.constants.ApplicationComplietionConstants.SUCCESS;
import static com.javarush.cryptoanalyzer.poltavets.constants.ConcoleViewConstants.*;

public class ConsoleView implements View{

    @Override
    public String[] getParameters() {
        Scanner scanner = new Scanner(System.in);
        String[] parameters = new String[10];
        System.out.print(GREETINGS);
        parameters[0] = getMode(scanner);
        if (Integer.parseInt(parameters[0]) == 1 || Integer.parseInt(parameters[0]) == 2)
        parameters[1] = getKey(scanner);
        // TODO сделана проверка режима работы и ключа, прописать запросы путей к файлам
        parameters[2] = "D:\\!JavaRushTest\\Output.txt";
        parameters[3] = "D:\\!JavaRushTest\\back and forth.txt";

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
        while(true){
            System.out.print(ACTION_SELECTION);
            boolean hasNextInt = scanner.hasNextInt();
            if (hasNextInt){
                int number = scanner.nextInt();
                if (number > 0 && number < 6) {
                    return Integer.toString(number);
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
        while(true){
            System.out.printf(KEY_SELECTION, RUSSIAN_SMALL_LETTERS.length);
            boolean hasNextInt = scanner.hasNextInt();
            if (hasNextInt){
                int number = scanner.nextInt();
                if(number != 0 && number%43!=0) {
                    if(number < 43 && number > -43) {
                        return Integer.toString(number);
                    } else {
                        number = number%43;
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




}
