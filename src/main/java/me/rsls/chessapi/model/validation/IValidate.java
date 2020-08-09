package me.rsls.chessapi.model.validation;

public interface IValidate {

    boolean isValid();
    void setValid(boolean valid);
    void verifyMove();

}
