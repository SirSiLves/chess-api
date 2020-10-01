package me.rsls.chessapi.model;

public class GameState {

    private boolean check, checkMate, isRemis, doubleCheck, promotion, castling;
    private Color checkColor;
    private Color winnerPlayer;
    private String remisReason;

    public GameState() {
        this.check = false;
        this.checkMate = false;
        this.isRemis = false;
        this.checkColor = null;
        this.winnerPlayer = null;
        this.doubleCheck = false;
        this.promotion = false;
        this.remisReason = null;
        this.castling = false;
    }

    public Color getCheckColor() {
        return checkColor;
    }

    public void setCheckColor(Color checkColor) {
        this.checkColor = checkColor;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isCheckMate() {
        return checkMate;
    }

    public void setCheckMate(boolean checkMate) {
        this.checkMate = checkMate;
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
        return doubleCheck;
    }

    public void setDoubleCheck(boolean doubleCheck) {
        this.doubleCheck = doubleCheck;
    }

    public boolean isPromoted() {
        return promotion;
    }

    public void setPromotion(boolean promotion) {
        this.promotion = promotion;
    }

    public String getRemisReason() {
        return remisReason;
    }

    public void setRemisReason(String remisReason) {
        this.remisReason = remisReason;
    }

    public boolean isCastling() {
        return castling;
    }

    public void setCastling(boolean castling) {
        this.castling = castling;
    }
}
