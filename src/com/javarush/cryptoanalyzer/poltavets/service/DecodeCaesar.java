package com.javarush.cryptoanalyzer.poltavets.service;

import com.javarush.cryptoanalyzer.poltavets.entity.Result;
import com.javarush.cryptoanalyzer.poltavets.exception.ApplicationException;

import static com.javarush.cryptoanalyzer.poltavets.repository.ResultCode.ERROR;
import static com.javarush.cryptoanalyzer.poltavets.repository.ResultCode.OK;

public class DecodeCaesar implements Function {
    @Override
    public Result execute(String[] parameters) {
        try {
        // TODO декодирование
        } catch (Exception e) {
            return new Result(ERROR, new ApplicationException("Расшифровка не удалась из-за ошибки", e));
        }
        return new Result(OK);
    }
}
