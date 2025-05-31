package com.javarush.cryptoanalyzer.poltavets.repository;

import com.javarush.cryptoanalyzer.poltavets.service.DecodeCaesar;
import com.javarush.cryptoanalyzer.poltavets.service.EncodeCaesar;
import com.javarush.cryptoanalyzer.poltavets.service.Function;
import com.javarush.cryptoanalyzer.poltavets.service.UnsupportedFunction;

public enum FunctionCode {
    ENCODE(new EncodeCaesar()), DECODE(new DecodeCaesar()), UNSUPPORTED_FUNCTION(new UnsupportedFunction());

    private Function function;

    FunctionCode(Function function) {
        this.function = function;
    }

    public Function getFunction() {
        return function;
    }
}
