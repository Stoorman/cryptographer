package com.javarush.cryptoanalyzer.poltavets.view;

import com.javarush.cryptoanalyzer.poltavets.entity.Result;

import java.util.Scanner;

import static com.javarush.cryptoanalyzer.poltavets.constants.ApplicationComplietionConstants.EXCEPTION;
import static com.javarush.cryptoanalyzer.poltavets.constants.ApplicationComplietionConstants.SUCCESS;

public class ConsoleView implements View{

    @Override
    public String[] getParameters() {
        String[] parameters = new String[10];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Режим работы 1-шифрование, 2-расшифровка, 3-брут форс, 4- статистический анализ");
       // parameters[0] = scanner.nextLine();
       // System.out.println();
        parameters[0] = "1";
        parameters[1] = "1";
        parameters[2] = "D:\\!JavaRushTest\\Input.txt";
        parameters[3] = "D:\\!JavaRushTest\\Output.txt";


        // TODO реализовать проверку входных значений
        return parameters;
    }

    @Override
    public void printResult(Result result) {
        switch (result.getResultCode()) {
            case OK -> System.out.println(SUCCESS);
            case ERROR -> System.out.println(EXCEPTION + result.getApplicationException().getMessage());
        }

    }
}
