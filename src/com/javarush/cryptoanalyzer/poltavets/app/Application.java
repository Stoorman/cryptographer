package com.javarush.cryptoanalyzer.poltavets.app;

import com.javarush.cryptoanalyzer.poltavets.controller.MainController;
import com.javarush.cryptoanalyzer.poltavets.entity.Result;
import com.javarush.cryptoanalyzer.poltavets.repository.FunctionCode;
import com.javarush.cryptoanalyzer.poltavets.service.Function;

import static com.javarush.cryptoanalyzer.poltavets.constants.FunctionCodeConstants.*;

public class Application {

    private final MainController mainController;

    public Application(MainController mainController) {
        this.mainController = mainController;
    }

    public Result run() {
        String[] parameters = mainController.getView().getParameters();
        String mode = parameters[0];
        Function function = getFunction(mode);
        return function.execute(parameters);
    }

    private Function getFunction(String mode) {

        return switch (mode) {
            case "1" -> FunctionCode.valueOf(ENCODE).getFunction();
            case "2" -> FunctionCode.valueOf(DECODE).getFunction();
            default -> FunctionCode.valueOf(UNSUPPORTED_FUNCTION).getFunction();

        };
    }


    public void printResult(Result result) {
        mainController.getView().printResult(result);

    }
}
