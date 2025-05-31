package com.javarush.cryptoanalyzer.poltavets.controller;

import com.javarush.cryptoanalyzer.poltavets.view.View;

public class MainController {

    private View view;

    public MainController(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }
}
