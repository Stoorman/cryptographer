package com.javarush.cryptoanalyzer.poltavets.service;

import com.javarush.cryptoanalyzer.poltavets.entity.Result;

public class UnsupportedFunction implements Function {
    @Override
    public Result execute(String[] parameters) {
        return null;
    }
}
