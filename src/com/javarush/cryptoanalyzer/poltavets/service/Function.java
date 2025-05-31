package com.javarush.cryptoanalyzer.poltavets.service;

import com.javarush.cryptoanalyzer.poltavets.entity.Result;

public interface Function {
    Result execute(String[] parameters);
}
