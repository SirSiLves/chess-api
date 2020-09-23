package me.rsls.chessapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

public class ClickedField {

    private String[] sourceField;

    public ClickedField(){ }

    public ClickedField(String[] sourceField){
        this.sourceField = sourceField;
    }

    @JsonProperty("sourceField")
    private void setSourceField(@Validated String[] sourceField) {
        this.sourceField = sourceField;
    }

    public String[] getSourceField() {
        return sourceField;
    }
}
