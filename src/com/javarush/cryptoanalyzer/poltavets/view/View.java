package com.javarush.cryptoanalyzer.poltavets.view;

import com.javarush.cryptoanalyzer.poltavets.entity.Result;

public interface View {
    String[] getParameters();
    void printResult(Result result);
}
