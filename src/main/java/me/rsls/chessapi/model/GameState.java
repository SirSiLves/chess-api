package me.rsls.chessapi.model;

public class GameState {

    private boolean isCheck, isCheckMate, isRemis, isDoubleCheck, isPromotion, isCastling;
    private Color checkColor;
    private Color winnerPlayer;
    private String remisReason;

    public GameState() {
        this.isCheck = false;
        this.isCheckMate = false;
        this.isRemis = false;
        this.checkColor = null;
        this.winnerPlayer = null;
        this.isDoubleCheck = false;
        this.isPromotion = false;
        this.remisReason = null;
        this.isCastling = false;
    }

    public Color getCheckColor() {
        return checkColor;
    }

    public void setCheckColor(Color checkColor) {
        this.checkColor = checkColor;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        this.isCheck = check;
    }

    public boolean isCheckMate() {
        return isCheckMate;
    }

    public void setCheckMate(boolean checkMate) {
        this.isCheckMate = checkMate;
    }

    public boolean isRemis() {
        return isRemis;
    }

    public void setRemis(boolean remis) {
        isRemis = remis;
    }

    public Color getWinner() {
        return winnerPlayer;
    }

    public void setWinner(Color winnerColor) {
        this.winnerPlayer = winnerColor;
    }

    public boolean isDoubleCheck() {
        return isDoubleCheck;
    }

    public void setDoubleCheck(boolean doubleCheck) {
        this.isDoubleCheck = doubleCheck;
    }

    public boolean isPromoted() {
        return isPromotion;
    }

    public void setPromotion(boolean promotion) {
        this.isPromotion = promotion;
    }

    public String getRemisReason() {
        return remisReason;
    }

    public void setRemisReason(String remisReason) {
        this.remisReason = remisReason;
    }

    public boolean isCastling() {
        return isCastling;
    }

    public void setCastling(boolean castling) {
        this.isCastling = castling;
    }
}
