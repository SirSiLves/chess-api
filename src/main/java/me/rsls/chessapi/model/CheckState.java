package me.rsls.chessapi.model;

public class CheckState {

    private boolean check, checkMate;

    public CheckState() {
        this.check = false;
        this.checkMate = false;
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
}
