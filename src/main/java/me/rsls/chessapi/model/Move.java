package me.rsls.chessapi.model;

public class Move {

    private final Field sourceField, targetField;
    private final Figure movedFigure, killedFigure;

    public Move(Field sourceField, Field targetField, Figure movedFigure, Figure killedFigure) {
        this.sourceField = sourceField;
        this.targetField = targetField;
        this.movedFigure = movedFigure;
        this.killedFigure = killedFigure;
    }

    public Field getSourceField() {
        return sourceField;
    }

    public Field getTargetField() {
        return targetField;
    }

    public Figure getMovedFigure() {
        return movedFigure;
    }

    public Figure getKilledFigure() {
        return killedFigure;
    }
}
