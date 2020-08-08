package me.rsls.chessapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

public class ClickedFields {
    String[] sourceField, targetField;

    public ClickedFields(){ }

    public ClickedFields(String[] sourceField, String[] targetField){
        this.sourceField = sourceField;
        this.targetField = targetField;
    }

    @JsonProperty("sourceField")
    private void setSourceField(@Validated String[] sourceField) {
        this.sourceField = sourceField;
    }

    @JsonProperty("targetField")
    private void setTargetField(@Validated String[] targetField) {
        this.targetField = targetField;
    }

    public String[] getSourceField() {
        return sourceField;
    }

    public String[] getTargetField() {
        return targetField;
    }
}
