package me.rsls.chessapi.model;

public class GameState {

    private boolean check, checkMate, isRemis;
    private Color checkColor;
    private Color winnerPlayer;

    public GameState() {
        this.check = false;
        this.checkMate = false;
        this.isRemis = false;
        this.checkColor = null;
        this.winnerPlayer = null;
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
}
