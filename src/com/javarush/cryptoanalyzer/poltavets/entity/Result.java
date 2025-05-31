package com.javarush.cryptoanalyzer.poltavets.entity;

import com.javarush.cryptoanalyzer.poltavets.exception.ApplicationException;
import com.javarush.cryptoanalyzer.poltavets.repository.ResultCode;

public class Result {

    private ResultCode resultCode;
    private ApplicationException applicationException;

    public Result(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public Result(ResultCode resultCode, ApplicationException applicationException) {
        this.resultCode = resultCode;
        this.applicationException = applicationException;
    }

    public ResultCode getResultCode() {
        return resultCode;
    }

    public ApplicationException getApplicationException() {
        return applicationException;
    }
}
