package me.rsls.chessapi.model.validation;

public class Validation {

    private String text;
    private boolean state;
    private final IValidate IValidate;


    public Validation(IValidate IValidate){
        this.text = "no matched rule";
        this.IValidate = IValidate;
    }

    public void executeValidation(){
        if(IValidate != null){
            this.IValidate.verifyMove();

            if(this.IValidate.isValid()){
                state = true;
            }
        }
    }

    public boolean isState(){
        return state;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
