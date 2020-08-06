package me.rsls.chessapi.model;

public class Validation {

    private String text;
    private  boolean state;

    public Validation(){
        this.text = "no matched rule";
        this.state = false;
    }

    public Validation(String text, boolean state) {
        this.text = text;
        this.state = state;
    }

    public String getText() {
        return text;
    }

    public boolean isState() {
        return state;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
