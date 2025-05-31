package com.javarush.cryptoanalyzer.poltavets;

import com.javarush.cryptoanalyzer.poltavets.app.Application;
import com.javarush.cryptoanalyzer.poltavets.controller.MainController;
import com.javarush.cryptoanalyzer.poltavets.entity.Result;
import com.javarush.cryptoanalyzer.poltavets.view.ConsoleView;
import com.javarush.cryptoanalyzer.poltavets.view.View;

public class EntryPoint {
    public static void main(String[] args) {
        View view = new ConsoleView();
        MainController mainController= new MainController(view);
        Application application = new Application(mainController);

        Result result = application.run();
        application.printResult(result);
    }
}
