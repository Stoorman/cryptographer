package com.javarush.cryptoanalyzer.poltavets.service;

import com.javarush.cryptoanalyzer.poltavets.entity.Result;
import com.javarush.cryptoanalyzer.poltavets.exception.ApplicationException;

import static com.javarush.cryptoanalyzer.poltavets.repository.ResultCode.ERROR;


public class DecodeCaesar implements Function {
    @Override
    public Result execute(String[] parameters) {
        String[] copyParameters = parameters;
        try {
            copyParameters[1] = Integer.toString(-Integer.parseInt(copyParameters[1]));
            return new EncodeCaesar().execute(copyParameters);
        } catch (RuntimeException e) {
            return new Result(ERROR, new ApplicationException("Расшифровка не удалась из-за ошибки", e));
        }
    }
}
