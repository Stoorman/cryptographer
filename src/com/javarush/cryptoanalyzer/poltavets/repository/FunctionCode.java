package com.javarush.cryptoanalyzer.poltavets.repository;

import com.javarush.cryptoanalyzer.poltavets.service.*;

public enum FunctionCode {
    ENCODE(new EncodeCaesar()), DECODE(new DecodeCaesar()),BRUTEFORCE(new BruteForce()), STATIC_ANALITIC(new StaticAnalitic()), UNSUPPORTED_FUNCTION(new UnsupportedFunction());

    private Function function;

    FunctionCode(Function function) {
        this.function = function;
    }

    public Function getFunction() {
        return function;
    }
}
