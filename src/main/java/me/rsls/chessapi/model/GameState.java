package me.rsls.chessapi.model;

public class GameState {

    private boolean check, checkMate, isRemis, doubleCheck, pawnChange;
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
        this.pawnChange = false;
        this.remisReason = null;
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

    public boolean isPawnChange() {
        return pawnChange;
    }

    public void setPawnChange(boolean pawnChange) {
        this.pawnChange = pawnChange;
    }

    public String getRemisReason() {
        return remisReason;
    }

    public void setRemisReason(String remisReason) {
        this.remisReason = remisReason;
    }
}
