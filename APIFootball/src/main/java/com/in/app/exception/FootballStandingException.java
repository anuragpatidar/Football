package com.in.app.exception;
public class FootballStandingException extends RuntimeException {
    public FootballStandingException(String exceptionMessage) {
        super(
            "Oops! Something went wrong, we are working on it please try again after sometime. Detailed Exception - " +
                exceptionMessage);
    }
}