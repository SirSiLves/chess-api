package me.rsls.chessapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

public class SelectedFigure {

    private FigureType figureType;

    public SelectedFigure() {
    }

    //needed for bot service
    public SelectedFigure(FigureType figureType) {
        this.figureType = figureType;
    }

    @JsonProperty("figureType")
    private void setFigureType(@Validated FigureType figureType) {
        this.figureType = figureType;
    }

    public FigureType getFigureType() {
        return figureType;
    }
}
